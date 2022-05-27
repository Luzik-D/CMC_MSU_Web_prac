package ru.msu.cmc.web_prac.video_rental.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class RecordWebTest {
    private final String recordTitle = "История транзакций";
    @Test
    void recordTest() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/records");
        assertEquals(recordTitle, driver.getTitle());
        List<WebElement> refs;

        //check transaction
        driver.findElement(By.id("clientName")).sendKeys("Лузик Дмитрий");
        driver.findElement(By.id("searchButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Список транзакций", driver.getTitle());
        refs = driver.findElements(By.id("recordId"));
        assertNotEquals(refs.size(), 0);
        driver.findElement(By.id("toMainPage")).click();
        assertEquals(recordTitle, driver.getTitle());

        driver.quit();
    }

    //creates transaction via order film
    @Test
    void createTransactionTest() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/");
        assertEquals("Фильмы", driver.getTitle());

        //select film
        List<WebElement> refs = driver.findElements(By.id("filmId"));
        assertNotEquals(refs.size(), 0);
        refs.get(3).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("copyButton")).click();

        //get copies
        assertEquals("Список носителей", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        refs = driver.findElements(By.id("copyId"));
        refs.get(1).click();
        assertEquals("Копия", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("orderButton")).click();

        //order
        assertEquals("Оформление заказа", driver.getTitle());
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("clientPhone")).sendKeys("77777777777");
        driver.findElement(By.id("date")).sendKeys("2022-05-26");
        driver.findElement(By.id("orderButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Клиент", driver.getTitle());

        driver.quit();

    }
}
