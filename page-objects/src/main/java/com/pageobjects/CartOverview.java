package com.pageobjects;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.framework.core.BasePage;

public class CartOverview extends BasePage {
	
	@FindBy(css ="td.product-name > a:nth-child(1)")
	private WebElement productOnCartlbl;
	@FindBy(css =".input-text.qty.text")
	private WebElement quantityTxtField;
	@FindBy(css ="td.product-subtotal > span.woocommerce-Price-amount.amount")
	private WebElement productPricesubtotallbl;
	
	public CartOverview(WebDriver driver) {
		super(driver);
		// TODO Auto-generated constructor stub
	}

	public void validateProductAdded(String[] productInformation) {
		waitForLoad(driver);
		assertTrue(productOnCartlbl.isDisplayed(),"Product Title is not displayed");
		assertEquals(productPricesubtotallbl.getText(),productInformation[0]);
		assertEquals(quantityTxtField.getAttribute("value"),productInformation[1].replaceAll("[^0-9]", "")); 		
	}
	

}
