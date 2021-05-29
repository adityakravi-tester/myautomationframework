package com.servicenow.tests.change;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import com.servicenow.base.BaseClass;
import com.servicenow.pages.incident.LoginPage;

public class CreateChangeTest extends BaseClass{
  
  public static ThreadLocal<String> changeNumber = new ThreadLocal<>();
  
  @BeforeTest
  public void setExceDataFile() {
    excelTestDataFile = "CreateChange";
  }
  
  @Test(dataProvider = "setData")
  public void testCreateChange(String filterType, String searchParameter, String serviceName, String category, String shortDescription) throws InterruptedException {
    new LoginPage(webdriver.get())
    .switchToFrame()
    .enterUsername(prop.get().getProperty("username"))
    .enterPassword(prop.get().getProperty("password"))
    .clickLoginButton()
    .enterIncidentInFilter(filterType)
    .clickCreateNewChangeLink()
    .clickNormalChangeCard()
    .getChangeRequestNumber(changeNumber)
    .clickSearchServiceButton()
    .searchService(serviceName)
    .selectService(serviceName)
    .selectCategory(category)
    .enterShortDescription(shortDescription)
    .clickSubmitButton()
    .clickOpenChangeLink()
    .searchGivenParameter(changeNumber.get())
    .verifyChangeNumber(changeNumber.get());
  }
}
