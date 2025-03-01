package resourses;
//enum is a special class in java which has collection of contants or methods
public enum APIResorses {
	
	AddPlaceAPI("/maps/api/place/add/json"),
	GetPlaceAPI("/maps/api/place/get/json"),
	DeletePlaceAPI("/maps/api/place/delete/json");
    private String resourse;
	APIResorses(String resourse) {
		this.resourse = resourse;
	}
	public String getResource() {
		return resourse;
	}

}
