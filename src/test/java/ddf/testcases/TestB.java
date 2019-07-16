package ddf.testcases;

import org.testng.annotations.AfterMethod;
import org.testng.annotations.Test;


public class TestB {

	@Test(priority = 1)
	public void TestB1() {

	}

	@Test(priority = 2, testName = "B2")
//	@Test(priority = 2, testName="A2",dependsOnMethods = { "TestA1" })
	public void TestB2() {
		// inherited from BaseTest
	}

//	@Test(priority = 3, dependsOnMethods = { "TestA1", "TestA2" })
	@Test(priority = 3, testName = "B3")
	public void TestB3() {

	}

	@AfterMethod
	public void quit() {
	}
}
