import java.util.ArrayList;
import java.util.Scanner;
import java.util.Stack;

/**
 * SWENG Assignment 1 - Group 38 [Austeja Pakulyte]
 * Simple calculator app which takes in an inputted mathematical expression and returns the result of the expression
 */
public class Calculator {
    private static Stack<String> nums = new Stack<>();
    private static Stack<String> ops = new Stack<>();

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

        // program runs until user wishes to quit
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
                // valid input
                if (!mathExp.get(0).equals("-1")){
                    System.out.println("Valid expression, performing calculation.\n");
                    String result = calculation(mathExp);
                    System.out.println("The result of your calculation is : " + result + "\n");
                }
                // invalid input
                else {
                    System.out.println("Error: Please enter a valid expression!");
                    System.out.println("A valid string consists of numbers followed by one of the available operators.\n");
                }
            }
        }
    }

    /**
     * Performs a mathematical calculation of a given string
     * @param mathExp : String to be evaluated
     * @return String of result
     */
    public static String calculation(ArrayList<String> mathExp) {
        String result = "";
        String val1 = "";
        String val2 = "";
        String op = "";

        for (String temp : mathExp) {
            // if temp is an operand, push to stack
            if (isNumberString(temp)) {
                nums.push(temp);
            }
            // if temp is an operator
            else if (isOperatorString(temp)) {
                // empty stack || precedence greater than stack top -> push to stack
                if (ops.empty() || precedence(ops.peek()) < precedence(temp)) {
                    ops.push(temp);
                }
                // precedence lower || equal to stack top -> evaluate current expression in stack
                else if (precedence(ops.peek()) >= precedence(temp)) {
                    while (!ops.empty() && precedence(ops.peek()) >= precedence(temp)) {
                        val2 = nums.pop();
                        val1 = nums.pop();
                        op = ops.pop();
                        nums.push(performOperation(val1, val2, op));
                    }
                    // push current operator to stack
                    ops.push(temp);
                }
            }
        }
        // finish evaluating expression in stack
        while (!nums.empty() && !ops.empty()){
            val2 = nums.pop();
            val1 = nums.pop();
            op = ops.pop();
            result = performOperation(val1, val2, op);
            nums.push(result);
        }
        return result;
    }

    /**
     * Method to perform actual calculation using Integers
     * @param val1 : first operand
     * @param val2 : second operand
     * @param op : operator
     * @return : String representation of mathematical result
     */
    private static String performOperation(String val1, String val2, String op) {
        int result = switch (op) {
            case "+" -> Integer.parseInt(val1) + Integer.parseInt(val2);
            case "-" -> Integer.parseInt(val1) - Integer.parseInt(val2);
            case "*" -> Integer.parseInt(val1) * Integer.parseInt(val2);
            default -> 0;
        };
        return Integer.toString(result);
    }

    /**
     * Gets the precedence of an operator (* > + || -)
     * @param op : operator to be checked
     * @return : int presentation of operator's precedence
     */
    private static int precedence(String op) {
        if (op.equals("+") || op.equals("-")) return 1;
        if (op.equals("*")) return 2;
        return -1;
    }

    /**
     * Validates and changes a string into a mathematical expression
     * @param expIn : User input to be validated
     * @return Arraylist of expression to be computed || -1 if string is invalid
     */
    public static ArrayList<String> nums_ops(String expIn) {
        ArrayList<String> expression = new ArrayList<>();
        String digit = "";
        int i = 0;

        while (i < expIn.length()){
            if (isNumberChar(expIn.charAt(i))){
                while (i < expIn.length() && isNumberChar(expIn.charAt(i))){
                    digit += expIn.charAt(i);
                    i++;
                }
                expression.add(digit);
                digit = "";
            }
            else if (isOperatorChar(expIn.charAt(i))){
                expression.add(String.valueOf(expIn.charAt(i)));
                i++;
                if (i >= expIn.length() || isOperatorChar(expIn.charAt(i))){
                    expression.clear();
                    expression.add("-1");
                    return expression;
                }
            }
            else if (!isNumberChar(expIn.charAt(i)) && !isOperatorChar(expIn.charAt(i))){
                expression.clear();
                expression.add("-1");
                return expression;
            }
        }
        if (expression.size() <= 2) {
            expression.clear();
            expression.add("-1");
        }
        return expression;
    }

    /**
     * Checks if a character is a valid operator
     * @param op : char to be checked
     * @return true if valid, false otherwise
     */
    private static boolean isOperatorChar(char op) {
        return op == '+' || op == '-' || op == '*';
    }

    /**
     * Checks if a String is a valid operator
     * @param op : String to be checked
     * @return true if valid, false otherwise
     */
    private static boolean isOperatorString(String op) {
        return op.equals("+") || op.equals("-") || op.equals("*");
    }

    /**
     * Checks if a character is a number
     * @param num : char to be checked
     * @return true if valid, false otherwise
     */
    private static boolean isNumberChar(char num) {
        return num >= '0' && num <= '9';
    }

    /**
     * Checks if a String is a number
     * @param num : String to be checked
     * @return true if valid, false otherwise
     */
    private static boolean isNumberString(String num) {
        try {
            Integer.parseInt(num);
            return true;
        }
        catch(NumberFormatException e) {
            return false;
        }
    }
}
