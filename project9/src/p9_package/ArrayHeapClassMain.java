package p9_package;

public class ArrayHeapClassMain
   {
    public static void main(String[] args)
       {
           ArrayHeapClass test = new ArrayHeapClass();
           test.setDisplayFlag(true);
           test.addItem(5);
           test.showArray();
           test.addItem(3);
           test.addItem(8);
           test.addItem(7);
           test.showArray();
           test.addItem(16);
           test.addItem(2);
           test.addItem(30);
           test.addItem(41);
           test.addItem(8);
           test.addItem(1);
           test.showArray();
           //array should be
           //[1,2,3,7,5,8,30,41,8,16]
           
           test.removeItem();
           test.showArray();
           test.removeItem();
           test.showArray();
           test.setDisplayFlag(false);
           test.showArray();
           
           ArrayHeapClass testTwo = new ArrayHeapClass(test);
           
           testTwo.setDisplayFlag(true);
           
           System.out.println("Test two");
           
           testTwo.showArray();
           testTwo.addItem(47);
           testTwo.showArray();
           
       }
   }