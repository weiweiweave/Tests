package com.digital.ace.java.banking.example;

import lombok.ToString;

public class Outer2 {

    static int outer2_var_static = 2;
    int outer2_var = 3;

    @ToString
    public static class Inner_static {

        public int getOuter2varStatic() {
            return outer2_var_static;
        }

        //4. class Inner_static can have access to only outer2_var_static? => true
        //Non-static field 'outer2_var' cannot be referenced from a static context
        //public int getOuter2var() {
        //    return outer2_var;
        //}
    }
}
