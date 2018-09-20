package com.sqs.cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class editObject {
    WebDriver driver;

    private By seatLocator = By.id("seat");
    private By updateButton = By.xpath(("/html/body/div/div/div[2]/form/button"));


    public editObject(WebDriver driver) {

        this.driver = driver;
    }

    public void assertEditPage(){

        String editHeader = "Edit Flight";
        By bodyTextLocator = By.tagName("body");

        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(editHeader));

    }

    public void assertFlightSuccessfullyUpdated(){

        String editHeader = "Flight successfully updated";
        By bodyTextLocator = By.tagName("body");

        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(editHeader));

    }


        public void editBooking(WebDriver driver,  String newSeat) throws InterruptedException {

            assertEditPage();

            //Thread.sleep(5000);
            driver.findElement(seatLocator).clear();
            driver.findElement(seatLocator).sendKeys(newSeat);

            driver.findElement(updateButton).click();

            assertFlightSuccessfullyUpdated();
        }
    }
