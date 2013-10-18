/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.martinfilliau.roomsavailability.configuration;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.hibernate.validator.constraints.NotEmpty;

/**
 *
 * @author martinfilliau
 */
public class ExchangeConfiguration {
    
    @JsonProperty
    @NotEmpty
    String username;
    
    @JsonProperty
    @NotEmpty
    String password;
    
    @JsonProperty
    @NotEmpty
    String domain;
    
    @JsonProperty
    @NotEmpty
    String url;

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
    
    public String getDomain() {
        return domain;
    }

    public String getUrl() {
        return url;
    }   
}
