package com.martinfilliau.roomsavailability.resources;

import com.martinfilliau.roomsavailability.configuration.ExchangeConfiguration;
import com.martinfilliau.roomsavailability.representations.BusyPeriods;
import java.net.URI;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import microsoft.exchange.webservices.data.AttendeeAvailability;
import microsoft.exchange.webservices.data.AttendeeInfo;
import microsoft.exchange.webservices.data.AvailabilityData;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.ExchangeService;
import microsoft.exchange.webservices.data.GetUserAvailabilityResults;
import microsoft.exchange.webservices.data.TimeWindow;
import microsoft.exchange.webservices.data.WebCredentials;

/**
 *
 * @author martinfilliau
 */
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomsAvailability {
 
    private final ExchangeConfiguration exchangeConfiguration;
    
    public RoomsAvailability(ExchangeConfiguration ec) {
        this.exchangeConfiguration = ec;
    }
    
    @GET
    public BusyPeriods busy(@QueryParam("email") String email) throws URISyntaxException, ParseException, Exception {
        ExchangeService service = new ExchangeService();
        ExchangeCredentials credentials = new WebCredentials(this.exchangeConfiguration.getUsername(),
                this.exchangeConfiguration.getPassword(), this.exchangeConfiguration.getDomain());
        service.setCredentials(credentials);
        service.setUrl(new URI(this.exchangeConfiguration.getUrl()));
        List<AttendeeInfo> attendees = new ArrayList<AttendeeInfo>();
        attendees.add(new AttendeeInfo(email));
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date start = sdf.parse("2013/10/18");
        Date end = sdf.parse("2013/10/25");
        GetUserAvailabilityResults results = service.getUserAvailability(attendees, new TimeWindow(start, end), AvailabilityData.FreeBusy);
        AttendeeAvailability aa = results.getAttendeesAvailability().getResponseAtIndex(0);
        return new BusyPeriods(aa.getCalendarEvents());
    }
    
}
