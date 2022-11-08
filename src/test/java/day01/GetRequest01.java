package day01;

import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import jdk.jfr.StackTrace;
import org.junit.Assert;
import org.junit.Test;

import static io.restassured.RestAssured.*;

public class GetRequest01 {

    @Test
    public void get01() {
        //set the url
        String url= "https://restful-booker.herokuapp.com/booking";


        //send the request
        //given().when().get(url); -->endpointe göndermek için request oluşturduk
        // Response respomse -->apı dan bize dönen response
        Response response =given().when().get(url);
        Response response1 =given().auth().basic("user","password").when().get(url);

     //   response.prettyPrint();
    //    response.prettyPeek(); //header altındakı degerlerı de yazdırıyor
        response.peek(); //responsumu stringler olarak döndürüyor

        System.out.println("response.statusCode() = " + response.statusCode());
        System.out.println("response.contentType() = " + response.contentType());
        System.out.println("response.statusLine() = " + response.statusLine());

        //Do Assertion
        //1. yol Junit Assertion ile
        Assert.assertEquals("KODHATALI",200,response.getStatusCode());
        //Junit Assertionları ile API testleri yaparız

        Assert.assertEquals("HTTP/1.1 200 OK",response.getStatusLine());
        Assert.assertEquals("application/json; charset=utf-8",response.getContentType());

        //2.yol asserThat ile
        response.then().assertThat().contentType(ContentType.JSON).
                statusCode(200).statusLine("HTTP/1.1 200 OK");


    }




}
