package client;

import java.util.Scanner;

import org.omg.CORBA.ORB;
import org.omg.CosNaming.*;

import calculator_module.Calculator;
import calculator_module.CalculatorHelper;

public class CalculatorClient {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        try {
            ORB orb = ORB.init(args, null);

            org.omg.CORBA.Object nsObj = orb.resolve_initial_references("NameService");
            NamingContextExt naming = NamingContextExtHelper.narrow(nsObj);
            Calculator calc = CalculatorHelper.narrow(naming.resolve_str("Calculator"));

            System.out.println("Connected to remote Calculator.");
            System.out.println("Enter two integers:");

            while (true) {
                System.out.print("\nfirst  number  (or q to quit) : ");
                if (!in.hasNextInt())
                    break;
                int a = in.nextInt();

                System.out.print("second number  (or q to quit) : ");
                if (!in.hasNextInt())
                    break;
                int b = in.nextInt();

                System.out.println("a + b = " + calc.add(a, b));
                System.out.println("a - b = " + calc.subtract(a, b));
                System.out.println("a * b = " + calc.multiply(a, b));
                try {
                    System.out.println("a / b = " + calc.divide(a, b));
                } catch (Exception ex) {
                    System.out.println("a / b = **error** (" + ex.getMessage() + ")");
                }
            }

            System.out.println("Bye!");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            in.close();
        }
    }
}
