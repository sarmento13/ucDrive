package ServerI;

import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.util.Scanner;

public class Admin {

    public static void main(String args[]) {

        try(Scanner sc = new Scanner(System.in)){

            ServerI h = (ServerI) LocateRegistry.getRegistry(4000).lookup("");

            while(true){

                System.out.print("----- Menu Admin -----\n");
                System.out.print("1 - Registar utilizador\n");
                System.out.print("2 - Listar diretorias de utlizador\n");
                System.out.print("3 - Failover\n");
                System.out.print("4 - Armazenamento\n");
                System.out.print("5 - Replicacao dos dados entre os vários servidores\n");
                int option = sc.nextInt();

                if(option == 1){
                    System.out.print("Username: ");
                    String username = sc.next();
                    System.out.print("\nPassword: ");
                    String password = sc.next();
                    System.out.print("\nDep: ");
                    String dep = sc.next();
                    System.out.print("\nTelefone: ");
                    int telefone = sc.nextInt();
                    System.out.print("\nMorada: ");
                    String morada = sc.next();
                    System.out.print("\nNumero CC: ");
                    int ncc = sc.nextInt();
                    System.out.print("\nValidade CC: ");
                    String validade = sc.next();

                    h.createUser(username,password,dep,telefone,morada,ncc,validade);

                }

            }

        } catch (Exception e) {
            System.out.println("Exception in main: " + e);
            e.printStackTrace();
        }

    }

}