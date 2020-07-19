//2016-18223 Jane Shin
import java.util.Random;
public class Compare {
    public static void main(String[] args) {
        myArrayList A = new myArrayList(100000);
        myLinkedList L = new myLinkedList();
        Random rand = new Random();
        String ch = "abcdefg0123456789";

        //adding random strings
        for (int i = 0; i < 100000; i++) {
            int len = rand.nextInt(5) + 1;
            String s = "";
            for (int j = 0; j < len; j++) {
                s = s.concat(Character.toString(ch.charAt(rand.nextInt(ch.length()))));
            }
            A.add(i, s);
            L.add(i, s);
        }

        //Comparing the Performance Results
        int[] indices = {1, 49999, 99999};
        for (int i = 0; i < indices.length; i++) {
            int ind = indices[i];
            long startTime;

            //GET
            startTime = System.nanoTime();
            A.get(ind);
            long getTIme1 = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            L.get(ind);
            long getTime2 = System.nanoTime() - startTime;

            //REMOVE
            startTime = System.nanoTime();
            Object a = A.remove(ind);
            long remTime1 = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            Object l = L.remove(ind);
            long remTime2 = System.nanoTime() - startTime;

            //ADD
            startTime = System.nanoTime();
            A.add(ind, a);
            long addTime1 = System.nanoTime() - startTime;
            startTime = System.nanoTime();
            L.add(ind, l);
            long addTime2 = System.nanoTime() - startTime;

            //Print the result
            System.out.println("\tArrayList\tLinkedList\tIndex = " + ind);
            System.out.println("---------------------------------------------");
            System.out.println("Add\t|" + addTime1 + "\t|" + addTime2);
            System.out.println("Get\t|" + getTIme1 + "\t|" + getTime2);
            System.out.println("Remove\t|" + remTime1 + "\t|" + remTime2);
            System.out.println();
            System.out.println();
        }
    }
}
