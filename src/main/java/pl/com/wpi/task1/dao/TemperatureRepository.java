package pl.com.wpi.task1.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import pl.com.wpi.task1.model.Temperature;

import java.util.Optional;

public interface TemperatureRepository extends JpaRepository<Temperature, Integer> {

    Optional<Temperature> findByLocalization(int localization);

}