package test;

import static org.junit.Assert.assertEquals;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Collection;
import java.util.concurrent.TimeUnit;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;


@RunWith(Parameterized.class)
public class test {
	
	private String name;
	private String email;
	private WebDriver driver;
	private String baseUrl;
	
	public test(String name,String email){
		this.name = name;
		this.email = email;
	}

	@Before
	public void setUp() throws Exception {
		driver = new FirefoxDriver();
		baseUrl = "http://www.ncfxy.com/";
		driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
		driver.get(baseUrl);
		
	}
	
	@Parameters
	public static Collection<Object[]> getData() throws IOException{
		File inFile = new File("C://Users/1dell/Desktop/info.csv");
		Object[][] obj = new Object[109][];
		String in ="";
		 try {
	        	
				@SuppressWarnings("resource")
				BufferedReader reader = new BufferedReader(new FileReader(inFile));
				int i = 0;
				while((in = reader.readLine())!=null){
					obj[i] = new Object[]{in.split(",")[0], in.split(",")[1]};
					i++;
					
				}  
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		return Arrays.asList(obj);
	}
 
	@After
	public void tearDown() throws Exception {
		driver.close();
	}

	
	@Test
	public void test() {
		
		
		WebElement element = driver.findElement(By.id("name"));
		element.sendKeys(this.name);
		
		WebElement element1 = driver.findElement(By.id("pwd"));
		element1.sendKeys(this.name.substring(4));
		
		WebElement element3 = driver.findElement(By.id("submit"));
		element3.click();
		
		assertEquals(this.email, driver.findElement(By.xpath(".//*[@id='table-main']/tr[1]/td[2]")).getText());
		
	}

}
