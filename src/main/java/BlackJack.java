import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

/**
 * Esta clase representa el juego de Blackjack.
 * Permite a un usuario jugar al Blackjack contra el crupier.
 */
public class BlackJack {
    Scanner scanner;
     Usuario usuario;
    Trabajadores trabajadores;
     Gui gui;
//     region cartas
    String [] cartasBlackJack;
    ArrayList<String> cartasBlackJackJugador;
    Random random;
    int puntos = 0;
//endregion
    /**
     * Constructor de la clase BlackJack.
     * @param usuario El usuario que jugará al Blackjack.
     * @param gui La interfaz de usuario para la presentación del juego.
     */
    public BlackJack(Usuario usuario,Gui gui) {
        scanner = new Scanner(System.in);
        this.usuario = usuario;
        this.gui = gui;
        trabajadores = new Trabajadores();
        random = new Random();
        cartasBlackJack = new String[]{"A","2","3","4","5","6","7","8","9","10","J","Q","K"};
        cartasBlackJackJugador = new ArrayList<>();

    }
    /**
     * Muestra el logo del juego de Blackjack.
     */
    private void mostrarLogo() {
        System.out.println("""
                 _     _            _    _            _   \s
                | |   | |          | |  (_)          | |  \s
                | |__ | | __ _  ___| | ___  __ _  ___| | __
                | '_ \\| |/ _` |/ __| |/ / |/ _` |/ __| |/ /
                | |_) | | (_| | (__|   <| | (_| | (__|   <\s
                |_.__/|_|\\__,_|\\___|_|\\_\\ |\\__,_|\\___|_|\\_\\
                                       _/ |               \s
                                      |__/                \s""");
    }
    /**
     * Muestra el menú inicial del juego de Blackjack.
     */
    public void menu() {
        mostrarLogo();
        System.out.println("Instrucciones:");
        System.out.println("""
                El objetivo del juego es conseguir una mano con un valor\s
                lo más cercano posible a 21 sin pasarse. Cada carta numérica tiene
                su valor nominal, las cartas del 2 al 10 valen su valor nominal,
                las figuras (J, Q, K) valen 10 puntos y el As vale 11 puntos.
                """);
        presentacion();
    }
    /**
     * Realiza una apuesta por parte del jugador.
     */
    private void apostar() {
        System.out.println("Hola me llamo " + trabajadores.obtenerTrabajador() + " sere tu crupier");
        darCartasBlackJackJugadorPrimera();
        darCartasBlackJackJugadorPrimera();
        System.out.println("Tus cartas: " + cartasBlackJackJugador + "[" + getPuntos() + "]");
        pregunta();
    }

    /**
     * Muestra el menú de presentación del juego de Blackjack.
     */
    public void presentacion() {
        String opcion;
        do {
            System.out.println("Pulsa 'c' para volver al menu");
            System.out.println("Pulsa 'x' para apostar");
            System.out.println("Pulsa 'z' para consultar tu saldo");

            opcion = scanner.next();
            if (opcion.equalsIgnoreCase("x")){
                gui.noDinero();
                apostar();
            } else if (opcion.equalsIgnoreCase("z")){
                System.out.println("Tienes " + usuario.getDinero() + " euros");
            }  else if (opcion.equalsIgnoreCase("c")) {
                gui.menu();
            }
        } while (!opcion.equalsIgnoreCase("x") || !opcion.equalsIgnoreCase("c"));
    }
/** Dar una carta al jugador*/
    public void darCartasBlackJackJugadorPrimera(){
        int x = random.nextInt(cartasBlackJack.length);
        cartasBlackJackJugador.add(cartasBlackJack[x]);
    }
/** Le da una carta al jugador y después le pregunta si quiere otra carta*/
    public void darCartasBlackJackJugador(){
        int x = random.nextInt(cartasBlackJack.length);
        cartasBlackJackJugador.add(cartasBlackJack[x]);
        System.out.println(cartasBlackJackJugador);
        pregunta();
    }
/** Pregunta si quieres una carta o plantarse*/
    public void pregunta() {
        if (puntuacion() <= 21) {
            System.out.println("Pulsa '1' para pedir");
            System.out.println("Pulsa '2' para plantarse");
            int opcion = scanner.nextInt();
            if (opcion == 2) {
                haGanado();
            } else if (opcion == 1) {
                darCartasBlackJackJugador();
            }
        } else {
            haGanado();
        }
    }
/** Si la carta es "A" vale 11 puntos, si es una figura vale 10 y si es una carta diferente vale su valor nominal. */
    private int puntuacion() {
        puntos = 0;
        for (String s : cartasBlackJackJugador) {
            if (s.equals("A")) {
                puntos += 11;
            } else if (s.equals("J") || s.equals("Q") || s.equals("K")){
                puntos += 10;
            } else {
                int x = Integer.parseInt(s);
                puntos += x;
            }
        }
        return puntos;
    }
    /** Verifica quien ha ganado o si ha habido un empate*/
    private void haGanado() {
        int puntosJugador = puntuacion();
        int puntosCupier = puntuacionTrabajador();

        System.out.println("Tu puntuación ha sido " + puntosJugador + " " + cartasBlackJackJugador);
        System.out.println("La puntuación del crupier ha sido " + puntosCupier);

        if (puntosJugador <= 21 && ( puntosJugador > puntosCupier || puntosCupier > 21)) {
            usuario.actualizarDinero(gui.getX());
            System.out.println("Has ganado, " + "tu saldo ahora es de " + usuario.getDinero());
            System.out.println();
        } else if (puntosJugador == puntosCupier) {
            System.out.println("Habéis empatado, " + "tu saldo es de " + usuario.getDinero());
        } else {
            usuario.actualizarDinero(-gui.getX());
            System.out.println("Has perdido, " + "tu saldo ahora es de " + usuario.getDinero());
            System.out.println();
        }
        cartasBlackJackJugador.clear();
        volver();
    }
/** Pregunta si quieres volver a jugar*/
    private void volver(){
        System.out.println("Presione 'z' para volver al menu");
        System.out.println("Presione 'x' para volver a jugar");
        String opcion = scanner.next();
        if (opcion.equals("x")){
            presentacion();
        } else if (opcion.equals("z")) {
            gui.menu();
        } else {
            volver();
        }
    }
/** Calcula los puntos del Cupier */
    private int puntuacionTrabajador() {
        int puntosT = 0;
        while (puntosT < 17) {
            int x = random.nextInt(cartasBlackJack.length);
            String carta = cartasBlackJack[x];
            if (carta.equals("A")) {
                puntosT += 11;
            } else if (carta.equals("J") || carta.equals("Q") || carta.equals("K")) {
                puntosT += 10;
            } else {
                puntosT += Integer.parseInt(carta);
            }
        }
        return puntosT;
    }
/** Devuelve la puntuación del jugador*/
    public int getPuntos() {
        return puntuacion();
    }

}

