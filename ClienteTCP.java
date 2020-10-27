import java.io. * ;
import java.net. * ;

public class ClienteTCP {
    public static void main(String[] args) throws IOException {
        System.out.println("---cliente---");

        Socket socketClienteTCP = null;
        PrintWriter out = null;
        BufferedReader in =null;

        try {
            socketClienteTCP = new Socket("localhost", 9876);
            out = new PrintWriter(socketClienteTCP.getOutputStream(), true); in =new BufferedReader(new InputStreamReader(
                    socketClienteTCP.getInputStream()));
        } catch(Exception e) {
            System.out.println("Error en cliente: " + e.getMessage());
        }

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System. in ));
        String cad;

        while ((cad = stdIn.readLine()) != null) {
            out.println(cad);
        }

        out.close(); in .close();
        stdIn.close();
        socketClienteTCP.close();
    }
}