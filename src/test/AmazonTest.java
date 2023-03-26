package test;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonTest {

	public static void main(String[] args) throws IOException {
		// TODO Auto-generated method stub

		System.setProperty("webdriver.chrome.driver", "chromedriver");
		WebDriver driver = new ChromeDriver();
		driver.get("https://www.amazon.in");

		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(3000, TimeUnit.MILLISECONDS);

		WebElement searchbox = driver.findElement(By.xpath("//input[@id='twotabsearchtextbox']"));
		searchbox.sendKeys("samsung mobile");

		WebElement searchicon = driver.findElement(By.xpath("//input[@id='nav-search-submit-button']"));
		searchicon.click();

		List<WebElement> productname = driver.findElements(By.xpath("//div[@class='a-section']//h2//span"));

		List<WebElement> productprice = driver.findElements(By.xpath(
				"//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price-whole']"));

		List<WebElement> pricesymbol = driver.findElements(By.xpath(
				"//div[@data-component-type='s-search-result']//div[contains(@class,'price')]//span[@class='a-price-symbol']"));

		for (int i = 0; i < productname.size(); i++) {

			System.out.println("Product Name: " + productname.get(i).getText());
			System.out.println("Product Price: " + pricesymbol.get(i).getText() + " " + productprice.get(i).getText());

		}

		TakesScreenshot tsObj = (TakesScreenshot) driver;
		File fileObj = tsObj.getScreenshotAs(OutputType.FILE);
		File screenshotObj = new File("amazon.png");

		FileUtils.copyFile(fileObj, screenshotObj);

		driver.close();

	}

}
