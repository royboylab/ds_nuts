package server;

import org.omg.CORBA.ORB;
import org.omg.PortableServer.POA;
import org.omg.PortableServer.POAHelper;
import org.omg.CosNaming.*;

import calculator_module.Calculator;
import calculator_module.CalculatorHelper;

public class CalculatorServer {

    public static void main(String[] args) {
        try {
            // 1. Boot ORB
            ORB orb = ORB.init(args, null);

            // 2. Grab Root POA and activate it
            POA rootPoa = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
            rootPoa.the_POAManager().activate();

            // 3. Create servant, give it the ORB pointer
            CalculatorImpl servant = new CalculatorImpl();
            servant.setORB(orb);

            // 4. Turn servant into CORBA object reference
            org.omg.CORBA.Object ref = rootPoa.servant_to_reference(servant);
            Calculator calcRef = CalculatorHelper.narrow(ref);

            // 5. Register in Naming Service
            org.omg.CORBA.Object nsObj = orb.resolve_initial_references("NameService");
            NamingContextExt naming = NamingContextExtHelper.narrow(nsObj);
            NameComponent[] path = naming.to_name("Calculator");
            naming.rebind(path, calcRef);

            System.out.println("CalculatorServer ready...");

            // 6. Event loop
            orb.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("CalculatorServer exiting.");
    }
}
