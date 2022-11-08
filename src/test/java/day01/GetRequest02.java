package day01;

import io.restassured.response.Response;
import org.junit.Test;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.equalTo;

public class GetRequest02 {
    @Test
    public void get02() {
        //set the url
        String url="https://reqres.in/api/users";

        //set the expected Data

        //send the request
        Response response=given().when().get(url);
    //    response.prettyPrint();
        response.prettyPeek();

        //Header Test

       response.then().assertThat().statusLine("HTTP/1.1 200 OK").statusCode(200).
               contentType("application/json; charset=utf-8");

       response.then().assertThat().header("Server", "cloudflare");

       //Body Test

        /*
        iid si 1 olanın asagıdakı formatta olup olmadıgını test edin

         {
            "id": 1,
            "email": "george.bluth@reqres.in",
            "first_name": "George",
            "last_name": "Bluth",
            "avatar": "https://reqres.in/img/faces/1-image.jpg"
        }
         */

        //Matcher Class ile
        //data dan sonra [ ile baslayınca data keyın valuesunun bir array veya list oldugunu anlıyoruz
        // listin içine girmek için [] acıp 0 dan baslayarak index girerek içerdekı
        //elemana ulaşabiliyoruz

        response.then().body("data[0].email",equalTo("george.bluth@reqres.in"),
                "data[0].first_name",equalTo("George"),
                "data[0].last_name",equalTo("Bluth"),
                "data[0].avatar",equalTo("https://reqres.in/img/faces/1-image.jpg"));





    }
}
