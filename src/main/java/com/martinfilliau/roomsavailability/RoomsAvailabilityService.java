package com.martinfilliau.roomsavailability;

import com.yammer.dropwizard.Service;
import com.martinfilliau.roomsavailability.configuration.AppConfiguration;
import com.martinfilliau.roomsavailability.resources.RoomsAvailability;
import com.yammer.dropwizard.config.Bootstrap;
import com.yammer.dropwizard.config.Environment;
import com.yammer.dropwizard.views.ViewBundle;
import java.text.SimpleDateFormat;
import org.eclipse.jetty.servlets.CrossOriginFilter;

/**
 *
 * @author martinfilliau
 */
public class RoomsAvailabilityService extends Service<AppConfiguration>{

    @Override
    public void initialize(Bootstrap<AppConfiguration> bootstrap) {
        bootstrap.getObjectMapperFactory().setDateFormat(new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssz"));
        bootstrap.setName("RoomsAvailabilityService");
        bootstrap.addBundle(new ViewBundle());
    }

    @Override
    public void run(AppConfiguration configuration, Environment environment) throws Exception {
        environment.addResource(new RoomsAvailability(configuration.getExchange()));
        environment.addFilter(CrossOriginFilter.class, "/*");
    }
    
    public static void main(String[] args) throws Exception {
        new RoomsAvailabilityService().run(args);
    }
    
}
