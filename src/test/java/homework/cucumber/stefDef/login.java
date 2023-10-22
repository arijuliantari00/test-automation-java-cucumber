package homework.cucumber.stefDef;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.edge.EdgeDriver;


import javax.swing.*;
import java.util.Objects;
import java.util.concurrent.TimeUnit;

public class login {
    WebDriver driver;
    String baseURL = "https://www.saucedemo.com/";

    @Given("Halaman Login")
    public void halamanLogin() {
        WebDriverManager.edgedriver().setup();
        driver = new EdgeDriver();
        driver.manage().timeouts().implicitlyWait(60, TimeUnit.SECONDS);
        driver.get(baseURL);

        //Assertion
        String loginPageWeb = driver.findElement(By.xpath("//div[contains(text(),'Swag Labs')]")).getText();
        Assert.assertEquals(loginPageWeb, "Swag Labs");
    }

    @When("Input Username")
    public void inputUsername() {
        driver.findElement(By.id("user-name")).sendKeys("standard_user");
    }

    @And("Input Password")
    public void inputPassword() {
        driver.findElement(By.id("password")).sendKeys("secret_sauce");
    }

    @Then("User masuk ke halaman shop")
    public void userMasukKeHalamanShop() {
        driver.findElement(By.id("inventory_container"));
        String text = driver.findElement(By.xpath("//span[contains(text(), 'Products')]")).getText();
        Assert.assertEquals(text, "Products");

        driver.close();
    }

    @And("Input Invalid Password")
    public void inputInvalidPassword() {
        driver.findElement(By.id("password")).sendKeys("eta_123");
    }

    @Then("Alert Cant Login")
    public void alertCantLogin() {
        String errorLogin = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(errorLogin, "Epic sadface: Username and password do not match any user in this service");

        driver.close();
    }

    @And("Click Login Buton")
    public void clickLoginButon() {
        driver.findElement(By.xpath("//input[@type='submit']")).click();
    }

    @Given("Halaman Products")
    public void halamanProducts() {
        halamanLogin();
        inputUsername();
        inputPassword();
        clickLoginButon();
    }

    @When("Click Title Product")
    public void clickTitleProduct() {
        driver.findElement(By.id("item_4_title_link")).click();
    }

    @Then("User masuk ke halaman Detail Product")
    public void userMasukKeHalamanDetailProduct() {
        String title = driver.findElement(By.className("inventory_details_name")).getText();

        Assert.assertEquals(title, "Sauce Labs Backpack");

    }

    @When("Right Click Title Product")
    public void rightClickTitleProduct() {
       driver.findElement(By.id("item_4_title_link"));
       driver.switchTo().newWindow(WindowType.TAB);

       String element = driver.findElement((By.xpath("//body"))).getText();
       if (Objects.equals(element, "")) {
           driver.quit();
       }
    }

    @When("Click Add to cart button")
    public void clickAddToCartButton() {
        driver.findElement(By.id("add-to-cart-sauce-labs-backpack")).click();
    }

    @And("Click Cart Button")
    public void clickCartButton() {
        driver.findElement(By.className("shopping_cart_link")).click();

    }
    @Then("User Masuk Halaman Cart")
    public void userMasukHalamanCart() {
        String title = driver.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(title, "Sauce Labs Backpack");
    }

    @And("Click Back to Product")
    public void clickBackToProduct() {
        driver.findElement(By.id("back-to-products")).click();
    }

    @Then("Close")
    public void close() {
        driver.quit();
    }

    @Given("Halaman Cart with product")
    public void halamanCartWithProduct() {
        halamanProducts();
        clickAddToCartButton();
        clickCartButton();
    }

    @When("Click Checkout")
    public void clickCheckout() {
        driver.findElement(By.id("checkout")).click();
    }

    @And("Input First Name")
    public void inputFirstName() {
        driver.findElement(By.id("first-name")).sendKeys("Ari");
    }

    @And("Input Last Name")
    public void inputLastName() {
        driver.findElement(By.id("last-name")).sendKeys("J");
    }

    @And("Input Zip Code")
    public void inputZipCode() {
        driver.findElement(By.id("postal-code")).sendKeys("80999");
    }

    @And("Click continue")
    public void clickContinue() {
        driver.findElement(By.id("continue")).click();
    }

    @And("Click finish")
    public void clickFinish() {
        String title = driver.findElement(By.className("inventory_item_name")).getText();
        Assert.assertEquals(title, "Sauce Labs Backpack");

        driver.findElement(By.id("finish")).click();
    }

    @Then("checkout Complete")
    public void checkoutComplete() {
        String complete = driver.findElement(By.className("complete-header")).getText();
        Assert.assertEquals(complete, "Thank you for your order!");
    }

    @Then("Alert Zipcode Required")
    public void alertZipcodeRequired() {
        String error = driver.findElement(By.xpath("//h3[@data-test='error']")).getText();
        Assert.assertEquals(error, "Error: Postal Code is required");
    }

    @Given("Halaman Products with two cart product")
    public void halamanProductsWithTwoCartProduct() {
        halamanProducts();
        clickAddToCartButton();
        driver.findElement(By.id("add-to-cart-sauce-labs-bike-light")).click();

    }

    @When("Click remove button")
    public void clickRemoveButton() {
        driver.findElement(By.id("remove-sauce-labs-backpack")).click();
    }

    @Then("Cart have one product")
    public void cartHaveOneProduct() {
       String cart = driver.findElement(By.className("shopping_cart_badge")).getText();
       Assert.assertEquals(cart, "1");
    }

}
