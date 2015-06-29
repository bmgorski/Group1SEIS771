package demo;

import java.io.File;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class AnimalCareTest {

	public static WebDriver driver;

	public static void main(String[] args) {

		File file = new File("/Users/bmgorski/Desktop/chromedriver");
		System.setProperty("webdriver.chrome.driver", file.getAbsolutePath());
		driver = new ChromeDriver();

		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

		driver.get("http://localhost:8080/animal-care/1");

		driver.findElement(By.id("username")).sendKeys("master@user.com");

		driver.findElement(By.id("password")).sendKeys("Test1!");

		driver.findElement(By.id("submit")).click();

		driver.findElement(By.id("care_date")).sendKeys("05/10/2015");

		driver.findElement(By.name("care_notes")).sendKeys("Rabies Vaccine completed");

		driver.findElement(By.id("submit")).click();

	}

}
