package ServerI;



import java.net.*;
import java.io.*;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.UnicastRemoteObject;
import java.io.File;
import java.util.ArrayList;


public class Server extends UnicastRemoteObject implements ServerI{
    private static int serverPort = 3000;
    private static int RMI = 4000;


    public Server() throws RemoteException {
        super();
    }

    public void createUser(String nome,String password,String dep,int telefone,String morada,int ncc,String validade) throws RemoteException {
        System.out.println(nome+" "+password+"\n");

        File f1 = new File(nome);
        Boolean bool = f1.mkdir();

        if(bool){
            System.out.println("Folder is created successfully");
        }

    }

    public static void main(String args[]){
        int numero=0;

        try {

            Server server = new Server();
            Registry r = LocateRegistry.createRegistry(RMI);
            r.rebind("", server);
            System.out.println("RMI [Porto: "+RMI+"]");

            try (ServerSocket listenSocket = new ServerSocket(serverPort)) {

                System.out.println("Socket [Porto: "+serverPort+"]");
                //System.out.println("LISTEN SOCKET=" + listenSocket);

                while (true) {

                    Socket clientSocket = listenSocket.accept(); // BLOQUEANTE
                    System.out.println("CLIENT_SOCKET (created at accept())=" + clientSocket);
                    numero++;
                    new Connection(clientSocket, numero);
                }
            }
            catch (IOException e) {
                System.out.println("Listen:" + e.getMessage());
            }



        }catch (RemoteException re) {
        System.out.println("Exception in HelloImpl.main: " + re);
        }
    }
}

//= Thread para tratar de cada canal de comunicação com um cliente
class Connection extends Thread {
    DataInputStream in;
    DataOutputStream out;
    Socket clientSocket;
    int thread_number;

    public Connection (Socket aClientSocket, int numero) {
        thread_number = numero;
        try{
            clientSocket = aClientSocket;
            in = new DataInputStream(clientSocket.getInputStream());
            out = new DataOutputStream(clientSocket.getOutputStream());
            this.start();
        }catch(IOException e){System.out.println("Connection:" + e.getMessage());}
    }
    //=============================
    public void run(){
        String[] clientData;

        try {
            while(true){
                String data = in.readUTF();
                System.out.println(data);

                clientData = data.split(",",2);
                System.out.println(clientData[0]);
                System.out.println(clientData[1]);

                out.writeUTF("Sucesso");
            }
        } catch(EOFException e) {
            System.out.println("EOF:" + e);
        } catch(IOException e) {
            System.out.println("IO:" + e);
        }
    }
}