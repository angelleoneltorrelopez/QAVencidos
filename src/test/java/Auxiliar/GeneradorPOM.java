package Auxiliar;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import procesos.impresoras3d.ProcesoImpresoras3D;

import java.io.File;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @author Jhonatan Flores 3/05/19
 * investigación
 */
public class GeneradorPOM extends TestBase {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger( GeneradorPOM.class.getName() );
    private ProcesoImpresoras3D login;
    private WebDriverWait wait;

    @FindBy(xpath = "//input[(@type=\"text\" or @type=\"date\") and not(ancestor::div[contains(@class,'hide')]) and not(ancestor::div[contains(@style,'display: none')])]")
    private List<WebElement> inputTxt;
    @FindBy(xpath = "//input[(@type=\"button\" or @type=\"submit\") and not(contains(@style,'none')) and not(ancestor::div[contains(@style,'none')]) and not(ancestor::div[contains(@class,'modal')])]|//button[(@type=\"button\" or @type=\"submit\" or contains(@onclick,'javascript')) and not(contains(@style,'none')) and not(ancestor::div[contains(@style,'display: none')]) and not(ancestor::div[contains(@class,'hide')])and not(ancestor::div[contains(@class,'modal')])]|//a[contains(@href,'tab')]//font")
    private List<WebElement> button;
    @FindBy(xpath = "//select[not(ancestor::div[contains(@class,'hide')]) and not(ancestor::div[contains(@style,'display: none')])]")
    private List<WebElement> select;
    @FindBy(xpath = "//input[(@type=\"radio\") and not(contains(@style,'none'))]")
    private List<WebElement> radioButton;

    //-----
    List<String>listaElementosTxt;
    List<String>listaMetodosTxt;

    List<String>listaElementosButton;
    List<String>listaMetodosButton;

    List<String>listaElementosSelect;
    List<String>listaMetodosSelect;

    List<String>listaElementosRadioButton;
    List<String>listaMetodosRadioButton;

    //private String url= Data.URL.alumnosBloqueadosPorChequesRechazados;
    private String url= "https://intranet.ufm.edu/cc/asistencia_financiera/admin_asistencia_financiera2.php";
    private String nombreClase="ConsultaSolicitudesAsistenciaFinanciera";

    @Test(description = "abre un url y genera un POM", dataProvider = "Firefox" )
    private void generaPOM(String browserName) {
        inicializarVariables();
        login.inicioDeSesion();/*
        cargarUrl(driver,url);
        esperarSegundos(3);*/

        listaElementosTxt=obtenerListaElementos(inputTxt,"txt");
        listaElementosTxt=arreglarDuplicidadElementos(listaElementosTxt);
        listaMetodosTxt=obtenerListaMetodos(inputTxt,"txt");

        listaElementosButton=obtenerListaElementos(button,"button");
        listaElementosButton=arreglarDuplicidadElementos(listaElementosButton);
        listaMetodosButton=obtenerListaMetodos(button,"button");

        listaElementosSelect=obtenerListaElementos(select,"select");
        listaElementosSelect=arreglarDuplicidadElementos(listaElementosSelect);
        listaMetodosSelect=obtenerListaMetodos(select,"select");

        listaElementosRadioButton=obtenerListaElementos(radioButton,"radio");
        listaElementosRadioButton=arreglarDuplicidadElementos(listaElementosRadioButton);
        listaMetodosRadioButton=obtenerListaMetodos(radioButton,"radio");

        List<String> listaElementos = new LinkedList<>();
        listaElementos.addAll(listaElementosTxt);
        listaElementos.addAll(listaElementosButton);
        listaElementos.addAll(listaElementosSelect);
        listaElementos.addAll(listaElementosRadioButton);

        List<String> listaMetodos = new LinkedList<>();
        listaMetodos.addAll(listaMetodosTxt);
        listaMetodos.addAll(listaMetodosButton);
        listaMetodos.addAll(listaMetodosSelect);
        listaMetodos.addAll(listaMetodosRadioButton);

        crearJava(listaElementos,listaMetodos);
    }

    private void inicializarVariables(){/*
        login = new Login(driver);*/
        this.driver = driver;
        wait = new WebDriverWait(driver,5);
        AjaxElementLocatorFactory factory = new AjaxElementLocatorFactory(this.driver, 5);
        PageFactory.initElements(factory, this);
    }

