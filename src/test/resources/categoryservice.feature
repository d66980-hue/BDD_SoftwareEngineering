Feature: Verfy the product service

  Scenario: client makes call to GET category
    When the client calls /getCategory
    Then the client receives status code of 200 for category
    And the client receives category with name


#GPT Based Scenarios
  # ─── GET CATEGORY ───────────────────────────────────────────


  Scenario: client makes call to GET category by valid ID
    Given a category with ID 1 exists
    When the client calls /getCategory/1
    Then the client receives status code of 200 for category
    And the client receives category with name

  Scenario: client makes call to GET category by invalid ID
    When the client calls /getCategory/9999
    Then the client receives status code of 404 for category

  Scenario: client makes call to GET category by non-numeric ID
    When the client calls /getCategory/abc
    Then the client receives status code of 400 for category

  Scenario: client makes call to GET category by special character ID
    When the client calls /getCategory/@
    Then the client receives status code of 404 for category

  # ─── GET PRODUCT ────────────────────────────────────────────

  Scenario: client makes call to GET all products
    When the client calls /getProduct
    Then the client receives status code of 200 for product
    And the client receives a list of products

  Scenario: client makes call to GET product by valid ID
    Given a product with ID 1 exists
    When the client calls /getProduct/1
    Then the client receives status code of 200 for product
    And the client receives product with name and price

  Scenario: client makes call to GET product by invalid ID
    When the client calls /getProduct/9999
    Then the client receives status code of 404 for product

  # ─── CREATE PRODUCT ─────────────────────────────────────────

  Scenario: client makes call to POST a new product with valid data
    Given the client has a valid product payload
    When the client calls POST /createProduct
    Then the client receives status code of 201 for product
    And the client receives the created product with an ID

  Scenario: client makes call to POST a product with missing name
    Given the client has a product payload without a name
    When the client calls POST /createProduct
    Then the client receives status code of 400 for product

  Scenario: client makes call to POST a product with negative price
    Given the client has a product payload with price -1
    When the client calls POST /createProduct
    Then the client receives status code of 400 for product

  Scenario: client makes call to POST a product with price of 0
    Given the client has a product payload with price 0
    When the client calls POST /createProduct
    Then the client receives status code of 400 for product

  # ─── UPDATE PRODUCT ─────────────────────────────────────────

  Scenario: client makes call to PUT an existing product
    Given a product with ID 1 exists
    When the client calls PUT /updateProduct/1 with valid data
    Then the client receives status code of 200 for product
    And the client receives the updated product

  Scenario: client makes call to PUT a non-existing product
    When the client calls PUT /updateProduct/9999 with valid data
    Then the client receives status code of 404 for product

  # ─── DELETE PRODUCT ─────────────────────────────────────────

  Scenario: client makes call to DELETE an existing product
    Given a product with ID 1 exists
    When the client calls DELETE /deleteProduct/1
    Then the client receives status code of 200 for product

  Scenario: client makes call to DELETE a non-existing product
    When the client calls DELETE /deleteProduct/9999
    Then the client receives status code of 404 for product

  # ─── SCENARIO OUTLINE (Boundary values) ─────────────────────

  Scenario Outline: client makes call to GET product with boundary IDs
    When the client calls /getProduct/<id>
    Then the client receives status code of <status>