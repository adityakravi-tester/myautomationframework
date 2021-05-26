package com.servicenow.tests.incident;

import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;
import com.servicenow.base.BaseClass;
import com.servicenow.pages.incident.LoginPage;

public class SampleTest extends BaseClass{
  
  @BeforeTest
  public void setExceDataFile() {
    excelTestDataFile = "Login";
  }
  
  @Test(dataProvider = "setData")
  public void testLogin(String username, String password, String profileUser) {
    
    new LoginPage(webdriver.get())
    .switchToFrame()
    .enterUsername(username)
    .enterPassword(password)
    .clickLoginButton()
    .verifyUserLoggedIn(profileUser);
  }
}
