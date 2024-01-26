package Module;

import java.io.IOException;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

import CommonUtils.JavaUtils;
import CommonUtils.PropertiesFileUtils;
import CommonUtils.WebDriverutils;

public class Contact {
	
	
	public static void main(String[] args) throws InterruptedException, IOException {
		
	
	   WebDriver cd;
	
	   PropertiesFileUtils utils = new PropertiesFileUtils();
	   JavaUtils js = new JavaUtils();
	   String Browser = utils.getdatafromPropertFile("browser");
       
       
       
    
		//To launch the browser we are going to use
		if(Browser.equalsIgnoreCase("Chrome"))
		{
			cd = new ChromeDriver();
		}
		else if(Browser.equalsIgnoreCase("Edge"))
		{
			cd = new EdgeDriver();
		}
		else
		{
			
			cd = new FirefoxDriver();
			System.out.println("DefaultBrowser");
		}
		
		WebDriverutils wd = new WebDriverutils();
		wd.maximizeWindow(cd);
		wd.waitWebElementToLoad(cd);
		
		
		String urll = utils.getdatafromPropertFile("url");
		String NAME = utils.getdatafromPropertFile("username");
		String pass = utils.getdatafromPropertFile("password");
		
	
		cd.get(urll);
		Thread.sleep(2000);
		
		//Step 6 : Login to the application
		cd.findElement(By.name("user_name")).sendKeys(NAME);
		Thread.sleep(2000);
		cd.findElement(By.name("user_password")).sendKeys(pass);
		Thread.sleep(2000);
		cd.findElement(By.id("submitButton")).click();
		
		cd.findElement(By.xpath("//a[text()='Contacts']")).click();
		Thread.sleep(2000);
		
		cd.findElement(By.cssSelector("img[alt='Create Contact...']")).click();
		Thread.sleep(2000);
		
		cd.findElement(By.name("firstname")).sendKeys(utils.getdatafromPropertFile("fname")+js.getRandomNumber());
		Thread.sleep(2000);
		
		cd.findElement(By.name("lastname")).sendKeys(utils.getdatafromPropertFile("lname"));
		Thread.sleep(2000);
		
		cd.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		Thread.sleep(2000);
		
		WebElement dropdown = cd.findElement(By.name("assigned_group_id"));
		wd.handleDropdown(dropdown,"Support Group");
		Thread.sleep(2000);
		
		cd.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		Thread.sleep(2000);
		
		//from parent to child
		
		wd.Switch(cd, "http://localhost:8888/index.php?action=index&module=Home");
		
		/*Set<String> ids = cd.getWindowHandles();
		System.out.println(ids);
		
		for(String e : ids)
		{
			String act = cd.switchTo().window(e).getCurrentUrl();
			System.out.println(act);
		}*/
		
     cd.findElement(By.id("search_txt")).sendKeys("Sunny1");
		
		cd.findElement(By.cssSelector("input[class='crmbutton small create']")).click();
		
		cd.findElement(By.xpath("//a[text()='Sunny1']")).click();
		
		wd.Switch(cd, "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
		
		/*Set<String> ids1 = cd.getWindowHandles();
		System.out.println(ids1);
		
		String parentturl = "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing";
		
		for(String e : ids1)
		{
			String act1 = cd.switchTo().window(e).getCurrentUrl();
			System.out.println(act1);
			
			if(act1.contains(parentturl))
			{
				break;
			}
			
		}*/
		

		
		//cd.findElement(By.id("jscal_trigger_birthday")).sendKeys("2024-01-01");
		
		cd.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
		
		wd.takeScreenShot(cd);
		
		//local date and time
		/*LocalDateTime ldt=LocalDateTime.now();
		String timedate = ldt.toString().replace(':', '-');
		
		//Take the sc
		TakesScreenshot ts = (TakesScreenshot) cd;
		
		File temp = ts.getScreenshotAs(OutputType.FILE);
		File destinationfile = new File("./screenshot/contact" + timedate + ".png");
		FileUtils.copyFile(temp, destinationfile);*/
		
		
		WebElement signout = cd.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		
		wd.mousehover(cd, signout);
		
		cd.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}



}


