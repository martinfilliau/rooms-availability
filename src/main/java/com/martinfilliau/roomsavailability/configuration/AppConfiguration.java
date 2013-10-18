package com.martinfilliau.roomsavailability.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.yammer.dropwizard.config.Configuration;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 *
 * @author martinfilliau
 */
public class AppConfiguration extends Configuration {
    
    @JsonProperty
    @Valid
    @NotNull
    ExchangeConfiguration exchange;

    public ExchangeConfiguration getExchange() {
        return exchange;
    }
    
}
