public class Usuario {
    private String nombre;
    private int dinero;

    public Usuario(int dinero) {
        this.dinero = dinero;
    }
//region Getter and Setter
    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public double getDinero() {
        return dinero;
    }
    public void actualizarDinero(int dinero) {
        this.dinero += dinero;
    }

    public void setDinero(int dinero) {
        this.dinero = dinero;
    }
    //    endregion
}
