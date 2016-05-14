package core;

import java.util.List;
import java.util.concurrent.TimeUnit;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class TestSignUp {

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = new FirefoxDriver();
		
//WebDriver driver = new HtmlUnitDriver();
// Positive TC:
		String test_case_id_0101 = "TC-01.01";
		String test_case_id_0102 = "TC-01.02";
		String test_case_id_0103 = "TC-01.03";
		String test_case_id_0104 = "TC-01.04";
		String test_case_id_0105 = "TC-01.05";

//Error handling TC:
		String test_case_id_0201 = "TC-02.01";
		String test_case_id_0202 = "TC-02.02";
		String test_case_id_0203 = "TC-02.03";
		String test_case_id_0204 = "TC-02.04";
		String test_case_id_0205 = "TC-02.05";

		String url = "http://learn2test.net/qa/apps/sign_up/v1/";
		String title_html_expected = "Welcome to Sign Up v1";
		String title_page_expected = "Sign Up";
		String email_format_msg_expected = "Invalid Email Address: xx[min2]@x[min1].xx[2-6]"; // TC 02.03
		String phone_format_msg_expected = "Invalid Phone Number: should be 10 digits"; // TC 02.04
		String lname_missing_expected = "Please enter Last Name";
		int t =4; // expected quantity of text field
		String fname = "John";
		String lname = "Johnson";
		String email = "J.Johnson@gmail.com";
		String eemail = "J.Johnson2gmail.com";
		String phone = "415-555-5555";
		String phone_inv_format = "415-555-5555-";
		
//TC-01.01 Html_Title
		driver.get(url);
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String title_html = driver.getTitle();
		//System.out.println(title_html);
		if (title_html_expected.equals(title_html)){
			System.out.println("Test Case ID: \t\t" + test_case_id_0101 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0101 + " FAILED");
		}
//TC-01.02 Page Title
		String title_page = driver.findElement(By.id("id_f_title")).getText();
		if (title_page_expected.equals(title_page)){
			System.out.println("Test Case ID: \t\t" + test_case_id_0102 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0102 + " FAILED");
		}
//TC-01.03 quote
		String quoteInitial = driver.findElement(By.id("id_quotes")).getText();
		driver.navigate().refresh();
		driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
		String quoteRefresh = driver.findElement(By.id("id_quotes")).getText();
		if (quoteInitial != quoteRefresh)
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0103 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0103 + " FAILED");	
		}
//TC-01.04 quote length
		String quote = driver.findElement(By.id("id_quotes")).getText();
		byte quoteLength = (byte) driver.findElement(By.id("id_quotes")).getText().length();
		if (quoteLength>=67 && quoteLength<=103)
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0104 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0104 + " FAILED");
		System.out.println("Quote: " + quote);
		System.out.println("Quote lenght: " + quoteLength);
		}

//TC-01.05 Text fields
		List<WebElement> text_field= driver.findElements(By.xpath(".//*[@type='text']"));
			if (text_field.size()==t)
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0105 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0105 + " FAILED");
		}

//TC-02.01 Invalid First Name format - error handling
		driver.findElement(By.id("id_fname")).sendKeys(fname+" "+lname);	
		driver.findElement(By.id("id_submit_button")).click();
		boolean fnameError = driver.findElement(By.id("fname_error")).isDisplayed();
		if (fnameError)
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0201 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0201 + " FAILED");
		}
		
//TC-02.02 Missing phone number - error handling
		driver.findElement(By.id("id_reset_button")).click();
		driver.findElement(By.id("id_fname")).sendKeys(fname);
		driver.findElement(By.id("id_lname")).sendKeys(lname);
		driver.findElement(By.id("id_email")).sendKeys(email);
		driver.findElement(By.id("id_submit_button")).click();
		boolean phoneError = driver.findElement(By.id("phone_error")).isDisplayed();
		if (phoneError)
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0202 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0202 + " FAILED");
		}
//Thread.sleep(1000);
//TC-02.03 Invalid e-mail address format - error handling
		driver.findElement(By.id("id_reset_button")).click();
		driver.findElement(By.id("id_fname")).sendKeys(fname);
		driver.findElement(By.id("id_lname")).sendKeys(lname);
		driver.findElement(By.id("id_phone")).sendKeys(phone);
		driver.findElement(By.id("id_email")).sendKeys(eemail);
		driver.findElement(By.id("id_submit_button")).click();
		String email_format_msg = driver.findElement(By.id("ErrorLine")).getText();
		if (email_format_msg.equals(email_format_msg_expected))
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0203 + " PASSED");
		}
		else
		{System.out.println("Test Case ID: \t\t" + test_case_id_0203 + " FAILED");
		System.out.println(email_format_msg);
		System.out.println(email_format_msg_expected);
		}
//Thread.sleep(1000);
//TC-02.04 Wrong Phone number format - error handling	
	 	driver.findElement(By.id("id_reset_button")).click();
		driver.findElement(By.id("id_fname")).sendKeys(fname);
		driver.findElement(By.id("id_lname")).sendKeys(lname);
		driver.findElement(By.id("id_phone")).sendKeys(phone_inv_format);
		driver.findElement(By.id("id_email")).sendKeys(email);
		driver.findElement(By.id("id_submit_button")).click();
		String phone_format_msg = driver.findElement(By.id("ErrorLine")).getText();
		if (phone_format_msg.equals(phone_format_msg_expected))
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0204 + " PASSED");
		}
		else
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0204 + " FAILED");
		}
//TC-02.05 Missing Last name - error handling
		driver.findElement(By.id("id_reset_button")).click();
		driver.findElement(By.id("id_fname")).sendKeys(fname);
		driver.findElement(By.id("id_lname")).sendKeys();
		driver.findElement(By.id("id_phone")).sendKeys(phone);
		driver.findElement(By.id("id_email")).sendKeys(email);
		driver.findElement(By.id("id_submit_button")).click();
		String lname_missing = driver.findElement(By.className("Errline")).getText();
		if (lname_missing.equals(lname_missing_expected))
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0205 + " PASSED");
		}
		else
		{
			System.out.println("Test Case ID: \t\t" + test_case_id_0205 + " FAILED");
		}
		
		Thread.sleep(1000);
		driver.quit();
	}

}
