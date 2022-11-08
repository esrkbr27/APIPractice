package day02;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class GetRequest05 {

    @Test
    public void get05() {

        String url= "https://www.gmibank.com/api/tp-customers";
        String token="eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJiYXRjaDgxIiwiYXV0aCI6IlJPTEVfQURNSU4iLCJleHAiOjE2NzAwMDUyNzZ9.8xLf49oKFpFH3HOmvA6i9ql4hGVmRzFzo0h8JskXtj5FQm--Mjv5OMnGJtjTHQBipSGQHU-4xXhp7XZvEe49LQ";

        Response response =given().headers("Authorization","Bearer"+ token).when().get(url);
        response.prettyPrint();




    }
}
