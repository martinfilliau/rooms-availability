package com.martinfilliau.roomsavailability.representations;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import microsoft.exchange.webservices.data.CalendarEvent;

/**
 *
 * @author martinfilliau
 */
public class BusyPeriods {
    
    @JsonProperty
    List<BusyPeriod> periods;

    public List<BusyPeriod> getPeriods() {
        return periods;
    }

    public void setPeriods(List<BusyPeriod> periods) {
        this.periods = periods;
    }
    
    public BusyPeriods(Collection<CalendarEvent> events) {
        this.periods = new ArrayList<BusyPeriod>();
        for (CalendarEvent e : events) {
            this.periods.add(new BusyPeriod(e));
        }
    }
}
