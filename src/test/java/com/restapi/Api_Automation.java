package com.restapi;

import io.restassured.response.Response;
import org.testng.annotations.Test;

import java.io.File;

import static io.restassured.RestAssured.given;

public class Api_Automation {

    @Test(priority = 0)
    public void createUser(){
        Response res = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 121,\n" +
                        "  \"username\": \"Rohan\",\n" +
                        "  \"firstName\": \"Rohan\",\n" +
                        "  \"lastName\": \"Dute\",\n" +
                        "  \"email\": \"rohan@gmail.com\",\n" +
                        "  \"password\": \"Rohan\",\n" +
                        "  \"phone\": \"9011824044\",\n" +
                        "  \"userStatus\": 1\n" +
                        "}")
                .when()
                .post("https://petstore.swagger.io/v2/user\n");
        res.prettyPrint();
        res.then().assertThat().statusCode(200);

    }

    @Test(priority = 1)
    public void loginUser(){
        Response response = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .pathParams("username","Rohan")
                .pathParams("password", "Rohan")
                .when()
                .get("https://petstore.swagger.io/v2/user/login?username={username}&password={password}");
        response.prettyPrint();
    }

    @Test(priority = 2)
    public void updateUser(){
        Response response1 = given()
                .header("accept", "application/json")
                .header("Content-Type", "application/json")
                .body("{\n" +
                        "  \"id\": 121,\n" +
                        "  \"username\": \"Rohan\",\n" +
                        "  \"firstName\": \"Rohan\",\n" +
                        "  \"lastName\": \"Dute\",\n" +
                        "  \"email\": \"rohandute@gmail.com\",\n" +
                        "  \"password\": \"Rohan\",\n" +
                        "  \"phone\": \"7894561258\",\n" +
                        "  \"userStatus\": 1\n" +
                        "}")
                .when()
                .put("https://petstore.swagger.io/v2/user/Rohan");
        response1.prettyPrint();
    }
    @Test(priority = 3)
    public void deleteUser()
    {
        Response response=given()
                .header("accept","application/json")
                .when()
                .delete("https://petstore.swagger.io/v2/user/Rohan");
        response.prettyPrint();
    }

    @Test(priority = 4)
    public void uploadImage()
    {
        File file=new File("C:\\Users\\hp\\Postman\\files\\dog-puppy-on-garden-royalty-free-image-1586966191.jpg");
        Response response=given()
                .header("accept","application/json")
                .header("Content-Type","multipart/form-data")
                .multiPart(file)
                .when()
                .post("https://petstore.swagger.io/v2/pet/852/uploadImage");
        response.prettyPrint();
        response.then().assertThat().statusCode(200);
    }

    @Test(priority = 5)
    public void getPets()
    {
        Response response=given()
                .header("accept"," application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/findByStatus?status=available");

        response.prettyPrint();
        response.then().assertThat().statusCode(200);

    }


    @Test(priority = 6)
    public void getPetById()
    {
        Response response=given()
                .header("accept"," application/json")
                .when()
                .get("https://petstore.swagger.io/v2/pet/9223372036854714294");
        response.prettyPrint();
//        response.then().assertThat().statusCode(200);
    }
}


