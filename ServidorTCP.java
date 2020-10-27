import java.net. * ;
import java.io. * ;

public class ServidorTCP {
    public static void main(String[] args) throws IOException {
        BaseDeDatos db = new BaseDeDatos();
        StringBuilder menuBuilder = new StringBuilder("MENU\n").append("1. Guardar\n").append("2. Verificar\n").append("3. Salir");
        ServerSocket socketServidorTCP = null;

        try {
            socketServidorTCP = new ServerSocket(9876);
        }
        catch(IOException e) {
            System.out.println("Error al crear socket: " + e.getMessage());
            System.exit(1);
        }

        Socket clientSocket = null;
        System.out.println(menuBuilder);

        try {
            clientSocket = socketServidorTCP.accept();
        }
        catch(IOException e) {
            System.err.println("Error al aceptar la conexion: " + e.getMessage());
            System.exit(1);
        }

        PrintWriter out = new PrintWriter(clientSocket.getOutputStream(), true);
        BufferedReader in =new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));

        String cad;

        while ((cad = in.readLine()) != null) {
            System.out.println(cad);
            out.println(cad);

            if ("1".equals(cad)) {
                System.out.println("GUARDAR");
                System.out.println("> Ingrese su nombre y su C.I.: ");
                String[] nombreCarnet = in.readLine().split(" ");
                db.guardar(nombreCarnet[0], nombreCarnet[1]);

                System.out.println("Se guardo con exito :)");
                System.out.println(menuBuilder);
            }
            if ("2".equals(cad)) {
                System.out.println("VERIFICAR");
                System.out.println("> Ingrese su C.I.: ");
                String carnet = in.readLine();
                // TODO: Verificar en base de datos
                boolean encontrado = db.buscar(carnet);
                if (encontrado) {
                    System.out.println("Nombre: " + db.getNombre());
                    System.out.println("C.I.: " + db.getCarnet());
                    System.out.println("FechaRegistro: " + db.getFechaRegistro());
                    System.out.println("Puerto: " + socketServidorTCP.getLocalPort());
                } else {
                    System.out.println("Usted on se encuentra en la BBBDD");
                    System.out.println(menuBuilder);
                }
            }
            if ("3".equals(cad)) {
                break;
            }
        }
        out.close(); in .close();
        clientSocket.close();
        socketServidorTCP.close();
    }
}