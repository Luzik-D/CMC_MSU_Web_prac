package ru.msu.cmc.web_prac.video_rental.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ErrorCasesTest {

    @Test
    void createWithEmptyField() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/");

        //films
        driver.findElement(By.id("createButton")).click();
        driver.findElement(By.id("filmTitle")).sendKeys("TestError");
        //all other fields are empty
        driver.findElement(By.id("createButton")).click();
        assertEquals("Ошибка", driver.getTitle());

        //clients
        driver.get("http://localhost:8080/clients");
        driver.findElement(By.id("createButton")).click();
        driver.findElement(By.id("clientName")).sendKeys("test");
        //all other fields are empty
        driver.findElement(By.id("createButton")).click();
        assertEquals("Ошибка", driver.getTitle());

        //records
        driver.get("http://localhost:8080/films/4");
        driver.findElement(By.id("createCopyButton")).click();
        driver.findElement(By.id("copyStatus")).sendKeys("test");
        //all other fields are empty
        driver.findElement(By.id("createButton")).click();
        assertEquals("Ошибка", driver.getTitle());

        driver.quit();
    }

    @Test
    void editWithEmptyFields() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/films/1");

        //films
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("filmTitle")).clear();
        driver.findElement(By.id("editButton")).click();
        assertEquals("Ошибка", driver.getTitle());

        //clients
        driver.get("http://localhost:8080/clients/1");
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("clientName")).clear();
        driver.findElement(By.id("editButton")).click();
        assertEquals("Ошибка", driver.getTitle());

        //copies
        driver.get("http://localhost:8080/copies/15");
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("copyStatus")).clear();
        driver.findElement(By.id("editButton")).click();
        assertEquals("Ошибка", driver.getTitle());

        driver.quit();
    }

    @Test
    void addClientWithExistingPhone() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/clients");

        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("clientPhone")).sendKeys("77777777777");
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Ошибка", driver.getTitle());

        driver.quit();
    }

    @Test
    void incorrectPhoneOrDateOrYearImport() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/clients");

        //phone
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("clientPhone")).sendKeys("1");
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Ошибка", driver.getTitle());

        //date
        driver.get("http://localhost:8080/copies/order-14");
        driver.findElement(By.id("clientPhone")).sendKeys("77777777777");
        driver.findElement(By.id("date")).sendKeys("1");
        driver.findElement(By.id("orderButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Ошибка", driver.getTitle());

        //year
        driver.get("http://localhost:8080/");
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("filmYear")).sendKeys("1");
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Ошибка", driver.getTitle());


        driver.manage().timeouts().implicitlyWait(1000, TimeUnit.MILLISECONDS);
        driver.quit();
    }
}
