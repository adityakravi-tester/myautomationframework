package com.servicenow.pages.change;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import com.servicenow.base.HomePage;

public class ServicesPage extends HomePage{

  public ServicesPage(WebDriver driver) {
    super(driver);
  }
  
  @FindBy(how =How.XPATH, using = "//*[@placeholder='Search' and @class='form-control']")
  private WebElement textboxSearch;
  
  public ServicesPage searchService(String serviceName) {
    textboxSearch.sendKeys(serviceName + Keys.ENTER);
    return this;
  }
  
  public CreateChangePage selectService(String serviceName) throws InterruptedException {
    Thread.sleep(3000);
    
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(5))
        .until(ExpectedConditions.elementToBeClickable(webdriver.get().findElement(By.xpath("//*[@class='glide_ref_item_link' and text()='"+serviceName+"']")))).click();
  
    switchToWindow(1);
    return new CreateChangePage(webdriver.get());
  }
}
