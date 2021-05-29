package com.servicenow.pages.change;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;

import com.servicenow.base.HomePage;

public class CreateChangeIndexPage extends HomePage{

  public CreateChangeIndexPage(WebDriver driver) {
    super(driver);
  }
  
  @FindBy(how =How.XPATH, using = "(//*[@class='change-model-card-component'])")
  private List<WebElement> listOfChangeModelCards;
  

  
  public CreateChangePage clickNormalChangeCard() {
    listOfChangeModelCards.get(1).click();
    return new CreateChangePage(webdriver.get());
  }
  
}
