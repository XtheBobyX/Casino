import java.util.Scanner;

public class Poker {
    Usuario usuario;
    Gui gui;
    Scanner scanner;
    private int x;
    public Poker(Usuario usuario, Gui gui) {
        this.usuario = usuario;
        this.gui = gui;
        scanner = new Scanner(System.in);
        x = 0;
    }
    private void mostrarLogo() {
        System.out.println(""" 
                              ____   ___  _  _______ ____ \s
                              |  _ \\ / _ \\| |/ / ____|  _ \\\s
                              | |_) | | | | ' /|  _| | |_) |
                              |  __/| |_| | . \\| |___|  _ <\s
                              |_|    \\___/|_|\\_\\_____|_| \\_\\""");
    }

    public void menu() {
        mostrarLogo();
        System.out.println("Instrucciones:");
        System.out.println("""
                El objetivo del juego es  formar la mejor mano de cinco cartas posible,
                usando una combinación de tus cartas privadas (conocidas como cartas "de mano")
                y las cartas comunitarias que se revelan en la mesa.
                """);
        presentacion();
    }

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

    private void apostar() {
        do {
            System.out.print("Cuanto quieres apostar:");
            x = scanner.nextInt();
            if (x > usuario.getDinero()){
                System.out.println("Te falta " + ( x - usuario.getDinero())+ " euros");
            }
            if (usuario.getDinero() == 0){gui.registro();}
        } while (x > usuario.getDinero());
        //    TODO

    }

    public void haGanado(){

    }

}
