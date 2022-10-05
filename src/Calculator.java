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
                ArrayList<String> mathExp = nums_ops(expIn);
                // validate string
                // perform calculation
                // output result
            }
        }
    }

    /**
     * Changes the given expression into numbers and operators
     * @param expIn : expression to be converted
     */
    private static ArrayList<String> nums_ops(String expIn) {
        char[] chars = expIn.toCharArray();
        ArrayList<String> expression = new ArrayList<>();
        String number = "";

        for (int i = 0; i < chars.length; i++){
            int j = -1;
            if (chars[i] >= '0' && chars[i] <= '9'){
                number += chars[i];
            }
            else if (chars[i] == '+' || chars[i] == '-' || chars[i] == '*'){
                expression.add(number);
                expression.add(String.valueOf(chars[i]));
                number = "";
            }
            if (i == chars.length-1 && !number.equals("")){
                expression.add(number);
            }
        }
        return expression;
    }
    /**
     * Checks if expression is valid, only numbers and no duplicate operators
     * @param expIn : expression to be validated
     * @return 1 if expression is valid, 0 if expression is not valid
     */
    private static boolean validateExp(String expIn){
        String[] chars = expIn.split("");

        for (String character : chars){
            if (Integer.parseInt(character) < 0 || Integer.parseInt(character) > 9
                || !character.equals("+") || !character.equals("-") || !character.equals("*")){
                return false;
            }

        }
        return true;
    }

}
