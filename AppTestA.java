package com.sqs.cloud9A;

import org.junit.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.concurrent.TimeUnit;

/**
 * Unit test for simple App.
 */
public class AppTestA
{
    static WebDriver driver;
    private String registrationSuccessful = "Registration Successful";
    private By bodyTextLocator = By.tagName("body");
    private String cloud9RegisterHeader = "Cloud9 - Register";

    @Before
    public void testSetup() throws InterruptedException {
        System.out.println("In testSetup ");
        System.setProperty("ChromeDriver", "C:/Drivers/chromedriver");
        driver = new ChromeDriver();
        driver.manage().timeouts().pageLoadTimeout(60, TimeUnit.SECONDS);

        String baseUrl = "http://10.9.10.39:81/sqlite/Main/login.html";
        //String baseUrl = "http://192.168.56.1:81/sqlite/Main/login.html";

        String expectedTitle = "Cloud9 Airlines";
        driver.get(baseUrl);
        String actualTitle = driver.getTitle();
        System.out.println("Actual Title is: " + actualTitle);
        Assert.assertEquals(actualTitle, expectedTitle);
        Thread.sleep(5000);
    }

    @BeforeClass
    public static void browserSetup()
    {

    }

    @Test
    public void shouldRegisterNewCustomer()
    {
        // Click the register tab on the login screen (Login screen is entry into system)
        driver.findElement(By.linkText("Register")).click();

        registrationObject registrationPage;
        registrationPage = new registrationObject(driver);
        registrationPage.populateRegistration("Wayne","Sinclair", "ws1009@sqs.com", "xxx");
   }

    @Test
    public void shouldLoginCustomer()
    {
        loginObject loginPage;
        loginPage = new loginObject(driver);
        loginPage.populateLogIn(driver,"ws1009@sqs.com", "xxx");
    }

    @Test
    public void bookingTest() {
        //Utilities.login(driver,"wsi@netactive.co.za", "xxx");
        //booking();

        loginObject loginPage;
        loginPage = new loginObject(driver);
        loginPage.populateLogIn(driver, "wsi@netactive.co.za", "xxx");

        bookingObject bookingPage;
        bookingPage = new bookingObject(driver);
        bookingPage.populateBooking(driver, "Johannesburg", "Durban", "A88", "First");
    }

    @Test
    public void updateSeatNumber() throws InterruptedException {

        loginObject loginPage;
        loginPage = new loginObject(driver);
        loginPage.populateLogIn(driver, "wsi@netactive.co.za", "xxx");

        String flightID = "265";
        String newSeatNumber = "A32";

        itineraryObject itineraryPage;
        itineraryPage = new itineraryObject(driver);
        String editURL = itineraryPage.scanBookingsAndFindFlightID(flightID);
        System.out.println("editURL is: " + editURL);

        if (editURL.equals("notfound")) {
            System.out.println("flightID: " + flightID + " not found" );
        } else {
            driver.get(editURL);
            // TODO create new object for editFlight class
            // TODO call the update method

            editObject editPage;
            editPage = new editObject(driver);
            editPage.editBooking(driver, newSeatNumber);
        }
        }



    @After
    public void tearDown()
    {
       // driver.close();
    }
}
