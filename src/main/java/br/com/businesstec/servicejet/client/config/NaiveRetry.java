package br.com.businesstec.servicejet.client.config;

import feign.RetryableException;
import feign.Retryer;
import org.springframework.stereotype.Component;

@Component
public class NaiveRetry implements Retryer {

    @Override
    public void continueOrPropagate(RetryableException e) {

    }

    @Override
    public Retryer clone() {
        return null;
    }
}
