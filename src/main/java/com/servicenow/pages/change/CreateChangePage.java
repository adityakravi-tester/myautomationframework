package com.servicenow.pages.change;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.Select;
import com.servicenow.base.HomePage;
import com.servicenow.pages.incident.CreateIncidentPage;
import com.servicenow.pages.incident.IncidentsPage;

public class CreateChangePage extends HomePage{

  public CreateChangePage(WebDriver driver) {
    super(driver);
  }
  
  @FindBy(how =How.XPATH, using = "//*[@id='change_request.number']")
  private WebElement textboxChangeNumber;
  
  @FindBy(how =How.XPATH, using = "//*[@id='change_request.category']")
  private WebElement dropdownCategory;
  
  @FindBy(how = How.XPATH, using = "//*[@id='lookup.change_request.business_service']")
  private WebElement buttonSearchService;
  
  @FindBy(how=How.XPATH,using = "//*[@id='change_request.short_description']")
  private WebElement textboxShortDescription;
  
  public CreateChangePage getChangeRequestNumber(ThreadLocal<String> changeNumber) {
    changeNumber.set(textboxChangeNumber.getAttribute("value"));
    return this;
  }
  
  public ServicesPage clickSearchServiceButton() {
    buttonSearchService.click();
    switchToWindow(2);
    return new ServicesPage(webdriver.get());
  }
  
  public CreateChangePage enterShortDescription(String shortDescription) {
    textboxShortDescription.sendKeys(shortDescription);
    return this;
  }
  
  public CreateChangePage selectCategory(String category) {
    new IncidentsPage(webdriver.get()).switchToFrame();
    new Select(dropdownCategory).selectByVisibleText(category);
    return this;
  }
  
  public CreateChangeIndexPage clickSubmitButton() {
    new CreateIncidentPage(webdriver.get()).clickSubmitButton();
    return new CreateChangeIndexPage(webdriver.get());
  }

}
