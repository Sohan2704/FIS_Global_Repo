import java.time.Duration;
import java.util.List;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

public class EcommerceTest {

	@Test
	public void addProductIntoCart() {
		
		WebDriver driver = new ChromeDriver();

		// Step 1: Open browser and navigate to ebay.com
		driver.get("https://www.ebay.com");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();

		// Step 2: Search for 'books'
		WebElement searchBox = driver.findElement(By.xpath("//input[@title='Search']"));
		searchBox.sendKeys("book");
		searchBox.sendKeys(Keys.ENTER);
		String parentWindow = driver.getWindowHandle();

		//click on first book in the list
		List<WebElement> listOfProduct = driver.findElements(By.cssSelector("div.s-item__title span"));
		System.out.println("Print the number of books are available a" + listOfProduct.size());
		listOfProduct.get(2).click();

		Set<String> allWindows = driver.getWindowHandles();
		for (String window : allWindows) {
			if (!window.equals(parentWindow)) {
				System.out.println("Switch to Child Window");

				driver.switchTo().window(window);
				// Step 4: On the item listing page, click 'Add to cart'

				WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(30));
				// WebElement addToCartButton = driver.findElement(By.id("atcBtn_btn_1"));
				WebElement addToCartButton = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("atcBtn_btn_1")));
				addToCartButton.click();
				System.out.println("Product has been added to cart");
				
				// Step 5: Verify the cart has been updated

				String productCount = driver.findElement(By.cssSelector(".badge")).getText();
				Assert.assertEquals(productCount, "1");
				driver.close();			
			}
		}
		driver.quit();
	}

}
