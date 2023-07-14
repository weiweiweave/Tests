package com.finance.banking.example;

public class OuterClass {

    private static String outerStatic = "outerStaticVar";
    private String outer = "outerVar";

    public static class InnerStaticClass {
        private static String innerStaticPrivateVar = "innerStaticPrivateVar";
        public static String innerStaticPublicVar = "innerStaticPublicVar";

        public InnerStaticClass() {
            System.out.println("InnerStaticClass Constructor");
        }

        public String getOuterStatic() {
            return outerStatic;
        }

        //Non-static field 'outer' cannot be referenced from a static context
        //public String getOuter() {
        //    return outer;
        //}
    }
}
