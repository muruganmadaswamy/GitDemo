package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;
import rahulshettyacademy.pageobjects.checkOutPage;

public class SubmitOrderTest extends BaseTest{
	String productName = "ZARA COAT 3";
	@Test(dataProvider="getData",groups= {"Purchase"})

	public void submitOrder(HashMap<String,String> input) throws IOException {
		// TODO Auto-generated method stub
		ProductCatalogue ProductCatalogue  = landingPage.loginApplication(input.get("email"), input.get("password"));
		List<WebElement>products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(input.get("product"));
		CartPage cartPage = ProductCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
		Assert.assertTrue(match);
		checkOutPage checkoutPage =cartPage.goToCheckout();
		checkoutPage.selectCountry("india");
		ConfirmationPage confirmationPage = checkoutPage.submitOrder();
		String confirmMessage = confirmationPage.getConfirmationMessage();
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));	
	}
	@Test(dependsOnMethods = {"submitOrder"})
	public void OrderHistoryTest()
	{
		ProductCatalogue ProductCatalogue  = landingPage.loginApplication("murugan.madaswamy@gmail.com", "Pass@123");
		OrderPage orderPage = ProductCatalogue.goToOrderPage();
		Assert.assertTrue(orderPage.VerifyOrderDisplay(productName));
	}
	
	
	
	
	@DataProvider
	public Object[][] getData() throws IOException
	{
		List<HashMap<String,String>> data = getJasonDataToMap(System.getProperty("user.dir")+ "//src//test//java//rahulshettyacademy//data//PurchaseOrder.jason");
		return new Object[][] { {data.get(0)},{data.get(1)} };
	}
}


