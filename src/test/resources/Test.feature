@All
Feature: Weather Application Testing

  @Test1
  Scenario Outline: Display 5 day weather forecast
    Given the weather application is loaded and available
    When the user enter the '<city_name>'
    Then the 5 day weather forecast of '<city_name>' is displayed
    Examples:
      | city_name |
      | edinburgh |
      | glasgow   |
      | london    |

  @Test2
  Scenario Outline: Display 3 hourly forecast when the day is selected and hide when selected the same day again
    Given the weather application is loaded and available
    When the user select a '<Day>'
    Then the 3 hourly forecast of '<Day>' is displayed under the summary
    And hide the 3 hourly forecast when the same '<Day>' is selected again
    Examples:
      | Day |
      | 1   |
      | 2   |
      | 3   |
      | 4   |
      | 5   |

  @Test3
  Scenario Outline: Daily forecast should summarise the 3 hour data
    Given the weather application is loaded and available
    When the 5 day weather forecast of '<city_name>' is displayed
    And the user select a '<Day>'
    Then make sure the day '<Day>' summary displayed is valid
    Examples:
     |city_name | Day | 
     |edinburgh | 1   |
     |glasgow | 2   |
      |edinburgh | 3   |
     