package ddf.testcases;

import java.util.Hashtable;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import ddf.base.BaseTest_Class;
import ddf.base.DataProviderClass;

public class TestA extends BaseTest_Class{

	@Test(priority = 1)
	public void TestA1() {
		openBrowser();
		navigate();
	}

//	@Test(priority = 2, testName = "A2")
//	@Test(priority = 2, testName="A2",dependsOnMethods = { "TestA1" })
	@Test(dataProvider="getData",dataProviderClass=DataProviderClass.class)
	public void TestA2(Hashtable<String, String> hashtable) {
		// inherited from BaseTest
	}

//	@Test(priority = 3, dependsOnMethods = { "TestA1", "TestA2" })
	@Test(priority = 3, testName = "A3")
	public void TestA3() {

	}

	@AfterMethod
	public void quit() {
	}
}
