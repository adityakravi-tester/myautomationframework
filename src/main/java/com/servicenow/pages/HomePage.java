package com.servicenow.pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchWindowException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicenow.base.BaseClass;
import org.testng.Assert;

public class HomePage extends BaseClass{

  public HomePage(WebDriver driver) {
    PageFactory.initElements(driver, this);
  }

  @FindBy(how = How.XPATH, using = "//*[@class='user-name hidden-xs hidden-sm hidden-md']")
  private WebElement userProfileIdentity;

  @FindBy(how = How.ID, using = "filter")
  private WebElement textboxFilterType;

  @FindBy(how = How.ID, using = "gsft_main")
  private WebElement frame;

  @FindBy(how = How.XPATH, using = "(//*[@class='sn-widget-list-title' and text()='Incidents'])[1]")
  private WebElement linkIncidents;

  @FindBy(how = How.XPATH, using = "(//*[@class='sn-widget-list-title' and text()='Open'])[1]")
  private WebElement linkOpen;

  public HomePage verifyUserLoggedIn(String profileUser) {
    Assert.assertEquals(profileUser, "System Administrator");
    return this;
  }

  public HomePage enterIncidentInFilter(String filterType) {
    textboxFilterType.sendKeys(filterType + Keys.ENTER);
    return this;
  }

  public HomePage clickIncidentsLink() {
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(linkIncidents)).click();
    return this;
  }

  public HomePage clickOpenLink() {
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(linkOpen)).click();
    return this;
  }

  public IncidentsPage switchToFrame() {
    webdriver.get().switchTo().frame(frame);
    return new IncidentsPage(webdriver.get());
  }

  public HomePage switchToWindow(int windowNumber) {
    List<String> windowHandles = null;
    try {
      windowHandles = new ArrayList<>(webdriver.get().getWindowHandles());
      webdriver.get().switchTo().window(windowHandles.get(windowNumber -1));
    } catch (NoSuchWindowException e) {
      System.err.println("Sorry there is no such window number. Total windows open is: "+ windowHandles.size() + ". You asked for window number: " + windowNumber);
    } catch (Exception e) {
      System.err.println("Something unexpected happened when handling windows !!!");
    }
    return this;
  }
}
