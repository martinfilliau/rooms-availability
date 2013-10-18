package com.martinfilliau.roomsavailability.services;

import com.martinfilliau.roomsavailability.configuration.ExchangeConfiguration;
import com.martinfilliau.roomsavailability.representations.BusyPeriods;
import java.net.URI;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import microsoft.exchange.webservices.data.AttendeeAvailability;
import microsoft.exchange.webservices.data.AttendeeInfo;
import microsoft.exchange.webservices.data.AvailabilityData;
import microsoft.exchange.webservices.data.ExchangeCredentials;
import microsoft.exchange.webservices.data.GetUserAvailabilityResults;
import microsoft.exchange.webservices.data.TimeWindow;
import microsoft.exchange.webservices.data.WebCredentials;

/**
 *
 * @author martinfilliau
 */
public class ExchangeService {
    
    private final ExchangeConfiguration conf;
    
    public ExchangeService(ExchangeConfiguration conf) {
        this.conf = conf;
    }
    
    /**
     * Find busy periods for a given email address
     * @param email email address
     * @param start start date of the period to search
     * @param end end date of the period to search
     * @return BusyPeriods
     * @throws Exception 
     */
    public BusyPeriods findBusyPeriods(String email, Date start, Date end) throws Exception {
        microsoft.exchange.webservices.data.ExchangeService service = new microsoft.exchange.webservices.data.ExchangeService();
        ExchangeCredentials credentials = new WebCredentials(this.conf.getUsername(), this.conf.getPassword(), this.conf.getDomain());
        service.setCredentials(credentials);
        service.setUrl(new URI(this.conf.getUrl()));
        List<AttendeeInfo> attendees = new ArrayList<AttendeeInfo>();
        attendees.add(new AttendeeInfo(email));
        GetUserAvailabilityResults results = service.getUserAvailability(attendees, new TimeWindow(start, end), AvailabilityData.FreeBusy);
        AttendeeAvailability aa = results.getAttendeesAvailability().getResponseAtIndex(0);
        return new BusyPeriods(aa.getCalendarEvents());
    }
}
