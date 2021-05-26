package com.servicenow.tests.incident;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.servicenow.base.BaseClass;
import com.servicenow.pages.incident.LoginPage;

public class DeleteIncidentTest extends BaseClass {

  @BeforeTest
  public void setExceDataFile() {
    excelTestDataFile = "DeleteIncident";
  }
  
  @Test(dataProvider = "setData")
  public void testDeleteTest(String filterType, String searchParam, String incidentNumber) {
    
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
    .deleteIncident()
    .verifyIncidentDeleted();
  }
}
