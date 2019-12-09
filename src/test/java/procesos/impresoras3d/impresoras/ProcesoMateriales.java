package procesos.impresoras3d.impresoras;

import Auxiliar.MetodosGenerales;
import clases.impresoras3d.impresoras.Material;
import org.openqa.selenium.WebDriver;
import pom.impresoras3D.MenuImpresoras3D;
import pom.impresoras3D.impresoras.AnadirOEditarMaterial;
import pom.impresoras3D.impresoras.Impresoras;
import pom.impresoras3D.impresoras.Materiales;

public class ProcesoMateriales extends MetodosGenerales {

    WebDriver driver;

    public ProcesoMateriales(WebDriver driver) {
        this.driver = driver;
    }

    public void crearMaterial(String nombreImpresora, Material material) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloImpresoras();

        Impresoras paginaImpresoras = new Impresoras(driver);
        paginaImpresoras.clickBotonAgregarMateriales(nombreImpresora);

        Materiales paginaMateriales = new Materiales(driver);
        paginaMateriales.clickBotonAgregarNuevoMaterial();

        llenarFormulario(material);

        AnadirOEditarMaterial paginaCrearMaterial = new AnadirOEditarMaterial(driver);
        paginaCrearMaterial.clickBotonGuardar();
    }

    public void editarMaterial(String nombreImpresora, String nombreMaterial, Material material) {
        MenuImpresoras3D menuImpresoras3D = new MenuImpresoras3D(driver);
        menuImpresoras3D.navegarSubmoduloImpresoras();

        Impresoras paginaImpresoras = new Impresoras(driver);
        paginaImpresoras.clickBotonAgregarMateriales(nombreImpresora);

        Materiales paginaMateriales = new Materiales(driver);
        paginaMateriales.clickBotonEditarMaterial(nombreMaterial);

        llenarFormulario(material);

        AnadirOEditarMaterial paginaEditarMaterial = new AnadirOEditarMaterial(driver);
        paginaEditarMaterial.clickBotonGuardar();
    }

    public void llenarFormulario(Material material) {
        AnadirOEditarMaterial paginaFormulario = new AnadirOEditarMaterial(driver);
        paginaFormulario.ingresarNombreMaterial(material.nombre);
        paginaFormulario.seleccionarTipoDeMaterial(material.tipoDeMaterial);
        paginaFormulario.seleccionarUnidadDeMedida(material.unidadDeMedida);
        if(paginaFormulario.existeSelectUnidadDeMedida()) {
            paginaFormulario.seleccionarUnidadDeMedida(material.unidadDeMedida);
        }
        paginaFormulario.ingresarConsumible(material.consumible);
        paginaFormulario.ingresarPrecioUnitario(material.precioUnitario);
        paginaFormulario.ingresarTotalDeMaterial(material.totalDeMaterial);
    }
}
