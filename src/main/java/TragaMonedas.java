import java.util.Random;
import java.util.Scanner;

public class TragaMonedas {
    Scanner scanner;
    Usuario usuario;
    Gui gui;
    String [] simbolos;
    int x,q,w,e;


    public TragaMonedas(Usuario usuario, Gui gui) {
        scanner = new Scanner(System.in);
        this.usuario = usuario;
        this.gui = gui;
        simbolos = new String[]{"X","A","7","K","J","#","$","&","L","O","X","A"};
        q = 0;
        w = 0;
        e = 0;
        x = 0;
    }

    private void mostrarLogo(){
        System.out.println("""
                 _____ ___    _   ___   _   __  __  ___  _  _ ___ ___   _   ___\s
                |_   _| _ \\  /_\\ / __| /_\\ |  \\/  |/ _ \\| \\| | __|   \\ /_\\ / __|
                  | | |   / / _ \\ (_ |/ _ \\| |\\/| | (_) | .` | _|| |) / _ \\\\__ \\
                  |_| |_|_\\/_/ \\_\\___/_/ \\_\\_|  |_|\\___/|_|\\_|___|___/_/ \\_\\___/""");
    }

    public void menu() {
        mostrarLogo();
        System.out.println("Instrucciones:");
        System.out.println("""
                El objetivo del juego es conseguir una combinación ganadora tras\s
                girar los rodillos si logras una combinación de tres símbolos iguales ganas el
                triple de lo que hayas apostado""");
        presentacion();
    }

    private void presentacion() {
        String opcion;
        do{
            System.out.println("\nPulsa 'c' para volver al menu");
            System.out.println("Pulsa 'z' para consultar tu saldo");
            System.out.println("Pulsa 'x' para apostar");
            opcion = scanner.next();
            if (opcion.equalsIgnoreCase("x")){
                if (usuario.getDinero() > 0) {
                    apostar();
                } else {
                    System.out.println("Estas en bancarrota");
                    gui.registro();
                }
            } else if (opcion.equalsIgnoreCase("z")){
                System.out.println("Tienes " + usuario.getDinero() + " euros");
            } else if (opcion.equalsIgnoreCase("c")) {
                gui.menu();
            }
        } while (!opcion.equalsIgnoreCase("x") || !opcion.equalsIgnoreCase("c"));
    }

    private void apostar() {
        do {
            System.out.print("Cuanto quieres apostar:");
            x = scanner.nextInt();
            if (x > usuario.getDinero()){
                System.out.println("Te falta " + ( x - usuario.getDinero())+ " euros");
            }
            if (usuario.getDinero() == 0){gui.registro();}
        } while (x > usuario.getDinero());
        Random random = new Random();
         q = random.nextInt(1,simbolos.length - 1);
         w = random.nextInt(1,simbolos.length - 1);
         e = random.nextInt(1,simbolos.length - 1);
        System.out.println("    | " + simbolos[q - 1] + " " + simbolos [w - 1] + " "+ simbolos[e - 1] + " |");
        System.out.println("--> " + "| " + simbolos[q] + " " + simbolos [w ] + " "+ simbolos[e] + " |");
        System.out.println("    | " + simbolos[q + 1] + " " + simbolos [w + 1] + " "+ simbolos[e + 1] + " |");

        haGanado();
    }

    private void haGanado() {
        if (q == w && q == e){
            System.out.println("Has ganado");
            usuario.actualizarDinero(x * 3);
        } else {
            System.out.println("Has perdido");
            usuario.actualizarDinero(- x);
        }
        System.out.println("Ahora tienes " + usuario.getDinero());

        System.out.println("Presione 'x' para volver a jugar");
        System.out.println("Presione 'z' para volver al menu");
        if (scanner.next().equals("x")){
            apostar();
        } else {
            gui.menu();
        }
    }
}
