
	import io.restassured.RestAssured;
	import io.restassured.response.Response;
	import org.testng.Assert;
import org.testng.annotations.Test;

	public class coinDeskRequestValidations {
	   
		@Test
		public void getApi() {
	        // Define the API endpoint
	        String apiUrl = "https://api.coindesk.com/v1/bpi/currentprice.json";

	        // Step 1: Send the GET request and capture the response
	        Response response = RestAssured.get(apiUrl);
	        
	        String responseBody = response.getBody().asString();
	        System.out.println("Response Body: \t\n" + responseBody);

	        // Step 2: Validate the response status code
	        int statusCode = response.getStatusCode();
	        Assert.assertEquals(statusCode, 200, "Failed! Unexpected status code.");

	        // Step 4: Validate the presence of 3 BPIs (USD, GBP, EUR)
	        boolean containsUSD = responseBody.contains("\"USD\"");
	        boolean containsGBP = responseBody.contains("\"GBP\"");
	        boolean containsEUR = responseBody.contains("\"EUR\"");

	        Assert.assertTrue(containsUSD, "USD not found in response!");
	        Assert.assertTrue(containsGBP, "GBP not found in response!");
	        Assert.assertTrue(containsEUR, "EUR not found in response!");

	        // Step 5: Validate GBP 'description' equals 'British Pound Sterling'
	        String gbpDescription = response.jsonPath().getString("bpi.GBP.description");
	        Assert.assertEquals(gbpDescription, "British Pound Sterling", "GBP description does not match!");

	        // Print success message if all validations pass
	        System.out.println("API Test Passed: All validations successful!");
	    }
	}