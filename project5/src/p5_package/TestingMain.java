package p5_package;

public class TestingMain 
{
    
    
    public static void main(String[] args)
    {
        int size = 0;
        int index;
        int[] studentArray = new int[9];
        for(int i = 0; i < 5; i++)
        {
            studentArray[i] = i;
            size++;
            System.out.println(studentArray[i]);        
        }
        System.out.println();
        for(index = size; index > 0; index--)
        {
            studentArray[index] = studentArray[index -1];
            System.out.println(studentArray[index]);
        }
        System.out.println(studentArray[1]);
        //System.out.println(index);
    }
}
