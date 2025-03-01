package stepDifinations;

import java.io.IOException;

import io.cucumber.java.Before;

public class Hooks {
@Before("@DeletePlace")
public void beforeScenario() throws IOException {
	StepDifination m = new StepDifination();
	if(StepDifination.place_id== null) {
	m.add_place_payload_with("aman", "Hindi", "bit mishra");
	m.user_calls_with_http_request("AddPlaceAPI", "Post");
	m.in_responce_body_is("status", "OK");
	m.verify_place_id_created_maps_to_using("aman", "GetPlaceAPI");
	}
}
}
