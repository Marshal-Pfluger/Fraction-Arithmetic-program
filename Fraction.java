//********************************************************************
//
//  Developer:     Marshal Pfluger
//
//  Project #:     Six
//
//  File Name:     Fraction.java
//
//  Course:        COSC 4301 Modern Programming
//
//  Due Date:      04/07/2023
//
//  Instructor:    Prof. Fred Kumi 
//
//  Java Version:  17.0.4.1
//
//  Description:   Fraction class to hold fraction objects and execute fraction arithmetic.  
//
//********************************************************************

// Start Fraction class
public class Fraction {
	private int numerator;
	private int denominator;
	
	public Fraction(int numerator, int denominator) {
		this.numerator = numerator;
		this.denominator = denominator;
		
		//fractionReduction();
	}
	
	public void setNumerator(int numerator) {
		this.numerator = numerator;
		
	}
	
	public void setDenominator(int denominator) {
		this.denominator = denominator;
		
	}
	
	public int getNumerator() {
		return this.numerator;
	}
	
	public int getDenominator() {
		return this.denominator;
	}
	
	   //***************************************************************
	   //
	   //  Method:       fractionMenu
	   // 
	   //  Description:  Returns a string of the menu options of the class
	   //
	   //  Parameters:   N/A
	   //
	   //  Returns:      String menu
	   //
	   //**************************************************************
	public String fractionMenu() {
		// Create menu string to return to call location
		String menu = "Select an operation:\n" +
                      "1. Add two fractions\n" +
                      "2. Subtract two fractions\n" +
                      "3. Multiply two fractions\n" +
                      "4. Divide two fractions\n" +
                      "5. Print the fractions\n" +
                      "6. Back to fraction entry\n";
		return menu;
	}// End fraction menu method
	
	   //***************************************************************
	   //
	   //  Method:       fractionReduction
	   // 
	   //  Description:  calls GCD function to reduce fraction
	   //
	   //  Parameters:   N/A
	   //
	   //  Returns:      N/A
	   //
	   //**************************************************************
    public void fractionReduction() {
    	// Calculate GCD
    	int greatestCommonDiv = calculateGcd(this.numerator, this.denominator);
    	try {
    		// Reduce the fraction
    		this.numerator /= greatestCommonDiv;
        	this.denominator /= greatestCommonDiv;
        	// If fraction is negative make sure negative sign is in right place
    		if (numerator < 0 || denominator < 0) {
    			this.numerator = Math.abs(this.numerator);
    			this.denominator = Math.abs(this.denominator);
            	this.numerator *= -1;
    		}
        // catch divide by 0 exception
    	} catch (ArithmeticException e) {
    		greatestCommonDiv = 0;
    	}
    	
    }// End fractionReduction method
    
	   //***************************************************************
	   //
	   //  Method:       fractionReduction
	   // 
	   //  Description:  calls GCD function to reduce fraction
	   //
	   //  Parameters:   N/A
	   //
	   //  Returns:      Int GCD
	   //
	   //**************************************************************
    public int calculateGcd(int numerator, int denominator) {
    	// Initialize GCD variable
    	int finalGCD = 0;
    	// Calculate GCD if the denominator is not zero
    	if (denominator != 0) {
    		finalGCD = calculateGcd(denominator, numerator % denominator);
    	}
    	else {
    		finalGCD = numerator;
    	}
    	return finalGCD;
    }// End calculateGcd method
    
	   //***************************************************************
	   //
	   //  Method:       leastCommonMulCalc
	   // 
	   //  Description:  calculates the least common multiple of a fraction
	   //
	   //  Parameters:   Fraction secondFraction
	   //
	   //  Returns:      int leastCommonMul
	   //
	   //**************************************************************
	public int leastCommonMulCalc(Fraction secondFraction) {
		// Return the least common multiple to call location
		return this.denominator * secondFraction.denominator / calculateGcd(this.denominator, secondFraction.denominator);
	}// End leastCommonMulCalc method

	   //***************************************************************
	   //
	   //  Method:       addFraction
	   // 
	   //  Description:  adds two fractions
	   //
	   //  Parameters:   Fraction secondFraction
	   //
	   //  Returns:      Fraction solution
	   //
	   //**************************************************************
	public Fraction addFraction(Fraction secondFraction) {
		// Use least common multiple to add fractions
        int leastCommonMul = leastCommonMulCalc(secondFraction);
        int newNumerator = this.numerator * (leastCommonMul / this.denominator) + secondFraction.numerator * (leastCommonMul / secondFraction.denominator);
        // Create new Fraction object for solution
		Fraction result = new Fraction(newNumerator, leastCommonMul);
		// Call fraction reduction on the new fraction.
		result.fractionReduction();
		return result;
	}// End addFraction method
	
