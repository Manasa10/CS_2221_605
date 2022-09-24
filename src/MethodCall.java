public class MethodCall {

    static int counter = 0;
    int aInt = 42;

    MethodCall aMethodCall1;

    public void MethodCall()	{
        counter++;
    }
    private void changeInt( int aInt)	{
        aInt *= aInt;
    }
    private void changeA(MethodCall aObject)	{
        aObject.setValue(getValue() * 2 );
    }
    public void setValue(int aInt)	{
        this.aInt = aInt;
    }
    public int getValue()	{
        return(aInt );
    }
    public String toString()	{
        return("counter/aInt = " + counter + "/" + aInt );
    }
    private MethodCall test()	{
        aMethodCall1 = new MethodCall();
        System.out.println("1. aInt = " + aInt );
        changeInt(aInt);
        System.out.println("2. aInt = " + aInt );

        System.out.println("1. aMethodCall1 = " + aMethodCall1 );
        aMethodCall1.changeA(aMethodCall1);
        System.out.println("2. aMethodCall1 = " + aMethodCall1 );
        return this;
    }
    public static void main(String args[] )	{
        System.out.println( ( new MethodCall().test() ) );
    }
}