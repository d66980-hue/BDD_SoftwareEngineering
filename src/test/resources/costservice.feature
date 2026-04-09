Feature: Verfy the cost service

  Scenario: client makes call to GET cost
    When the client calls /getCost
    Then the client receives status code of 200
    And the client receives product with cost