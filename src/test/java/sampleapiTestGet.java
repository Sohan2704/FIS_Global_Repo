import io.restassured.RestAssured;
import io.restassured.response.Response;
import org.testng.Assert;
import org.testng.annotations.Test;

public class sampleapiTestGet {
    @Test
    public void testGetRequest() {
        // Set the base URI
        RestAssured.baseURI = "https://api.coindesk.com/v1";

        // Send GET request
        Response response = RestAssured
                .given()
                .get("/bpi/currentprice.json");

        // Validate the response status code
        Assert.assertEquals(response.getStatusCode(), 200, "Invalid Status Code!");

        // Validate that the response contains USD, GBP, and EUR
        String responseBody = response.getBody().asString();
        Assert.assertTrue(responseBody.contains("USD"), "USD not found in response!");
        Assert.assertTrue(responseBody.contains("GBP"), "GBP not found in response!");
        Assert.assertTrue(responseBody.contains("EUR"), "EUR not found in response!");

        // Print the response for debugging
        System.out.println("Response: " + response.prettyPrint());
    }
}
