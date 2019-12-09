package impresoras3d.impresoras;

public class Material {

    public String nombre;
    public String tipoDeMaterial;
    public String consumible;
    public String unidadDeMedida;
    public float precioUnitario;
    public float totalDeMaterial;

    public Material() {
        nombre = "";
        tipoDeMaterial = "";
        consumible = "";
        unidadDeMedida = "";
        precioUnitario = 0;
        totalDeMaterial = 0;
    }

    public String obtenerStringPrecioUnitario(){
        return String.format("%.2f", precioUnitario);
    }

    public String obtenerStringTotalMaterial() {
        return String.format("%.2f", totalDeMaterial);
    }

    public String toString() {
        String stringNombre = "(nombre : " + nombre + ")";
        String stringTipoDeMaterial = "(tipoDeMaterial : " + nombre + ")";
        String stringUnidadDeMedida = "(unidadDeMedida : " + unidadDeMedida + ")";
        String stringConsumible = "(stringConsumible : " + consumible + ")";
        String stringPrecioUnitario = "(precioUnitario : " + precioUnitario + ")";
        String stringTotalDeMaterial = "(totalDeMaterial : " + totalDeMaterial + ")";
        return "{" + stringNombre + ", " + stringTipoDeMaterial + ", " + stringUnidadDeMedida + ", " + stringConsumible + ", " + stringPrecioUnitario + ", " + stringTotalDeMaterial + "}";
    }
}
