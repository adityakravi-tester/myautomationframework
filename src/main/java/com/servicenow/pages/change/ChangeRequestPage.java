package com.servicenow.pages.change;

import java.time.Duration;

import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import com.servicenow.base.HomePage;
import com.servicenow.pages.incident.CreateIncidentPage;
import com.servicenow.pages.incident.IncidentsPage;

public class ChangeRequestPage extends HomePage{

  public ChangeRequestPage(WebDriver driver) {
    super(driver);
  }
  @FindBy(how = How.ID, using = "sysverb_new")
  private WebElement buttonNewChange;
  
  @FindBy(how =How.XPATH, using = "//*[@class='form-control default-focus-outline']")
  private WebElement dropdownSearchParam;

  @FindBy(how =How.XPATH, using = "//*[@placeholder='Search' and @class='form-control']")
  private WebElement textboxSearch;

  @FindBy(how =How.XPATH, using = "//*[@class='vt']/a")
  private WebElement linkChangeNumber;
  
  public CreateChangePage clickNewIncidentButton() {
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(buttonNewChange)).click();
    return new CreateChangePage(webdriver.get());
  }

  public ChangeRequestPage verifyChangeNumber(String changeNumber) {
    textboxSearch.sendKeys(changeNumber + Keys.ENTER);
    Assert.assertEquals(linkChangeNumber.getText(), changeNumber, "The change request is not created");
    return this;
  }

  public ChangeRequestPage selectSearchParameter(String searchParam) {
    new Select(dropdownSearchParam).selectByVisibleText(searchParam);
    return this;
  }

  public ChangeRequestPage searchGivenParameter(String changeNumber) {
    textboxSearch.sendKeys(changeNumber + Keys.ENTER);
    return this;
  }
  
}
