package client;

import java.rmi.Naming;
import remotes.Search;

/**
 * Thin client that:
 * 1. looks up the remote stub in the registry
 * 2. invokes query(...) as if the object were local
 * (the call is transparently marshalled across the network)
 */
public class ClientRequest {

    public static void main(String[] args) {
        try {
            String term = args.length == 0 ? "p2p" : args[0];

            String url = "rmi://localhost:1099/REMOTE_SEARCH";
            Search stub = (Search) Naming.lookup(url);

            String result = stub.query(term);
            System.out.println("[CLIENT] Result → " + result);
        } catch (Exception e) {
            System.err.println("[CLIENT] fatal: " + e);
        }
    }
}
