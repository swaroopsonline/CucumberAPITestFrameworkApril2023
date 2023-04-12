package com.swa.APITestingFramework.cuke.apis;

import static io.restassured.RestAssured.given;

import java.util.ArrayList;

import com.swa.APITestingFramework.cuke.pojo.Orders;
import com.swa.APITestingFramework.cuke.pojo.PurchaseUnits;
import com.swa.APITestingFramework.cuke.base.BaseTest;

import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class OrderAPI extends BaseTest {
	
	
	
	static String orderId;
	
	public static String getAccessToken() {
		
		
		String access_token = given().param("grant_type", "client_credentials")
				.auth().preemptive()
				.basic(client_id, secret)
				.post("/v1/oauth2/token")
				.jsonPath()
				.get("access_token").toString();
		
		return access_token;
		
	}
	
	public static Response createOrder(String currency_code, String currency_value) {
		
		ArrayList<PurchaseUnits> list = new ArrayList<PurchaseUnits>();
		list.add(new PurchaseUnits(currency_code,currency_value));
		Orders order = new Orders("CAPTURE",list);
		
		Response response = given()
		.contentType(ContentType.JSON)
		.auth().oauth2(getAccessToken())
		.body(order)
		.post("/v2/checkout/orders");
		
		orderId = response.jsonPath().get("id").toString();
		
		return response;
		
	}
	
	public static Response getOrder() {		
		
		System.out.println("Order id is : "+orderId);
		Response response = given()
		.contentType(ContentType.JSON)
		.auth().oauth2(getAccessToken())
		
		.get("/v2/checkout/orders"+"/"+orderId);
		
		return response;
		
	}
	

}
