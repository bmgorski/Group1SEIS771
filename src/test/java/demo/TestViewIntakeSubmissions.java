package demo;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class TestViewIntakeSubmissions {

	
	public static WebDriver driver;

	public static void main(String[] args) {

		File file = new File("/Users/bmgorski/Desktop/chromedriver");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("localhost:8080/review-intake-submission/1");

		driver.findElement(By.id("username")).sendKeys("master@user.com");

		driver.findElement(By.id("password")).sendKeys("Test1!");

		driver.findElement(By.id("submit")).click();

		driver.findElement(By.name("action")).click();

	}

}
