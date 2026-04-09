package com.test.springbdd;

import io.cucumber.java.en.*;
import io.restassured.response.Response;
import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;
import static org.junit.Assert.*;

import org.junit.runner.RunWith;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import io.cucumber.spring.CucumberContextConfiguration;
import com.test.springbdd.StepDefinition;

@RunWith(SpringRunner.class)
@AutoConfigureMockMvc
@CucumberContextConfiguration
@SpringBootTest(classes = SpringBddApplication.class)



public class StepDefinition {}
