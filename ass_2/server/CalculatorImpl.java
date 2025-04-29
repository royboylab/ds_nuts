package server;

import org.omg.CORBA.ORB;
import calculator_module.CalculatorPOA;

public class CalculatorImpl extends CalculatorPOA {

    private ORB orb;

    public void setORB(ORB orb) {
        this.orb = orb;
    }

    @Override
    public int add(int a, int b) {
        return a + b;
    }

    @Override
    public int subtract(int a, int b) {
        return a - b;
    }

    @Override
    public int multiply(int a, int b) {
        return a * b;
    }

    @Override
    public int divide(int a, int b) {
        return a / b;
    }

    @Override
    public void shutdown() {
        orb.shutdown(false);
    }
}
