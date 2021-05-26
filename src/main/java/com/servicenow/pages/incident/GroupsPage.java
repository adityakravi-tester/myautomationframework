package com.servicenow.pages.incident;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import com.servicenow.base.HomePage;

public class GroupsPage extends HomePage{

  public GroupsPage(WebDriver driver) {
    super(driver);
  }
  
  @FindBy(how =How.XPATH, using ="//*[@class='glide_ref_item_link']")
  private List <WebElement> listOfGroups;
  
  public ViewIncidentPage selectAssigneeGroup(String assigneeGroup) throws InterruptedException {
    Thread.sleep(3000);
    
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(5))
        .until(ExpectedConditions.elementToBeClickable(webdriver.get().findElement(By.xpath("//*[@class='glide_ref_item_link' and text()='"+assigneeGroup+"']")))).click();
    /*
     * System.out.println("The size of groups" + listOfGroups.size()); for(int i =
     * 0; i < listOfGroups.size(); i++) {
     * if(listOfGroups.get(i).getText().equals(assigneeGroup)) {
     * listOfGroups.get(i).click(); break; } }
     */
    switchToWindow(1);
    return new ViewIncidentPage(webdriver.get());
  }
  
  public GroupsPage searchGroup(String assigneeGroup) {
    new IncidentsPage(webdriver.get()).searchGivenParameter(assigneeGroup);
    return this;
  }
}
