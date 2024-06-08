package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.entity.CompanyEntity;
import com.dmdev.springboot.lesson.entity.EmployeeEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface EmployeeCustomRepository  {
    List<EmployeeEntity> findCustomQuery();
}
