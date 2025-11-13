package com.project.tests;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static io.restassured.RestAssured.*;

public class ApiTests {

    @BeforeClass
    public void setup() {
        RestAssured.baseURI = "https://reqres.in";
    }

    @Test(groups = {"API"})
    public void createUser_Post_Returns201_and_containsNameJobIdCreatedAt() {
        String reqBody = "{\"name\": \"Alex Fletcher\", \"job\": \"QA Lead\"}";

        Response res = given().contentType(ContentType.JSON).body(reqBody).when().post("/api/users").then().extract().response();

        Assert.assertEquals(res.getStatusCode(), 201);
        Assert.assertEquals(res.jsonPath().getString("name"), "Alex Fletcher");
        Assert.assertEquals(res.jsonPath().getString("job"), "QA Lead");
        Assert.assertNotNull(res.jsonPath().getString("id"), "id should be present");
        Assert.assertNotNull(res.jsonPath().getString("createdAt"), "createdAt should be present");
    }

    @Test(groups = {"API"})
    public void getUser_GetUser2_Returns200_and_nameJanet() {
        Response res = given().when().get("/api/users/2").then().extract().response();

        Assert.assertEquals(res.getStatusCode(), 200);
        Assert.assertEquals(res.jsonPath().getInt("data.id"), 2);
        Assert.assertEquals(res.jsonPath().getString("data.first_name"), "Janet");
    }
}
