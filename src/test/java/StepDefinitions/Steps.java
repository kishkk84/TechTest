package StepDefinitions;

import Admin.TestManagement;
import Admin.WebDriverManagement;
import Pages.landingPage;
//import cucumber.api.Scenario;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
//import org.junit.After;
//import org.junit.Before;
//import org.openqa.selenium.WebDriver;

public class Steps extends TestManagement {

//    @Before
//    public void beforeScenario(){
//
//        //driver = WebDriverManagement.getChromeDriver();
//        //driver = WebDriverManagement.getFirefoxDriver();
//    }

    @Given("^the weather application is loaded and available$")
    public void theWeatherApplicationIsLoadedAndAvailable() {

    	driver = WebDriverManagement.getChromeDriver();

        landingPage homePage = new landingPage(driver);
        homePage.maximiseWindow();
        homePage.navigateToPage(AppUrl);
    }

    @When("^the user enter the '(.*)'$")
    public void whenTheUserEnterTheCity(String cityText){

        landingPage homePage = new landingPage(driver);
        homePage.City(cityText);

    }

    @Then("^the 5 day weather forecast of '(.*)' is displayed$")
    public void theFiveDayWeatherForecastIsDisplayed(String cityText){

        landingPage homePage = new landingPage(driver);
        homePage.fiveRowSummary(cityText);

    }

    @When("^the user select a '(.*)'$")
    public void theUserSelectADay(int clickDay){
        landingPage homePage = new landingPage(driver);
        homePage.clickDayRow(clickDay);

    }

    @Then("^the 3 hourly forecast of '(.*)' is displayed under the summary$")
    public void theThreeHourlyForecastIsDisplayedUnderTheSummary(int dayNumber){
    	
        landingPage homePage = new landingPage(driver);
        homePage.rowDetails(dayNumber);

    }

    @And("^hide the 3 hourly forecast when the same '(.*)' is selected again$")
    public void hideTheThreeHourlyForecastWhenTheSameDayIsSelectedAgain(int clickDay){
        landingPage homePage = new landingPage(driver);
        homePage.clickDayRow(clickDay);
    }

    @Then("^make sure the day '(.*)' summary displayed is valid$")
    public void makeSureTheDaySummaryDisplayedIsValid(int clickDay){

        landingPage homePage = new landingPage(driver);
        homePage.verifySummaryConditon(clickDay);
        homePage.verifyMaxTemperature(clickDay);
        homePage.verifyMinTemperature(clickDay);
        homePage.verifyWindSpeed(clickDay);
    }

//    @After
//    public void afterScenario(){
//
//        driver.close();
//        driver.quit();
//
//
//    }
}
