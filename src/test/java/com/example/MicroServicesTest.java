package com.example;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static io.restassured.RestAssured.*;
import static org.hamcrest.Matchers.*;

public class MicroservicesAPITests {

    @BeforeAll
    public static void setup() {
        // Set up the base URI and port for each service
        RestAssured.baseURI = "http://localhost";
    }

    @Test
    public void testOrderServiceGetOrder() {
        given()
                .port(8081) // Port for Order Service
                .when()
                .get("/orders/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1));
    }

    @Test
    public void testOrderServiceCreateOrder() {
        String orderJson = "{\"productId\": 1, \"quantity\": 2}";

        given()
                .port(8081) // Port for Order Service
                .contentType(ContentType.JSON)
                .body(orderJson)
                .when()
                .post("/orders")
                .then()
                .statusCode(201)
                .body("message", equalTo("Order created successfully"));
    }

    @Test
    public void testProductServiceGetProduct() {
        given()
                .port(8082) // Port for Product Service
                .when()
                .get("/products/1")
                .then()
                .statusCode(200)
                .contentType(ContentType.JSON)
                .body("id", equalTo(1));
    }

    @Test
    public void testProductServiceCreateProduct() {
        String productJson = "{\"name\": \"Laptop\", \"price\": 1500}";

        given()
                .port(8082) // Port for Product Service
                .contentType(ContentType.JSON)
                .body(productJson)
                .when()
                .post("/products")
                .then()
                .statusCode(201)
                .body("message", equalTo("Product created successfully"));
    }

    @Test
    public void testFileServiceUploadFile() {
        given()
                .port(8083) // Port for File Service
                .multiPart("file", "testfile.txt", "This is a test file".getBytes())
                .when()
                .post("/files/upload")
                .then()
                .statusCode(200)
                .body("message", equalTo("File uploaded successfully"));
    }

    @Test
    public void testFileServiceDownloadFile() {
        given()
                .port(8083) // Port for File Service
                .when()
                .get("/files/download/testfile.txt")
                .then()
                .statusCode(200)
                .contentType(ContentType.TEXT)
                .body(equalTo("This is a test file"));
    }
}

