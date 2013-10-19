package com.martinfilliau.roomsavailability.views;

import com.martinfilliau.roomsavailability.representations.BusyPeriods;
import com.yammer.dropwizard.views.View;

/**
 *
 * @author martinfilliau
 */
public class BusyPeriodsView extends View {
        
    private final BusyPeriods bp;
    
    public BusyPeriodsView(BusyPeriods bp) {
        super("busyperiods.mustache");
        this.bp = bp;
    }
    
    public BusyPeriods getBusyPeriods() {
        return bp;
    }

}
