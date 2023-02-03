//********************************************************
//Author_Name : Sheetal_Chaudhari
//Project     : Json_Server_Api_Automation
//Date        : 01/01/2023
//********************************************************

package com.bridgelabz.qa.api_automation;

import java.util.concurrent.TimeUnit;

import org.hamcrest.Matchers;
import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Json_Server_ApiAutomationTest {

	@Test (priority = 4)
	public void create_post() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("id",3);
		json.put("title", "wings of fire");
		json.put("author","Arun tiwari" );
		requestSpecification.body(json.toJSONString());
		Response response = requestSpecification.post("http://localhost:3000/posts");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
	}

	@Test(priority = 6)

	public void get_post() {

		Response response = RestAssured.get("http://localhost:3000/posts/3");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		response.then().assertThat().body("author", Matchers.equalTo("Arun Tiwari"));
		System.out.println("Response Time of get_post in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));


	}

	@Test(priority = 5)

	public void update_post() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("id",3);
		json.put("title", "wings of fire- APJ Abdul kalam ji");
		json.put("author","Arun tiwari" );
		requestSpecification.body(json.toJSONString());
		Response response = requestSpecification.put("http://localhost:3000/posts/3");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of update_post in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 7)

	public void delete_post() {

		Response response = RestAssured.delete("http://localhost:3000/posts/4");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of delete_post in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));


	}

	@Test(priority = 8)

	public void create_comments() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("id",3);
		json.put("body", "grate book Arun ji on ABJ Abdul Kalam Sir");
		json.put("postId",3);
		requestSpecification.body(json.toJSONString());
		Response response = requestSpecification.post("http://localhost:3000/comments");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
		response.then().assertThat().log().all();
		System.out.println("Response Time of create_comments in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}
	@Test(priority = 10)

	public void get_comments() {

		Response response = RestAssured.get("http://localhost:3000/comments");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of get_comments in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 9)

	public void update_comment() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("id",3);
		json.put("body", "grate book Arun Tiwari ji on ABJ Abdul Kalam Sir");
		json.put("postId",3);
		requestSpecification.body(json.toJSONString());
		Response response = requestSpecification.put("http://localhost:3000/comments/3");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of update_comment in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));


	}

	@Test(priority = 11)

	public void delete_comment() {

		Response response = RestAssured.delete("http://localhost:3000/comments/3");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of delete_comment in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));


	}

	@Test(priority = 1)

	public void create_profile() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("name","sandeep singh");
		requestSpecification.body(json.toJSONString());
		Response response = requestSpecification.post("http://localhost:3000/profile");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(201);
		response.then().assertThat().log().all();
		System.out.println("Response Time of create_profile in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test(priority = 3)

	public void get_profile() {

		Response response = RestAssured.get("http://localhost:3000/profile");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of get_profile in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test (priority = 2)

	public void update_profile() {
		RequestSpecification requestSpecification = RestAssured.given();
		requestSpecification.header("Content-Type","application/json");
		JSONObject json = new JSONObject();
		json.put("name","Abhishek Kumar Mishra");
		requestSpecification.body(json.toJSONString());
		Response response = requestSpecification.put("http://localhost:3000/profile");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of update_profile in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));

	}

	@Test

	public void get_all_comments_for_perticular_post() {

		Response response = RestAssured.get("http://localhost:3000/comments/?postId=1");
		System.out.println("Response code : " + response.getStatusCode());
		response.prettyPrint();
		response.then().assertThat().statusCode(200);
		response.then().assertThat().log().all();
		System.out.println("Response Time of get_all_comments_for_perticular_post in ms = "+ response.timeIn(TimeUnit.MICROSECONDS));


	}

}

