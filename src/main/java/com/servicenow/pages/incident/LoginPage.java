package com.servicenow.pages.incident;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;

import com.servicenow.base.BaseClass;
import com.servicenow.base.HomePage;

public class LoginPage extends BaseClass{
  
  public LoginPage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }
  
  @FindBy(how = How.ID, using = "user_name")
  private WebElement textUsername;
  
  @FindBy(how = How.ID, using = "user_password")
  private WebElement textPassword;
  
  @FindBy(how = How.ID, using = "sysverb_login")
  private WebElement buttonLogin;
  
  @FindBy(how = How.ID, using = "gsft_main")
  private WebElement frame;
  
  
  public LoginPage enterUsername(String username) {
    textUsername.sendKeys(username);
    return this;
  }
  
  public LoginPage enterPassword(String password) {
    textPassword.sendKeys(password);
    return this;
  }
  
  public HomePage clickLoginButton() {
    buttonLogin.click();
    return new HomePage(webdriver.get());
  }
  
  public LoginPage switchToFrame() {
    webdriver.get().switchTo().frame(frame);
    return this;
  }

}
