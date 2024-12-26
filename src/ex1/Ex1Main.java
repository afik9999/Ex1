package ex1;

import java.util.Arrays;
import java.util.Scanner;

public class Ex1Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String num1 = "", num2="", quit = "quit";
        while (!num1.equals(quit) && !num2.equals(quit)) {
            System.out.println();
            System.out.println("Ex1 class solution:");
            System.out.println("Enter a string as number#1 (or \"quit\" to end the program): ");
            num1 = sc.next();
            if (num1.equals(quit)) {
                break;
            }
            boolean isNumberNum1 = Ex1.isNumber(num1);
            int number2IntNum1 = Ex1.number2Int(num1);
            System.out.println("num1= " + num1 + " is number: " + isNumberNum1 + " , value: " + number2IntNum1);
            
            System.out.println("Enter a string as number#2 (or \"quit\" to end the program): ");
            num2 = sc.next();
            if (num2.equals(quit)) {
                break;
            }
            boolean isNumberNum2 = Ex1.isNumber(num2);
            int number2IntNum2 = Ex1.number2Int(num2);
            System.out.println("num2= " + num2 + " is number: " + isNumberNum2 + " , value: " + number2IntNum2);

            System.out.println("Enter a base for output: (a number [2,16]");
            int base = sc.nextInt();
            String sum = Ex1.int2Number(number2IntNum1 + number2IntNum2, base);
            String mul = Ex1.int2Number(number2IntNum1 * number2IntNum2, base);
            System.out.println(num1 + " + " + num2 + " = " + sum);
            System.out.println(num1 + " * " + num2 + " = " + mul);
            String [] numbers = {num1, num2, sum, mul};
            int maxIdx = Ex1.maxIndex(numbers);
            System.out.println("Max number over " + Arrays.toString(numbers) + " is: " + numbers[maxIdx]);

        }
        System.out.println("quiting now...");
    }
}