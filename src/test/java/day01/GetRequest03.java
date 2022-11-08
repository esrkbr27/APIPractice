package day01;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest03 {


  /* adresine gidip  https://reqres.in/api/users/3
    asagıdakı doğrulamaları yapın
    /*
    Matchers ile doğrulama yapalım
      {
    "data": {
        "id": 3,
        "email": "emma.wong@reqres.in",
        "first_name": "Emma",
        "last_name": "Wong",
        "avatar": null
    },

   */
    @Test
    public void get01() {
        //1.set the url
 //      String url="https://reqres.in/api/users/3";

 //      //set the expected data

 //      //send the request

 //      Response response=given().when().get(url);

 //      //Matchers ile doğrulama
 //    response.then().body("data.id",equalTo(3));
 //    response.then().body("data.email",equalTo("emma.wong@reqres.in"));
 //    response.then().body("data.first_name",equalTo("Emma"));
 //    response.then().body("data.last_name",equalTo("Wong"));
 //    response.then().body("avatar",equalTo(null));
 //    response.then().body("data.email",equalTo("emma.wong@reqres.in"));

      /*


      {

      //    responseun asagıdakı sekılde döndüğünü doğrulayın
    "data": {
        "id": 5,
        "email": "charles.morris@reqres.in",
        "first_name": "Charles",
        "last_name": "Morris",
        "avatar": "https://reqres.in/img/faces/5-image.jpg"
    },
    "support": {
        "url": "https://reqres.in/#support-heading",
        "text": "To keep ReqRes free, contributions towards server costs are appreciated!"
    }
}
       */


        String url1="https://reqres.in/api/users/5";

        //set the expected data
        Map<String,String> datamap=new HashMap<String,String>(Map.of("email","charles.morris@reqres.in",
                                                                     "first_name","Charles","last_name","Morris",
                                                                      "avatar","https://reqres.in/img/faces/5-image.jpg"));

        System.out.println("datamap:"+datamap);

        Map<String,String> supprtmap=new TreeMap<String,String>(Map.of("url","https://reqres.in/#support-heading",
                                                                        "text","To keep ReqRes free, contributions towards server costs are appreciated!"));

        System.out.println("supprtmap:"+ supprtmap);

        Map<String,Object> expectedmap=new HashMap<>(Map.of("data",datamap,"support",supprtmap));

        System.out.println("expectedmap:"+expectedmap);

        Response response=given().when().get(url1);
        JsonPath json=response.jsonPath();
        json.getMap("data");
        json.getMap("support");

        assertEquals(((Map)expectedmap.get("data")).get("first_name"),json.getMap("data").get("first_name") );
        assertEquals(((Map)expectedmap.get("data")).get("last_name"),json.getMap("data").get("last_name") );
        assertEquals(((Map)expectedmap.get("data")).get("email"),json.getMap("data").get("email") );
        assertEquals(((Map)expectedmap.get("data")).get("avatar"),json.getMap("data").get("avatar") );
        assertEquals(((Map)expectedmap.get("support")).get("url"),json.getMap("support").get("url") );
        assertEquals(((Map)expectedmap.get("support")).get("text"),json.getMap("support").get("text") );

    }
}
