package ServerI;

import java.io.*;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client {
    private static int serversocket = 3000;


    public static void main(String[] var0) {
        if (var0.length == 0) {
            System.out.println("java TCPClient hostname");
            System.exit(0);
        }

        try {
            Socket var1 = new Socket(var0[0], serversocket);

            try {
                System.out.println("SOCKET=" + var1);
                DataInputStream var2 = new DataInputStream(var1.getInputStream());
                DataOutputStream var3 = new DataOutputStream(var1.getOutputStream());

                try (Scanner sc = new Scanner(System.in)){
                    while(true) {
                        System.out.print("Username: ");
                        String username = sc.nextLine();
                        System.out.print("Password: ");
                        String password = sc.nextLine();

                        var3.writeUTF(username + "," + password);

                        String var6 = var2.readUTF();

                        if (var6.compareTo("Sucesso") == 0) {

                            while (true) {

                                System.out.println("----- Menu Cliente -----");
                                System.out.println("1 - Alterar password");
                                System.out.println("2 - Configurar enderecos e portos");
                                System.out.println("3 - Listar ficheiros da diretoria do servidor");
                                System.out.println("4 - Alterar diretoria atual do servidor");
                                System.out.println("5 - Listar ficheiros da diretoria atual do cliente");
                                System.out.println("6 - Alterar diretoria atual do cliente");
                                System.out.println("7 - Descarregar ficheiro do servidor");
                                System.out.println("8 - Carregar ficheiro para o servidor");
                                System.out.println("9 - Desconectar");

                                int option = sc.nextInt();

                                if (option == 1) {
                                    System.out.print("Nova password: ");
                                    String new_pass = sc.nextLine();
                                    var3.writeUTF(new_pass + "," + Integer.toString(option));


                                }


                                File f = new File("./");
                                File[] list = f.listFiles();

                                if (list != null) {
                                    for (File files : list) {
                                        System.out.println(files.getName());
                                    }
                                } else {
                                    System.out.println("ola");
                                }
                            }
                        }
                    }
                } catch (Throwable var9) {

                    throw var9;
                }
            } catch (Throwable var10) {
                try {
                    var1.close();
                } catch (Throwable var7) {
                    var10.addSuppressed(var7);
                }

                throw var10;
            }
        } catch (UnknownHostException var11) {
            System.out.println("Sock:" + var11.getMessage());
        } catch (EOFException var12) {
            System.out.println("EOF:" + var12.getMessage());
        } catch (IOException var13) {
            System.out.println("IO:" + var13.getMessage());
        }

    }
}

