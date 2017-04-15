package com.simpleprog.proteintracker.tests;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class SeleniumTests {
    @Test
    public void CanOpenGoogle() {
        WebDriver driver = new ChromeDriver();
        driver.get("http://google.com");
        WebElement searchBox = driver.findElement(By.name("q"));
        searchBox.sendKeys("NaviBand");
        searchBox.submit();
    }
}
