import java.util.Scanner;
import java.util.Random;
import java.util.Map;
import java.util.HashMap;

public class Ruleta {
    private static final String COLOR_ROJO = "ROJO" ;
    private static final String COLOR_NEGRO = "NEGRO";
    private static final String PAR = "PAR" ;
    private static final String IMPAR = "IMPAR";
    private Scanner scanner;
    Usuario usuario;
    Gui gui;
    Random random;
    private int dineroApostado;
    private Map<String,Integer> numerosRuleta;
    HashMap<Integer,NumeroRuleta> ruleta = new HashMap<>();
    boolean rojo;
    boolean negro;
    boolean par;
    boolean impar;
//    region
//    endregion
    public Ruleta(Usuario usuario, Gui gui) {
        scanner = new Scanner(System.in);
        this.usuario = usuario;
        this.gui = gui;
        random = new Random();
        numerosRuleta = new HashMap<>();
        dineroApostado = 0;
//        * XCXCXCXC
        //region Ruleta.puts
        ruleta.put(0, new NumeroRuleta(0, "Verde", "N/A", "N/A")); // El 0 es verde y no tiene otras características especiales
        ruleta.put(1, new NumeroRuleta(1, "Rojo", "Impar", "1ra Columna"));
        ruleta.put(2, new NumeroRuleta(2, "Negro", "Par", "2da Columna"));
        ruleta.put(3, new NumeroRuleta(3, "Rojo", "Impar", "3ra Columna"));
        ruleta.put(4, new NumeroRuleta(4, "Negro", "Par", "1ra Columna"));
        ruleta.put(5, new NumeroRuleta(5, "Rojo", "Impar", "2da Columna"));
        ruleta.put(6, new NumeroRuleta(6, "Negro", "Par", "3ra Columna"));
        ruleta.put(7, new NumeroRuleta(7, "Rojo", "Impar", "1ra Columna"));
        ruleta.put(8, new NumeroRuleta(8, "Negro", "Par", "2da Columna"));
        ruleta.put(9, new NumeroRuleta(9, "Rojo", "Impar", "3ra Columna"));
        ruleta.put(10, new NumeroRuleta(10, "Negro", "Par", "1ra Columna"));
        ruleta.put(11, new NumeroRuleta(11, "Negro", "Impar", "2da Columna"));
        ruleta.put(12, new NumeroRuleta(12, "Rojo", "Par", "3ra Columna"));
        ruleta.put(13, new NumeroRuleta(13, "Negro", "Impar", "1ra Columna"));
        ruleta.put(14, new NumeroRuleta(14, "Rojo", "Par", "2da Columna"));
        ruleta.put(15, new NumeroRuleta(15, "Negro", "Impar", "3ra Columna"));
        ruleta.put(16, new NumeroRuleta(16, "Rojo", "Par", "1ra Columna"));
        ruleta.put(17, new NumeroRuleta(17, "Negro", "Impar", "2da Columna"));
        ruleta.put(18, new NumeroRuleta(18, "Rojo", "Par", "3ra Columna"));
        ruleta.put(19, new NumeroRuleta(19, "Rojo", "Impar", "1ra Columna"));
        ruleta.put(20, new NumeroRuleta(20, "Negro", "Par", "2da Columna"));
        ruleta.put(21, new NumeroRuleta(21, "Rojo", "Impar", "3ra Columna"));
        ruleta.put(22, new NumeroRuleta(22, "Negro", "Par", "1ra Columna"));
        ruleta.put(23, new NumeroRuleta(23, "Rojo", "Impar", "2da Columna"));
        ruleta.put(24, new NumeroRuleta(24, "Negro", "Par", "3ra Columna"));
        ruleta.put(25, new NumeroRuleta(25, "Rojo", "Impar", "1ra Columna"));
        ruleta.put(26, new NumeroRuleta(26, "Negro", "Par", "2da Columna"));
        ruleta.put(27, new NumeroRuleta(27, "Rojo", "Impar", "3ra Columna"));
        ruleta.put(28, new NumeroRuleta(28, "Negro", "Par", "1ra Columna"));
        ruleta.put(29, new NumeroRuleta(29, "Negro", "Impar", "2da Columna"));
        ruleta.put(30, new NumeroRuleta(30, "Rojo", "Par", "3ra Columna"));
        ruleta.put(31, new NumeroRuleta(31, "Negro", "Impar", "1ra Columna"));
        ruleta.put(32, new NumeroRuleta(32, "Rojo", "Par", "2da Columna"));
        ruleta.put(33, new NumeroRuleta(33, "Negro", "Impar", "3ra Columna"));
        ruleta.put(34, new NumeroRuleta(34, "Rojo", "Par", "1ra Columna"));
        ruleta.put(35, new NumeroRuleta(35, "Negro", "Impar", "2da Columna"));
        ruleta.put(36, new NumeroRuleta(36, "Rojo", "Par", "3ra Columna"));
        //endregion
    }
    /**
     * Muestra el logo del juego de Ruleta.
     */
    public void mostrarLogo(){
        System.out.println("""
                 ____            _          _          \s
                |  _ \\   _   _  | |   ___  | |_    __ _\s
                | |_) | | | | | | |  / _ \\ | __|  / _` |
                |  _ <  | |_| | | | |  __/ | |_  | (_| |
                |_| \\_\\  \\__,_| |_|  \\___|  \\__|  \\__,_|""");
    }
    /**
     * Muestra el menú inicial del juego de la Ruleta.
     */
    public void menu() {
        mostrarLogo();
        System.out.println("Instrucciones:");
        System.out.println("""
                El objetivo del juego es predecir dónde caerá\s
                la bola después de que el crupier haga girar la rueda. Los jugadores\s
                pueden realizar diferentes tipos de apuestas, cada una con su propia\s
                probabilidad de éxito y pago asociado.""");
        presentacion();
    }
    /**
     * Muestra el menú de presentación del juego de Blackjack.
     */
    public void presentacion() {
        String opcion;
        do {
            System.out.println("\nPulsa 'c' para volver al menu");
            System.out.println("Pulsa 'x' para apostar");
            System.out.println("Pulsa 'z' para consultar tu saldo");
            opcion = scanner.next();
            if (opcion.equalsIgnoreCase("x")){
                antesJugar();
            } else if (opcion.equalsIgnoreCase("z")){
                System.out.println("Tienes " + usuario.getDinero() + " euros");
            } else if (opcion.equalsIgnoreCase("c")) {
                gui.menu();
            }
        } while (!opcion.equalsIgnoreCase("x"));
    }
    /**
     * Realiza una apuesta por parte del jugador.
     */
    public void antesJugar() {
        String opcion;
        do{
            System.out.println("Pulsa 'z' para apostar a números individuales: ");
            System.out.println("Pulsa 'x' para apostar a un grupo de números:  ");
            System.out.println("Pulsa 'c' para terminar");
            opcion = scanner.next();
            if (opcion.equalsIgnoreCase("z")){
                apuestasIndividuales();
            } else if (opcion.equalsIgnoreCase("x")) {
                apuestasGrupos();
            } else if (opcion.equalsIgnoreCase("c")) {
                apostar();
            }
        } while (opcion.equalsIgnoreCase("z") || opcion.equalsIgnoreCase("x"));
    }

