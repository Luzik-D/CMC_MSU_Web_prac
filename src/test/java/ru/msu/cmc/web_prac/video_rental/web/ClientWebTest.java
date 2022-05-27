package ru.msu.cmc.web_prac.video_rental.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class ClientWebTest {
    private final String clientTitle = "Клиенты";

    @Test
    void clientsTest() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        //driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/clients");
        assertEquals(clientTitle, driver.getTitle());

        //test filter

        /*
         *   test filter, create, edit, delete
         */
        List<WebElement> refs;
        //filter
        driver.findElement(By.id("clientName")).sendKeys("Лузик Дмитрий");
        driver.findElement(By.id("searchButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Отфильтрованные клиенты", driver.getTitle());
        refs = driver.findElements(By.id("clientId"));
        assertEquals(refs.size(), 1);
        driver.findElement(By.id("toMainPage")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        //create
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Создание клиента", driver.getTitle());
        driver.findElement(By.id("clientName")).sendKeys("Тестовый клиент");
        driver.findElement(By.id("clientPhone")).sendKeys("12345678905");
        driver.findElement(By.id("clientAddress")).sendKeys("Москва");
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(clientTitle, driver.getTitle());
        refs = driver.findElements(By.id("clientId"));
        assertEquals(refs.size(), 6);

        //edit
        refs = driver.findElements(By.id("clientId"));
        refs.get(5).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Редактирование клиента", driver.getTitle());
        driver.findElement(By.id("clientAddress")).sendKeys(", улица Пушкина");
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(clientTitle, driver.getTitle());

        //delete
        refs = driver.findElements(By.id("clientId"));
        refs.get(5).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(clientTitle, driver.getTitle());
        refs = driver.findElements(By.id("clientId"));
        assertNotEquals(refs.size(), 6);
        driver.quit();
    }
}
