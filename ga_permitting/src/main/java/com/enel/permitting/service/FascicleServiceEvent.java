package com.enel.permitting.service;

import org.springframework.context.ApplicationEvent;

/**
 * This is an optional class used in publishing application events.
 * This can be used to inject events into the Spring Boot audit management endpoint.
 */
public class FascicleServiceEvent extends ApplicationEvent {

    public FascicleServiceEvent(Object source) {
        super(source);
    }

    public String toString() {
        return "My FascicleService Event";
    }
}