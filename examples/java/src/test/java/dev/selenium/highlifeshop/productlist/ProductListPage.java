/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.selenium.highlifeshop.productlist;

import java.time.Duration;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

/**
 *
 * @author ivano
 */
public class ProductListPage {

  protected WebDriver driver;
  private WebElement sortingOrderLink;

  public ProductListPage(WebDriver driver){
    this.driver = driver;
    driver.get("https://highlifeshop.com/speedbird-cafe");
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    sortingOrderLink =  driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div/div[1]/div[6]/div[4]/a"));
  }

  public void changeSortingOrder() {
    JavascriptExecutor js = (JavascriptExecutor)driver;
    js.executeScript("arguments[0].click()", sortingOrderLink);
    driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
    sortingOrderLink =  driver.findElement(By.xpath("//*[@id=\"maincontent\"]/div/div[1]/div[6]/div[4]/a"));
  }

  public void selectValueForSorting(String sortingCriteria) {
    Select dropdown = new Select(driver.findElement(By.id("sorter")));
    dropdown.selectByValue(sortingCriteria);
  }

  public WebElement getSortingOrderLink() {
    return sortingOrderLink;
  }
  
  public List<WebElement> getProductList() {
    WebElement productListContainer = driver.findElement(By.cssSelector(".products.list.items.product-items"));
    return productListContainer.findElements(By.tagName("li"));

  }

}
