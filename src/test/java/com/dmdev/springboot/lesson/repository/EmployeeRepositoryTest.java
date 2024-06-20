package com.dmdev.springboot.lesson.repository;

import com.dmdev.springboot.lesson.IntegrationTestBase;
import com.dmdev.springboot.lesson.dto.EmployeeFilter;
import com.dmdev.springboot.lesson.entity.EmployeeEntity;

import com.dmdev.springboot.lesson.projection.EmployeeNameView;
import com.dmdev.springboot.lesson.projection.EmployeeNativeView;
import com.dmdev.springboot.lesson.util.QPredicates;
import com.querydsl.core.types.dsl.BooleanExpression;
import org.hamcrest.collection.IsCollectionWithSize;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

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
        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("ivaN")
                .build();
        List<EmployeeEntity> customQuery = employeeRepository.findByFilter(filter);
        assertThat(customQuery, hasSize(0));
    }

    @Test
    void testQueryDslPredicates() {
        BooleanExpression predicate = QEmployeeEntity.employeeEntity.firstName.containsIgnoreCase("ivaN")
                .and(QEmployeeEntity.employeeEntity.salary.goe(1000));
        Page<EmployeeEntity> allValues = employeeRepository.findAll(predicate, Pageable.unpaged());
        assertThat(allValues.getContent(), hasSize(1));
    }

    @Test
    void testQPredicates() {
        EmployeeFilter filter = EmployeeFilter.builder()
                .firstName("ivaN")
                .salary(1000)
                .build();
        Predicate predicate = QPredicates.builder()
                .add(filter.getFirstName(), employeeEntity.firstName::containsIgnoreCase)
                .add(filter.getLastName(), employeeEntity.firstName::containsIgnoreCase)
                .add(filter.getSalary(), employeeEntity.saalry::goe)
                .buildAnd();
        Iterable<EmployeeEntity> result = employeeRepository.findAll(predicate);
        assertTrue(result.iterator().hasNext());
        System.out.println();
    }
}