package com.capgemini.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import com.capgemini.util.Calculatrice;
//on lance un test sous eclipse en ce placant sur cette classe
//et en activant le menu contextuel Run as ... / JUnit Test
public class TestCalculatrice {
	private Calculatrice calculatrice; //à tester
	
	@Before
	public void initTest() {
		this.calculatrice = new Calculatrice();
	}
	
	@Test
	public void testAddition() {
		int resAdd = this.calculatrice.addition(2, 3);
		Assert.assertTrue(resAdd==5);
	}
	
	@Test
	public void testMultiplication() {
		int resMult = this.calculatrice.multiplication(2, 3);
		Assert.assertTrue(resMult==6);
	}
}