    private String obtenerElemento(WebElement webElement, String tipoElemento){
        String name=obtenerAtributo(webElement,"name",driver,wait);
        String id=obtenerAtributo(webElement,"id",driver,wait);
        String href=obtenerAtributo(webElement,"href",driver,wait);
        String value=obtenerAtributo(webElement,"value",driver,wait);
        String text=obtenerTextoDeElemento(webElement,driver,wait);
        String tagName=webElement.getTagName();

        boolean nameAtribute=!(name==null || name.isEmpty());
        boolean idAtribute=!(id==null || id.isEmpty());
        boolean hrefAtribute=!(href==null || href.isEmpty());
        boolean valueAtribute=!(value==null || value.isEmpty());
        boolean textAtribute=!text.isEmpty();

        String nombre="";
        if(idAtribute){
            nombre=id;
        }else if(nameAtribute){
            nombre=name;
        }else if(hrefAtribute){
            nombre=href;
        }else if(valueAtribute){
            nombre=value;
        }else if(textAtribute){
            nombre=text;
        }

        if(nombre.length()<1){
            Assert.fail("Atributo no encontrado");
        }
        String nombreM=primeraMayuscula(nombre);

        String definicion="";
        if(idAtribute){
            definicion="\"//"+tagName+"[@id='"+id+"']\"";
            definicion="@FindBy(xpath = "+definicion+")\n"+"\tWebElement "+tipoElemento+nombreM+";";
        }else if(nameAtribute){
            definicion="\"//"+tagName+"[@name='"+name+"']\"";
            definicion="@FindBy(xpath = "+definicion+")\n"+"\tWebElement "+tipoElemento+nombreM+";";
        }else if(hrefAtribute){
            definicion="\"//"+tagName+"[@href='"+href+"']\"";
            definicion="@FindBy(xpath = "+definicion+")\n"+"\tWebElement "+tipoElemento+nombreM+";";
        }else if(valueAtribute){
            definicion="\"//"+tagName+"[@value='"+value+"']\"";
            definicion="@FindBy(xpath = "+definicion+")\n"+"\tWebElement "+tipoElemento+nombreM+";";
        }else if(textAtribute){
            definicion="\"//"+tagName+"[text()='"+text+"']\"";
            definicion="@FindBy(xpath = "+definicion+")\n"+"\tWebElement "+tipoElemento+nombreM+";";
        }

        return definicion;
    }
    private String obtenerMetodo(WebElement webElement,String tipoElemento){
        String name=obtenerAtributo(webElement,"name",driver,wait);
        String id=obtenerAtributo(webElement,"id",driver,wait);
        String href=obtenerAtributo(webElement,"href",driver,wait);
        String value=obtenerAtributo(webElement,"value",driver,wait);
        String text=obtenerTextoDeElemento(webElement,driver,wait);

        boolean nameAtribute=!(name==null || name.isEmpty());
        boolean idAtribute=!(id==null || id.isEmpty());
        boolean hrefAtribute=!(href==null || href.isEmpty());
        boolean valueAtribute=!(value==null || value.isEmpty());
        boolean textAtribute=!text.isEmpty();

        String nombre="";
        if(idAtribute){
            nombre=id;
        }else if(nameAtribute){
            nombre=name;
        }else if(hrefAtribute){
            nombre=href;
        }else if(valueAtribute){
            nombre=value;
        }else if(textAtribute){
            nombre=text;
        }

        if(nombre.length()<1){
            Assert.fail("Atributo no encontrado");
        }
        String nombreM=primeraMayuscula(nombre);
        nombre=primeraMinusculaPorPalabra(nombreM);

        String metodo="";
        switch (tipoElemento) {
            case "txt":  metodo = "public void ingresar"+nombreM+"(String "+nombre+"){\n" +
                    "\t\tingresarTexto("+tipoElemento+nombreM+","+nombre+",driver,wait);\n" +
                    "\t}";
                break;
            case "button":  metodo = "public void click"+nombreM+"(){\n" +
                    "\t\tclick("+tipoElemento+nombreM+",driver,wait);\n" +
                    "\t}";
                break;
            case "select":  metodo = "public void seleccionar"+nombreM+"(String "+nombre+"){\n" +
                    "\t\tseleccionarOpcionPorTexto("+tipoElemento+nombreM+","+nombre+",driver,wait);\n" +
                    "\t}";
                break;
            case "radio": metodo = "public void clickRadio"+nombreM+"(){\n" +
                    "\t\tclick("+tipoElemento+nombreM+",driver,wait);\n" +
                    "\t}";
                break;
            default: metodo = "tipo de elemento inválido";
                break;
        }

        return metodo;
    }

