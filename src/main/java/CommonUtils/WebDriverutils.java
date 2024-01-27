package CommonUtils;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Set;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

	public class WebDriverutils {
		
		
		//Window related methods
		public void maximizeWindow(WebDriver cd)
		{
			cd.manage().window().maximize();
			
		}
		
		public void minimizeWindow(WebDriver cd)
		{
			cd.manage().window().minimize();
			
		}
		
		public void fullScreenWindow(WebDriver cd)
		{
			cd.manage().window().fullscreen();;
			
		}
		
		//TimeOutMethods
		public void waitWebElementToLoad(WebDriver cd)
		{
			cd.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		}
		
		//Select Methods
		public void handleDropdown(WebElement target , String text)
		{
			Select s =new Select(target);
			s.selectByVisibleText(text);
			
		}
		
		public void handleDropdown(WebElement target , int index)
		{
			Select s =new Select(target);
			s.selectByIndex(index);
			
		}
		
		public void handleDropdownvalue(WebElement target ,String value)
		{
			Select s =new Select(target);
			s.selectByValue(value);
			
		}
		
		//Action class
		public void mousehover(WebDriver cd, WebElement target)
		{
			Actions a = new Actions(cd);
			a.moveToElement(target);
			a.perform();
		}
		
		public void rightClick(WebDriver cd , WebElement target)
		{
			Actions a = new Actions(cd);
			a.contextClick(target);	
			a.perform();
		}
		
		public void doubleClick(WebDriver cd , WebElement target)
		{
			Actions a = new Actions(cd);
			a.doubleClick(target);	
			a.perform();
		}
		
		public void click(WebDriver cd , WebElement target)
		{
			Actions a = new Actions(cd);
			a.click(target);	
			a.perform();
		}
		
		//parent to child
		public void Switch(WebDriver cd , String expectedurl)
		{
			Set<String> ids1 = cd.getWindowHandles();
			System.out.println(ids1);
			
			for(String e : ids1)
			{
				String act1 = cd.switchTo().window(e).getCurrentUrl();
				System.out.println(act1);
				
				if(act1.contains(expectedurl))
				{
					break;
				}
			}
			
			
				
			}
		
		  public void takeScreenShot(WebDriver cd) throws IOException
		{
			  LocalDateTime ldt=LocalDateTime.now();
				String timedate = ldt.toString().replace(':', '-');
				
				//Take the sc
				TakesScreenshot ts = (TakesScreenshot) cd;
				
				File temp = ts.getScreenshotAs(OutputType.FILE);
				File destinationfile = new File("./screenshot/contact" + timedate + ".png");
				FileUtils.copyFile(temp, destinationfile);
				
			
		
		}
		  
		  public String takeScreenShot1(WebDriver cd,String Screenshotname) throws IOException
			{
				  LocalDateTime ldt=LocalDateTime.now();
				  String timedate = ldt.toString().replace(':', '-');
					
					//Take the sc
					TakesScreenshot ts = (TakesScreenshot) cd;
					
					File temp = ts.getScreenshotAs(OutputType.FILE);
					File destinationfile = new File("./screenshot/" + Screenshotname + timedate + ".png");
					FileUtils.copyFile(temp, destinationfile);
					return destinationfile.getAbsolutePath();
					
					
				
			
			}
			  
		  
		  public void okpopup(WebDriver cd)
		  {
			  cd.switchTo().alert().accept();
		  }
		  public void cancelpoppup(WebDriver cd)
		  {
			  cd.switchTo().alert().dismiss();;
		  }
		  public void textpopup(WebDriver cd)
		  {
			  cd.switchTo().alert().getText();
		  }
		  public void valuepopup(WebDriver driver,String text)
		  {
			  driver.switchTo().alert().sendKeys(text);
		  }
		  
		  public void frames(WebDriver driver,int index)
		  {
			  driver.switchTo().frame(index);
		  }
		  
		  public void frames(WebDriver driver,String name)
		  {
			  driver.switchTo().frame(name);
		  }
		  
		  public void frames(WebDriver driver,WebElement element)
		  {
			  driver.switchTo().frame(element);
		  }
		  
		  public void pageload(WebDriver driver)
		  {
			  driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(10));                    
		  }
		  
		  public void waitWebElementToDisplay(WebDriver driver, String Expectedurl)
		  {
			  WebDriverWait waits = new WebDriverWait(driver,Duration.ofSeconds(0));
			  waits.until(ExpectedConditions.urlToBe(Expectedurl));
		  }
		  
		  
		  
		  
	}
		
		
		
		
		
		
		





