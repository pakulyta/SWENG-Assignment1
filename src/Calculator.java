import java.util.ArrayList;
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

            String expIn = input.nextLine().trim();

            if (expIn.equalsIgnoreCase("Quit") || expIn.equalsIgnoreCase("End")){
                System.out.println("Thank you for using this calculator!" + "\nCalculator quitting.");
                quit = true;
            }
            else {
                String mathExp = nums_ops(expIn);
                if (!mathExp.equals("ERROR")){
                    System.out.println("Valid expression, performing calculation.\n");
                    // perform calculation
                    // output result
                }
                else {
                    System.out.println("Error: Please enter a valid expression!");
                    System.out.println("A valid string consists of numbers followed by one of the available operators.\n");
                }
            }
        }
    }

    /**
     * Validates and changes a string into a mathematical expression
     * @param expIn : User input to be validated
     * @return String expression to be evaluated || ERROR is string is invalid
     */
    private static String nums_ops(String expIn) {
        ArrayList<String> expression = new ArrayList<>();
        String digit = "";
        int i = 0;

        while (i < expIn.length()){
            if (isNumber(expIn.charAt(i))){
                while (i < expIn.length() && isNumber(expIn.charAt(i))){
                    digit += expIn.charAt(i);
                    i++;
                }
                expression.add(digit);
            }
            else if (isOperand(expIn.charAt(i))){
                expression.add(String.valueOf(expIn.charAt(i)));
                i++;
                if (isOperand(expIn.charAt(i))){
                    return "ERROR";
                }
            }
            else if (!isNumber(expIn.charAt(i)) && !isOperand(expIn.charAt(i))){
                    return "ERROR";
            }
        }
        return expression.toString();
    }

    /**
     * Checks if a character is a valid operator
     * @param op : char to be checked
     * @return true if valid, false otherwise
     */
    private static boolean isOperand(char op) {
        return op == '+' || op == '-' || op == '*';
    }

    /**
     * Checks if a character is a number
     * @param num : char to be checked
     * @return true if valid, false otherwise
     */
    private static boolean isNumber(char num) {
        return num >= '0' && num <= '9';
    }

}
