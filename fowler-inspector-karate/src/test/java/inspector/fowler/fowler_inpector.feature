
Feature: Navigation on Martin Fowler website to verify accessibility of data
  @setup
  Scenario:
    * def data_nav = read('./data.json')

  Scenario Outline: Verify Navigation and Elements:
    # Given driver '<base_url>'
    Given driver baseUrl
    # Click on element with href attribute
    When click("//a[@href='<nav_path>']")
    # Wait for an element containing specific text to appear on screen
    And waitFor("//*[contains(text(),'<nav_text>')]")
    # Then check that an element with href attribute is available
    * def existingElement = locate("//a[@href='<existing_path>']")
    Then match existingElement != null
    # Closing the browser after the test
    * driver.quit()

    Examples:
      | karate.setup().data_nav |