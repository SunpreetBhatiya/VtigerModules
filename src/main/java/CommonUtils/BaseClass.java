package CommonUtils;

import java.io.IOException;
import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Listeners;
import org.testng.annotations.Test;

@Listeners(ListenerImplemenation.class)
public class BaseClass {
	
	public WebDriver cd;
	public static WebDriver sdriver;
	
	PropertiesFileUtils util = new PropertiesFileUtils();
	//String Browser = util.getdatafromPropertFile("browser");
	WebDriverutils wd = new WebDriverutils();
	 //PropertiesFileUtils p = new PropertiesFileUtils();
	
	
	@BeforeSuite
	public void BS()
	{
		System.out.println("Connect to database");
	}
	
	@BeforeClass//browser selection
	public void BC() throws IOException
	{
		String urll = util.getdatafromPropertFile("url");
		String Browser = util.getdatafromPropertFile("browser");
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
		
		sdriver=cd;
		wd.maximizeWindow(cd);
		wd.waitWebElementToLoad(cd);
        cd.get(urll);
		
	}
	
	@BeforeMethod// login to aplln
	public void BM() throws IOException
	{
		
		String NAME = util.getdatafromPropertFile("username");
		String pass = util.getdatafromPropertFile("password");
		
		cd.findElement(By.name("user_name")).sendKeys(NAME);
		cd.findElement(By.name("user_password")).sendKeys(pass);
		cd.findElement(By.id("submitButton")).click();
	}
	
	
	@Test
	public void contactTest() throws InterruptedException, IOException
	{
		PropertiesFileUtils utils = new PropertiesFileUtils();
		JavaUtils js = new JavaUtils();
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
		Assert.assertEquals("pune", "Puna");
		
		cd.findElement(By.xpath("(//img[@alt='Select'])[1]")).click();
		Thread.sleep(2000);
		
		//from parent to child
		
		wd.Switch(cd, "http://localhost:8888/index.php?action=index&module=Home");
		
		
		
        cd.findElement(By.id("search_txt")).sendKeys("Sunny1");
		
		cd.findElement(By.cssSelector("input[class='crmbutton small create']")).click();
		
		cd.findElement(By.xpath("//a[text()='Sunny1']")).click();
		
		wd.Switch(cd, "http://localhost:8888/index.php?module=Contacts&action=EditView&return_action=DetailView&parenttab=Marketing");
		
		
		
		cd.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
		wd.takeScreenShot(cd);
		
		
	}
	
	
	@Test
	public void organisationTest() throws InterruptedException, IOException
	{
		WebDriver driver;
		JavaUtils js = new JavaUtils();
		cd.findElement(By.xpath("(//a[text()='Organizations'])[1]")).click();
		Thread.sleep(2000);
		
		//click on plus icon
		cd.findElement(By.cssSelector("img[alt='Create Organization...']")).click();
		Thread.sleep(2000);
		
		String accname = util.getdatafromPropertFile("organname");
		
		//select organization name
		cd.findElement(By.name("accountname")).sendKeys(accname + js.getRandomNumber());
		Thread.sleep(2000);
		
		//Click on the radio button
		
		cd.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		Assert.assertEquals("pune", "Puna");
	
		
		
		
		
		//Slect support group in dropdown
		WebElement dropdown = cd.findElement(By.name("assigned_group_id"));
		Select s =new Select(dropdown);
		s.selectByVisibleText(util.getdatafromPropertFile("grpdd"));
		Thread.sleep(2000);
		
		//click on plus button
		//driver.findElement(By.cssSelector("img[alt='Select']")).click();
		
		//select as enginnering in industry
		WebElement groupdd = cd.findElement(By.name("industry"));
		Select s1 = new Select(groupdd);
		s1.selectByVisibleText(util.getdatafromPropertFile("industry"));
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
		Assert.assertEquals("pune", "Puna");
		
		
		
		cd.findElement(By.xpath("(//input[@name='assigntype'])[2]")).click();
		
		WebElement dd = cd.findElement(By.name("assigned_group_id"));
		Select s = new Select(dd);
		s.selectByVisibleText("Support Group");
		
		cd.findElement(By.cssSelector("input[class='crmbutton small save']")).click();
		
	}
	
	
	
	
	@AfterMethod
	public void AM()
	{
		WebElement signout = cd.findElement(By.cssSelector("img[src='themes/softed/images/user.PNG']"));
		
		wd.mousehover(cd, signout);
		
		cd.findElement(By.xpath("//a[text()='Sign Out']")).click();
	}
	
	
	
	
	
	
	
	@AfterClass
	public void AC()
	{
	   cd.quit();
	}
	
	
	
	@AfterSuite
	public void AS()
	{
		System.out.println("Disconnect from database");
	}
	
	

}
