package stepDifinations;

import static io.restassured.RestAssured.given;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.builder.RequestSpecBuilder;
import io.restassured.builder.ResponseSpecBuilder;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import io.restassured.specification.ResponseSpecification;
import pojo.AddPlacePojo;
import pojo.location;
import resourses.APIResorses;
import resourses.TestDataBuild;
import resourses.Utils;

public class StepDifination extends Utils{
	RequestSpecification res;
	ResponseSpecification rspspec;
	Response response ;
	 String resp;
	 TestDataBuild testDataBuild = new TestDataBuild();
	 static String place_id;
	@Given("Add Place payload with {string} {string} {string}")
	public void add_place_payload_with(String name, String language, String address) throws IOException {
	    
 
	
		 rspspec = new ResponseSpecBuilder().expectStatusCode(200).expectContentType(ContentType.JSON).build();
		
         res = given().spec(addPlaceReqSpec()).body(testDataBuild.addPlaceReqData(name,language,address));
	
	}
	
	
	@When("user calls {string} with {string} http request")
	public void user_calls_with_http_request(String resourse, String httpReq) {
		
		APIResorses apiResourse = APIResorses.valueOf(resourse);
		
		if(httpReq.equalsIgnoreCase("post")) {
			response  = res.when().post(apiResourse.getResource());
					
		}
		else if (httpReq.equalsIgnoreCase("get")) {
			response  = res.when().get(apiResourse.getResource());
		}

		
	}
	
	
	@Then("the API call got sucess with status code {int}")
	public void the_api_call_got_sucess_with_status_code(Integer int1) {
	    // Write code here that turns the phrase above into concrete actions
	 Assert.assertEquals(response.getStatusCode(), 200);
	}
	@Then("{string} in responce body is {string}")
	public void in_responce_body_is(String key, String value) {
	    // Write code here that turns the phrase above into concrete actions
	   resp = response.asString();
	    
	    Assert.assertEquals(getJsonPath(resp,key), value);
	}
	@Then("verify place_Id created maps to {string} using {string}")
	public void verify_place_id_created_maps_to_using(String name, String resource) throws IOException {
	place_id = getJsonPath(resp,"place_id");
	   res = given().spec(addPlaceReqSpec()).queryParam("place_id", place_id);
	   user_calls_with_http_request(resource,"get");
	   resp = response.asString();
	   String actualName  = getJsonPath(resp,"name");
	   Assert.assertEquals(actualName,name);
	}
	

	@Given("Delete Place Payload")
	public void delete_place_payload() throws IOException {
		res = given().spec(addPlaceReqSpec()).body(testDataBuild.deletePlacePayload(place_id));
	}
}
