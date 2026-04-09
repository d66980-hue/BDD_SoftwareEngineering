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
//@CucumberContextConfiguration
//@SpringBootTest(classes = SpringBddApplication.class)



//public class StepDefinition {}

public class ProductServiceSteps {

    private static final String BASE_URL = "http://localhost:8080";
    private Response response;
    private String requestBody;

    // ─── CATEGORY STEPS ─────────────────────────────────────────

    @When("the client calls /getCategory")
    public void getCategory() {
        response = given()
            .baseUri(BASE_URL)
            .when()
            .get("/getCategory");
    }

    @When("the client calls /getCategory/{id}")
    public void getCategoryById(String id) {
        response = given()
            .baseUri(BASE_URL)
            .when()
            .get("/getCategory/" + id);
    }

    @Then("the client receives status code of {int} for category")
    public void verifyCategoryStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("the client receives category with name")
    public void verifyCategoryHasName() {
        response.then().body("name", notNullValue());
    }

    @Given("a category with ID {int} exists")
    public void categoryExists(int id) {
        // Pre-condition check or test data setup
        Response check = given()
            .baseUri(BASE_URL)
            .when()
            .get("/getCategory/" + id);
        assertEquals(200, check.getStatusCode());
    }

    // ─── PRODUCT STEPS ──────────────────────────────────────────

    @When("the client calls /getProduct")
    public void getAllProducts() {
        response = given()
            .baseUri(BASE_URL)
            .when()
            .get("/getProduct");
    }

    @When("the client calls /getProduct/{id}")
    public void getProductById(String id) {
        response = given()
            .baseUri(BASE_URL)
            .when()
            .get("/getProduct/" + id);
    }

    @Then("the client receives status code of {int} for product")
    public void verifyProductStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @Then("the client receives status code of {int}")
    public void verifyStatusCode(int statusCode) {
        assertEquals(statusCode, response.getStatusCode());
    }

    @And("the client receives a list of products")
    public void verifyProductList() {
        response.then().body("$", not(empty()));
    }

    @And("the client receives product with name and price")
    public void verifyProductHasNameAndPrice() {
        response.then()
            .body("name", notNullValue())
            .body("price", notNullValue());
    }

    @Given("a product with ID {int} exists")
    public void productExists(int id) {
        Response check = given()
            .baseUri(BASE_URL)
            .when()
            .get("/getProduct/" + id);
        assertEquals(200, check.getStatusCode());
    }

    // CREATE PRODUCT STEPS

    @Given("the client has a valid product payload")
    public void validProductPayload() {
        requestBody = """
            {
                "name": "Test Product",
                "price": 9.99,
                "categoryId": 1
            }
            """;
    }

    @Given("the client has a product payload without a name")
    public void payloadWithoutName() {
        requestBody = """
            {
                "price": 9.99,
                "categoryId": 1
            }
            """;
    }

    @Given("the client has a product payload with price {double}")
    public void payloadWithPrice(double price) {
        requestBody = String.format("""
            {
                "name": "Test Product",
                "price": %s,
                "categoryId": 1
            }
            """, price);
    }

    @When("the client calls POST /createProduct")
    public void createProduct() {
        response = given()
            .baseUri(BASE_URL)
            .contentType("application/json")
            .body(requestBody)
            .when()
            .post("/createProduct");
    }

    @And("the client receives the created product with an ID")
    public void verifyCreatedProductHasId() {
        response.then().body("id", notNullValue());
    }

    // ─── UPDATE PRODUCT STEPS ───────────────────────────────────

    @When("the client calls PUT /updateProduct/{int} with valid data")
    public void updateProduct(int id) {
        requestBody = """
            {
                "name": "Updated Product",
                "price": 19.99,
                "categoryId": 1
            }
            """;
        response = given()
            .baseUri(BASE_URL)
            .contentType("application/json")
            .body(requestBody)
            .when()
            .put("/updateProduct/" + id);
    }

    @And("the client receives the updated product")
    public void verifyUpdatedProduct() {
        response.then().body("name", equalTo("Updated Product"));
    }

    // ─── DELETE PRODUCT STEPS ───────────────────────────────────

    @When("the client calls DELETE /deleteProduct/{int}")
    public void deleteProduct(int id) {
        response = given()
            .baseUri(BASE_URL)
            .when()
            .delete("/deleteProduct/" + id);
    }


}
