package com.sqs.cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

public class bookingObject {
    WebDriver driver;

    private By homePageLinkLocator = By.linkText("Homepage");
    private By bookFlightLinkLocator = By.linkText("Book Flight");
    private By signInButtonLocator = By.xpath(("/html/body/div/form/button"));
    private By bodyTextLocator = By.tagName("body");
    private String bookingHeader = "Book Flight";
    private String bookingSuccessful = "Flight booked successfully";
    private By originLocator = By.name("Origin");
    private By destinationLocator = By.name("Destination");
    private By flightClassLocator = By.name("Flightclass");
    private By seatLocator = By.id("seat");
    private By bookButton = By.xpath("/html/body/div/div/div[2]/form/button");

    public bookingObject(WebDriver driver) {

        this.driver = driver;
    }

    public void assertBookingHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(bookingHeader));
    }

    public void assertBookingSuccessful(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(bookingSuccessful));
    }

    public void clickBookButton(WebDriver driver){

        driver.findElement(signInButtonLocator).click();
    }

    public void populateBooking(WebDriver driver, String origin, String destination, String seat, String flightClass) {

        assertBookingHeader();
        driver.findElement(bookFlightLinkLocator).click();
        String bodyText1 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText1.contains(bookingHeader));

        WebElement Origin = driver.findElement(originLocator);
        Select OriginDropDownSelect = new Select(Origin);
        OriginDropDownSelect.selectByVisibleText(origin);

        WebElement Destination = driver.findElement(destinationLocator);
        Select DestinationDropDownSelect = new Select(Destination);
        DestinationDropDownSelect.selectByVisibleText(destination);

        driver.findElement(seatLocator).sendKeys(seat);

        WebElement Flightclass = driver.findElement(flightClassLocator);
        Select FlightclassDropDownSelect = new Select(Flightclass);
        FlightclassDropDownSelect.selectByVisibleText(flightClass);

        driver.findElement(bookButton).click();
        //driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();

        String bodyText2 = driver.findElement(By.tagName("body")).getText();
        Assert.assertTrue("Text not found!", bodyText2.contains(bookingSuccessful));
    }
    //clickBookButton(driver);
}
