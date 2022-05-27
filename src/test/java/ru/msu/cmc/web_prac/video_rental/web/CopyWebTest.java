package ru.msu.cmc.web_prac.video_rental.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class CopyWebTest {

    @Test
    void testCopy() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/");
        assertEquals("Фильмы", driver.getTitle());
        List<WebElement> refs = driver.findElements(By.id("filmId"));

        //create
        refs.get(4).click();
        assertEquals("Фильм", driver.getTitle());
        driver.findElement(By.id("createCopyButton")).click();
        assertEquals("Создание носителя", driver.getTitle());
        driver.findElement(By.id("copyType")).sendKeys("Диск");
        driver.findElement(By.id("copyStatus")).sendKeys("Свободный");
        driver.findElement(By.id("copyPrice")).sendKeys("1000");
        driver.findElement(By.id("createButton")).click();
        assertEquals("Фильм", driver.getTitle());

        //edit
        driver.findElement(By.id("copyButton")).click();
        assertEquals("Список носителей", driver.getTitle());
        refs = driver.findElements(By.id("copyId"));
        assertEquals(refs.size(), 3);
        refs.get(2).click();
        assertEquals("Носитель", driver.getTitle());
        driver.findElement(By.id("editButton")).click();
        assertEquals("Редактирование носителя", driver.getTitle());
        driver.findElement(By.id("copyStatus")).clear();
        driver.findElement(By.id("copyStatus")).sendKeys("Используется");
        driver.findElement(By.id("editButton")).click();
        assertEquals("Фильм", driver.getTitle());

        //if copy status is "using" then there is no button for order
        driver.findElement(By.id("copyButton")).click();
        refs = driver.findElements(By.id("copyId"));
        refs.get(2).click();
        assertNotNull(driver.findElements(By.id("orderButton")).isEmpty());

        //delete
        driver.findElement(By.id("deleteButton")).click();
        assertEquals("Фильм", driver.getTitle());
        driver.findElement(By.id("copyButton")).click();
        refs = driver.findElements(By.id("copyId"));
        assertNotEquals(refs.size(), 3);

        driver.quit();
    }
}
