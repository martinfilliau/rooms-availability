package com.martinfilliau.roomsavailability.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import microsoft.exchange.webservices.data.CalendarEvent;

/**
 *
 * @author martinfilliau
 */
public class BusyPeriod {
    
    @JsonProperty
    private String from;
    
    @JsonProperty
    private String to;

    public BusyPeriod(CalendarEvent e) {
        this.setFrom(e.getStartTime().toString());
        this.setTo(e.getEndTime().toString());
    }
    
    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }
    
    
}
