import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.InputMismatchException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        double principle = getInputFromUser("Principle");
        //Set to month rate and handling percentages
        double monthlyInterestRate =
                getInputFromUser("Annual Interest Rate") / (12*100);
        //Number of month payments
        double numberOfPayments = getInputFromUser("Period (Years)") * 12;

        BigDecimal result = countMonthlyMortgagePayment(principle,
                monthlyInterestRate,
                numberOfPayments);
        System.out.println("Yours mortgage payment is " + result + "$");
    }

    public static double getInputFromUser(String whatIsRequested) {
        boolean isSessionExists = true;
        double requestedValue = 0;
        while(isSessionExists) {
            isSessionExists = false;
            System.out.println("Enter " + whatIsRequested + ":");
            Scanner scanner = new Scanner(System.in);
            try {
                requestedValue = scanner.nextDouble();
            } catch (InputMismatchException e) {
                System.out.println("Entered input type has to be a number");
                isSessionExists = true;
            }
        }
        return requestedValue;
    }
    public static BigDecimal countMonthlyMortgagePayment(double principle,
                                                         double monthlyInterestRate,
                                                         double numberOfPayments) { //In months
        double poweredFactor = Math.pow(1+monthlyInterestRate,numberOfPayments);

        double result = principle*
                        (monthlyInterestRate*poweredFactor)/
                        (poweredFactor - 1);

        BigDecimal bd = new BigDecimal(result);
        return bd.setScale(2, RoundingMode.HALF_UP);
    }
}

