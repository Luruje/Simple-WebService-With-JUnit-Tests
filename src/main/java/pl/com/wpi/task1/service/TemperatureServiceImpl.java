package pl.com.wpi.task1.service;

import java.util.Collection;
import java.util.Optional;
import java.util.function.Consumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import pl.com.wpi.task1.dao.TemperatureRepository;
import pl.com.wpi.task1.model.Temperature;

@Service
public class TemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TemperatureRepository temperatureRepository;

    TemperatureServiceImpl(TemperatureRepository temperatureRepository){
        this.temperatureRepository = temperatureRepository;
    }

    @Override
    public Collection<Temperature> getValues() {
        return temperatureRepository.findAll();
    }

    @Override
    public Temperature save(Temperature temperature) {
        Optional<Temperature> foundTemperature = temperatureRepository.findByLocalization(temperature.getLocalization());

        if(foundTemperature.isPresent()) {
            if (foundTemperature.get().getValue() <= temperature.getValue())
                foundTemperature.get().setValue(temperature.getValue());
            return foundTemperature.get();
        }
        else
            return temperatureRepository.save(temperature);
    }


}