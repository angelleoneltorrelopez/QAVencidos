package clases.impresoras3d.usuarios;

public class Usuario {

    public String nombre;
    public String correo;
    public String rol;

    public Usuario() {
        nombre = "";
        correo = "";
        rol = "";
    }

    public String toString() {
        String stringNombre = "(nombre : " + nombre + ")";
        String stringCorreo = "(correo : " + correo + ")";
        String stringRol = "(rol : " + rol  + ")";
        return "{" + stringNombre + ", " + stringCorreo + ", " + stringRol + "}";
    }
}
