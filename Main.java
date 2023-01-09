import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        final int MONTHS_IN_YEAR = 12;
        final int PERCENT = 100;

        final int MAX_LOAN = 1_000_000; //GBP
        final int MIN_LOAN = 50_000;
        final int MAX_TERM = 35;
        final int MIN_TERM = 5;
        final float MAX_RATE = 15f;
        final float MIN_RATE = 0.05f;

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
                System.out.println("Enter a whole Number");
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
                System.out.println("Enter a number with 2 decimal places");
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
                System.out.println("Enter a whole number.");
            }
        }

        double compoundRate = Math.pow((1+monthlyRate), termMonths);
        double monthlyRepayment = principal * monthlyRate * compoundRate / (compoundRate - 1);

        String outputFormatted = "£" + String.format("%.2f", monthlyRepayment);
        System.out.println("Monthly repayment = " + outputFormatted);

    }
}