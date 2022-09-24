public class  HelloWorld	{
    static int counter = 0;
    int aInt = 0;
    int a;
    private HelloWorld()	{
        counter ++;
    }
    private static int getCounter()	{
        return counter;
    }
    private void setState(int aInt)	{
        this.aInt = aInt;
    }
    private void setBoth(HelloWorld aHelloWorld)	{
        aInt = 42;
        System.out.println("this " + this.aInt);
        System.out.println("this " + aHelloWorld.aInt);

        aHelloWorld.setState(aInt * 2 );
    }
    public String toString()	{
        System.out.println(a);
        return "" + aInt + "/" + counter;
    }
    public static void main(String args[] )	{
//        System.out.println(3 + 7);
//        System.out.println(3 + 7 + "abc");
//        System.out.println(3 + 7 + "abc" + 1);
//        System.out.println(3 + 7 + "abc" + 1 + 2);
//        System.out.println("" + 3 + 7 + "abc" + 1 + 2);
//        System.out.println("" + ( 3 + 7 ) + "abc" + ( 1 + 2 ));
        System.out.println(new HelloWorld());
        String b = "A";
//        System.out.println(a);

    }
}