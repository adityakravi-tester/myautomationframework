package com.servicenow.tests.incident;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.servicenow.base.BaseClass;
import com.servicenow.pages.incident.LoginPage;

public class ResolveIncidentTest extends BaseClass{
  
  @BeforeTest
  public void setExceDataFile() {
    excelTestDataFile = "ResolveIncident";
  }
  
  @Test(dataProvider ="setData")
  public void testResolveIncident(String filterType, String searchParam, String incidentNumber, String state, String resolutionCode, String resolutionNotes) {
    new LoginPage(webdriver.get())
    .switchToFrame()
    .enterUsername(prop.get().getProperty("username"))
    .enterPassword(prop.get().getProperty("password"))
    .clickLoginButton()
    .enterIncidentInFilter(filterType)
    .clickOpenIncidentLink()
    .switchToFrame()
    .searchGivenParameter(incidentNumber)
    .clickSearchedIncident()
    .selectState(state)
    .clickOnResolutionInformationTab()
    .selectResolutionCode(resolutionCode)
    .enterResolutionNotes(resolutionNotes)
    .clickResolveButton()
    .searchGivenParameter(incidentNumber)
    .verifyIncidentState(state);
  }
}
