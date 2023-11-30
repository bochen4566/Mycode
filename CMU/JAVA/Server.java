import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server {
    public static void main(String[] args) {
        int port = 8001;
        if (args.length == 1) {
            port = Integer.parseInt(args[0]);
        }

        ServerSocket serverSocket = null;
        try {
            serverSocket = new ServerSocket(port);
            while (true) {
                Socket clientConnection = serverSocket.accept();
                handleClient(clientConnection);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (serverSocket != null) {
                try {
                    serverSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void handleClient(Socket clientConnection) throws IOException {
        Scanner in = new Scanner(clientConnection.getInputStream());
        PrintWriter out = new PrintWriter(
                new BufferedWriter(new OutputStreamWriter(clientConnection.getOutputStream())));

        while (true) {
            String message = in.nextLine();
            System.out.println("Client says: " + message);

            if ("QUIT".equalsIgnoreCase(message)) {
                break;
            }

            Scanner replyScanner = new Scanner(System.in);
            System.out.print("Enter reply message: ");
            String reply = replyScanner.nextLine();

            out.println(reply);
            out.flush();
        }

        clientConnection.close();
    }
}
