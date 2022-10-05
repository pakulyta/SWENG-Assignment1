import java.util.Scanner;

/**
 * SWENG Assignment 1 - Group 38
 * Simple calculator app which takes in an inputted mathematical expression and returns the result of the expression
 */
public class Calculator {
    static String operations = ("""
                The available operations are as follows:
                 * Addition (+)
                 * Subtraction (-)
                 * Multiplication (*)
                 * Quit (Quit/End)""");

    public static void main(String[] args) {
        boolean quit = false;

        Scanner input = new Scanner(System.in);
        System.out.println("""
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
                  Welcome to the Calculator app!
                ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~""");

        //program runs until user wishes to quit
        while (!quit){
            System.out.println(operations);
            System.out.println("Please enter your mathematical expression:");

            String expIn = input.next().trim();

            if (expIn.equalsIgnoreCase("Quit") || expIn.equalsIgnoreCase("End")){
                System.out.println("Thank you for using this calculator!" + "\nCalculator quitting.");
                quit = true;
            }
            else {
                // validate string
                // perform calculation
                // output result
            }
        }
    }
}
