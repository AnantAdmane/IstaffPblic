package com.bpl.hrm.objectrepositoryutility;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

/**
 * This class contains object repository for all the web elements present on the
 * employee web page.
 * 
 * @author Anant
 */
public class EmployeePage {
	WebDriver driver;

	/**
	 * This constructor helps to initialize all the located web elements in this
	 * class.
	 * 
	 * @param driver
	 */
	public EmployeePage(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Add New Employee']")
	private WebElement addNewEmpBtn;

	@FindBy(xpath = "//label[text()='Name*']/following-sibling::input")
	private WebElement empName;

	public WebElement getEmpName() {
		return empName;
	}

	@FindBy(xpath = "//label[text()='Email*']/following-sibling::input")
	private WebElement empEmail;

	public WebElement getEmpEmail() {
		return empEmail;
	}

	@FindBy(xpath = "//label[text()='Phone*']/following-sibling::input")
	private WebElement empPhone;

	public WebElement getEmpPhone() {
		return empPhone;
	}

	@FindBy(xpath = "//label[text()='Username*']/following-sibling::input")
	private WebElement empUserName;

	public WebElement getEmpUsername() {
		return empUserName;
	}

	@FindBy(xpath = "//label[text()='Designation*']/following-sibling::input")
	private WebElement empDesignation;

	public WebElement getEmpDesignation() {
		return empDesignation;
	}

	@FindBy(xpath = "//label[text()='Experience*']/following-sibling::input")
	private WebElement empExperience;

	public WebElement getEmpExperience() {
		return empExperience;
	}

	@FindBy(name = "project")
	private WebElement dropDown;

	public WebElement getDropDown() {
		return dropDown;
	}

	@FindBy(xpath = "//input[@value='Add']")
	private WebElement addBtn;

	public WebElement getAddBtn() {
		return addBtn;
	}

	@FindBy(xpath = "//input[@type='email']")
	private WebElement edtBtn;

	public WebElement getEdtBtn() {
		return edtBtn;
	}
	
	@FindBy(xpath="//div[@role='alert']")
	private WebElement confirmMsg;
	public WebElement getConfirmMsg()
	{
		return confirmMsg;
	}
	@FindBy(xpath = "//input[@value='Save']")
	private WebElement saveBtn;

	public WebElement getSaveBtn() {
		return saveBtn;
	}
	
	public WebElement getAddNewEmpBtn() {
		return addNewEmpBtn;
	}

	/**
	 * This method helps to create new employee
	 * 
	 * @param empName
	 * @param empEmail
	 * @param empPhoneNo
	 * @param empUsernamets
	 * @param designation
	 * @param empExp
	 * @param index
	 */
	public void createEmployee(String empName, String empEmail, String empPhoneNo, String empUsernamets,
			String designation, String empExp, int index) {
		getAddNewEmpBtn().click();
		getEmpName().sendKeys(empName);
		getEmpEmail().sendKeys(empEmail);
		getEmpPhone().sendKeys(empPhoneNo);
		getEmpUsername().sendKeys(empUsernamets);
		getEmpExperience().sendKeys(empExp);
		getEmpDesignation().sendKeys(designation);
		Select s = new Select(getDropDown());
		s.selectByIndex(index);
		Actions ac = new Actions(driver);
		ac.scrollToElement(getAddBtn());
		getAddBtn().click();
	}

}
