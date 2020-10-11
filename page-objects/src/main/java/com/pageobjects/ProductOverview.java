package com.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;
import static io.restassured.RestAssured.given;
import java.util.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.testng.Assert;

import com.framework.core.BasePage;

public class ProductOverview extends BasePage {

	public ProductOverview(WebDriver driver) {
		super(driver);
	}

	/**
	 * Selectors
	 */
	@FindBy(id = "tab-description")
	private WebElement productdescription;
	@FindBy(css = "h1")
	private WebElement productTitle;
	@FindBy(css = ".entry-summary p.price > span.woocommerce-Price-amount.amount")
	private WebElement productprice;
	@FindBy(className = "input-text")
	private WebElement quantitytxtfield;
	@FindBy(name = "add-to-cart")
	private WebElement addtochartbtn;
	@FindBy(linkText = "View cart")
	private WebElement viewcartbtn;
	@FindBy(css = "a.cart-contents > span.count")
	private WebElement cartCountlbl;
	@FindBy(css = ".cart-contents > span.woocommerce-Price-amount.amount")
	private WebElement subtotallbl;

	String quantity = "7";
	String basicUrl = "http://34.205.174.166/product/";

	/**
	 * Verify if the Product page is displayed and some assertions Returns a String
	 * array with
	 * 
	 * @param cartCount
	 * @param subtotal
	 */
	public String[] isThePageLoadedandProductDisplayed(String productName) throws InterruptedException {
		String url = basicUrl + productName;
		driver.get(url);
		driver.manage().window().maximize();
		waitForLoad(driver);
		assertTrue(productTitle.isDisplayed(), "Product Title is not displayed");
		assertTrue(productdescription.isDisplayed(), "Product Description is not displayed");
		assertTrue(productprice.isDisplayed(), "Product Price is not displayed");

		updateCartQuantity();

		assertTrue(cartCountlbl.getText().contains(quantity), "The quantity was not updated");

		viewcartbtn.click();

		String[] productSubtotalandCount = new String[2];
		productSubtotalandCount[0] = subtotallbl.getText();
		;
		productSubtotalandCount[1] = cartCountlbl.getText();
		return productSubtotalandCount;

	}

	/**
	 * Update the quantity for the product on the cart *
	 */
	private void updateCartQuantity() {
		System.out.println("Updating the quantity on the cart");
		quantitytxtfield.clear();
		quantitytxtfield.sendKeys(quantity);
		addtochartbtn.click();
		waitForLoad(driver);
	}
}
