class StringThing {

    public static String method1()	{
        return "1" + "23";
    }
    public static String method2()	{
        return new String (method1());
    }
    public static void main( String args[] ) {
        int counter = 0;
        int theNumberThree = 3;
// first block begins 		/////////////////////////////////////////////////////////////
        String aString = "12" + "3";
        String bString = "123";
        String cString = "123123";
        String dString = "123" + aString;
        String eString = "12" + theNumberThree;
        String fString = "123";

        // 1. true because both variables have the same literal and hence point
        // to the same address
        System.out.println("" + ++counter + ".	" +   ( aString  == bString ) );

        // 2. false because both variables point to different addresses as they
        // contain different literals
        System.out.println("" + ++counter + ".	" +   ( bString  == cString ) );

        // 3. false because concatenation of literals in dstring is resolved
        // during runtime and is then stored in a different memory location
        System.out.println("" + ++counter + ".	" +   ( cString  == dString ) );

        // 4. false because concatenation of literals in estring is resolved
        // during runtime and is then stored in a different memory location
        System.out.println("" + ++counter + ".	" +   ( eString  == fString ) );

        // 5. false because eString is stored in a new memory location whereas the
        // RHS points to the location of first occurrence of literal "123"
        System.out.println("" + ++counter + ".	" +   ( eString  == "12" + "3" ) );

        // 6. false because eString is stored in a new memory location whereas the
        // RHS points to the location of first occurrence of literal "123"
        System.out.println("" + ++counter + ".	" +   ( eString  == "12" + 3 ) );

        // 7. false because eString is stored in a new memory location whereas the
        // RHS points to the location of first occurrence of literal "123"
        System.out.println("" + ++counter + ".	" +   ( eString  == ( 1 + "" ) + "2" + "3" ) );
// first block ends 		/////////////////////////////////////////////////////////////

// second block begins 		/////////////////////////////////////////////////////////////
        String aaString = "1" + 23;
        String bbString = new String("1" + "23");
        String ccString = new String(aString);
        String ddString = method1();
        String eeString = method2();
        String ffString = method1() + method2();

        // 8. false, Since bbString has the new keyword, it points to a new memory
        // location different from aaString
        System.out.println("" + ++counter + ".	" +   ( aaString  == bbString ) );

        // 9. false, Since ccString has the new keyword, it points to a new memory
        // location different from aaString
        System.out.println("" + ++counter + ".	" +   ( aaString  == ccString ) );

        // 10. true, since both variables contain same literals, it will point to
        // the same memory location
        System.out.println("" + ++counter + ".	" +   ( aaString  == ddString ) );

        // 11. false, Since eeString has the new keyword, it points to a new memory
        // location different from aaString
        System.out.println("" + ++counter + ".	" +   ( aaString  == eeString ) );

        // 12. false, Since ffeString has the new keyword from the method2() call,
        // it points to a new memory location different from given literal
        System.out.println("" + ++counter + ".	" +   ( ffString  == "123123" ) );

        // false, concatenation takes precedence and "11. 123123" != "123123"
        System.out.println("" + ++counter + ".	" +     ffString  == "123123"   );

        // 14. 123123123123
        System.out.println("" + ++counter + ".	" +     ffString  +  "123123"   );
// second block ends 		/////////////////////////////////////////////////////////////

    }
}