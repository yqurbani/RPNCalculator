/* Name: Yassin Qurbani

Course: CS 2500, Computer Programming II, Section 001

Instructor: Professor Shah

Date: 30 September 2024 */

import java.util.Scanner;

//PROGRAM: RPN Calculator 

/*
INPUT: The user of this program first inputs two numbers then a specific letter or opperator printed out by the menu to choose a specific action.

OUTPUT: This program prints the stack or updates it depending on what opperation the user chooses to do with the opperands. When the user answers with 'q', the program stops.


PRECONDITIONS and POSTCONDITIONS: None.
*/

public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        PostFixEvaluator evaluator = new PostFixEvaluator();
        evaluator.printHelp();
        boolean continueOps = true;

        while (continueOps) {
            String expression = in.nextLine();

            if (expression.isEmpty()){
              continue;
            } 

            if (expression.equalsIgnoreCase("q")) {
                System.out.print("See you next time!");
                continueOps = false;
                continue;
            }

            evaluator.evaluate(expression);
        }
        in.close();
    }
}