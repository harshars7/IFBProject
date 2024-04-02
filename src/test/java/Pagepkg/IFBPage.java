package Pagepkg;

import java.io.File;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.Duration;
import java.util.Set;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.io.FileHandler;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class IFBPage {
WebDriver driver;
	
	String BaseURL = "https://www.ifbappliances.com/";
	
	By IfbLogo = By.xpath("//*[@id='header']/div/div[3]/div[1]/a/picture/img");
	By CreateLoginbtn = By.xpath("//*[@id='customer-menu']");
	By SignInbtn = By.xpath("//*[@id='header']/div/div[3]/div[2]/div[3]/nav/a[1]");	
	By EmailIdIFB = By.xpath("//*[@id='email']");
	By IFBPassword = By.xpath("//*[@id='pass']");
	By SignInAccount = By.xpath("//*[@id='send2']");	
	By Products = By.xpath("//*[@id=\"header\"]/div/div[2]/div/nav/div[1]/a");
	By CloseBtn = By.xpath("//*[@id=\"header\"]/div/div[3]/div[2]/div[1]/div/form/div[1]/div/div/div[1]");	
	By ProductList = By.xpath("//*[@id=\"maincontent\"]/div[3]/div/section/div[2]/div/form[1]/div[1]/a/picture/img");
	By Pincode = By.xpath("//*[@id='customer_pincode']");
	By SubmitPincode = By.xpath("//*[@id='zipcode-submit']/span");
	By Search = By.xpath("//*[@id='search']");	
	By Microwave = By.xpath("//*[@id=\"maincontent\"]/div[3]/div[1]/div[2]/div[2]/ol/li[1]/div/a/span/span/picture/img");
	By AddToCart = By.xpath("//*[@id='cart-btn-sticky']/a");
	By MyCart = By.xpath("//*[@id='menu-cart-icon']");
	By CloseCart = By.xpath("//*[@id='cart-drawer']/div/div[2]/div[1]/button");
	By Youtube = By.xpath("//*[@id='html-body']/div[2]/footer/div[2]/div[2]/div/div[3]/div/div/a[2]");
	By Instagram = By.xpath("//*[@id='html-body']/div[2]/footer/div[2]/div[2]/div/div[3]/div/div/a[3]");

	
	
	public IFBPage(WebDriver driver)
	{
		this.driver=driver;
	}
	// To verify the logo is displayed or not in the website
	
		public void IFBVerifyLogo()
		{
			WebElement logo = driver.findElement(IfbLogo);
			if(logo.isDisplayed())
			{
				System.out.println("\nSuccess : Logo is Displayed");
			}
			else
			{
				System.out.println("\nError : Logo is not Displayed!!");
			}
		}
		// To verify the title
		
		public void VerifyIFBTitle()
		{
			String ActualTitle = driver.getTitle();
			String ExpectedTitle = "IFB Appliances - Buy Latest Home & Kitchen Appliances Online";
		    if(ActualTitle.equalsIgnoreCase(ExpectedTitle))
			    {
				System.out.println("\nTitle Verification : Pass");
				System.out.println("\nActual Title = "+ActualTitle);
				}
			else
		        {
				System.out.println("\nTitle Verification : Failed!!");
				System.out.println("\nActual Title = "+ActualTitle);
		     	}
                   }
		
		//To verify the HttpConnection and response code
		
		public void IFBVerifyHttpConnection() throws Exception 
		{
			URL ob=new URL(BaseURL);
			HttpURLConnection Con = (HttpURLConnection)ob.openConnection();
			Con.connect();
			if(Con.getResponseCode()==200)
			{
				System.out.println("\nResponse Code = "+Con.getResponseCode());
				System.out.println("\nValid URL = "+BaseURL);
			}
			else
				if(Con.getResponseCode()==404)
			{   System.out.println("\nResponse Code = "+Con.getResponseCode());
				System.out.println("\nBroken Link = "+BaseURL);
			}
		}
		
		// to get page source
		public void pagesource()
		{   
			String title=driver.getTitle();
			String Pagesrc=driver.getPageSource();
			
			if(Pagesrc.contains("Appliances"))
			{
				System.out.println("\nSuccess\nPage source - Contains(Appliances)");
				System.out.println("\ntitle = "+title);
			}
			else
			{
				System.out.println("Page source failed!!");
			}
		    }
		
		// To set values to the page from excelsheet
		
		public void SetValuesFromExcel(String Email,String Password) throws Exception
		{ 
			Thread.sleep(1000);
			
		    //Explicit wait -- To wait for a particular element, eg button click
			WebDriverWait  wait= new WebDriverWait(driver,Duration.ofSeconds(10));
			
			WebElement createAccBtn =  wait.until(ExpectedConditions.visibilityOfElementLocated(CreateLoginbtn));
			createAccBtn.click();
			
			driver.findElement(SignInbtn).click();
			
			driver.findElement(EmailIdIFB).clear();
			driver.findElement(EmailIdIFB).sendKeys(Email);
			
			driver.findElement(IFBPassword).clear();
			driver.findElement(IFBPassword).sendKeys(Password);	
		}
		//SignIn to the registered account
		public void SignIn()
		{
			driver.findElement(SignInAccount).click();
		}
		 
		//To view the products
		
		public void IFBHeaders() throws Exception
		{
			driver.findElement(Products).click();
			Thread.sleep(1000);
			driver.findElement(CloseBtn).click();
			Thread.sleep(1000);
			driver.findElement(ProductList).click();
			Thread.sleep(1000);
			driver.findElement(Pincode).sendKeys("678612");
			driver.findElement(SubmitPincode).click();	
		}
		
		//To search the products
		
		public void Search() throws Exception
		
		{  Thread.sleep(2000);
			//to search products on press of enter key
			driver.findElement(Search).sendKeys("25 litre microwave",Keys.ENTER);
			driver.findElement(Microwave).click();
			Thread.sleep(200);
			
			// add the product to the cart
			driver.findElement(AddToCart).click();
			Thread.sleep(200);
			
			//view cart
			driver.findElement(MyCart).click();
			
		}
		//To take screenshot of the items added in the cart
		public void Screensht() throws Exception
		{  
			Thread.sleep(1000);
			File Screenshots=((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
			FileHandler.copy(Screenshots, new File("./Screenshots//MyCart.png"));
	
			driver.findElement(CloseCart).click();
			
		}
		
		//to scroll down to the bottom of the page
		public void scrolldown()
		{
	
			JavascriptExecutor js=(JavascriptExecutor)driver;
			js.executeScript("window.scrollBy(0,document.body.scrollHeight)");
		}
		
		//Redirect to Youtube - window handling
		
		public void windowhandleYoutube() throws Exception
		{
			//to get parent window handle
			String parentwindow=driver.getWindowHandle();
			System.out.println("parent window title = "+driver.getTitle());
			
			driver.findElement(Youtube).click();
			
			//to get all currently opened window details
			Set<String> allWindowHandle=driver.getWindowHandles();
			
			//loop to find handle of new window
			for(String WindowHandles:allWindowHandle)
			{
				System.out.println("Window handle = "+WindowHandles);
				if(!WindowHandles.equalsIgnoreCase(parentwindow))
				{
		        driver.switchTo().window(WindowHandles);
		        Thread.sleep(10000);
		         // to close the youtube window
				    driver.close();
			    }
			   driver.switchTo().window(parentwindow);
		        }
		        }
		//Redirect to instagram - window handling
		
		public void windowhandleInstagram() throws Exception
		{
			//to get parent window handle
			String parentwindow1=driver.getWindowHandle();
			System.out.println("parent window title = "+driver.getTitle());
			
			driver.findElement(Instagram).click();
			
			//to get all currently opened window details
			Set<String> allWindowHandle1=driver.getWindowHandles();
			
			//loop to find handle of new window
			for(String WindowHandles1:allWindowHandle1)
			{
				System.out.println("Window handle = "+WindowHandles1);
				if(!WindowHandles1.equalsIgnoreCase(parentwindow1))
				{
		        driver.switchTo().window(WindowHandles1);
		        Thread.sleep(10000);
		  
				    //driver.close();
			    }
			   driver.switchTo().window(parentwindow1);
		        }
		        }

}
