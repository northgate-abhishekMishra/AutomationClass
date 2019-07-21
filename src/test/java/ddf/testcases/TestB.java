package ddf.testcases;

import java.util.Hashtable;

import org.testng.SkipException;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;

import ddf.base.Check_TestSkip;
import ddf.base.DataProviderClass;
import ddf.base.XLS_POI;


public class TestB {

	@Test(dataProvider="getData",dataProviderClass=DataProviderClass.class)
	public void TestB(Hashtable<String, String> hashtable) {
		Check_TestSkip.skip("TestB");
	}

//	@Test(priority = 2, testName = "B2")
//	@Test(priority = 2, testName="A2",dependsOnMethods = { "TestA1" })
//	public void TestB2() {
		// inherited from BaseTest
//	}

//	@Test(priority = 3, dependsOnMethods = { "TestA1", "TestA2" })
//	@Test(priority = 3, testName = "B3")
//	public void TestB3() {

//	}

//	@AfterMethod
//	public void quit() {
//	}
}
