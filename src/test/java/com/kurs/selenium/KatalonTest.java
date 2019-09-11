package com.kurs.selenium;


import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import javax.swing.text.html.HTMLDocument;

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
        fillForm( "last-name", "Kowalski");
        WebElement Gender=driver.findElement(By.xpath("/html/body/div/div/form/div[3]/div/div/label[1]"));
        System.out.println(Gender.isEnabled());
        Gender.click();
        fillForm( "dob", "05/22/2010");
        fillForm( "address", "Prosta 51");
        fillForm( "email", "karol.kowalski@mailinator.com");
        fillForm( "password", "Pass123");
        fillForm( "company", "Coderslab");
        fillForm( "comment", "Moj pierwszy automat testowy");


        driver.findElement(By.id("submit")).click();

        WebElement successMessage = driver.findElement(By.id("submit-msg"));
        Assert.assertEquals("Successfully submitted!", successMessage.getText());
    }


    @After
    public void tearDown() throws Exception {
        // Zamknij przeglądarkę
        driver.quit();
    }

    public void fillForm(String identyfikator, String wartosc) {
        WebElement poleFormularza = driver.findElement(By.id(identyfikator));
        poleFormularza.isDisplayed();
        poleFormularza.isEnabled();
        poleFormularza.sendKeys(wartosc);
        System.out.println("Pole " + identyfikator + " ma wartosc " + wartosc);
    }
}