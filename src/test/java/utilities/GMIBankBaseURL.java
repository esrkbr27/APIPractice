package utilities;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.specification.RequestSpecification;
import org.junit.Before;

public class GMIBankBaseURL extends Authentication {

    protected RequestSpecification spec;

    @Before
    public  void setup(){
        spec=new RequestSpecBuilder().setBaseUri("httpS://www.gmibank.com/api/").build();
    }



}
