package com.martinfilliau.roomsavailability.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import microsoft.exchange.webservices.data.CalendarEvent;

/**
 *
 * @author martinfilliau
 */
public class BusyPeriod {
    
    @JsonProperty
    private Date from;
    
    @JsonProperty
    private Date to;

    public BusyPeriod(CalendarEvent e) {
        this.setFrom(e.getStartTime());
        this.setTo(e.getEndTime());
    }
    
    public Date getFrom() {
        return from;
    }

    public void setFrom(Date from) {
        this.from = from;
    }

    public Date getTo() {
        return to;
    }

    public void setTo(Date to) {
        this.to = to;
    }
    
}
