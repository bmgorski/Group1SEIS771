package demo;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestSubmissionApplicationSelenium {

	public static WebDriver driver;

	public static void main(String[] args) {

		File file = new File("/Users/bmgorski/Desktop/chromedriver");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("localhost:8080/submit");

		driver.findElement(By.id("username")).sendKeys("master@user.com");

		driver.findElement(By.id("password")).sendKeys("Test1!");

		driver.findElement(By.id("submit")).click();

		driver.findElement(By.id("name")).sendKeys("CatZilla");

		driver.findElement(By.id("animal_type")).sendKeys("cat");

		driver.findElement(By.id("birthdate")).sendKeys("01/10/2010");

		driver.findElement(By.id("weight")).sendKeys("30");

		driver.findElement(By.id("breed")).sendKeys("unknown");

		driver.findElement(By.id("animal_size")).sendKeys("huge");

		driver.findElement(By.id("reported_condition")).sendKeys("mentally unstable");

		driver.findElement(By.id("date_submitted")).sendKeys("01/10/2015");

		driver.findElement(By.id("requests")).sendKeys("Use caution");

		driver.findElement(By.id("submit")).click();

	}

}
