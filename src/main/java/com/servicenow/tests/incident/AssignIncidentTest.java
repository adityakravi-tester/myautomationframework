package com.servicenow.tests.incident;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.servicenow.base.BaseClass;
import com.servicenow.pages.incident.LoginPage;

public class AssignIncidentTest extends BaseClass{
  
  @BeforeTest
  public void etExceDataFile() {
    excelTestDataFile = "AssignIncident";
  }
  
  @Test(dataProvider = "setData")
  public void testAssignIncident(String filterType, String searchParam, String incidentNumber, String assigneeGroup, String assigneeUser, String workNotes) throws InterruptedException {
    new LoginPage(webdriver.get())
    .switchToFrame()
    .enterUsername(prop.get().getProperty("username"))
    .enterPassword(prop.get().getProperty("password"))
    .clickLoginButton()
    .enterIncidentInFilter(filterType)
    .clickOpenLink()
    .switchToFrame()
    .searchGivenParameter(incidentNumber)
    .clickSearchedIncident()
    .clickAssigneeGroupButton()
    .searchGroup(assigneeGroup)
    .selectAssigneeGroup(assigneeGroup)
    .clickAssigneeUserButton()
    .selectUser(assigneeUser)
    .enterWorkNotes(workNotes)
    .clickUpdateButton()
    .searchGivenParameter(incidentNumber)
    .verifyAssignedGroupAndUser(assigneeGroup, assigneeUser);
  }
}
