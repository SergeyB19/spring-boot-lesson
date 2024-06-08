package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.IntegrationTestBase;
import com.dmdev.springboot.lesson.entity.EmployeeEntity;

import com.dmdev.springboot.lesson.projection.EmployeeNameView;
import com.dmdev.springboot.lesson.projection.EmployeeNativeView;
import org.hamcrest.collection.IsCollectionWithSize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.util.List;
import java.util.Optional;

import static org.hamcrest.MatcherAssert.assertThat;

import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.junit.jupiter.api.Assertions.*;

class EmployeeRepositoryTest extends IntegrationTestBase {
    @Autowired
    private EmployeeRepository employeeRepository;
    private static final Integer IVAN_ID = 1;


    @Test
    void testFindById() {
        Optional<EmployeeEntity> employee = employeeRepository.findById(IVAN_ID);
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstName() {
        Optional<EmployeeEntity> employee = employeeRepository.findByFirstNameContainingIgnoreCase("va");
        assertTrue(employee.isPresent());
    }

    @Test
    void testFindByFirstNameAndSalary() {
        List<EmployeeEntity> employees = employeeRepository.findAllByFirstNameAndSalary("Ivan", 1000);
        assertThat(employees, hasSize(1));
    }

    @Test
    void testFindBySalary() {
        List<EmployeeNameView> employees = employeeRepository.findAllBySalaryGreaterThan(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindBySalaryNative() {
        List<EmployeeNativeView> employees = employeeRepository.findAllBySalaryGreaterThanGreaterThanNative(500);
        assertThat(employees, hasSize(2));
    }

    @Test
    void testFindCustomQuery() {
        List<EmployeeEntity> customQuery = employeeRepository.findCustomQuery();
        assertThat(customQuery, hasSize(0));
    }
}