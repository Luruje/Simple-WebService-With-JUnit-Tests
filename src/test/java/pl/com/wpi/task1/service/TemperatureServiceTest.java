package pl.com.wpi.task1.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import pl.com.wpi.task1.dao.TemperatureRepository;
import pl.com.wpi.task1.model.Temperature;

import java.lang.reflect.Array;
import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class TemperatureServiceTest {

    @Test
    @DisplayName("Case: findAll returns a list with the temperatures, method getValues returns it")
    void test_getValues_temperatureList_returnsListOfTemperatures() {
        Collection<Temperature> mockCollection =
                Arrays.asList(new Temperature(0, 10),
                new Temperature(1, 20));

        TemperatureRepository mockTemperatureRepository = mock(TemperatureRepository.class);
        when(mockTemperatureRepository.findAll())
                .thenReturn((List<Temperature>) mockCollection);

        TemperatureServiceImpl toTest = new TemperatureServiceImpl(mockTemperatureRepository);
        Collection<Temperature> result = toTest.getValues();

        assertEquals(result, mockCollection);
    }

    @Test
    @DisplayName("Case: findAll returns a empty list, method getValues returns it")
    void test_getValues_emptyList_returnsEmptyList() {
        Collection<Temperature> mockCollection = new ArrayList<>();

        TemperatureRepository mockTemperatureRepository = mock(TemperatureRepository.class);
        when(mockTemperatureRepository.findAll())
                .thenReturn((List<Temperature>) mockCollection);

        TemperatureServiceImpl toTest = new TemperatureServiceImpl(mockTemperatureRepository);
        Collection<Temperature> result = toTest.getValues();

        assertEquals(mockCollection, result);
    }

    @Test
    @DisplayName("Case: findByLocalization method returns Optional with Temperature object (old temperature), old temperature is higher than new one. Expected return: old temperature")
    void test_save_optionalWithTemperature_oldTemperatureHigher_returnsOldTemperature() {
        Collection<Temperature> mockCollection = new ArrayList<>();
        Temperature givenOldTemperature = new Temperature(0, 10);
        int givenLocalization = 0;

        TemperatureRepository mockTemperatureRepository = mock(TemperatureRepository.class);

        //findByLocalization returns mocked, pre-created temperature that is higher than the new one
        when(mockTemperatureRepository.findByLocalization(givenLocalization))
                .thenReturn(Optional.of(givenOldTemperature));

        //if after the IF statements the old temperature is given as an argument (as assumed to happen), then it should return it as well
        when(mockTemperatureRepository.save(givenOldTemperature))
                .thenReturn(givenOldTemperature);

        //system to test
        Temperature givenNewTemperature = new Temperature(0, 5);
        TemperatureServiceImpl toTest = new TemperatureServiceImpl(mockTemperatureRepository);

        Temperature result = toTest.save(givenNewTemperature);

        assertEquals(givenOldTemperature, result);
    }

    @Test
    @DisplayName("Case: findByLocalization method returns Optional with Temperature object (old temperature), old temperature is lower than new one. Expected return: new temperature")
    void test_save_optionalWithTemperature_oldTemperatureLower_returnsNewTemperature() {
        Collection<Temperature> mockCollection = new ArrayList<>();
        Temperature givenOldTemperature = new Temperature(0, 10);
        int givenLocalization = 0;

        TemperatureRepository mockTemperatureRepository = mock(TemperatureRepository.class);

        //findByLocalization returns mocked, pre-created temperature that is lower than the new one
        when(mockTemperatureRepository.findByLocalization(givenLocalization))
                .thenReturn(Optional.of(givenOldTemperature));

        Temperature givenNewTemperature = new Temperature(0, 20);
        //if after the IF statements the new temperature is given as an argument (as assumed to happen), then it should return it as well
        when(mockTemperatureRepository.save(givenNewTemperature))
                .thenReturn(givenNewTemperature);

        //system to test

        TemperatureServiceImpl toTest = new TemperatureServiceImpl(mockTemperatureRepository);

        Temperature result = toTest.save(givenNewTemperature);

        assertEquals(givenNewTemperature, result);
    }

    @Test
    @DisplayName("Case: findByLocalization method returns empty Optional, expected return: new temperature")
    void test_save_emptyOptional_returnsNewTemperature() {
        Collection<Temperature> mockCollection = new ArrayList<>();
        int givenLocalization = 0;

        TemperatureRepository mockTemperatureRepository = mock(TemperatureRepository.class);

        //findByLocalization returns empty optional
        when(mockTemperatureRepository.findByLocalization(givenLocalization))
                .thenReturn(Optional.empty());

        Temperature givenNewTemperature = new Temperature(0, 20);
        //if after the IF statements the new temperature is given as an argument (as assumed to happen), then it should return it as well
        when(mockTemperatureRepository.save(givenNewTemperature))
                .thenReturn(givenNewTemperature);

        //system to test

        TemperatureServiceImpl toTest = new TemperatureServiceImpl(mockTemperatureRepository);

        Temperature result = toTest.save(givenNewTemperature);

        assertEquals(givenNewTemperature, result);
    }

}