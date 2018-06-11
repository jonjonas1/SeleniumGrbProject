package com.weborder;

import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Order {
	
	public static void main(String[] args) throws InterruptedException {
		
		System.setProperty("webdriver.chrome.driver",
				"C:\\Users\\ojuraev\\CyberTek\\Selenium\\drivers\\chromedriver.exe");
		WebDriver driver = new ChromeDriver();
		
		driver.get("http://secure.smartbearsoftware.com/samples/TestComplete12/WebOrders/Login.aspx");
		
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$username")).sendKeys("Tester");
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$password")).sendKeys("test");
		Thread.sleep(1000);
		driver.findElement(By.name("ctl00$MainContent$login_button")).click();
		Thread.sleep(1000);
		
//		driver.get("http://secure.smartbearsoftware.com/samples/testcomplete12/weborders/Process.aspx");
		driver.findElement(By.linkText("Order")).click();
		
		Random rd = new Random();
		int num = rd.nextInt(100)+1;
		String nums=""+num;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtQuantity")).sendKeys(nums);;
		//Customer name
		int r = (int) (Math.random()*4);
		String list =new String [] {"Bob", "Alisa", "Michelle", "Juan", "Bill"}[r];
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$txtName")).sendKeys("John "+list+" Smith");
		//street name
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox2")).sendKeys("123 Any Street");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox3")).sendKeys("Austin");
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox4")).sendKeys("Texas");
		int zip = rd.nextInt(100000-10000) + 10000;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox5")).sendKeys(""+zip);
		//card click
//		driver.findElement(By.cssSelector("input[type='radio']")).click();
		int cardType = rd.nextInt(3);
		if(cardType==0) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_0")).click();
		}else if(cardType==1) {
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_1")).click();
		}else{
			driver.findElement(By.id("ctl00_MainContent_fmwOrder_cardList_2")).click();			
		}
		//card Number
		String cardNumber;
		if(cardType==0) {
			cardNumber="4";
			for(int i=1; i<16; i++)
				cardNumber+=rd.nextInt(3);
		}
		else if(cardType==1) {
			cardNumber="5";
			for(int i=1; i<16; i++)
				cardNumber+=rd.nextInt(3);
		}else {
			cardNumber="3";
			for(int i=1; i<15; i++)
				cardNumber+=rd.nextInt(3);
		}
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox6")).sendKeys(cardNumber);
		//Expiration Date
		int dt = rd.nextInt(12-1)+1;
		int Low = 18;
		int High = 23;
		int res = rd.nextInt(High-Low)+Low;
		driver.findElement(By.name("ctl00$MainContent$fmwOrder$TextBox1")).sendKeys("0"+dt+"/"+res);
			/*OR Below
			 * 	int mm = rd.nextInt(12 - 1) + 1;
			    String m = "";
			    if (mm < 10) {
			      m = "0" + mm;
			    } else {
			      m = "" + mm;
			    }
			 */
		//Process
		Thread.sleep(2000);
		driver.findElement(By.id("ctl00_MainContent_fmwOrder_InsertButton")).click();
		
		String expected = "New order has been successfully added";
		String actual = driver.findElement(By.id("ctl00_MainContent_fmwOrder")).getText();
				
		if(actual.contains(expected)) {
			System.out.println("pass");
		}else { 
			System.out.println("fail");
			System.out.println("Expected \t"+ expected);
			System.out.println("Actual \t"+actual);
		}
		
		Thread.sleep(7000);
		driver.close();
	}

}
