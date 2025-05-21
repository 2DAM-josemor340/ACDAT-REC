import java.io.File;

public class GestionarSesiones {
    public static void main(String[] args) {
        GestionarSAX gestor = new GestionarSAX();
        gestor.procesar("res" + File.separator + "users.txt");
    }
}
