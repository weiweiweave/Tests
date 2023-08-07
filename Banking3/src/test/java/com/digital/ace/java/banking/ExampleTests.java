package com.digital.ace.java.banking;

import com.digital.ace.java.banking.example.Outer;
import com.digital.ace.java.banking.example.Outer2;
import com.digital.ace.java.banking.example.OuterClass;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ExampleTests {

    @Test
        //1. Static class Inner_static cannot be instantiated? => false
    void testInstantiateInnerStatic() {
        Outer2.Inner_static innerStatic  = new Outer2.Inner_static();
        System.out.println("1. Static class Inner_static cannot be instantiated:" + innerStatic.toString());

    }

    @Test
        //3. class Inner can have access to outer_var? => true
    void testClassInnerhasAccessto_outer_var() {
        //6. inner class requires outer class reference => true
        Outer outer = new Outer();
        Outer.Inner inner = outer.getInner();
        System.out.println("class Inner can have access to outer_var: " + inner.get_outer_var());
    }

    @Test
        //2. static class can only have a static attributes and methods? => false
    void testClassInnerStaticHasAccessToNonStaticMethod() {
        //5. inner static class does not need a reference of outer class => true
        Outer2.Inner_static innerStatic  = new Outer2.Inner_static();
        System.out.println("2. static class can only have a static attributes and methods: "+ innerStatic.getOuter2varStatic());
    }

    //inner classes have access to all members of the enclosing class
    // (including private ones)
    @Test
    void testInnerHaveAllAccess () {
        Outer outer = new Outer();
        Outer.Inner inner = outer.getInner();
        System.out.println("inner classes have access to all members of the enclosing class");
        System.out.println("outer var = " + inner.get_outer_var());
    }
    //static nested classes only have access to static members of the outer class.
    @Test
    void testInnerStaticHaveStaticAccess () {
        OuterClass.InnerStaticClass inner = new OuterClass.InnerStaticClass();
        System.out.println("Accessing outerStatic = " + inner.getOuterStatic());
    }
}
