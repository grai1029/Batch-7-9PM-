package ObjectRepositories;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ProductPage {
	
	WebDriver driver;
	public ProductPage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath="//span[text()='Add Product']")
	private WebElement addProductBtn;

	@FindBy(name="productName")
	private WebElement productNameTF;
	
	@FindBy(name="productCategory")
	private WebElement productCategoryDd;

	@FindBy(name="quantity")
	private WebElement quantityDd;
	
	@FindBy(name="price")
	private WebElement priceDd;
	
	@FindBy(name="vendorId")
	private WebElement vendorIdDd;
	
	@FindBy(xpath="//button[@type='submit']")
	private WebElement createProductSubmitBtn;
	public WebDriver getDriver() {
		return driver;
	}

	public WebElement getAddProductBtn() {
		return addProductBtn;
	}

	public WebElement getProductNameTF() {
		return productNameTF;
	}

	public WebElement getProductCategoryDd() {
		return productCategoryDd;
	}

	public WebElement getQuantityDd() {
		return quantityDd;
	}

	public WebElement getVendorIdDd() {
		return vendorIdDd;
	}

	
	public WebElement getCreateProductSubmitBtn() {
		return createProductSubmitBtn;
	}

	public WebElement getPriceDd() {
		return priceDd;
	}
	
	
}
