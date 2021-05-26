package com.servicenow.base;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;

import com.servicenow.utilities.ExcelDataFactory;
import com.servicenow.utilities.PropertyReader;

public class BaseClass {
  
  public static final ThreadLocal<WebDriver> webdriver = new ThreadLocal<>();
  public static ThreadLocal<Properties> prop = new ThreadLocal<>();
  public String excelTestDataFile;
  
  @Parameters({"os","browser"})
  @BeforeMethod
  public void setup(String os, String browser) throws IOException {
    FileInputStream propertyLocation = new FileInputStream("./testdata/service-now.properties");
    DriverFactory webDriverFactory = new DriverFactory();
    PropertyReader properties = new PropertyReader();
    webDriverFactory.setDriver(os, browser);
    properties.setProperty(new Properties());
    prop.set(properties.getProperty());
    webdriver.set(webDriverFactory.getDriver());
    webdriver.get().manage().window().maximize();
    webdriver.get().manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
    webdriver.get().manage().deleteAllCookies();
    prop.get().load(propertyLocation);
    webdriver.get().get(prop.get().getProperty("url"));
  }
  
  @AfterMethod(alwaysRun =true)
  public void tearDown() {
    webdriver.get().close();
  }
  
  @AfterSuite(alwaysRun = true)
  public void afterSuite(ITestContext context) throws IOException {
    String browser = context.getCurrentXmlTest().getParameter("browser");
    Runtime.getRuntime().exec("taskkill /f /im "+ browser +"driver.exe");
  }
  
  @DataProvider
  public String[][] setData() throws IOException {
    return new ExcelDataFactory().readExcelData(excelTestDataFile);
  }
}
