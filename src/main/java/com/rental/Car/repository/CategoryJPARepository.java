package com.rental.Car.repository;

import com.rental.Car.model.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryJPARepository extends JpaRepository<Category, Long> {
}
