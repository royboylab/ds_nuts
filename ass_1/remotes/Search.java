package remotes;

import java.rmi.Remote;
import java.rmi.RemoteException;

/**
 * A pure interface that the client compiles against.
 * Every method must:
 * • be public
 * • declare RemoteException
 * • return a serialisable type
 */
public interface Search extends Remote {
    String query(String search) throws RemoteException;
}
