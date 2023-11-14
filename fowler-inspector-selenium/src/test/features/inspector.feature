Feature: Navigation on Martin Fowler website to verify accessibility of data

  Scenario Outline: Verify Navigation and Elements:
    Given I am on the base URL
    When I click on the link with href "<nav_path>"
    And I wait for an element containing text "<nav_text>"
    And I verify an element with href "<existing_path>" exists

    Examples:
      | nav_path | nav_text | existing_path |
      | /architecture | Software Architecture Guide | /microservices  |
      | /aboutMe.html | Martin Fowler | /aboutMe.html  |
      | /aboutMe.html | Martin Fowler | /architecture  |
      | /architecture | Software Architecture Guide | /architecture  |