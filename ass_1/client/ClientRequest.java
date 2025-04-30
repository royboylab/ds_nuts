package client;

import java.util.*;
import java.rmi.Naming;
import remotes.Search;

public class ClientRequest {

    public static void main(String[] args) {
        try {
            Scanner sc = new Scanner(System.in);

            String url = "rmi://localhost:1099/REMOTE_SEARCH";
            Search stub = (Search) Naming.lookup(url);

            System.out.println("Enter string");
            String term = sc.next();

            String result = stub.query(term);
            System.out.println("[CLIENT] Result → " + result);
            sc.close();

        } catch (Exception e) {
            System.err.println("[CLIENT] fatal: " + e);
        }
    }
}
