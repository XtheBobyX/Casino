import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Trabajadores {
    ArrayList <String> trabajadores;
    private String[] nombres = {"Karla", "Juan", "Maria", "Pedro", "Luis", "Ana", "Diego", "Sofia", "Pablo", "Elena", "Carlos", "Laura", "Boby", "Julia"};

    public Trabajadores() {
        trabajadores = new ArrayList<>();
        trabajadores.addAll(List.of(nombres));
    }

    public ArrayList<String> getTrabajadores() {
        return trabajadores;
    }

    public String obtenerTrabajador(){
        Random random = new Random();
        int x = random.nextInt(trabajadores.size());
        return trabajadores.get(x);
    }
}
