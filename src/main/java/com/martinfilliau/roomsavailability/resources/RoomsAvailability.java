package com.martinfilliau.roomsavailability.resources;

import com.martinfilliau.roomsavailability.configuration.ExchangeConfiguration;
import com.martinfilliau.roomsavailability.representations.BusyPeriods;
import com.martinfilliau.roomsavailability.services.ExchangeService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ws.rs.Consumes;
import javax.ws.rs.DefaultValue;
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
@Path("/availability")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class RoomsAvailability {
 
    private final ExchangeService service;
    
    public RoomsAvailability(ExchangeConfiguration ec) {
        this.service = new ExchangeService(ec);
    }
    
    @GET
    public BusyPeriods busy(@QueryParam("email") String email,
                            @QueryParam("date") @DefaultValue("today") String date) {
        Date start;
        Date end;
        if("today".equals(date)) {
            start = getTodayWithHour(0, 0);
            end = getNextDay(start);
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");            
            try {
                Date day = sdf.parse(date);
                start = getDateWithHour(day, 0, 0);
                end = getNextDay(start);
            } catch (ParseException ex) {
                throw new WebApplicationException(ex, 400);
            }
        }
        try {
            return this.service.findBusyPeriods(email, start, end);
        } catch (Exception ex) {
            Logger.getLogger(RoomsAvailability.class.getName()).log(Level.SEVERE, null, ex);
            throw new WebApplicationException(ex, 500);
        }
    }
    
    private Date getDateWithHour(Date date, int hour, int minutes) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minutes);
        return cal.getTime();
    }
    
    private Date getTodayWithHour(int hour, int minutes) {
        Calendar cal = new GregorianCalendar();
        cal.set(Calendar.HOUR_OF_DAY, hour);
        cal.set(Calendar.MINUTE, minutes);
        return cal.getTime();
    }

    private Date getNextDay(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        int current = cal.get(Calendar.DAY_OF_YEAR);
        cal.set(Calendar.DAY_OF_YEAR, current + 1);
        return cal.getTime();
    }
}
