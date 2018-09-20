package com.sqs.cloud9A;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class itineraryObject {
    WebDriver driver;

    public itineraryObject(WebDriver driver) {
        this.driver = driver;
    }

    public String scanBookingsAndFindFlightID(String flightID){
        //Utilities.login(driver,"wsi@netactive.co.za", "xxx");

        // trying to click on update
        //driver.findElement(By.linkText("Update")).click();

        System.out.println("Flight ID is: " + flightID);

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));

        System.out.println("Number of rows is " + rows.size());

        int rowNum =  rows.size();

        // Get booking id for latest booking
        List<WebElement> colVals2 = rows.get(rowNum-1).findElements(By.tagName("td"));
        //System.out.println("What is this " + colVals2.get(3).getText());
        String columnContents;
        Boolean flightIDFound = false;
        for(int i=1; i<rowNum; i++){
            List<WebElement> colVals1 = rows.get(i).findElements(By.tagName("td"));
            for(int j=0; j<6; j++){
                columnContents = colVals1.get(j).getText();
                if (columnContents.equals(flightID)) {
                    System.out.println("Yippeee,  found it: i=" + i + " : j = " + j);
                    System.out.println("Column Contents = " + columnContents);
                    flightIDFound = true;
                    //  create the URL to navigate to the edit screen for the flightid of interest
              //  updateLink = colVals1.get(0);
                }
                System.out.println("j= " + j + " : " + colVals1.get(j).getText());
                // If we come out of the loop and have not found the ID, then print a relevant message
            }
        }

        String updateURL = "notdefined";
        if (flightIDFound) {
            updateURL = "URL here";
            updateURL = "http://10.9.10.39:81/sqlite/Main/editflight.php?FlightID=" + flightID;
        }
        else {
            updateURL = "notfound";
        }
        return updateURL;
    }
}