    public void apostar() {
        System.out.println("\nTus apuestas son:");
        pintarNumRuleta();
        haGanado();
        gui.menu();

    }

    public boolean haGanado() {
        int resultado = random.nextInt(37);
        System.out.println("Numero de la ruleta: " + resultado);
        boolean haGanado = false;
        if (numerosRuleta.containsKey(String.valueOf(resultado))) {
            haGanado = true;
        } else {
            boolean apuestaRojo = rojo && ruleta.get(resultado).getColor().equalsIgnoreCase(COLOR_ROJO);
            boolean apuestaNegro = negro && ruleta.get(resultado).getColor().equalsIgnoreCase(COLOR_NEGRO);
            boolean apuestaPar = par && ruleta.get(resultado).getParImpar().equalsIgnoreCase(PAR);
            boolean apuestaImpar = impar && ruleta.get(resultado).getParImpar().equalsIgnoreCase(IMPAR);

            if (apuestaRojo || apuestaNegro || apuestaPar || apuestaImpar) {
                haGanado = true;
            }
        }

        if (haGanado) {
            usuario.actualizarDinero(dineroApostado);
            System.out.println("Has ganado");
        } else {
            usuario.actualizarDinero(- dineroApostado);
            System.out.println("Has perdido");
        }
        System.out.println("Ahora tienes :" + usuario.getDinero());
        ruleta.clear();
        return haGanado;
    }



    public void pintarNumRuleta() {
        StringBuilder sb = new StringBuilder();
        sb.append("{");
        for (String s : numerosRuleta.keySet()) {
            sb.append(s).append(",");
        }
        if (sb.length() > 1) {
            sb.deleteCharAt(sb.length() - 1);
        }
        sb.append("}");
        System.out.print(sb);
        System.out.println();
    }


