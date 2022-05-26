package ru.msu.cmc.web_prac.video_rental.web;

import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FilmWebTest {
    private final String pageTitle = "Фильмы";

    @Test
    void filmsTest() {
        System.setProperty("webdriver.gecko.driver", "/usr/local/bin/geckodriver");
        WebDriver driver = new FirefoxDriver();
        driver.get("http://localhost:8080/");

        assertEquals(pageTitle, driver.getTitle());
    }
}
