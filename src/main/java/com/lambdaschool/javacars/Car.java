package com.lambdaschool.javacars;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Data
@Entity
public class Car {
    private @Id @GeneratedValue Long id;
    private String language;
    private Long population;

    public Car(){

    }

    public Car(String language, Long population) {
        this.language = language;
        this.population = population;
    }
}
