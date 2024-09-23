package com.bpl.hrm.payrolltest;

import java.awt.Robot;
import java.awt.event.KeyEvent;

import javax.crypto.spec.OAEPParameterSpec;

import org.openqa.selenium.By;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.aventstack.extentreports.Status;
import com.bpl.hrm.basetest.BaseClass;
import com.bpl.hrm.objectrepositoryutility.EditPayrollPage;
import com.bpl.hrm.objectrepositoryutility.EmployeePage;
import com.bpl.hrm.objectrepositoryutility.HomePage;
import com.bpl.hrm.webdriverutility.UtilityClassObject;
/**
 * This testng class contains test cases for payroll module. 
 * 
 * @author Anant
 */
public class UpdatePayrollTest extends BaseClass
{
	@Test(groups="systemTest")
	/**
	 * This testng method performs the functionality to create employee and active payroll.
	 * @throws Throwable
	 */
	public void updatePayroll() throws Throwable
	{
		HomePage hp=new HomePage(driver);	
		UtilityClassObject.getTest().log(Status.INFO,"read data from excel");
		String empName=eLib.getDataFromExcel("Sheet1", 1, 2)+jLib.getRandomNumber();
		String empEmail=eLib.getDataFromExcel("Sheet1", 1, 3);
		String empPhone=eLib.getDataFromExcel("Sheet1", 1, 4);
		String empUsername=eLib.getDataFromExcel("Sheet1", 1, 5)+jLib.getRandomNumber();
		String empDesignation=eLib.getDataFromExcel("Sheet1", 1, 6);
		String empExp=eLib.getDataFromExcel("Sheet1", 1, 7);
		String newEmail=eLib.getDataFromExcel("Sheet1", 1, 3);
		String xpath="//tbody/tr[*]/td[text()='"+empName+"']/following-sibling::td/a";
		wLib.zoomOut();
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Employee Page");
		hp.getEmployeesTab().click();
		EmployeePage ep=new EmployeePage(driver);
		ep.getAddNewEmpBtn().click();
		ep.getEmpName().sendKeys(empName);
		ep.getEmpEmail().sendKeys(empEmail);
		ep.getEmpPhone().sendKeys(empPhone);
		ep.getEmpUsername().sendKeys(empUsername);
		ep.getEmpExperience().sendKeys(empExp);
		ep.getEmpDesignation().sendKeys(empDesignation);
		wLib.selectOption(driver, ep.getDropDown(),3);
		Actions ac=new Actions(driver);
		ac.scrollToElement(ep.getAddBtn());
		ep.getAddBtn().click();
		UtilityClassObject.getTest().log(Status.INFO,"New Employee created "+empName);
		UtilityClassObject.getTest().log(Status.INFO,"Navigate to Payroll");
		hp.getPayrollTab().click();
		driver.findElement(By.xpath(xpath)).click();
		UtilityClassObject.getTest().log(Status.INFO,"Read payroll data from excel");
		EditPayrollPage epay=new EditPayrollPage(driver);
		/**create payroll for employee**/
		String basic=eLib.getDataFromExcel("Sheet2", 1, 2);
		String hra=eLib.getDataFromExcel("Sheet2", 1, 3);
		String lta=eLib.getDataFromExcel("Sheet2", 1, 4);
		String state=eLib.getDataFromExcel("Sheet2", 1, 5);
		epay.getBasicPayEdt().sendKeys(basic);
		epay.getHraEdt().sendKeys(hra);
		epay.getLtaEdt().sendKeys(lta);
		epay.getStatEdt().sendKeys(state);
		wLib.selectOption(driver,epay.getStatusDropdown(),"Active");
		epay.getSaveBtn().click();
		//Thread.sleep(1000);
		//String actMsg=driver.findElement(By.xpath("//div[@role='alert']")).getText();
		String actMsg=driver.findElement(By.xpath("//div[@class='Toastify__toast Toastify__toast--success']/div[contains(text(),'Payroll S')]")).getText();
		//String actMsg=epay.getActMsg().getText();
		System.out.println(actMsg);
		String xpathPayroll="//tbody/tr[*]/td[text()='"+empName+"']/following-sibling::td[4]";
		String actStatus = driver.findElement(By.xpath(xpathPayroll)).getText();
		System.out.println("==="+actStatus+"===");
		Assert.assertEquals(actStatus, "Active");
		String empId=driver.findElement(By.xpath("//tbody/tr[*]/td[text()='"+empName+"']/preceding-sibling::td")).getText();
		/**verify payroll updated**/
		Assert.assertEquals(actMsg, empId+" Payroll Successfully Updated");
	}
}
