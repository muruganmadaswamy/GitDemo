package rahulshettyacademy.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import rahulshettyacademy.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	public LandingPage(WebDriver driver)
	{
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	//WebElement userEmail = driver.findElement(By.id("userEmail"));
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement passwordEle;
	
	@FindBy(id="login")
	WebElement submit;
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
									
	
	public ProductCatalogue loginApplication(String email,String password)
	{
		userEmail.sendKeys(email);
		passwordEle.sendKeys(password);
		submit.click();
		ProductCatalogue ProductCatalogue = new ProductCatalogue(driver);
		return ProductCatalogue;
	}
	public String getErrorMessage()
	{
		waitForwebElementToAppear(errorMessage);
		return errorMessage.getText();
	}
	
	private void waitForwebElementToAppear(WebElement errorMessage2) {
		// TODO Auto-generated method stub
		
	}
	public void goTo()
	{
		driver.get("https://www.rahulshettyacademy.com/client");
	}
	}



