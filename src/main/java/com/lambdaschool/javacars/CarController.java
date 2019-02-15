package com.lambdaschool.javacars;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@Slf4j
@RestController
public class CarController {
    private final CarRepository carrepos;
    private final RabbitTemplate rt;

    public CarController(CarRepository carrepos, RabbitTemplate rt){
        this.carrepos;
        this.rt = rt;
    }

    @GetMapping("/cars")
    public List<Car> all(){
        return carrepos.findAll();
    }

    @PostMapping("/cars/upload")
    public List<Car> newCar(@RequestBody List<Car> newCars){
        return carrepos.saveAll(newCars);
    }
}
