package server;

import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

import remotes.Search;
import remotes.SearchQuery;

public class SearchServer {

    public static void main(String[] args) {
        try {
            Search service = new SearchQuery();
            Registry reg = LocateRegistry.createRegistry(1099);

            Naming.rebind("rmi://localhost:1099/REMOTE_SEARCH", service);

            System.out.println("[SERVER] SearchServer ready");
        } catch (Exception e) {
            System.err.println("[SERVER] fatal: " + e);
        }
    }
}
