public class NumeroRuleta {
    private int numero;
    private String color;
    private String columna;
    private String parImpar;

    public NumeroRuleta(int numero, String color, String tipo, String columna) {
        this.numero = numero;
        this.color = color;
        parImpar = tipo;
        this.columna = columna;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getParImpar() {
        return parImpar;
    }

    public void setParImpar(String parImpar) {
        this.parImpar = parImpar;
    }
}
