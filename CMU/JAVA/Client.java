import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Client {
    public static void main(String[] args) {
        String address = "localhost"; // Default address
        int port = 8001; // Default port

        // Check if address and port number are provided as arguments
        if (args.length == 2) {
            address = args[0];
            port = Integer.parseInt(args[1]);
        }

        Socket clientSocket = null;
        try {
            clientSocket = new Socket(address, port);
            BufferedReader in = new BufferedReader(
                    new InputStreamReader(clientSocket.getInputStream()));
            PrintWriter out = new PrintWriter(
                    new BufferedWriter(
                            new OutputStreamWriter(clientSocket.getOutputStream())));
            Scanner scanner = new Scanner(System.in);

            while (true) {
                System.out.print("Enter message to send (or 'QUIT' to exit): ");
                String outMessage = scanner.nextLine();

                if ("QUIT".equalsIgnoreCase(outMessage)) {
                    out.println(outMessage);
                    out.flush();
                    break;
                }

                out.println(outMessage);
                out.flush();

                String inMessage = in.readLine();
                System.out.println("Server says: " + inMessage);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (clientSocket != null) {
                try {
                    clientSocket.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
