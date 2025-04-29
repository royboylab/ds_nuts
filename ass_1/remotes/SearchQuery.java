package remotes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

/**
 * Concrete implementation exported as a single-object remote server.
 * UnicastRemoteObject:
 * • opens a ServerSocket
 * • generates a dynamic Stub (proxy)
 * • handles remote-call dispatch
 */
public class SearchQuery extends UnicastRemoteObject implements Search {

    // Mandatory zero-arg constructor that delegates to UnicastRemoteObject
    public SearchQuery() throws RemoteException {
        super();
    }

    @Override
    public String query(String search) throws RemoteException {
        // Toy search engine
        return "p2p".equalsIgnoreCase(search) ? "Found 1 result" : "No results found";
    }
}
