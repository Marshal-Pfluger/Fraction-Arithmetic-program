//********************************************************************
//
//  Developer:     Marshal Pfluger
//
//  Project #:     Six
//
//  File Name:     Program6.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      04/07/2023
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  17.0.4.1
//
//  Description:   Test class for the fraction class  
//
//********************************************************************

//Import libraries
import java.util.Scanner;

public class Program6 {
    public static void main(String[] args) {
    	// Instantiate object to call non static methods
    	Program6 obj = new Program6();
    	obj.developerInfo();
    	obj.runTest();
    }// End main 
    
//***************************************************************
//
//  Method:       runTest
// 
//  Description:  runs the program  
//
//  Parameters:   N/A
//
//  Returns:      N/A
//
//**************************************************************
    public void runTest() {
    	// Declare fractions for program
    	Fraction frac1;
    	Fraction frac2;
    	do {
    		// Have user enter first fraction
    		printOutput("Please enter the first fraction in the format 'Numerator/Denominator'\nenter 0/0 to exit");
    		// Send first fraction to fraction builder
    		frac1 = fractionBuilder();
    		// If user enters 0/0 do not ask for second fraction
    		if (frac1.getNumerator() != 0 || frac1.getDenominator() != 0) {
    			// Have user enter second fraction
    			printOutput("Please enter the second fraction in the format 'Numerator/Denominator'");
    			// Send second fraction to fraction builder
    			frac2 = fractionBuilder();
    			// Call menu switch to allow user to select operations
    			menuSwitch(frac1, frac2);
    		}
        // Exit loop if user enters 0/0
    	} while(frac1.getNumerator() != 0 || frac1.getDenominator() != 0);
    }// End runTest method
    
//***************************************************************
//
//  Method:       fractionBuilder
// 
//  Description:  prompts user for input and parses user responses  
//
//  Parameters:   N/A
//
//  Returns:      Fraction fraction
//
//**************************************************************
    public Fraction fractionBuilder() {
    	// Declare variable for input validation
    	boolean methodState = false;
    	// Declare variables for input parsing 
    	int numerator = 0;
    	int denominator = 0;
    	// Loop if input is invalid
    	do {
    		// Get string from user choice method
        	String fractionStrng = userChoice();
        	// Split string into numerator and denominator
        	String[] fullFraction = fractionStrng.split("/");
    		try {
    			// Parse input from user and store in num and den
            	numerator = Integer.parseInt(fullFraction[0]);
            	denominator = Integer.parseInt(fullFraction[1]);
            	// Check input for validity
            	methodState = inputValidation(numerator, denominator);
            // Catch exceptions loop through again if invalid or error occurs 
    		}catch(ArrayIndexOutOfBoundsException | ArithmeticException | NumberFormatException e) {
    			System.out.println("**Entry error, please try again**");
    			methodState = true;
    		}
        // End loop if input is valid
    	}while (methodState);
    	return new Fraction(numerator, denominator);
    }// End Fraction builder method
    
//***************************************************************
//
//  Method:       menuSwitch
// 
//  Description:  takes user choice from CashRegister displayMenu method to make selections. Will allow the user to perform different operations on the same two fractions 
//
//  Parameters:   N/A
//
//  Returns:      String file
//
//**************************************************************
	public void menuSwitch(Fraction frac1, Fraction frac2) {
		String userChoice;
		do {
			// Display the fraction menu
			printOutput(frac1.fractionMenu());
			// Get users choice for menu
			userChoice = userChoice();
			// Add fractions
			if(userChoice.equals("1")) {
			    Fraction sum = frac1.addFraction(frac2);
			    printOutput(sum.toString());
			// Subtract fractions
			}else if(userChoice.equals("2")){
				Fraction difference = frac1.subtractFraction(frac2);
				printOutput(difference.toString());
			// Multiply fractions
			}else if(userChoice.equals("3")) {
				Fraction product = frac1.multiplyFraction(frac2);
				printOutput(product.toString());
			// Divide fractions
			}else if(userChoice.equals("4")) {
				Fraction quotient = frac1.divideFraction(frac2);
				printOutput(quotient.toString());
			// Display current fractions
			}else if(userChoice.equals("5")) {
				printOutput("Fraction one: " + frac1.toString() + "\nFraction two: " + frac2.toString());
			}
			// Exit loop and return to fraction entry if user enters 6
			} while (!userChoice.equals("6"));
	}// End menuSwitch method
	
//***************************************************************
//
//  Method:       printOutput
// 
//  Description:  Prints the output from the user selected operation 
//
//  Parameters:   N/A
//
//  Returns:      N/A
//
//**************************************************************
	public void printOutput(String outputString) {
		// print nice looking output for user
		System.out.println("\n*************************\n");
		System.out.println(outputString);
	}// End printOutput method
    
//***************************************************************
//
//  Method:       userChoice
// 
//  Description:  gets the inventory file from user (Path). 
//
//  Parameters:   N/A
//
//  Returns:      String file
//
//**************************************************************
public String userChoice() {
	// Use Scanner to receive user input
	Scanner userInput = new Scanner(System.in);
	// Inform user of interaction
	//System.out.println("Please enter a fraction in the format 'Numerator/Denominator'");
	String userChoice = userInput.nextLine();
	// close scanner when program exits. 
	if (userChoice == "0/0") {
		userInput.close();
	}
	System.out.println();
	return userChoice;
}// End userChoice
    
    
//***************************************************************
//
//  Method:       inputValidation
// 
//  Description:  validates the input for the program 
//
//  Parameters:   N/A
//
//  Returns:      Boolean
//
//**************************************************************
    public boolean inputValidation(int numerator, int denominator) {
    	boolean validity = false;
    	// Validate input for divide by zero
    	if (numerator != 0 & denominator == 0) {
    		validity = true;
    		System.out.println("**Cannot divide by zero, please try again**");
    	}
    	return validity;
    }// End inputValidation method

        
//***************************************************************
//
//  Method:       developerInfo (Non Static)
// 
//  Description:  The developer information method of the program
//
//  Parameters:   None
//
//  Returns:      N/A 
//
//**************************************************************
    public void developerInfo()
    {
    	System.out.println("Name:    Marshal Pfluger");
	    System.out.println("Course:  COSC 4301 Modern Programming");
	    System.out.println("Project: Six\n\n");
	    } // End of the developerInfo method
}