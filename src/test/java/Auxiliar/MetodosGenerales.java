package Auxiliar;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.Reporter;
import pom.general.EnviarCorreo;

import java.util.*;
import java.util.concurrent.ThreadLocalRandom;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Angel Torre
 */
public class MetodosGenerales {

    private static final Logger LOGGER = Logger.getLogger(MetodosGenerales.class.getName());
    private boolean highligh = true;

    public void closeDriver(WebDriver driver) {
        driver.close();
       // driver.quit();
    }

    public void step(String message) {
        Reporter.log("<li>" + message + "</li>");
    }

    public void resaltar(WebDriver driver, WebElement element){
        if(highligh){
            if (driver instanceof JavascriptExecutor) {
                ((JavascriptExecutor)driver).executeScript("arguments[0].style.border='3px solid green'", element);
            }
        }
    }

    public String obtenerAtributo(WebElement elemento,String atributo, WebDriver driver, WebDriverWait wait){
        resaltar(driver, elemento);
        wait.until(ExpectedConditions.visibilityOf(elemento));
        return elemento.getAttribute(atributo);
    }

    public void irAlSitio(String pagina, WebDriver driver) {
        if (pagina.isEmpty()) {
            Assert.fail("LA URL ESTÁ VACÍA.");
        }
        try {
            driver.manage().window().maximize();
            driver.get(pagina);
            this.esperarCargaDeLaPagina(driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.getMessage(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void esperarCargaDeLaPagina(WebDriver driver) {
        try {
            ExpectedCondition<Boolean> pageLoadCondition = new
                    ExpectedCondition<Boolean>() {
                        public Boolean apply(WebDriver driver) {
                            return ((JavascriptExecutor) driver).executeScript("return document.readyState").equals("complete");
                        }
                    };
            WebDriverWait wait = new WebDriverWait(driver, 30);
            wait.until(pageLoadCondition);
        } catch (Exception e) {
            //e.printStackTrace();
            LOGGER.log(Level.WARNING, "Falló esperar carga de la página.");
        }
    }

    public void colocarCursorSobreElemento(WebElement element, WebDriver driver, WebDriverWait wait) {
        Actions builder = new Actions(driver);
        builder.moveToElement(element).build().perform();
    }

    public void click(WebElement element, WebDriver driver, WebDriverWait wait) {
        try {
            this.desplazarseAlElemento(element, driver);
            resaltar(driver, element);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            this.esperarCargaDeLaPagina(driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void clickElementoViaJS(WebElement elemento, WebDriver driver, WebDriverWait wait) {
        try {
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", elemento);
            esperarCargaDeLaPagina(driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void clickXpathViaJS(String xpath, WebDriver driver, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            ((JavascriptExecutor) driver).executeScript("arguments[0].click();", driver.findElement(By.xpath(xpath)));
            esperarCargaDeLaPagina(driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void clickElementoSVG(WebElement elemento, WebDriver driver, WebDriverWait wait) {
        Actions builder = new Actions(driver);
        try {
            builder.click(elemento).build().perform();
            resaltar(driver, elemento);
            esperarCargaDeLaPagina(driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void clickElementoSVGXpath(String xpath, WebDriver driver, WebDriverWait wait) {
        Actions builder = new Actions(driver);
        try {
            wait.until(ExpectedConditions.presenceOfAllElementsLocatedBy(By.xpath(xpath)));
            builder.click(driver.findElement(By.xpath(xpath))).build().perform();
            esperarCargaDeLaPagina(driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void desplazarseAlElemento(WebElement element, WebDriver driver) {
        try {
            Thread.sleep(500);//esto por el DOM
            ((JavascriptExecutor) driver).executeScript("arguments[0].scrollIntoView(true);", element);
            Thread.sleep(500);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void ingresarTexto(WebElement casillaTexto, String texto, WebDriver driver, WebDriverWait wait) {
        try {
            this.desplazarseAlElemento(casillaTexto, driver);
            resaltar(driver, casillaTexto);
            wait.until(ExpectedConditions.visibilityOf(casillaTexto));
            casillaTexto.clear();
            casillaTexto.sendKeys(Keys.chord(Keys.CONTROL,"a", Keys.DELETE));
            casillaTexto.sendKeys(texto);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public boolean existeWebElement(WebElement element,WebDriver driver, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOf(element));
            resaltar(driver, element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public List<String> obtenerElementosAleatoriosLista(List<String> lista, int numeroDeElementos) {
        List<String> listaSalida = new ArrayList<>();
        Random random = new Random();
        int aux;

        System.out.println("Se extrajeron los siguientes elementos aleatorios de la lista:");

        for (int i = 0; i < numeroDeElementos; i++) {
            aux = random.nextInt(lista.size());
            listaSalida.add(lista.get(aux));

            System.out.println(" - " + lista.get(aux));

            lista.remove(aux);
        }
        return listaSalida;
    }

    public void quitarElementoLista(List<String> lista, String textoDeElementoAEliminar) {
        lista.remove(textoDeElementoAEliminar);
    }

    public boolean existeXpath(String xpath, WebDriver driver, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            resaltar(driver, wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath))));
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public WebElement encontrarElementoXpath(String xpath, WebDriver driver, WebDriverWait wait) {
        WebElement elementoObjetivo = null;
        try {
            elementoObjetivo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath(xpath)));
            this.desplazarseAlElemento(elementoObjetivo, driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
        return elementoObjetivo;
    }

    public List<WebElement> encontrarListaElementosXpath(String xpath, WebDriver driver, WebDriverWait wait) {
        List<WebElement> lista = new LinkedList<>();
        try {
            lista = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.xpath(xpath)));
            this.desplazarseAlElemento(lista.get(0), driver);
            resaltar(driver, lista.get(0));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
        return lista;
    }

    public WebElement encontrarElementoCSS(String selectorCSS, WebDriver driver, WebDriverWait wait) {
        WebElement elementoObjetivo = null;
        try {
            elementoObjetivo = wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(selectorCSS)));
            this.desplazarseAlElemento(elementoObjetivo, driver);
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
        return elementoObjetivo;
    }

    public List<WebElement> encontrarListaElementosCSS(String selectorCSS, WebDriver driver, WebDriverWait wait) {
        List<WebElement> lista = new LinkedList<>();
        try {
            lista = wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.cssSelector(selectorCSS)));
            this.desplazarseAlElemento(lista.get(0), driver);
            resaltar(driver, lista.get(0));
        } catch (Exception e) {
            LOGGER.log(Level.SEVERE, e.toString(), e);
            Assert.fail(e.getMessage());
        }
        return lista;
    }

    public void seleccionarOpcionPorTexto(WebElement select, String opcion, WebDriver driver, WebDriverWait wait) {
        try {
            this.desplazarseAlElemento(select, driver);
            resaltar(driver, select);
            wait.until(ExpectedConditions.visibilityOf(select));
            Select opciones = new Select(select);
            opciones.selectByVisibleText(opcion);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public boolean existeSeleccionarOpcionTexto(WebElement select, String opcion, WebDriver driver, WebDriverWait wait) {
        boolean existeValue = false;
        try {
            this.desplazarseAlElemento(select, driver);
            wait.until(ExpectedConditions.visibilityOf(select));
            resaltar(driver, select);
            Select opciones = new Select(select);
            opciones.selectByVisibleText(opcion);
            existeValue = true;
        } catch (Exception e) {
            existeValue = false;
        }
        return existeValue;
    }

    public void seleccionarOpcionValue(WebElement select, String value, WebDriver driver, WebDriverWait wait) {
        try {
            this.desplazarseAlElemento(select, driver);
            resaltar(driver, select);
            wait.until(ExpectedConditions.visibilityOf(select));
            Select opciones = new Select(select);
            opciones.selectByValue(value);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public void seleccionarOpcionValue(WebElement select, String value, WebDriver driver) {
        try {
            this.desplazarseAlElemento(select, driver);
            resaltar(driver, select);
            Select opciones = new Select(select);
            opciones.selectByValue(value);
        } catch (Exception e) {
            LOGGER.log(Level.WARNING, e.toString(), e);
            Assert.fail(e.getMessage());
        }
    }

    public boolean invisibilidadWebElement(WebElement element,WebDriver driver, WebDriverWait wait) {
        try {
            wait.until(ExpectedConditions.invisibilityOf(element));
            resaltar(driver, element);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void notificarRegresion(String clase, String metodo, String mensajeRegresion) {
        String asuntoCorreo = "REGRESIÓN: [" + clase + "] --" + (new Date());
        String cuerpoCorreo = "<strong> Clase: </strong>[" + clase + "]" + "<br />" +
                "<strong>Método:</strong>. [" + metodo + "]" + "<br />" +
                "<strong>Motivo de fallo:</strong> " + mensajeRegresion;
        EnviarCorreo.enviarCorreo(asuntoCorreo, cuerpoCorreo);
        Assert.fail(mensajeRegresion);
    }

    public String obtenerClase() {
        return getClass().getSimpleName();
    }

    public String obtenerMetodo() {
        if (Thread.currentThread().getStackTrace().length > 2) {
            return Thread.currentThread().getStackTrace()[2].getMethodName();
        } else {
            return "undefined";
        }
    }

    public boolean existeAlerta(WebDriver driver) {
        try{
            esperar(2000);
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException ex) {
            return false;
        }
    }

    public void clickAceptarAlerta(WebDriver driver) {
        try {
            aceptarAlerta(driver);
        } catch (Exception alarma) {
            notificarRegresion(obtenerClase(), obtenerMetodo(), "No hay alarma para hacer click.");
        }
    }

    private String aceptarAlerta(WebDriver driver) {
        Alert alert = driver.switchTo().alert();
        String alertText = alert.getText();
        alert.accept();
        return alertText;
    }

    public String clickConAlerta(WebElement element, WebDriver driver, WebDriverWait wait) {
        String textoAlerta = "";
        try {
            this.desplazarseAlElemento(element, driver);
            resaltar(driver, element);
            wait.until(ExpectedConditions.visibilityOf(element));
            wait.until(ExpectedConditions.elementToBeClickable(element));
            element.click();
            esperar(100);
            try {
                textoAlerta = aceptarAlerta(driver);
            } catch (Exception alarma) {
                notificarRegresion(obtenerClase(), obtenerMetodo(), "No salio la alarma esperada");
            }
            this.esperarCargaDeLaPagina(driver);
        } catch (Exception e) {
            Assert.fail(e.getMessage());
        }

        return textoAlerta;
    }

    public WebElement obtenerWebElementAleatorioLista(List<WebElement> listaElementos) {
        Random random = new Random();

        return listaElementos.get(random.nextInt(listaElementos.size() - 1) + 1);
    }

    public String obtenerStringAleatorioLista(List<String> lista) {
        return lista.get(obtenerEnteroAleatorio(lista.size()));
    }

    public boolean contieneString(String textoContenedor, String textoContenido, String ... regexTextoAIgnorar) {

        for(int numeroRegexAIgnorar = 0; numeroRegexAIgnorar < regexTextoAIgnorar.length; numeroRegexAIgnorar++) {
            textoContenedor = textoContenedor.replaceAll(regexTextoAIgnorar[numeroRegexAIgnorar], "");
            textoContenido = textoContenido.replaceAll(regexTextoAIgnorar[numeroRegexAIgnorar], "");
        }

        System.out.println("Comparando '" + textoContenedor + "' y '" + textoContenido + "'");

        return(textoContenedor.contains(textoContenido));
    }

    public void iniciarClickSostenido(WebElement elemento, WebDriver driver) {
        Actions action = new Actions(driver);
        action.clickAndHold(elemento).build().perform();
    }

    public void terminarClickSostenido(WebElement elemento, WebDriver driver) {
        Actions action = new Actions(driver);
        action.moveToElement(elemento).release();
    }

    public String obtenerTextoDeElemento(WebElement elemento, WebDriver driver, WebDriverWait wait){
        try{
            this.desplazarseAlElemento(elemento,driver);
            resaltar(driver,elemento);
            wait.until(ExpectedConditions.elementToBeClickable(elemento));
        }catch (Exception e) {
            Assert.fail(e.getMessage());
        }
        return elemento.getText();
    }

    public List<String> obtenerTextoLista(List<WebElement> listaWebElements, WebDriver driver) {
        List<String> strLista = new LinkedList<>();
        for (WebElement camposColumna : listaWebElements) {
            strLista.add(camposColumna.getText().trim());
        }
        return strLista;
    }

    public float redondearConDecimales(float numero, int cifrasDecimales) {
        float decimalRedondeo = (float) (0.5 * Math.pow(10, -1 * cifrasDecimales));
        int entero = (int) ((numero + decimalRedondeo) * Math.pow(10, cifrasDecimales));
        return ((float) entero) / ((float)(Math.pow(10, cifrasDecimales)));
    }

    public int obtenerEnteroAleatorio(int rango) {
        return ThreadLocalRandom.current().nextInt(rango);
    }

    public float obtenerFloatAleatorio(int rango, int posicionesDecimales) {
        int potenciaDe10 = (int) Math.pow(10, posicionesDecimales);
        int numero = rango * potenciaDe10;
        numero = ThreadLocalRandom.current().nextInt(numero);
        return ((float) numero) / ((float) potenciaDe10);
    }

    public List<Integer> obtenerEnterosAleatoriosDistintos(int minimoRango, int maximoRango, int cantidad) {
        List<Integer> listaSalida = new ArrayList<>();
        List<Integer> listaAleatorios = new ArrayList<>();

        if (cantidad < (maximoRango - minimoRango)) {
            for(int i = minimoRango; i < maximoRango; i++) {
                listaAleatorios.add(i);
            }
            System.out.print("\nLista de indices aleatorios generada: [");
            for(int i = 0; i < cantidad; i++) {
                int indiceAleatorio = obtenerEnteroAleatorio(listaAleatorios.size());
                listaSalida.add(listaAleatorios.get(indiceAleatorio));
                listaAleatorios.remove(indiceAleatorio);

                System.out.print(" | " + listaSalida.get(i) + " | ");
            }
        } else {
            System.out.println("No es posible obtener " + cantidad + " enteros distintos en un rango de " + (maximoRango - minimoRango) + " números.");
        }

        System.out.println("]");

        return listaSalida;
    }

    public void quitarAtributoReadOnly(WebElement elemento, WebDriver driver) {
        ((JavascriptExecutor) driver).executeScript("arguments[0].removeAttribute('readonly','readonly')", elemento);
    }

    public void fallarPrueba(String mensaje) {
        Assert.fail(mensaje);
    }
}