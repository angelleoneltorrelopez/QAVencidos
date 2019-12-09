package procesos.impresoras3d.usuarios;

import Auxiliar.MetodosGenerales;
import clases.impresoras3d.usuarios.Usuario;
import org.openqa.selenium.WebDriver;
import pom.impresoras3D.MenuImpresoras3D;
import pom.impresoras3D.usuarios.AnadirOEditarUsuario;
import pom.impresoras3D.usuarios.Usuarios;

/**
 * @author Rafael Mazariegos
 */

public class ProcesoUsuarios extends MetodosGenerales {

    WebDriver driver;

    public ProcesoUsuarios(WebDriver driver) {
        this.driver = driver;
    }

    public void crearUsuario(Usuario usuario) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloUsuarios();

        Usuarios paginaUsuarios = new Usuarios(driver);

        paginaUsuarios.clickBotonAgregarNuevoUsuario();

        AnadirOEditarUsuario paginaAgregarUsuario = new AnadirOEditarUsuario(driver);

        paginaAgregarUsuario.ingresarNombreUsuario(usuario.nombre);
        paginaAgregarUsuario.ingresarCorreoUsuario(usuario.correo);
        paginaAgregarUsuario.seleccionarRol(usuario.rol);
        paginaAgregarUsuario.clickBotonGuardar();
    }

    public void editarUsuario(String nombreUsuarioAEditar, Usuario usuario) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloUsuarios();

        Usuarios paginaUsuarios = new Usuarios(driver);

        paginaUsuarios.clickBotonEditarUsuario(nombreUsuarioAEditar);

        AnadirOEditarUsuario paginaEditarUsuario = new AnadirOEditarUsuario(driver);
        paginaEditarUsuario.ingresarNombreUsuario(usuario.nombre);
        paginaEditarUsuario.seleccionarRol(usuario.rol);
        paginaEditarUsuario.clickBotonGuardar();
    }
}
