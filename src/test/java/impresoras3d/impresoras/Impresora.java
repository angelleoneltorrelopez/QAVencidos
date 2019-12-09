package impresoras3d.impresoras;

import Auxiliar.MetodosGenerales;

public class Impresora extends MetodosGenerales {

    public String nombre;
    public float consumo;
    public boolean requiereLaminas;
    public String medida;

    public Impresora() {
        nombre = "";
        consumo = 0;
        requiereLaminas = false;
        medida = "";
    }

    public String obtenerStringConsumo() {
        return String.format("%.2f", consumo);
    }

    public String toString() {
        String stringNombre = "(nombre : " + nombre + ")";
        String stringConsumo = "(consumo : " + consumo + ")";
        String stringRequiereLaminas = "(requiereLaminas : " + requiereLaminas  + ")";
        String stringMedida = "(medida : " + medida  + ")";
        return "{" + stringNombre + ", " + stringConsumo + ", " + stringRequiereLaminas + ", " + stringMedida + "}";
    }
}
