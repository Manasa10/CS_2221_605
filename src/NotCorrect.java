
public class  NotCorrect	{

    static int counter;
    NotCorrect aNotCorrect; // =  new NotCorrect(); // creation of a new object

    public NotCorrect() {
        System.out.println("so Many Calls: " + counter++ );
    }
    public static void main(String args[] )	{
        NotCorrect aNotCorrect = new NotCorrect();
    }
}