package com.martinfilliau.roomsavailability.resources;

import com.martinfilliau.roomsavailability.configuration.ExchangeConfiguration;
import com.martinfilliau.roomsavailability.representations.BusyPeriods;
import com.martinfilliau.roomsavailability.services.ExchangeService;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.WebApplicationException;
import javax.ws.rs.core.MediaType;

/**
 *
 * @author martinfilliau
 */
@Path("/rooms")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomsAvailability {
 
    private final ExchangeService service;
    
    public RoomsAvailability(ExchangeConfiguration ec) {
        this.service = new ExchangeService(ec);
    }
    
    @GET
    public BusyPeriods busy(@QueryParam("email") String email) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        Date start;
        Date end;
        try {
            start = sdf.parse("2013/10/18");
            end = sdf.parse("2013/10/25");
        } catch (ParseException ex) {
            throw new WebApplicationException(ex, 400);
        }
        try {
            return this.service.findBusyPeriods(email, start, end);
        } catch (Exception ex) {
            Logger.getLogger(RoomsAvailability.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex, 500);
        }
    }
    
}
