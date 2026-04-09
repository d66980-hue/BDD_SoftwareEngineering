Feature: Verify the location service

  Scenario: client makes call to GET location
    When the client calls /getLocation
    Then the client receives status code of 200
    And the client receives product with location