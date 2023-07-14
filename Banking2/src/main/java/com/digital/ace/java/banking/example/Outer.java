package com.digital.ace.java.banking.example;

import lombok.ToString;

public class Outer {

    private int outer_var = 1;

    @ToString
    public class Inner {

        public int get_outer_var() {
            return outer_var;
        }
    }

    public Inner getInner() {
        return new Inner();
    }


}
