/**
 * This class can has methods which are duplicated from GroupsPage. We can put them in a common class.
 */

package com.servicenow.pages;

import java.time.Duration;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class UsersPage extends HomePage{

  public UsersPage(WebDriver driver) {
    super(driver);
  }
  
  public ViewIncidentPage selectUser(String assigneeUser) throws InterruptedException {
    Thread.sleep(3000);
    
    new WebDriverWait(webdriver.get(), Duration.ofSeconds(5))
        .until(ExpectedConditions.elementToBeClickable(webdriver.get().findElement(By.xpath("//*[@class='glide_ref_item_link' and text()='"+assigneeUser+"']")))).click();
  
    switchToWindow(1);
    return new ViewIncidentPage(webdriver.get());
  }
  
  public UsersPage searchUsers(String assigneeUser) {
    new IncidentsPage(webdriver.get()).searchGivenParameter(assigneeUser);
    return this;
  }
}
