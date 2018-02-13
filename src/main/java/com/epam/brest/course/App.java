package com.epam.brest.course;

/**
 * Hello world!
 */
public class App {
    public static void main(String[] args) {
        System.out.println("Hello World!");
    }

    /**
     * Returns an element of int array
     * @param index required position in array
     * @return an element by its index
     */
    public static int getArrayElement(int index){
        int[] myArray = new int[10];
        for (int i = 0; i < myArray.length; ++i){
            myArray[i] = i;
        }
        return myArray[index];
    }

    /**
     * Returns the sum of two int numbers
     * @param a the 1st number
     * @param b the 2nd number
     * @return the sum of parameters a and b
     */
    public static int findSum(int a, int b){
        return a + b;
    }


}
