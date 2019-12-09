package clases.impresoras3d.costos;

public class Costo {

    public String nombre;
    public int cantidad;
    public float valorUnitario;
    public int depreciacion;
    public String tipoDeCosto;

    public Costo() {
        nombre = "";
        cantidad = 0;
        valorUnitario = 0;
        depreciacion = 0;
        tipoDeCosto = "";
    }

    public String obtenerStringCantidad() {
        return Integer.toString(cantidad);
    }

    public String obtenerStringValorUnitario() {
        if(valorUnitario == (long) valorUnitario)
            return String.format("%d",(long)valorUnitario);
        else
            return String.format("%s",valorUnitario);
    }

    public String obtenerStringDepreciacion() {
        return Integer.toString(depreciacion);
    }

    public String toString() {
        String stringNombre = "(nombre : " + nombre + ")";
        String stringCantidad = "(cantidad : " + cantidad + ")";
        String stringValorUnitario = "(valorUnitario : " + obtenerStringValorUnitario() + ")";
        String stringDepreciacion = "(depreciacion : " + depreciacion + ")";
        String stringTipoDeCosto = "(tipoDeCosto : " + tipoDeCosto + ")";
        return "{" + stringNombre + ", " + stringCantidad + ", " + stringValorUnitario + ", " + stringDepreciacion + ", " + stringTipoDeCosto + "}";
    }
}
