import java.util.Scanner;

public class Gui {
    Scanner scanner;
    Usuario usuario;
    BlackJack blackJack;
    Ruleta ruleta;
    HighOrLower highOrLower;
    TragaMonedas tragaMonedas;
    Poker poker;
    private int x = 0;

    public int getX() {
        return x;
    }

    public Gui() {
        scanner = new Scanner(System.in);
        int saldoInicial = 100;
        usuario = new Usuario(saldoInicial);
        blackJack = new BlackJack(usuario,this);
        ruleta = new Ruleta(usuario,this);
        highOrLower = new HighOrLower(usuario,this);
        tragaMonedas = new TragaMonedas(usuario,this);
        poker = new Poker(usuario,this);
    }

    public void mostrarLogo(){
        System.out.println("""
                               _            \s
                              (_)           \s
                  ___ __ _ ___ _ _ __   ___ \s
                 / __/ _` / __| | '_ \\ / _ \\\s
                | (_| (_| \\__ \\ | | | | (_) |
                 \\___\\__,_|___/_|_| |_|\\___/""");
    }
    public void registro(){
        mostrarLogo();
        System.out.print("\nIntroduce tu nombre:");
        usuario.setNombre(scanner.next());
        usuario.setDinero(100);
        menu();
    }

    public void menu(){
        System.out.println("\nBienvenido " + usuario.getNombre() + " a Luxor Royale Casino \n");
        System.out.println("A que quieres jugar: \n");
        System.out.println("0.Salir");
        System.out.println("1.BlackJack");
        System.out.println("2.Ruleta");
        System.out.println("3.High or Lower");
        System.out.println("4.TragaMonedas");
        System.out.println("5.Poker");
        leerOpc();
    }
    public void leerOpc(){
        switch (scanner.nextInt()){
            case 0:
                registro();
                break;
            case 1:
                blackJack.menu();
                break;
            case 2:
                ruleta.menu();
                break;
            case 3:
                highOrLower.menu();
                break;
            case 4:
                tragaMonedas.menu();
                break;
            case 5:

                break;
            default:
                System.out.println("Error");
        }
    }

    public void noDinero(){
        do {
            System.out.print("Cuanto quieres apostar:");
            x = scanner.nextInt();
            if (!estabancarrota()) {
                if (x > usuario.getDinero()) {
                    System.out.println("No tienes suficiente dinero,te falta " + (x - usuario.getDinero()) + " euros");
                }
            } else {
                registro();
            }
        } while (x > usuario.getDinero());
    }

    public boolean estabancarrota(){
        if (usuario.getDinero() == 0){
            System.out.println("Estas en bancarrota");
            registro();
            return true;
        }
        return false;
    }



}