	   //***************************************************************
	   //
	   //  Method:       subtractFraction
	   // 
	   //  Description:  subtracts two fractions
	   //
	   //  Parameters:   Fraction secondFraction
	   //
	   //  Returns:      Fraction solution
	   //
	   //**************************************************************
	
	public Fraction subtractFraction(Fraction secondFraction) {
		// use the least common multiple to subtract functions
		int leastCommonMul = leastCommonMulCalc(secondFraction);
        int newNumerator = this.numerator * (leastCommonMul / this.denominator) - secondFraction.numerator * (leastCommonMul / secondFraction.denominator);
        // Create new Fraction object for solution
		Fraction result = new Fraction(newNumerator, leastCommonMul); 
		// Call fraction reduction on the new fraction. 
		result.fractionReduction();
		return result;
	}// End subtractFraction method
	
	   //***************************************************************
	   //
	   //  Method:       multiplyFraction
	   // 
	   //  Description:  multiplies two fractions
	   //
	   //  Parameters:   Fraction secondFraction
	   //
	   //  Returns:      Fraction solution
	   //
	   //**************************************************************
	public Fraction multiplyFraction(Fraction secondFraction) {
		// Multiply the tops and bottoms
		int numerator = this.numerator * secondFraction.numerator;
		int denominator = this.denominator * secondFraction.denominator;
		// Create new Fraction object for solution
		Fraction result = new Fraction(numerator, denominator);
		// Call fraction reduction on the new fraction. 
		result.fractionReduction();
		return result;
	}// End multiplyFraction method
	
	   //***************************************************************
	   //
	   //  Method:       divideFraction
	   // 
	   //  Description:  divides two fractions
	   //
	   //  Parameters:   Fraction secondFraction
	   //
	   //  Returns:      Fraction solution
	   //
	   //**************************************************************
	public Fraction divideFraction(Fraction secondFraction) {
		// Flip the second and multiply for dividing fractions
		int numerator = this.numerator * secondFraction.denominator;
		int denominator = this.denominator * secondFraction.numerator;
		// Create new Fraction object for solution
		Fraction result = new Fraction(numerator, denominator); 
		// Call fraction reduction on the new fraction. 
		result.fractionReduction();
		return result;
	}// End divideFraction method
	
	   //***************************************************************
	   //
	   //  Method:       toString
	   // 
	   //  Description:  returns string form of the fraction
	   //
	   //  Parameters:   N/A
	   //
	   //  Returns:      String displayString
	   //
	   //**************************************************************
    public String toString() {
    	// call displayImproper to get formatted string
        return displayImproper();
    }// End toString Method

	   //***************************************************************
	   //
	   //  Method:       displayImproper
	   // 
	   //  Description:  returns string form of the fraction and accounts for improper fractions
	   //
	   //  Parameters:   N/A
	   //
	   //  Returns:      String displayString
	   //
	   //**************************************************************
    public String displayImproper() {
    	// Declare variable to hold string to return to toString
		String displayString;
		// If fraction is improper display improper fraction
        if (Math.abs(numerator) >= Math.abs(denominator)) {
            int wholeNumber = numerator / denominator;
            int remainder = numerator % denominator;
            // If it is a whole number
            if (remainder == 0) {
                displayString = String.format("%d\n", wholeNumber);
            } else {
            	// If it is a negative improper
            	if (numerator < 0 || denominator < 0) {
            		displayString = String.format("%d %d/%d\n", wholeNumber, Math.abs(remainder), Math.abs(denominator));
            	// If it is a positive improper
            	}else {
            		displayString = String.format("%d %d/%d\n", wholeNumber, remainder, denominator);
            	}   
            }
        // If numerator is zero answer will be zero
        } else if(numerator == 0) {
        	displayString = String.format("0");
        // If not improper fraction 
        }else {
			displayString = String.format("The Fraction is not an improper fraction.\n %d/%d\n", numerator, denominator);
        }
        return displayString;
    }// End displayImproper method	
}// End class
