package com.dmdev.springboot.lesson;

import jakarta.transaction.Transactional;

import org.springframework.boot.test.context.SpringBootTest;


import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest
@Transactional
public abstract class IntegrationTestBase {
}