    private List<String>obtenerListaElementos(List<WebElement>listaWebElements,String tipoElemento){
        List<String> strLista = new LinkedList<>();
        for (WebElement elemento : listaWebElements) {
            strLista.add(obtenerElemento(elemento,tipoElemento));
        }
        return strLista;
    }

    private List<String>obtenerListaMetodos(List<WebElement>listaWebElements,String tipoElemento){
        List<String> strLista = new LinkedList<>();
        for (WebElement elemento : listaWebElements) {
            strLista.add(obtenerMetodo(elemento,tipoElemento));
        }
        return strLista;
    }

    private List<String> arreglarDuplicidadElementos(List<String> elementos){
        List<String> listaFinal=new ArrayList<>();
        List<String> copiaElementos=new ArrayList<>(elementos);
        String remplazo="";
        for(int i=0;i<copiaElementos.size();i++){
            String elementoActual=copiaElementos.get(i);
            copiaElementos.remove(i);
            if(copiaElementos.indexOf(elementoActual)!=-1){
                copiaElementos.remove(copiaElementos.indexOf(elementoActual));
                String[] separado=elementoActual.split("\"");
                separado[1]="\"("+separado[1]+")[1]\"";
                remplazo="";
                for(int j=0;j<separado.length;j++){
                    remplazo=remplazo+separado[j];
                }
                listaFinal.add(remplazo);
                separado=elementoActual.split("\"");
                separado[1]="\"("+separado[1]+")[2]\"";
                remplazo="";
                for(int j=0;j<separado.length;j++){
                    remplazo=remplazo+separado[j];
                }
                listaFinal.add(remplazo);
            }else{
                listaFinal.add(elementoActual);
            }
            copiaElementos.add(i,remplazo);
        }
        return listaFinal;
    }

    private void crearJava(List<String> listaElementos,List<String> listaMetodos){
        try {
            String ruta = System.getProperty("user.dir")+"\\src\\test\\java\\generadorPOM\\"+nombreClase+".java";
            String[] rutaA=ruta.split(Pattern.quote(File.separator));
            String pack=rutaA[rutaA.length-2];
            PrintWriter writer = new PrintWriter(ruta, "UTF-8");
            writer.println("package "+pack+";");
            writer.println("");
            writer.println("import auxiliar.MetodosGenerales;");
            writer.println("import org.openqa.selenium.WebDriver;");
            writer.println("import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;");
            writer.println("import org.openqa.selenium.WebElement;");
            writer.println("import org.openqa.selenium.support.ui.WebDriverWait;");
            writer.println("import org.openqa.selenium.support.FindBy;");
            writer.println("import org.openqa.selenium.support.PageFactory;");
            writer.println("");
            writer.println("public class "+nombreClase+" extends MetodosGenerales{");
            writer.println("");
            writer.println("\tWebDriver driver;");
            writer.println("\tWebDriverWait wait;");
            writer.println("\tAjaxElementLocatorFactory factory;");
            writer.println("");
            for (String elemento : listaElementos) {
                writer.println("\t"+elemento);
            }
            writer.println("");
            writer.println("\tpublic "+nombreClase+"(WebDriver driver){");
            writer.println("\t\tthis.driver = driver;");
            writer.println("\t\twait = new WebDriverWait(driver,5);");
            writer.println("\t\tfactory = new AjaxElementLocatorFactory(this.driver, 5);");
            writer.println("\t\tPageFactory.initElements(factory, this);");
            writer.println("\t\t}");
            writer.println("");
            for (String metodo : listaMetodos) {
                writer.println("\t"+metodo);
            }
            writer.println("");
            writer.println("}");
            writer.close();
        } catch (Exception e) {
            e.printStackTrace();
            Assert.fail(e+"");
        }
    }

    private static String primeraMayusculaPorPalabra(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toUpperCase() + str.substring(1);
        }
    }
    private static String primeraMinusculaPorPalabra(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            return str.substring(0, 1).toLowerCase() + str.substring(1);
        }
    }

    public static String primeraMayuscula(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        } else {
            String[] palabras=str.split("_");
            String palabraCamel="";
            for(String palabra:palabras){
                palabraCamel=palabraCamel+primeraMayusculaPorPalabra(palabra);
            }
            palabras=palabraCamel.split(" ");
            palabraCamel="";
            for(String palabra:palabras){
                palabraCamel=palabraCamel+primeraMayusculaPorPalabra(palabra);
            }
            return palabraCamel;
        }
    }
}
