package pl.com.wpi.task1.api;

import java.util.Collection;

import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import pl.com.wpi.task1.model.Temperature;
import pl.com.wpi.task1.service.TemperatureService;


@RestController
public class Task1Rest {

    @Autowired
    private TemperatureService temperatureService;

    @PostMapping("/temperature/value")
    public ResponseEntity<Temperature> postValue(@RequestBody Temperature temperature) throws Exception {
        temperatureService.save(temperature);
        return ResponseEntity.ok(temperatureService.save(temperature));
    }

    @GetMapping("/temperature/values")
    public Collection<Temperature> getValues() {

        return temperatureService.getValues();
    }
}


