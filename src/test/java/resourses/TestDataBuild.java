package resourses;

import java.util.ArrayList;
import java.util.List;

import pojo.AddPlacePojo;
import pojo.location;

public class TestDataBuild {

	public AddPlacePojo addPlaceReqData(String name, String language, String address) {
		AddPlacePojo app = new AddPlacePojo(); 
		app.setAccuracy(50);
		app.setName(name);
		app.setPhone_number("(+91) 983 893 3937");
		app.setAddress(address);
		app.setWebsite("http://google.com");
		app.setLanguage(language);
		List<String> ls = new ArrayList<>();
		ls.add("shoe park");
		ls.add("shop");
		app.setTypes(ls);
		location loc = new location();
		loc.setLat(-38.383494);
		loc.setLng(33.427362);
		app.setLocation(loc);
		return app;
	}
	public String deletePlacePayload(String place_id) {
		return "{\r\n"
				+ "    \"place_id\":\""+place_id+"\"\r\n"
				+ "}";
	}
}
