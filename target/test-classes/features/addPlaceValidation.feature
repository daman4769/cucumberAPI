Feature: Valadating Place api
@AddPlace
Scenario Outline: verify if place is being Succesfully added using AddPlace API
	Given Add Place payload with "<name>" "<language>" "<address>"
	When user calls "AddPlaceAPI" with "post" http request
	Then the API call got sucess with status code 200
	And "status" in responce body is "OK"
	And "scope" in responce body is "APP"
	And verify place_Id created maps to "<name>" using "GetPlaceAPI"
	Examples: 
	|name  | language | address |
	|AHouse| English  | India   |
	|BHouse| Hindi    | Bharat  |
	
@DeletePlace	
Scenario: Verify if Delete Place functionslity is working 
	Given Delete Place Payload
	When user calls "DeletePlaceAPI" with "post" http request
	Then  the API call got sucess with status code 200
	And "status" in responce body is "OK"
	