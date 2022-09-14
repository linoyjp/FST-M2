package LiveProject;

import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.lessThan;

public class GitHub_RestAssuredProject {

    RequestSpecification requestSpec;
    ResponseSpecification responseSpec;
    int ssh_id;
    String  param2;

    @BeforeClass
    public void setup (){
        requestSpec=new RequestSpecBuilder()
                .setContentType("application/json")
                .setBaseUri("https://api.github.com")
                .addHeader("Authorization","token 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")
                .build();

        responseSpec=new ResponseSpecBuilder()
                //.expectStatusCode(201)
                //.expectBody("status",equalTo("alive"))
                .expectResponseTime(lessThan(5000L))
                .build();
    }

    @Test (priority = 1)
    public void postrequest(){
        // Request body
        Map<String,Object> reqBody=new HashMap<>();
        reqBody.put("title","TestAPIKey");
        reqBody.put("key","ssh-rsa AAAAB3NzaC1yc2EAAAADAQABAAAAgQCbaM5BYo9b3ea2arjvY3VPBBooi03Fyvl/ld+8coAGr75CopjrLvPsirXon2OmRPH47810xdCf7XnJD5vHc6mRw8jGTFU1y3y1ao9XFrkdQ9TS0Hfwgm4Y9ayph12mlZ4t2BI+ryVaEdHRdk17w4TTGy0mbVjIaN35TaAMNaVZww== ");
        //Generate Response
        Response response=given().spec(requestSpec).body(reqBody)
                .when().post("/user/keys");
        //print
        System.out.println(response.getBody().prettyPrint());
        //extract
        ssh_id=response.then().extract().path("id");
        System.out.println("print ssh id from github:"+ssh_id);

        // add assertion
        response.then().assertThat().statusCode(201);
    }
    @Test(priority = 2)
    public void getrequest(){
        Response getresponse=given().spec(requestSpec).pathParams("keyId",ssh_id)
                .when().get("/user/keys/{keyId}");
        getresponse.then().log().all();
        // add assertion
        getresponse.then().assertThat().statusCode(200);

    }
    @Test (priority = 3)
    public void deleterequest(){
        given().spec(requestSpec).log().all().pathParams("keyId",ssh_id)
                .when().delete("/user/keys/{keyId}")
                .then().log().all().statusCode(204);
    }

}
