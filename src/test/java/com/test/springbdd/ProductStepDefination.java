
/*

package com.test.springbdd;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.hamcrest.Matchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

@AutoConfigureMockMvc
public class ProductStepDefination extends StepDefinition {

    @Autowired
    private MockMvc mvc;

    ResultActions action;
    String requestBody;

    // ─── GET PRODUCT ────────────────────────────────────────────

    @When("the client calls \\/getProduct")
    public void the_client_calls_getProduct() throws Exception {
        action = mvc.perform(get("/getProduct")
            .contentType(MediaType.APPLICATION_JSON));
    }

    @When("the client calls \\/getProduct\\/{id}")
    public void the_client_calls_getProduct_by_id(String id) throws Exception {
        action = mvc.perform(get("/getProduct/" + id)
            .contentType(MediaType.APPLICATION_JSON));
    }

    @Then("the client receives status code of {int}")
    public void the_client_receives_status_code_of(Integer status) throws Exception {
        action.andExpect(status().is(status));
    }

    @Then("the client receives product with name")
    public void the_client_receives_product_with_name() throws Exception {
        action.andExpect(jsonPath("name", Matchers.is("Test")));
    }

    @And("the client receives a list of products")
    public void the_client_receives_list_of_products() throws Exception {
        action.andExpect(jsonPath("$", Matchers.not(Matchers.empty())));
    }

    @And("the client receives product with name and price")
    public void the_client_receives_product_with_name_and_price() throws Exception {
        action.andExpect(jsonPath("$.name").exists())
              .andExpect(jsonPath("$.price").exists());
    }

    @Given("a product with ID {int} exists")
    public void a_product_with_id_exists(int id) throws Exception {
        ResultActions check = mvc.perform(get("/getProduct/" + id)
            .contentType(MediaType.APPLICATION_JSON));
        check.andExpect(status().isOk());
    }

    // ─── GET CATEGORY ───────────────────────────────────────────

    @When("the client calls \\/getCategory")
    public void the_client_calls_getCategory() throws Exception {
        action = mvc.perform(get("/getCategory")
            .contentType(MediaType.APPLICATION_JSON));
    }

    @When("the client calls \\/getCategory\\/{id}")
    public void the_client_calls_getCategory_by_id(String id) throws Exception {
        action = mvc.perform(get("/getCategory/" + id)
            .contentType(MediaType.APPLICATION_JSON));
    }

    @Then("the client receives status code of {int} for category")
    public void the_client_receives_status_code_for_category(Integer status) throws Exception {
        action.andExpect(status().is(status));
    }

    @And("the client receives category with name")
    public void the_client_receives_category_with_name() throws Exception {
        action.andExpect(jsonPath("$.name").exists());
    }

    @Given("a category with ID {int} exists")
    public void a_category_with_id_exists(int id) throws Exception {
        ResultActions check = mvc.perform(get("/getCategory/" + id)
            .contentType(MediaType.APPLICATION_JSON));
        check.andExpect(status().isOk());
    }

    // ─── GET LOCATION ───────────────────────────────────────────

    @When("the client calls \\/getLocation")                      // ← fixed: was mockMvc
    public void the_client_calls_get_location() throws Exception {
        action = mvc.perform(get("/getLocation")
            .contentType(MediaType.APPLICATION_JSON));
    }

    @Then("the client receives product with location")            // ← fixed: was response
    public void the_client_receives_product_with_location() throws Exception {
        action.andExpect(jsonPath("$.location").exists());
    }

    // ─── CREATE PRODUCT ─────────────────────────────────────────

    @Given("the client has a valid product payload")
    public void the_client_has_valid_product_payload() {
        requestBody = "{\"name\": \"Test Product\", \"price\": 9.99, \"categoryId\": 1}";
    }

    @Given("the client has a product payload without a name")
    public void the_client_has_payload_without_name() {
        requestBody = "{\"price\": 9.99, \"categoryId\": 1}";
    }

    @Given("the client has a product payload with price {double}")
    public void the_client_has_payload_with_price(double price) {
        requestBody = "{\"name\": \"Test Product\", \"price\": " + price + ", \"categoryId\": 1}";
    }

    @When("the client calls POST \\/createProduct")
    public void the_client_calls_post_createProduct() throws Exception {
        action = mvc.perform(post("/createProduct")
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody));
    }

    @And("the client receives the created product with an ID")
    public void the_client_receives_created_product_with_id() throws Exception {
        action.andExpect(jsonPath("$.id").exists());
    }

    // ─── UPDATE PRODUCT ─────────────────────────────────────────

    @When("the client calls PUT \\/updateProduct\\/{int} with valid data")
    public void the_client_calls_put_updateProduct(int id) throws Exception {
        requestBody = "{\"name\": \"Updated Product\", \"price\": 19.99, \"categoryId\": 1}";
        action = mvc.perform(put("/updateProduct/" + id)
            .contentType(MediaType.APPLICATION_JSON)
            .content(requestBody));
    }

    @And("the client receives the updated product")
    public void the_client_receives_updated_product() throws Exception {
        action.andExpect(jsonPath("$.name", Matchers.is("Updated Product")));
    }

    // ─── DELETE PRODUCT ─────────────────────────────────────────

    @When("the client calls DELETE \\/deleteProduct\\/{int}")
    public void the_client_calls_delete_deleteProduct(int id) throws Exception {
        action = mvc.perform(delete("/deleteProduct/" + id)
            .contentType(MediaType.APPLICATION_JSON));
    }
}

*/

