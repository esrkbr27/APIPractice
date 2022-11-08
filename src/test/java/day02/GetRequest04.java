package day02;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.List;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.*;

public class GetRequest04 {

    /*
    http://dummy.restapiexample.com/api/v1/employees  url’ine
GET request’i yolladigimda gelen response’un
status kodunun 200 ve content type’inin “application/json”
ve employees sayisinin 24
ve employee’lerden birinin “Ashton Cox”
ve gelen yaslar icinde 21, 61, ve 23 degerlerinden birinin oldugunu test edin.
     */

    @Test
    public void get04() {

      //set the  url
        String url=" http://dummy.restapiexample.com/api/v1/employees";

      //set the expected data

        //send the request
      Response response = given().when().get(url);

      //Do Assertion
        response.then().assertThat().statusCode(200).
                contentType(ContentType.JSON);

      //  body metodunda bulunan
        //  hasSize() methodu aradıgımız datadan istediğimiz yerde kaç tane oldugunu bize verir.
        //  hasItems() methodu aradıgımız dataların istenen yerde olup olmadıgını verir
        //hasItem() methodu aradıgımız datanın istenen yerde olup olmadıgını verir.

        response.then().assertThat().
                body("data.id",hasSize(24),
                        "data.name",hasItem("Ashton Cox"),
                        "data.employee_age",hasItems(21,61,23));

        JsonPath json = response.jsonPath();
        List<Integer> idList = json.getList("data.id");
        System.out.println(idList.size());



    }
}
