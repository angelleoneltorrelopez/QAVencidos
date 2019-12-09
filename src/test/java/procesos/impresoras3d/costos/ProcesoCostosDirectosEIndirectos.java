package procesos.impresoras3d.costos;

import Auxiliar.MetodosGenerales;
import clases.impresoras3d.costos.Costo;
import org.openqa.selenium.WebDriver;
import pom.impresoras3D.MenuImpresoras3D;
import pom.impresoras3D.costos.AnadirOEditarCosto;
import pom.impresoras3D.costos.CostosDirectosEIndirectos;

/**
 * @author Rafael Mazariegos
 */

public class ProcesoCostosDirectosEIndirectos extends MetodosGenerales {

    WebDriver driver;

    public ProcesoCostosDirectosEIndirectos(WebDriver driver) {
        this.driver = driver;
    }

    public void crearCosto(Costo costo) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloCostosDirectosEIndirectos();

        CostosDirectosEIndirectos paginaCostos = new CostosDirectosEIndirectos(driver);
        paginaCostos.agregarNuevoCosto();

        llenarFormularioYGuardar(costo);
    }

    public void editarCosto(String nombreAnterior, Costo costo) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloCostosDirectosEIndirectos();

        CostosDirectosEIndirectos paginaCostos = new CostosDirectosEIndirectos(driver);
        paginaCostos.editarCosto(nombreAnterior);

        llenarFormularioYGuardar(costo);
    }

    private void llenarFormularioYGuardar(Costo costo) {
        AnadirOEditarCosto paginaFormularioCosto = new AnadirOEditarCosto(driver);
        paginaFormularioCosto.ingresarNombre(costo.nombre);
        paginaFormularioCosto.ingresarCantidad(costo.obtenerStringCantidad());
        paginaFormularioCosto.ingresarValorUnitario(costo.obtenerStringValorUnitario());
        paginaFormularioCosto.ingresarDepreciacion(costo.obtenerStringDepreciacion());
        paginaFormularioCosto.seleccionarTipoDeCosto(costo.tipoDeCosto);
        paginaFormularioCosto.clickBotonGuardar();
    }
}
