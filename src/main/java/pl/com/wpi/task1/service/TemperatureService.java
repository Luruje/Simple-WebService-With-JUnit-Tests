package pl.com.wpi.task1.service;

import java.util.Collection;

import pl.com.wpi.task1.model.Temperature;

public interface TemperatureService {
    Collection<Temperature> getValues();
    Temperature save(Temperature temperature);
}