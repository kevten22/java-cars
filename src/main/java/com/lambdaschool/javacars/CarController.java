package com.lambdaschool.javacars;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
public class CarController {
    private final CarRepository carrepos;
    private final RabbitTemplate rt;

    public CarController(CarRepository carrepos, RabbitTemplate rt){
        this.carrepos = carrepos;
        this.rt = rt;
    }

    @GetMapping("/cars")
    public List<Car> all(){
        return carrepos.findAll();
    }

    @GetMapping("/cars/id/{id}")
    public Car findOne(@PathVariable Long id){
        return carrepos.findById(id)
                .orElseThrow(()-> new CarNotFoundException(id));
    }

    @PostMapping("/cars/upload")
    public List<Car> newCar(@RequestBody List<Car> newCars){

        CarLog message = new CarLog("Data Loaded");
        rt.convertAndSend(JavaCarsApplication.QUEUE_NAME, message.toString());
        log.info("Message Sent");

        return carrepos.saveAll(newCars);
    }

    @DeleteMapping("/cars/delete/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id)
    {
        carrepos.deleteById(id);

        CarLog message = new CarLog("{id} Data deleted");
        rt.convertAndSend(JavaCarsApplication.QUEUE_NAME, message.toString());
        log.info("Message Sent");

        return ResponseEntity.noContent().build();
    }
}
