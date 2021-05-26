package com.servicenow.pages.incident;

import java.time.Duration;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicenow.base.HomePage;

public class ViewIncidentPage extends HomePage{
  
  public ViewIncidentPage(WebDriver driver) {
    super(driver);
  }
  
  @FindBy(how =How.XPATH, using ="//*[@id='incident.urgency']")
  private WebElement dropdownUrgency;

  @FindBy(how =How.XPATH, using ="//*[@id='incident.state']")
  private WebElement dropdownState;

  @FindBy(how =How.XPATH, using ="//*[@id='sysverb_update_bottom']")
  private WebElement buttonUpdate;
  
  @FindBy(how =How.XPATH, using ="//*[@id='lookup.incident.assignment_group']")
  private WebElement buttonAssigneeGroup;
  
  @FindBy(how =How.XPATH, using ="//*[@id='lookup.incident.assigned_to']")
  private WebElement buttonAssigneeUser;
  
  @FindBy(how =How.XPATH, using ="//*[@id='activity-stream-textarea']")
  private WebElement textareaWorkNotes;
  
  @FindBy(how =How.XPATH, using ="//*[@class='tab_caption_text' and text()='Resolution Information']")
  private WebElement tabstripResolutionInfo;
  
  @FindBy(how =How.XPATH, using ="//*[@id='incident.close_code']")
  private WebElement dropdownResolutionCode;
  
  @FindBy(how =How.XPATH, using ="//*[@id='incident.close_notes']")
  private WebElement textareaResolutionNotes;
  
  @FindBy(how =How.XPATH, using ="//*[@id='resolve_incident_bottom']")
  private WebElement buttonResolve;
  
  @FindBy(how =How.XPATH, using ="//*[@id='sysverb_delete']")
  private WebElement buttonDelete;
  
  @FindBy(how =How.XPATH, using ="//*[@id='ok_button']")
  private WebElement buttonConfirmDelete;

  public ViewIncidentPage selectUrgency(String urgency) {
    new Select(dropdownUrgency).selectByVisibleText(urgency);
    return this;
  }

  public ViewIncidentPage selectState(String state) {
    new Select(dropdownState).selectByVisibleText(state);
    return this;
  }
  
  public GroupsPage clickAssigneeGroupButton() {
    buttonAssigneeGroup.click();
    switchToWindow(2);
    return new GroupsPage(webdriver.get());
  }
  
  public UsersPage clickAssigneeUserButton() {
    switchToFrame();
    buttonAssigneeUser.click();
    switchToWindow(2);
    return new UsersPage(webdriver.get());
  }
  
  public ViewIncidentPage enterWorkNotes(String workNotes) {
    switchToFrame();
    textareaWorkNotes.sendKeys(workNotes);
    return this;
  }
  
  public IncidentsPage clickUpdateButton() {
    buttonUpdate.click();
    return new IncidentsPage(webdriver.get());
  }
  
  public ViewIncidentPage clickOnResolutionInformationTab() {
    tabstripResolutionInfo.click();
    return this;
  }
  
  public ViewIncidentPage selectResolutionCode(String resolutionCode) {
    new Select(dropdownResolutionCode).selectByVisibleText(resolutionCode);
    return this;
  }
  
  public ViewIncidentPage enterResolutionNotes(String resolutionNotes) {
    textareaResolutionNotes.sendKeys(resolutionNotes);
    return this;
  }
  
  public IncidentsPage clickResolveButton() {
    buttonResolve.click();
    return new IncidentsPage(webdriver.get());
  }
  
  public IncidentsPage deleteIncident() {
    buttonDelete.click();
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(5)).until(ExpectedConditions.elementToBeClickable(buttonConfirmDelete)).click();
    return new IncidentsPage(webdriver.get());
  }
}
