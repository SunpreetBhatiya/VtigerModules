package Module;

import java.io.IOException;
import java.time.Duration;

import org.apache.poi.EncryptedDocumentException;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import CommonUtils.JavaUtils;
import CommonUtils.PropertiesFileUtils;
import CommonUtils.WebDriverutils;

public class Oragnisation {
	
	public static void main(String[] args) throws EncryptedDocumentException, IOException, InterruptedException {
		WebDriver driver;
	    //from properties file
		PropertiesFileUtils util = new PropertiesFileUtils();
		String Browser = util.getdatafromPropertFile("browser");
		
		
		
		
		//web driver util class object
		WebDriverutils wd = new WebDriverutils();
		JavaUtils js = new JavaUtils();
		
		
		
		
		
		
		//To launch the browser we are going to use
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			driver = new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("Edge"))
		{
			driver = new EdgeDriver();
		}
		else
		{
			
			driver = new FirefoxDriver();
			System.out.println("DefaultBrowser");
		}
		wd.maximizeWindow(driver);
		wd.waitWebElementToLoad(driver);
		
		
		String urll = util.getdatafromPropertFile("url");
		String NAME = util.getdatafromPropertFile("username");
		String pass = util.getdatafromPropertFile("password");
		
		
		
		//step 5 :Load the url
		driver.get(urll);
		
		//Step 6 : Login to the application
		driver.findElement(By.name("user_name")).sendKeys(NAME);
		driver.findElement(By.name("user_password")).sendKeys(pass);
		driver.findElement(By.id("submitButton")).click();
		
		//click on organisation
		driver.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		Thread.sleep(2000);
		
		//click on plus icon
		driver.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
		Thread.sleep(2000);
		
		String accname = util.getdatafromPropertFile("organname");
		
		//select organization name
		driver.findElement(By.name("accountname")).sendKeys(accname + js.getRandomNumber());
		Thread.sleep(2000);
		
		//Click on the radio button
		
		driver.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		
		
		
		//Slect support group in dropdown
		WebElement dropdown = driver.findElement(By.name("assigned_group_id"));
		Select s =new Select(dropdown);
		s.selectByVisibleText(util.getdatafromPropertFile("grpdd"));
		Thread.sleep(2000);
		
		//click on plus button
		//driver.findElement(By.cssSelector("img[alt='Select']")).click();
		
		//select as enginnering in industry
		WebElement groupdd = driver.findElement(By.name("industry"));
		Select s1 = new Select(groupdd);
		s1.selectByVisibleText(util.getdatafromPropertFile("industry"));
		Thread.sleep(2000);
		
		//Click on save button
		driver.findElement(By.xpath("(//input[@class='crmbutton small save'])[2]")).click();
		
		//fetch the url
		String actualtitle = driver.getTitle();
		System.out.println(actualtitle);
		
		String expectedtitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		Thread.sleep(2000);
		
		WebDriverWait waits = new WebDriverWait(driver, Duration.ofSeconds(20));
		waits.until(ExpectedConditions.titleIs(expectedtitle));
		System.out.println(expectedtitle);
		Thread.sleep(5000);
		
		WebElement signout = driver.findElement(By.xpath("//img[@src='themes/softed/images/user.PNG']"));
		wd.mousehover(driver, signout);
		Thread.sleep(2000);
		
		driver.findElement(By.xpath("//a[text()='Sign Out']")).click();
		
	}
	



	
	}
	
	
	


