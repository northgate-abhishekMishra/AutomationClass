package ddf.testcases;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.testng.SkipException;
import org.testng.annotations.Test;

import ddf.base.BaseTest_Class;
import ddf.base.Check_TestSkip;
import ddf.base.DataProviderClass;
import ddf.base.XLS_POI;


public class TestC {
	@Test(dataProvider="getData",dataProviderClass=DataProviderClass.class)
	public void TestC1(Hashtable<String, String> hashtable) {
		Check_TestSkip.skip("TestC");
		
	}
}