    private void apuestasIndividuales() {
        String opcion;
        do {
            System.out.println("Elige los números que quieras del 0 al 36");
            System.out.println("Pulsa z cuando quieras dejar de apostar");
            opcion = scanner.next();
            if (!opcion.equalsIgnoreCase("z")) {
                int numero = Integer.parseInt(opcion);
                System.out.println("Cuanto euros quieres apostar en el " + opcion + " :");
                int saldo = scanner.nextInt();
                if (puedeApostar(saldo)) {
                    realizarApostar(numero, saldo);
                } else {
                    System.out.println("No tienes suficiente dinero para apostar esa cantidad.");
                    pintarNumRuleta();
                }
            }
        } while (!opcion.equalsIgnoreCase("z"));
        if (opcion.equalsIgnoreCase("z")) {
            antesJugar();
        }
    }

    public boolean puedeApostar(int saldo) {
        return saldo <= usuario.getDinero() && saldo <= (usuario.getDinero() - dineroApostado);
    }

    public void realizarApostar(int numero, int saldo) {
        dineroApostado += saldo;
        if (numero >= 0 && numero <= 36) {
            numerosRuleta.put(String.valueOf(numero), saldo);
        }
        pintarNumRuleta();
    }




    public void apuestasGrupos() {
        String opcion;
        do {
            System.out.println("Pulsa 'z' para apostar par/impar");
            System.out.println("Pulsa 'x' para apostar a rojo/negro");
            System.out.println("Pulsa 'c' para apostar a un grupo especifico");
            System.out.println("Pulsa 'v' para terminar");
            opcion = scanner.next();

            switch (opcion.toLowerCase()) {
                case "z":
                    System.out.println("Has seleccionado apostar a par/impar");
                    apuestaParI();
                    break;
                case "x":
                    System.out.println("Has seleccionado apostar a rojo/negro");
                    apuestaColor();
                    break;
                case "v":
                    antesJugar();
                    break;
                default:
                    System.out.println("Opción no válida. Inténtalo de nuevo.");
                    break;
            }
        } while (!opcion.equalsIgnoreCase("v"));
    }

    private void apuestaColor() {
        String opcion;
        System.out.println("Pulsa 'z' para apostar a rojo");
        System.out.println("Pulsa 'x' para apostar a negro");
        opcion = scanner.next();
        if (opcion.equalsIgnoreCase("z")){
            System.out.println("Cuanto quieres apostar en el rojo");
            int saldo = scanner.nextInt();
            if (puedeApostar(saldo)){
                dineroApostado += saldo;
                numerosRuleta.put("Rojo",saldo);
                rojo = true;
            } else {
                System.out.println("No tienes suficiente dinero para apostar esa cantidad.");
                System.out.println("Tienes " + (usuario.getDinero() - dineroApostado));
            }
        } else if (opcion.equalsIgnoreCase("x")) {
            System.out.println("Cuanto quieres apostar en el negro");
            int saldo = scanner.nextInt();
            if (puedeApostar(saldo)){
                dineroApostado += saldo;
                numerosRuleta.put("Negro",saldo);
                negro = true;
            } else {
                System.out.println("No tienes suficiente dinero para apostar esa cantidad.");
                System.out.println("Tienes " + (usuario.getDinero() - dineroApostado));
            }
        }
        apuestasGrupos();
    }

    private void apuestaParI() {
        String opcion;
        System.out.println("Pulsa 'z' para apostar a par");
        System.out.println("Pulsa 'x' para apostar a impar");
        opcion = scanner.next();
        if (opcion.equalsIgnoreCase("z")){
            System.out.println("¿Cuanto quieres apostar al par?");
            int saldo = scanner.nextInt();
            if (puedeApostar(saldo)) {
                dineroApostado += saldo;
                numerosRuleta.put("Par", saldo);
                par = true;
            } else {
                System.out.println("No tienes suficiente dinero para apostar esa cantidad.");
                System.out.println("Tienes " + (usuario.getDinero() - dineroApostado));
            }
        } else if (opcion.equalsIgnoreCase("x")) {
            System.out.println("¿Cuanto quieres apostar al impar?");
            int saldo = scanner.nextInt();
            if (puedeApostar(saldo)) {
                dineroApostado += saldo;
                numerosRuleta.put("Impar", saldo);
                impar = true;
            } else {
                System.out.println("No tienes suficiente dinero para apostar esa cantidad.");
                System.out.println("Tienes " + usuario.getDinero() + " euros disponibles.");
            }
        }
        apuestasGrupos();
    }
}
