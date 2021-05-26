package com.servicenow.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

public class IncidentsPage extends HomePage{

  public IncidentsPage(WebDriver driver) {
    super(driver);
  }

  @FindBy(how = How.ID, using = "sysverb_new")
  private WebElement buttonNewIncident;

  @FindBy(how =How.XPATH, using = "//*[@class='form-control default-focus-outline']")
  private WebElement dropdownSearchParam;

  @FindBy(how =How.XPATH, using = "//*[@placeholder='Search' and @class='form-control']")
  private WebElement textboxSearch;

  @FindBy(how =How.XPATH, using = "//*[@class='vt']/a")
  private WebElement incidentNumberLink;

  @FindBy(how = How.XPATH, using = "//*[@class='list2_no_records']/td")
  private WebElement textNoRecordsMessage;

  public CreateIncidentPage clickNewIncidentButton() {
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(10)).until(ExpectedConditions.elementToBeClickable(buttonNewIncident)).click();
    return new CreateIncidentPage(webdriver.get());
  }

  public IncidentsPage verifyIncidentNumber(String incidentNumber) {
    textboxSearch.sendKeys(incidentNumber + Keys.ENTER);
    Assert.assertEquals(incidentNumber, incidentNumberLink.getText(), "The incident is not created");
    return this;
  }

  public IncidentsPage selectSearchParameter(String searchParam) {
    new Select(dropdownSearchParam).selectByVisibleText(searchParam);
    return this;
  }

  public IncidentsPage searchGivenParameter(String incidentNumber) {
    textboxSearch.sendKeys(incidentNumber + Keys.ENTER);
    return this;
  }

  public ViewIncidentPage clickSearchedIncident() {
    try {
      incidentNumberLink.click();
    } catch (NoSuchElementException e) {
      System.err.println("-----\n-----\nThe element is not present on the page. Ending execution !!!");
      System.exit(1);
    }
    return new ViewIncidentPage(webdriver.get());
  }

  public IncidentsPage verifyUrgencyAndStateSelected() {
    int priorityColumnNo = getColumnNumberBasedOnHeader("Priority");
    int stateColumnNo = getColumnNumberBasedOnHeader("State");
    String actualPriority = webdriver.get().findElement(By.xpath("(//*[@id='incident_table']//tr)[3]/td["+priorityColumnNo+"]")).getText();
    String actualState =webdriver.get().findElement(By.xpath("(//*[@id='incident_table']//tr)[3]/td["+stateColumnNo+"]")).getText();
    Assert.assertEquals(actualPriority.replaceAll("[^a-zA-Z]", ""), "Moderate", "Priority not matching");
    Assert.assertEquals(actualState, "In Progress", "States not matching");
    return this;
  }

  public IncidentsPage verifyAssignedGroupAndUser(String expectedGroup, String expectedUser) {
    int assignedGroupColumnNo = getColumnNumberBasedOnHeader("Assignment group");
    int assignedUserColumnNo = getColumnNumberBasedOnHeader("Assigned to");
    String actualGroup = webdriver.get().findElement(By.xpath("(//*[@id='incident_table']//tr)[3]/td["+assignedGroupColumnNo+"]")).getText();
    String actualUser =webdriver.get().findElement(By.xpath("(//*[@id='incident_table']//tr)[3]/td["+assignedUserColumnNo+"]")).getText();
    Assert.assertEquals(actualGroup, expectedGroup, "Priority not matching");
    Assert.assertEquals(actualUser, expectedUser, "States not matching");
    return this;
  }

  public IncidentsPage verifyIncidentState(String expectedState) {
    int stateColumnNo = getColumnNumberBasedOnHeader("State");
    String actualState = webdriver.get().findElement(By.xpath("(//*[@id='incident_table']//tr)[3]/td["+stateColumnNo+"]")).getText();
    Assert.assertEquals(actualState, expectedState, "States are not matching");
    return this;
  }

  public IncidentsPage verifyIncidentDeleted() {
    Assert.assertEquals(textNoRecordsMessage.getText(), "No records to display");
    return this;
  }

  public int getColumnNumberBasedOnHeader(String headerName) {
    String headerValue = "";
    int columnNumber = 0;
    int cols = webdriver.get().findElements(By.xpath("(//*[@id='incident_table']//tr)[1]/th")).size();
    for(int i=3; i <= cols; i++) {
      headerValue = webdriver.get().findElement(By.xpath("((//*[@id='incident_table']//tr)[1]/th["+i+"]//a)[1]")).getText();
      if(headerValue.equals(headerName) || headerValue.contains(headerName)) {
        columnNumber = i;
        break;
      }
    }
    return columnNumber;
  }
}
