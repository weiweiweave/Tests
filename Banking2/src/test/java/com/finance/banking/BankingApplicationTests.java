package com.finance.banking;

import com.finance.banking.example.Outer;
import com.finance.banking.example.Outer2;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class BankingApplicationTests {

	@Test
	void contextLoads() {
	}

	@Test
	//1. Static class Inner_static cannot be instantiated? => false
	void testInstantiateInnerStatic() {
		Outer2.Inner_static innerStatic  = new Outer2.Inner_static();
		System.out.println("1. Static class Inner_static cannot be instantiated:" + innerStatic.toString());

	}

	@Test
	//3. class Inner can have access to outer_var? => true
	void classInnerhasAccessto_outer_var() {
		Outer outer = new Outer();
		Outer.Inner inner = outer.getInner();
		System.out.println("class Inner can have access to outer_var: " + inner.get_outer_var());
	}

	@Test
	//2. static class can only have a static attributes and methods? => false
	void classInnerStaticHasAccessToNonStaticMethod() {
		Outer2.Inner_static innerStatic  = new Outer2.Inner_static();
		System.out.println("2. static class can only have a static attributes and methods: "+ innerStatic.getOuter2varStatic());
	}



}
