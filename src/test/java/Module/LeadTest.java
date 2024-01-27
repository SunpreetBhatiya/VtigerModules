package Module;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import CommonUtils.BaseClass;
import CommonUtils.JavaUtils;
import CommonUtils.PropertiesFileUtils;

public class LeadTest extends BaseClass{
	
	
	@Test
	public void LeadTest() throws InterruptedException, IOException
	{
		PropertiesFileUtils utils = new PropertiesFileUtils();
		JavaUtils js = new JavaUtils();
		
		cd.findElement(By.xpath("(//a[text()='Leads'])[1]")).click();
		
		
		cd.findElement(By.cssSelector("img[title='Create Lead...']")).click();
		
		cd.findElement(By.cssSelector("input[name='firstname']")).sendKeys(utils.getdatafromPropertFile("fname")+js.getRandomNumber());
		cd.findElement(By.name("lastname")).sendKeys(utils.getdatafromPropertFile("lname"));
		cd.findElement(By.name("company")).sendKeys(utils.getdatafromPropertFile("company"));
		
		cd.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		WebElement dd = cd.findElement(By.name("assigned_group_id"));
		Select s = new Select(dd);
		s.selectByVisibleText("Support Group");
		
		cd.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
		
	}

}
