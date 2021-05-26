package com.servicenow.tests.incident;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.servicenow.base.BaseClass;
import com.servicenow.pages.incident.LoginPage;

public class UpdateIncidentTest extends BaseClass{
  
  @BeforeTest
  public void setExceDataFile() {
    excelTestDataFile = "UpdateIncident";
  }
  
  @Test(dataProvider = "setData")
  public void testUpdateIncident(String filterType, String incidentNumber, String urgency, String state, String priority) {
    new LoginPage(webdriver.get())
    .switchToFrame()
    .enterUsername(prop.get().getProperty("username"))
    .enterPassword(prop.get().getProperty("password"))
    .clickLoginButton()
    .enterIncidentInFilter(filterType)
    .clickIncidentsLink()
    .switchToFrame()
    .searchGivenParameter(incidentNumber)
    .clickSearchedIncident()
    .selectUrgency(urgency)
    .selectState(state)
    .clickUpdateButton()
    .searchGivenParameter(incidentNumber)
    .verifyUrgencyAndStateSelected();
  }
}
