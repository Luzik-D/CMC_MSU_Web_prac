package ru.msu.cmc.web_prac.video_rental.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.firefox.FirefoxDriver;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;

public class FilmWebTest {
    private final String filmTitle = "Фильмы";
    private final String filteredFilmsTitle = "Отфильтрованные фильмы";
    private final String clientTitle = "Клиенты";
    private final String recordTitle = "Транзакции";

    @Test
    void filmsTest() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/");

        assertEquals(filmTitle, driver.getTitle());

        //try to find film Brother
        driver.findElement(By.id("filmTitle")).sendKeys("Брат 2");
        driver.findElement(By.id("buttonSubmit")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filteredFilmsTitle, driver.getTitle());

        List<WebElement> refs = driver.findElements(By.id("refId"));
        System.out.printf("REFS " + refs);
        assertEquals(refs.size(), 1);

        //turn back to film page
        driver.findElement(By.id("toMainPage")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filmTitle, driver.getTitle());


        /*
         * create film, edit film then delete film
         */
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Создание фильма", driver.getTitle());

        driver.findElement(By.id("filmTitle")).sendKeys("Тест");
        driver.findElement(By.id("filmCompany")).sendKeys("Тестовая компания");
        driver.findElement(By.id("filmDirector")).sendKeys("Тестовый режиссер");
        driver.findElement(By.id("filmYear")).sendKeys("2022");
        driver.findElement(By.id("filmDescription")).sendKeys("Тестовое описание");
        driver.findElement(By.id("createButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filmTitle, driver.getTitle());
        refs = driver.findElements(By.id("filmId"));
        assertEquals(6, refs.size());

        //edit
        refs.get(5).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Фильм", driver.getTitle());
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals("Редактирование фильма", driver.getTitle());
        driver.findElement(By.id("filmTitle")).sendKeys("Test");
        driver.findElement(By.id("editButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filmTitle, driver.getTitle());

        //delete
        refs = driver.findElements(By.id("filmId"));
        refs.get(5).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("deleteButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filmTitle, driver.getTitle());
        refs = driver.findElements(By.id("filmId"));
        assertNotEquals(6, refs.size());

        driver.quit();
    }

    /*
     * show all disks and cassettes for film
     */
    @Test
    void CopiesTest() {

        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.manage().window().setPosition(new Point(0, 0));
        driver.manage().window().setSize(new Dimension(1024, 768));
        driver.get("http://localhost:8080/");
        List<WebElement> refs = driver.findElements(By.id("filmId"));
        refs.get(3).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);

        //disks
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("diskButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        List<WebElement> disks = driver.findElements(By.id("diskId"));
        assertEquals(3, disks.size());
        driver.findElement(By.id("toMainPage")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filmTitle, driver.getTitle());

        //cassettes
        refs = driver.findElements(By.id("filmId"));
        refs.get(3).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("cassetteButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        List<WebElement> cassettes = driver.findElements(By.id("cassetteId"));
        assertEquals(1, cassettes.size());
        driver.findElement(By.id("toMainPage")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filmTitle, driver.getTitle());

        //all copies
        refs = driver.findElements(By.id("filmId"));
        refs.get(3).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        driver.findElement(By.id("copyButton")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        List<WebElement> copies = driver.findElements(By.id("copyId"));
        assertEquals(4, copies.size());
        driver.findElement(By.id("toMainPage")).click();
        driver.manage().timeouts().implicitlyWait(500, TimeUnit.MILLISECONDS);
        assertEquals(filmTitle, driver.getTitle());

        driver.quit();
    }
}
