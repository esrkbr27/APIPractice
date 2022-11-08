package day02;

import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import org.junit.Test;
import utilities.Authentication;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertEquals;

public class GetRequest06 extends Authentication {

    @Test
    public void get6() {
        /*
          "id": 114351,
    "firstName": "Della",
    "lastName": "Heaney",
    "middleInitial": "Russell Homenick V",
    "email": "ricardo.larkin@yahoo.com",
    "mobilePhoneNumber": "123-456-7893",
    "phoneNumber": "213-456-7893",
    "zipCode": "53081",
    "address": "75164 McClure Stream",
    "city": "Gislasonburgh",
    "ssn": "823-25-7239",
    "createDate": "2021-12-05T23:00:00Z",
    "zelleEnrolled": true,
    "country": {
        "id": 3,
        "name": "USA",
        "states": null
    },
         */

        String url = "https://www.gmibank.com/api/tp-customers/114351";

        Response response = given().headers("Authorization", "Bearer " + generateToken()).when().get(url);

        response.prettyPrint();

        //Matchers class ile müşteri bilgilerini doğrulayın

        response.then().assertThat().body("firstName",equalTo("Della"),
                                              "lastName",equalTo("Heaney"),
                                                    "email",equalTo("ricardo.larkin@yahoo.com"),
                                                     "mobilePhoneNumber",equalTo("123-456-7893"),
                                                     "accounts[0].balance",equalTo(69700),
                                                     "accounts[0]accountType",equalTo("CREDIT_CARD"),
                                                     "accounts[1].balance",equalTo(    11190),
                                                     "accounts[1]accountType",equalTo("CHECKING"));




        //Jsonpath ile müşteri bilgilerini doğrulayın
          JsonPath json=response.jsonPath();

          assertEquals("Della",json.getString("firstName"));
          assertEquals ("Heaney",json.getString("lastName"));
          assertEquals("ricardo.larkin@yahoo.com","email");
          assertEquals("123-456-7893",json.getString("mobilePhoneNumber"));



    }

}