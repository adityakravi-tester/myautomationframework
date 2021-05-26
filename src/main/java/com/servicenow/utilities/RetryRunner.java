package com.servicenow.utilities;

import org.testng.IRetryAnalyzer;
import org.testng.ITestResult;

public class RetryRunner implements IRetryAnalyzer{
  
  private int maxRetry = 1;
  private int retryCount = 0;
  public int getMaxRetry() {
    return maxRetry;
  }
  public void setMaxRetry(int maxRetry) {
    this.maxRetry = maxRetry;
  }
  @Override
  public boolean retry(ITestResult result) {
    if(!result.isSuccess() && retryCount < getMaxRetry()) {
      System.out.println("Inside retry analyzer");
      retryCount = retryCount +1;
      return true;
    }
    return false;
  }
}
