package com.pageobjects;

import static io.restassured.RestAssured.given;
import static org.testng.Assert.assertEquals;

import java.util.*;

import io.restassured.RestAssured;
import io.restassured.builder.ResponseBuilder;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class RestAssureAPIrequests {
	static String url = "http://34.205.174.166/wp-json/wc/v3/products";
	String username = "shopmanager";
	String password = "axY2 rimc SzO9 cobf AZBw NLnX";

	/**
	 * Getting the JSON response with Rest Assure and returning the name and id for
	 * the new product created
	 * 
	 * @return apiValues
	 * @param name
	 * @param id
	 */

	public String[] getResponseBodyForCreateProduct() {
		Random random = new Random(System.currentTimeMillis());
		int intrandom = random.nextInt(1000);
		String aleatoryNumber = String.valueOf(intrandom);
		String body = "{\r\n" + "  \"name\": \"Quality" + aleatoryNumber + "\"," + "  \"type\": \"simple\",\r\n"
				+ "  \"regular_price\": \"21.99\",\r\n"
				+ "  \"description\": \"Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas. Vestibulum tortor quam, feugiat vitae, ultricies eget, tempor sit amet, ante. Donec eu libero sit amet quam egestas semper. Aenean ultricies mi vitae est. Mauris placerat eleifend leo.\",\r\n"
				+ "  \"short_description\": \"Pellentesque habitant morbi tristique senectus et netus et malesuada fames ac turpis egestas.\",\r\n"
				+ "  \"categories\": [\r\n" + "    {\r\n" + "      \"id\": 9\r\n" + "    },\r\n" + "    {\r\n"
				+ "      \"id\": 14\r\n" + "    }\r\n" + "  ],\r\n" + "  \"images\": [\r\n" + "    {\r\n"
				+ "      \"src\": \"http://demo.woothemes.com/woocommerce/wp-content/uploads/sites/56/2013/06/T_2_front.jpg\"\r\n"
				+ "    },\r\n" + "    {\r\n"
				+ "      \"src\": \"http://demo.woothemes.com/woocommerce/wp-content/uploads/sites/56/2013/06/T_2_back.jpg\"\r\n"
				+ "    }\r\n" + "  ]\r\n" + "}";

		Response res = given().auth().preemptive().basic(username, password)
				.header("Accept", ContentType.JSON.getAcceptHeader()).contentType(ContentType.JSON).body(body).post(url)
				.then().assertThat().contentType(ContentType.JSON).and().statusCode(201).extract().response();

		String name = res.jsonPath().get("name");
		String id = String.valueOf(res.jsonPath().get("id"));
		System.out.println("Product created with the name " + name);
		System.out.println("Product created with the id " + id);
		String[] apiValues = { name, id };
		return apiValues;

	}

	/**
	 * Removing the product via API and getting the status code from the JSON
	 * Response
	 * @return None
	 */
	public void removeProductFromPage(String productId) {

		RestAssured.baseURI = url;
		System.out.println("Removing the product");
		Response response = null;

		try {
			response = RestAssured.given().auth().preemptive().basic(username, password)
					.header("Accept", ContentType.JSON.getAcceptHeader()).header("consumer_key", "consumer_secret")
					.contentType(ContentType.JSON).delete("/" + productId + "?force=true");
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Response :" + response.asString());
		assertEquals(response.getStatusCode(), 200, "Status code is not right");
		System.out.println("The Status Code is correct :" + response.getStatusCode());
	}
}
