

//Intention of this file is to test Gap Buffers vs Ropes vs Regular Java Strings

//Assuming average submitted code file is about 300 lines with 16 chars a line = 4800 chars


import java.util.List;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;

public class PerformanceTest {
    public static void main(String[] args) {
        //Set up the Rope, GapBuffer, String data structs
        Rope rope = new Rope();
        List<Integer> gapBuff = new GapBuffer<>();
        String str = "";
        int size = 4800; // 1 File
        int testSet = 16; // 1 line
        int modifySet = 160; // 10 lines
        Random rand = new Random();
        
        //First add the 4800 random chars for Ropes
        long x1 = System.nanoTime();
        for(int i = 0; i< size; i++) {
            int curr = rand.nextInt();
            rope.concat(Integer.toString(curr));
        }
        long x2 = System.nanoTime();
        //Turns out java treats all integer values, like longs, same as double. Not %ld here
        System.out.printf("Rope Concatentation time: %d ns %n", (x2-x1));

        //Now concat for GapBuffer
        x1 = System.nanoTime();
        for(int i = 0; i< size; i++) {
            int curr = rand.nextInt();
            gapBuff.add(curr);
        }
        x2 = System.nanoTime();
        System.out.printf("Gap Buffer Concatentation time: %d ns %n", (x2-x1));

        //Now concat for Java Strings
        x1 = System.nanoTime();
        for(int i = 0; i< size; i++) {
            int curr = rand.nextInt();
            str += Integer.toString(curr);
        }
        x2 = System.nanoTime();
        System.out.printf("String Concatentation time: %d ns %n", (x2-x1));

        //Now we want to test the random retrival of a char for each data struc
        x1 = System.nanoTime();
        for(int i = 0; i< size; i++) {
            //Using ThreadedLocalRandom to have bounded random number to not get an exception
            int randIndex = ThreadLocalRandom.current().nextInt(0,4800);
            rope.indexAt(randIndex);
        }
        x2 = System.nanoTime();
        System.out.printf("Rope Retrieval time: %d ns %n", (x2-x1));

        x1 = System.nanoTime();
        for(int i = 0; i<size;i++) {
            int randIndex = ThreadLocalRandom.current().nextInt(0,4800);
            gapBuff.get(randIndex);
        }
        x2 = System.nanoTime();
        System.out.printf("GapBuffer Retrieval time: %d ns %n", (x2 - x1));


    }

}
