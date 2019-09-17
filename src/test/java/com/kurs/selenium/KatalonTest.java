package com.kurs.selenium;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class KatalonTest {

    private WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver",
                "src/test/resources/chromedriver");

        // Uruchom nowy egzemplarz przeglądarki Chrome
        driver = new ChromeDriver();

        // Zmaksymalizuj okno przeglądarki
        driver.manage().window().maximize();
        // Przejdź do formularzu Katalona
        driver.get("https://katalon-test.s3.amazonaws.com/demo-aut/dist/html/form.html");
    }

    @Test
    public void TestKatalon() {

        fillForm( "first-name", "Karol");
        //assertTrue(labelFormularza.getText().startsWith("First"));

        fillForm( "last-name", "Kowalski");
        //assertTrue(labelFormularza.getText().startsWith("Last"));


        WebElement maleGenderRadio = driver.findElement(By.name("gender"));
        System.out.println(maleGenderRadio.isEnabled());
        maleGenderRadio.click();

        fillForm( "dob", "05/22/2010");
        //assertTrue(labelFormularza.getText().startsWith("Date"));


        fillForm( "address", "Prosta 51");
        //assertTrue(labelFormularza.getText().startsWith("Add"));


        fillForm( "email", "karol.kowalski@mailinator.com");
        //assertTrue(labelFormularza.getText().startsWith("Email"));


        fillForm( "password", "Pass123");
        //assertTrue(labelFormularza.getText().startsWith("Pass"));


        fillForm( "company", "Coderslab");
        //assertTrue(labelFormularza.getText().startsWith("Com"));


        fillForm( "comment", "Moj pierwszy automat testowy");
        //assertTrue(labelFormularza.getText().startsWith("Comm"));


        driver.findElement(By.id("submit")).click();

        WebElement successMessage = driver.findElement(By.id("submit-msg"));
        assertEquals("Successfully submitted!", successMessage.getText());
    }


    @After
    public void tearDown() throws Exception {
        // Zamknij przeglądarkę
        driver.quit();
    }

    public void fillForm(String identyfikator, String wartosc) {
        WebElement poleFormularza = driver.findElement(By.id(identyfikator));
        WebElement labelFormularza = driver.findElement(By.xpath("//label[@for='" + identyfikator + "']"));
        Boolean isDisplayed = poleFormularza.isDisplayed();
        Boolean isEnabled = poleFormularza.isEnabled();
        poleFormularza.sendKeys(wartosc);

        System.out.println("Pole " + labelFormularza.getText() + " ma wartosc " + poleFormularza.getAttribute("value"));
    }
}