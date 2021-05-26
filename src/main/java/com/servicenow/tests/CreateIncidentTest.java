package com.servicenow.tests;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.servicenow.base.BaseClass;
import com.servicenow.pages.CreateIncidentPage;
import com.servicenow.pages.IncidentsPage;
import com.servicenow.pages.LoginPage;

public class CreateIncidentTest extends BaseClass {
  
  public ThreadLocal<String> incidentNumber = new ThreadLocal<String>();
  
  @BeforeTest
  public void setExceDataFile() {
    excelTestDataFile = "CreateIncident";
  }
  
  @Test(dataProvider = "setData")
  public void testCreateIncident(String scenarioType, String filterType, String shortDescription, String expectedErrorMessage) {
    
    new LoginPage(webdriver.get())
    .switchToFrame()
    .enterUsername(prop.get().getProperty("username"))
    .enterPassword(prop.get().getProperty("password"))
    .clickLoginButton()
    .enterIncidentInFilter(filterType)
    .clickIncidentsLink()
    .switchToFrame()
    .clickNewIncidentButton()
    .getIncidentNumber(incidentNumber)
    .enterShortDescription(shortDescription)
    .clickSubmitButton();
    
    if(scenarioType.equalsIgnoreCase("positive")) {
      new IncidentsPage(webdriver.get()).verifyIncidentNumber(incidentNumber.get());
    } else {
      new CreateIncidentPage(webdriver.get()).verifyErrorMessage(expectedErrorMessage);
    }
  }
}
