import java.util.Scanner;

public class Main {
    
    // useful constants
    private static final int MONTHS_IN_YEAR = 12;
    private static final int PERCENT = 100;
    // min and max permitted values
    private static final int MAX_LOAN = 1_000_000; //GBP
    private static final int MIN_LOAN = 50_000;
    private static final int MAX_TERM = 35;
    private static final int MIN_TERM = 5;
    private static final float MAX_RATE = 15f;
    private static final float MIN_RATE = 0.05f;
    
    public static void main(String[] args) {

        // initialise variables
        
        float principal = 0;
        float yearlyRate = 0;
        float monthlyRate = 0;
        int termYears = 0;
        int termMonths = 0;

        Scanner inputs = new Scanner(System.in);

        // Enter the loan amount ('Principal')

        while (principal < MIN_LOAN | principal > MAX_LOAN) {
            System.out.println("Enter Loan Amount between £50,000 - £1,000,000 : ");
            String input = inputs.nextLine();
            try {
                principal = Integer.parseInt(input);
            }
            catch (NumberFormatException e) {
                System.out.print("Enter a whole Number");
            }
        }

        // Enter the yearly interest rate

        while (yearlyRate < MIN_RATE | yearlyRate > MAX_RATE){
            System.out.println("Enter Annual Interest Rate between 0.05% and 15.00%: ");
            String input = inputs.nextLine();
            try {
                yearlyRate = Float.parseFloat(input);
                monthlyRate = yearlyRate / (MONTHS_IN_YEAR * PERCENT);
            }
            catch (NumberFormatException e) {
                System.out.print("Enter a number with 2 decimal places");
            }
        }

        // Enter the term of the loan (years)

        while (termYears < MIN_TERM | termYears > MAX_TERM){
            System.out.println("Enter the term of the loan (5 - 35 Years Inclusive).: ");
            String input = inputs.nextLine();
            try {
                termYears = Integer.parseInt(input);
                termMonths = termYears * 12;
            }
            catch (NumberFormatException e) {
                System.out.print("Enter a whole number.");
            }
        }

        double monthlyRepayment = calculateMonthlyPayment(monthlyRate, termMonths, principal);
        double totalCostLoan = calculateTotalPayment(monthlyRepayment, termYears);
        System.out.println("Monthly repayment = £" + String.format("%.2f", monthlyRepayment));
        System.out.println("Total Cost of Loan = £" + String.format("%.2f", totalCostLoan));
        
    }


    private static double calculateMonthlyPayment(float mRate, float mTerm, float amount ){
        double compoundRate = Math.pow((1+mRate), mTerm);
        double monthlyRepayment = amount * mRate * compoundRate / (compoundRate - 1);
        return monthlyRepayment;
    }

    private static double calculateTotalPayment(double monthlyPayment, int termInYears){
        double totalPayment = monthlyPayment * MONTHS_IN_YEAR * termInYears;
        return totalPayment;
    }

}
