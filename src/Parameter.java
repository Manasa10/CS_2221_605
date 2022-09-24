public class Parameter {

    int aInt = 42;
    int instanceInt = 0;
    static Parameter aParameter;

    private static void change( int aInt)	{
        aInt = 2 * aInt;
    }
    private static void change(Parameter aObject)	{
        aObject.instanceInt = 100;
    }
    public String toString()	{
        return("aInt/instanceInt = " + aInt + "/" + instanceInt );
    }
    public static void main(String args[] )	{
        int aInt = 42;
        System.out.println("before: aInt = " + aInt);
        change(aInt);
        System.out.println("after: aInt = " + aInt);
        aParameter = new Parameter();
        System.out.println("before: aParameter = " + aParameter );
        change(aParameter);
        System.out.println("after: aParameter = " + aParameter );
    }
}