package com.servicenow.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import io.github.bonigarcia.wdm.WebDriverManager;

public class DriverFactory {

  private WebDriver driver;

  public void setDriver(String os, String browser) {

    if(os.equalsIgnoreCase("windows")) {
      if(browser.equalsIgnoreCase("chrome")) {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
      } else if(browser.equalsIgnoreCase("gecko")) {
        WebDriverManager.firefoxdriver().browserVersion("0.20.1").setup();
        driver = new FirefoxDriver();
      } else if(browser.equalsIgnoreCase("msedge")) {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
      } else {
        System.out.println("Currently only supporting windows machine");
        driver = null;
      }
    }
  }

  public WebDriver getDriver() {
    return driver;
  }
}
