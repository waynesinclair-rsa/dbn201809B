package com.sqs.cloud9A;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class loginObject {
    WebDriver driver;

    private By emailAddressLocator = By.name("email");
    private By passwordLocator = By.name("password");
    private By signInButtonLocator = By.xpath(("/html/body/div/form/button"));
    private By bodyTextLocator = By.tagName("body");
    private By homePageLinkLocator = By.linkText("Homepage");
    private String loginHeader = "Cloud9 - Sign in";
    private String loginSuccessful = "Welcome";

    public loginObject(WebDriver driver) {

        this.driver = driver;
    }

    public void assertLoginHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(loginHeader));
    }

    public void assertLoginSuccessful(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(loginSuccessful));
    }

    public void clickSignIn(WebDriver driver){

        driver.findElement(signInButtonLocator).click();
    }

    public void clickHomePage(WebDriver driver){
        driver.findElement(homePageLinkLocator).click();
    }

    public void populateLogIn(WebDriver driver, String email, String password) {
        assertLoginHeader();
        driver.findElement(emailAddressLocator).sendKeys(email);
        driver.findElement(passwordLocator).sendKeys(password);
        clickSignIn(driver);
        assertLoginSuccessful();
        clickHomePage(driver);
    }
}
