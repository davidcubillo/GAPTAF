package com.framework.tests;

import org.testng.annotations.Test;

import com.framework.core.BaseTest;
import com.pageobjects.CartOverview;
import com.pageobjects.ProductOverview;
import com.pageobjects.RestAssureAPIrequests;

public class TestPartTwo extends BaseTest {
	/**
	 * Creates a new product via API then add some products to the chart via API ,
	 * update the quantity and finally deletes the previously created product via
	 * API
	 * 
	 * @param productApiValues
	 * @param productSubtotalandCount
	 */
	@Test
	public void productOverviewTest() throws InterruptedException {
		//Classes instances
		ProductOverview productPage = new ProductOverview(getDriver());
		RestAssureAPIrequests apiRequest = new RestAssureAPIrequests();
		CartOverview cart = new CartOverview(getDriver());
		//Creating a new product
		String[] productApiValues = apiRequest.getResponseBodyForCreateProduct();
		//Verify if the product have the right information
		String[] productSubtotalandCount = productPage.isThePageLoadedandProductDisplayed(productApiValues[0]);
		//Validating if the product count and subtotal are ok
		cart.validateProductAdded(productSubtotalandCount);
		//Deleting the previously product added
		apiRequest.removeProductFromPage(productApiValues[1]);
	}
}
