package day03;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.GMIBankBaseURL;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class GetRequest07 extends GMIBankBaseURL {
    /*
    http://www.gmibank.com/api/tp-customers/110472 adresindeki müşteri bilgilerini doğrulayın
            “firstName”: “Melva”,
            “lastName”: “Bernhard”,
            “email”: “chas.kuhlman@yahoo.com”
            “zipCode”: “40207"
            “country” “name”: “San Jose”
            “login”: “delilah.metz”
     */


    @Test
    public void get07() {

        //set the url
        spec.pathParams("first","tp-customers","second",110472 );

        //set the expected data


        //send the request get the response
        //GMIBankBaseURL in asagıdakı sorguyu yapabilmesi için authenticate e ihtiyac vardır.
        //Tek bir classa sadece extends yapabilecegimiz için Base url classını Authentication classına extends
        //yaparak bu classtakı metotlardan da faydalanmış olduk
        //Bearer yanında bir bosluk olmalı yoksa hata verir
      Response response=  given().spec(spec).header("Authorization", "Bearer "+generateToken()).
              when().get("/{first}/{second}");

      response.prettyPrint();

      //Matchers ile doğrulama (Matchers classdakı equalTo metodunu import ettik
        response.then().assertThat().body("firstName",equalTo("Melva"),
                "lastName",equalTo("Bernhard"),
                "email",equalTo("chas.kuhlman@yahoo.com"),
                "zipCode",equalTo("40207"),
                "country.name",equalTo("San Jose"),
                "login",equalTo(null));

        //JSONpATH İLE

        JsonPath json=response.jsonPath();

        assertEquals("Melva",json.getString("firstName"));
        assertEquals("Bernhard",json.getString("lastName"));
        assertEquals("chas.kuhlman@yahoo.com",json.getString("email"));
        assertEquals("40207",json.getString("zipCode"));
        assertEquals("San Jose",json.getString("country.name"));
        assertNull(json.getString("login"));

    }
}
