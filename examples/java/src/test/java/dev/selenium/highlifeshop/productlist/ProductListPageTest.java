/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dev.selenium.highlifeshop.productlist;

import io.github.bonigarcia.wdm.WebDriverManager;
import java.util.List;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

/**
 *
 * @author ivano
 */
public class ProductListPageTest {

    private static ChromeDriver driver;
    private static ProductListPage page;

    @BeforeAll
    public static void beforeAll() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        page = new ProductListPage(driver);
    } 

    @Test
    public void changeSortingOrderTest() {        
        Assertions.assertEquals("desc", page.getSortingOrderLink().getAttribute("data-value"));
        page.changeSortingOrder();
        Assertions.assertEquals("asc", page.getSortingOrderLink().getAttribute("data-value"));
    }

    @Test
    public void checkPriceSortingTest() {
        page.selectValueForSorting("price");
        List<WebElement> products = page.getProductList();
        for(int i = 0; i < products.size() - 1; i++) {
            WebElement p1 = products.get(i);
            WebElement p2 = products.get(i + 1);
            int p1Id = Integer.parseInt(p1.getAttribute("data-product-id"));
            int p2Id = Integer.parseInt(p2.getAttribute("data-product-id"));
            float price1, price2;
            try {
                price1 = Float.parseFloat(p1.findElement(By.id("product-price-" + p1Id)).getAttribute("data-price-amount"));
            } catch (NoSuchElementException e) {
                price1 = Float.parseFloat(p1.findElement(By.id("from-" + p1Id)).getAttribute("data-price-amount"));
            }
            try {
                price2 = Float.parseFloat(p2.findElement(By.id("product-price-" + p2Id)).getAttribute("data-price-amount"));
            } catch (NoSuchElementException e) {
                price2 = Float.parseFloat(p2.findElement(By.id("from-" + p2Id)).getAttribute("data-price-amount"));
            }
            Assertions.assertTrue(price1 >= price2, "price1 was : " + price1 + " but price2 was: " + price2);
        }
    }

    @AfterAll
    public static void afterAll() {
        driver.quit();
    } 
}
