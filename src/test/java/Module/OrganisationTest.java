package Module;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.Test;

import CommonUtils.BaseClass;
import CommonUtils.JavaUtils;
import CommonUtils.PropertiesFileUtils;
import CommonUtils.WebDriverutils;

public class OrganisationTest extends BaseClass{
	
	
	
	
	@Test
	public void organisationTest() throws InterruptedException, IOException
	{
		WebDriver driver;
		JavaUtils js = new JavaUtils();
		WebDriverutils wd = new WebDriverutils();
		PropertiesFileUtils utils = new PropertiesFileUtils();
		
		cd.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		Thread.sleep(2000);
		
		//click on plus icon
		cd.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
		Thread.sleep(2000);
		
		String accname = utils.getdatafromPropertFile("organname");
		
		//select organization name
		cd.findElement(By.name("accountname")).sendKeys(accname + js.getRandomNumber());
		Thread.sleep(2000);
		
		//Click on the radio button
		
		cd.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		
		
		
		//Slect support group in dropdown
		WebElement dropdown = cd.findElement(By.name("assigned_group_id"));
		Select s =new Select(dropdown);
		s.selectByVisibleText(utils.getdatafromPropertFile("grpdd"));
		Thread.sleep(2000);
		
		//click on plus button
		//driver.findElement(By.cssSelector("img[alt='Select']")).click();
		
		//select as enginnering in industry
		WebElement groupdd = cd.findElement(By.name("industry"));
		Select s1 = new Select(groupdd);
		s1.selectByVisibleText(utils.getdatafromPropertFile("industry"));
		Thread.sleep(2000);
		
		//Click on save button
		cd.findElement(By.xpath("(//input[@class='crmbutton small save'])[2]")).click();
		
		//fetch the url
		String actualtitle = cd.getTitle();
		System.out.println(actualtitle);
		
		String expectedtitle = "Administrator - Organizations - vtiger CRM 5 - Commercial Open Source CRM";
		Thread.sleep(2000);
		
		WebDriverWait waits = new WebDriverWait(cd, Duration.ofSeconds(20));
		waits.until(ExpectedConditions.titleIs(expectedtitle));
		System.out.println(expectedtitle);
		Thread.sleep(5000);
	}

}
