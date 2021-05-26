package com.servicenow.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.testng.Assert;

public class CreateIncidentPage extends HomePage{

  public CreateIncidentPage(WebDriver driver) {
    super(driver);
  }
  @FindBy(how = How.XPATH, using = "//*[@id='lookup.incident.caller_id']")
  private WebElement buttonCaller;
  
  @FindBy(how = How.XPATH, using ="//*[@id='incident.short_description']")
  private WebElement textboxShortDescription;
  
  @FindBy(how = How.XPATH, using ="//*[@id='sysverb_insert_bottom']")
  private WebElement buttonSubmit;
  
  @FindBy(how = How.XPATH, using = "//*[@id='incident.number']")
  private WebElement textboxIncidentNumber;
  
  @FindBy(how = How.XPATH, using = "//*[@class='outputmsg_text']")
  private WebElement textErrorMessage;

  public CreateIncidentPage enterShortDescription(String shortDescription) {
    textboxShortDescription.sendKeys(shortDescription);
    return this;
  }
  
  public CreateIncidentPage getIncidentNumber(ThreadLocal<String> incidentNumber) {
    incidentNumber.set(textboxIncidentNumber.getAttribute("value"));
    return this;
  }
  
  public IncidentsPage clickSubmitButton() {
    buttonSubmit.click();
    return new IncidentsPage(webdriver.get());
  }
  
  public CreateIncidentPage verifyErrorMessage(String expectedErrorMessage) {
    Assert.assertEquals(expectedErrorMessage, textErrorMessage.getText());
    return this;
  }
}
