import java.util.Random;
import java.util.Scanner;

public class HighOrLower {
    Scanner scanner;
    Usuario usuario;
    Gui gui;
    String [] cartasBlackJack;
    String [] cartasJugador = new String[2];
    Random random;


    public HighOrLower(Usuario usuario, Gui gui) {
        scanner = new Scanner(System.in);
        this.usuario = usuario;
        this.gui = gui;
        cartasBlackJack = new String[]{"A","2","3","4","5","6","7","8","9","J","Q","K"};
        random = new Random();
    }

    public void mostrarLogo(){
        System.out.println("""
                 _   _ _       _                    _                          \s
                | | | (_) __ _| |__     ___  _ __  | |    _____      _____ _ __\s
                | |_| | |/ _` | '_ \\   / _ \\| '__| | |   / _ \\ \\ /\\ / / _ \\ '__|
                |  _  | | (_| | | | | | (_) | |    | |__| (_) \\ V  V /  __/ |  \s
                |_| |_|_|\\__, |_| |_|  \\___/|_|    |_____\\___/ \\_/\\_/ \\___|_|  \s
                         |___/                                                 \s""");
    }

    public void menu() {
        mostrarLogo();
        System.out.println("Instrucciones:");
        System.out.println("""
                El objetivo del juego "High or Lower" es predecir si el próximo número\s
                será mayor o menor que el número actual.En caso de empate gana el casino\s
                El As vale 11 puntos""");
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
        int x;
        do {
            System.out.print("Cuanto quieres apostar:");
            x = scanner.nextInt();
            if (x > usuario.getDinero()){
                System.out.println("Te falta " + ( x - usuario.getDinero())+ " euros");
            }
        } while (x > usuario.getDinero());
        String opcion;
        boolean haGanado = false;
        int a = random.nextInt(cartasBlackJack.length);
        cartasJugador[0] = cartasBlackJack[a];
        System.out.println("Tu carta actual es "+ cartasJugador[0]);
        System.out.println("Pulsa 'z' si crees que la siguiente carta sera mayor");
        System.out.println("Pulsa 'x' si crees que la siguiente carta sera menor");
        opcion = scanner.next();
        int b = random.nextInt(cartasBlackJack.length);
        cartasJugador [1] = cartasBlackJack[b];
        int z = puntos(cartasJugador[0]);
        int n = puntos(cartasJugador[1]);

        if (z < n && opcion.equals("z")){
            haGanado = true;
        } else if (z > n && opcion.equals("z")) {
            haGanado = false;
        } else if (z > n && opcion.equals("x")){
            haGanado = true;
        } else if (z < n && opcion.equals("x")) {
            haGanado = false;
        }
       if (haGanado){
           usuario.actualizarDinero(x);
           System.out.println("Has ganado, " + "tu saldo ahora es de " + usuario.getDinero());
       } else {
           usuario.actualizarDinero(-x);
           System.out.println("Has perdido, " + "tu saldo ahora es de " + usuario.getDinero());
       }
        mostrarCartas();
        System.out.println("Presione 'x' para volver a jugar");
        System.out.println("Presione 'z' para volver al menu");
        if (scanner.next().equals("x")){
            apostar();
        } else {
            gui.menu();
        }
    }
        public void mostrarCartas(){
            System.out.println("Te ha salido " + cartasJugador[1] + "\n");
        }
    private int puntos(String s) {
        int puntosX;
        if (s.equals("A")){
            puntosX = 11;
        } else if (s.equals("J") || s.equals("Q") || s.equals("K")) {
            puntosX = 10;
        } else {
            puntosX = Integer.parseInt(s);
        }
        return puntosX;
    }
}
