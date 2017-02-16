package Pages;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.MatcherAssert.assertThat;

import java.util.Arrays;
import java.util.List;
//import com.sun.deploy.util.ArrayUtil;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import Admin.TestManagement;

public class landingPage extends TestManagement {

    WebDriver webDriver;
    WebDriverWait webDriverWait;

    public landingPage(WebDriver webDriver) {
        this.webDriver = webDriver;
        this.webDriverWait = new WebDriverWait(webDriver, 10);
    }


    public landingPage maximiseWindow() {
        webDriver.manage().window().maximize();
        return this;
    }

    public landingPage navigateToPage(String pageUrl) {
        webDriver.get(pageUrl);

        WebElement heading = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//h1[contains(text(), 'Five Day')]")));
        assertThat(heading.getText(), containsString("Five Day Weather Forecast for"));
        return this;
    }

    public landingPage City(String city){

        WebElement cityField = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.id("city")));
        cityField.clear();
        cityField.sendKeys(city);
        cityField.sendKeys(Keys.ENTER);

        String[] cityList = {"aberdeen", "dundee", "edinburgh", "glasgow", "perth", "stirling"};

        if(Arrays.asList(cityList).contains(city)){

            System.out.println("The city entered is " + city + " and it's valid!! ");
        } else {
            System.out.println("The city entered " + city + " is invalid!!");
        }

        return this;
    }

    public landingPage fiveRowSummary(String city){
    	
    	 String[] cityList = {"aberdeen", "dundee", "edinburgh", "glasgow", "perth", "stirling"};

         if(!Arrays.asList(cityList).contains(city)){

        	 WebElement noSummary = webDriverWait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@data-test = 'error']")));
        	 System.out.println("Error Displayed");
        	
         } else {    	
    	
         	List <WebElement> Day1Summary = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//div[@style = 'padding-bottom: 20px;']")));

            int counter = 0;
            for(WebElement daySummary : Day1Summary){
                counter++;
            }

            if (counter == 5){

                System.out.println("The total summary rows are: " +counter);
            }
            else {

                System.out.println("There are only "+ counter +" rows!!");
            }

         }
        return this;
    }

    public landingPage clickDayRow(int dayNumber){

        WebElement Day = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-test = 'day-"+dayNumber+"']")));
        Day.click();
        return this;
    }


    public landingPage rowDetails(int dayNumber){

        List <WebElement> threeHourIntervals = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../../following-sibling::div[1]/*/*/span[@class = 'hour']")));

        int difference = 0;
        for (int index = 0; index < threeHourIntervals.size() - 1; index++){

            difference = Integer.parseInt(threeHourIntervals.get(index + 1).getText()) - Integer.parseInt(threeHourIntervals.get(index).getText());
        }

        System.out.println("The difference in time interval is " + difference + "!!");
        return this;
    }

    public landingPage verifySummaryConditon (int dayNumber){

    	String dayDetailCondition = driver.findElement(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../../following-sibling::div[1]/*/*/*[name()='svg'][@data-test = 'description-"+dayNumber+"-1']")).getAttribute("aria-label");
        String daySummaryCondition = driver.findElement(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../following-sibling::span[1]/*[local-name() = 'svg']")).getAttribute("aria-label");
    	
        if (dayDetailCondition.equals(daySummaryCondition)){
        	
        	if(dayDetailCondition.equals("Rain")) {
            
        		System.out.println("The day "+dayNumber+" summary has a Rain Forecast!!");
          } else{

        	  System.out.println("The day "+dayNumber+" summary has a Cloudy Forecast!!");
          }
        	
        }
        return this;
    }

    public landingPage verifyMaxTemperature (int dayNumber){

        WebElement summaryTemp = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../following-sibling::span[2]/span[@class = 'max']")));
        
        String str = summaryTemp.getText();
        String temp = str.substring(0, str.length()-1);
        int s = Integer.parseInt(temp);
        
        List <WebElement> maxTemperature = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../../following-sibling::div[1]/*/*/span[@class = 'max']")));

        String str1 = maxTemperature.get(0).getText();
        String temp1 = str1.substring(0, str1.length()-1);
        int max = Integer.parseInt(temp1);

        for (WebElement maxTemp : maxTemperature){

        	String str2 = maxTemp.getText();
            String temp2 = str2.substring(0, str2.length()-1);
            
            int tmp = Integer.parseInt(temp2);

            if (tmp > max){

                max = tmp;
            }

        }

        if (s == max){

            System.out.println("The maximum temperature of Day " +dayNumber+ " is " +max);
        }
            return this;
    }

    public landingPage verifyMinTemperature (int dayNumber){

        WebElement summaryTemp = webDriverWait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../following-sibling::span[2]/span[@class = 'rmq-5ea3c959 min']")));
                
        String str = summaryTemp.getText();
        String temp = str.substring(0, str.length()-1);
        int s = Integer.parseInt(temp);
        
        List <WebElement> minTemperature = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../../following-sibling::div[1]/*/*/span[@class = 'rmq-5ea3c959 min']")));

        String str1 = minTemperature.get(0).getText();
        String temp1 = str1.substring(0, str1.length()-1);
        int min = Integer.parseInt(temp1);

        for (WebElement minTemp : minTemperature){

        	String str2 = minTemp.getText();
            String temp2 = str2.substring(0, str2.length()-1);
            
            int tmp = Integer.parseInt(temp2);

            if (tmp < min){

                min = tmp;
            }

        }

        if (s == min){

            System.out.println("The minimum temperature of Day " +dayNumber+ " is " +min);
        }
            return this;
    }

    public landingPage verifyWindSpeed (int dayNumber){

    	List <WebElement> detailsWindSpeed = webDriverWait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath("//span[@data-test = 'day-"+dayNumber+"']/../../following-sibling::div[1]/*/*/span[@class = 'speed']")));

        String str1 = detailsWindSpeed.get(0).getText();
        String temp1 = str1.substring(0, str1.length()-3);
        int speed = Integer.parseInt(temp1);

        System.out.println("The current wind speed of Day " +dayNumber+ " is " +speed+"kph");
       
        return this;
    }

    
}
