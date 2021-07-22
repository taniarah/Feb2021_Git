package variousConcepts;

import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import junit.framework.Assert;

public class LearnDropdown {

	WebDriver driver;
	
	@Before 
	public void init() {
		
		System.setProperty("webdriver.chrome.driver", "driver\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.manage().deleteAllCookies();
		driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		
		driver.get("https://www.techfios.com/billing/?ng=login/");
		
	}
	
	@Test
	public void dropdown() {
		
		WebElement USERNAME_ELEMENT = driver.findElement(By.xpath("//input[@id='username']"));
		WebElement PASSWORD_ELEMENT = driver.findElement(By.xpath("//input[@id='password']"));
		WebElement SIGNIN_ELEMENT = driver.findElement(By.xpath("/html/body/div/div/div/form/div[3]/button"));
		
		USERNAME_ELEMENT.sendKeys("demo@techfios.com");
		PASSWORD_ELEMENT.sendKeys("abc123");		
		SIGNIN_ELEMENT.click();
		//very first page got loaded

		//Now verify that we are actually on a page where we have the Dashboard navigation element present
		WebElement DASHBOARD_NAV_ELEMENT = driver.findElement(By.xpath("//h2[contains(text(), 'Dashboard')]"));	
		Assert.assertEquals("Dashboard page not found!!", "Dashboard", DASHBOARD_NAV_ELEMENT.getText());
		
		
		
		//Now, find the Customer Element and click on it
		WebElement CUSTOMER_NAV_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/a"));
		CUSTOMER_NAV_ELEMENT.click();
		//Now assertion needed here as we are not atually loading a new page .... it is simply opening up the detail navigation menu on the left
		
		//Now find the add Customer detail navigation and click on it
		WebElement DASHBOARD_ADD_CUSTOMER_NAV_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[1]/a"));
		System.out.println("get text value is: " + DASHBOARD_ADD_CUSTOMER_NAV_ELEMENT.getText());
		
		DASHBOARD_ADD_CUSTOMER_NAV_ELEMENT.click();
		//after the click a new page is loaded, so we need to have a new element on the new page which will be used for assertion purpose
		
		WebElement ADD_CUSTOMER_FULLNAME_ELEMENT = driver.findElement(By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[1]/label"));
		
		Assert.assertEquals("Add Customer page not found!!", "Full Name*", ADD_CUSTOMER_FULLNAME_ELEMENT.getText());
		
		//Full Name
		driver.findElement(By.xpath("//*[@id=\"account\"]")).sendKeys("Tania Rahman");
		
		//Dropdown element
		WebElement COMPANY_dropdown_ELEMENT = driver.findElement(By.xpath("//select[@id='cid']"));
		
		Select sel = new Select(COMPANY_dropdown_ELEMENT);
		sel.selectByVisibleText("Techfios");
		
		//Email
		driver.findElement(By.xpath("//*[@id=\"email\"]")).sendKeys("trahman@gmail.com");
		
		//Phone number
		driver.findElement(By.xpath("//*[@id=\"phone\"]")).sendKeys("2143335588");
		//String phonePath = "//*[@id=\"phone\"]";
		//driver.findElement(By.xpath(phonePath)).sendKeys("2143304488");
		
		//Address
		driver.findElement(By.xpath("//*[@id=\"address\"]")).sendKeys("5800 Wade blvd");
		
		//city
		driver.findElement(By.xpath("//*[@id=\"city\"]")).sendKeys("Frisco");
		
		//State
		driver.findElement(By.xpath("//*[@id=\"state\"]")).sendKeys("TX");
		
		//Postal Code
		driver.findElement(By.xpath("//*[@id=\"zip\"]")).sendKeys("75035");
		
		//Country
		//WebElement COUNTRY_DROPDOWN_ELEMENT = driver.findElement(By.xpath("//*[@id=\"rform\"]/div[1]/div[1]/div[9]/div/span/span[1]/span"));
		WebElement COUNTRY_DROPDOWN_ELEMENT = 
				driver.findElement(By.xpath("//*[@id=\"country\"]"));
		Select select = new Select(COUNTRY_DROPDOWN_ELEMENT);
		select.selectByVisibleText("United States");
		
		//Save
		WebElement SAVE_ELEMENT = driver.findElement(By.xpath("//*[@id=\"submit\"]"));
		SAVE_ELEMENT.click();
		
		//List customer
		WebElement LIST_CUSTOMER_ELEMENT = driver.findElement(By.xpath("//*[@id=\"side-menu\"]/li[3]/ul/li[2]/a"));
		LIST_CUSTOMER_ELEMENT.click();
	}
	
}
