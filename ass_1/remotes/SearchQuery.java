package remotes;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class SearchQuery extends UnicastRemoteObject implements Search {

    public SearchQuery() throws RemoteException {
        super();
    }

    @Override
    public String query(String search) throws RemoteException {

        return "p2p".equalsIgnoreCase(search) ? "Found 1 result" : "No results found";
    }
}
