package ServerI;

import java.rmi.*;

public interface ServerI extends Remote {
    public void createUser(String nome,String password,String dep,int telefone,String morada,int ncc,String validade) throws RemoteException;

}