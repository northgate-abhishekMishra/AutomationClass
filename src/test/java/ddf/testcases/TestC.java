package ddf.testcases;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

import org.testng.annotations.Test;

import ddf.base.DataProviderClass;


public class TestC{
	@Test(dataProvider="getData",dataProviderClass=DataProviderClass.class)
	public void TestC1(Hashtable<String, String> hashtable) {
		for (int i = 0; i < hashtable.size(); i++) {
			Set<String> keyset = hashtable.keySet();
			Iterator<String> iterator = keyset.iterator();
			while (iterator.hasNext()) {
				System.out.println("Key = " + iterator.next());
			}
		}
	
	}
	
}
