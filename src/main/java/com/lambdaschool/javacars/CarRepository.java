package com.lambdaschool.javacars;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface CarRepository extends JpaRepository<Car, Long> {
    List<Car> findAllByYear(int year);

    List<Car> findAllByBrandIgnoreCase(String brand);
}
