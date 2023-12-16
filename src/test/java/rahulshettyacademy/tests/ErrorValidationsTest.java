package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.sun.net.httpserver.Authenticator.Retry;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;



public class ErrorValidationsTest extends BaseTest{
	@Test(groups= {"ErrorHandling"},retryAnalyzer=rahulshettyacademy.TestComponents.Retry.class)	

	public void LoginErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		//String productName = "ZARA COAT 3";
		landingPage.loginApplication("murugan.madaswamy@gmail.com", "Pass@12");
		Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());
		System.out.println("postJira");
		System.out.println("postJira2");
		System.out.println("postJira3");
		
	}
	@Test

	public void ProductErrorValidation() throws IOException {
		// TODO Auto-generated method stub
		String productName = "ZARA COAT 3";
		ProductCatalogue ProductCatalogue  = landingPage.loginApplication("murugan.madaswamy@gmail.com", "Pass@123");
		List<WebElement>products = ProductCatalogue.getProductList();
		ProductCatalogue.addProductToCart(productName);
		CartPage cartPage = ProductCatalogue.goToCartPage();
		Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
		Assert.assertFalse(match);

	}



}
