package reporteria;

import org.testng.Assert;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.LinkedList;
import java.util.List;

/**
 * @author Luis Diego
 * @modificado jhona
 */
public class ConstruirReporte {
    //---------
    static String carpetaTest = "";
    static String carpetaProceso = "";

    static String parametro1 = "notificarRegresion";
    static String parametro2 = "@Test";
    //-----
    static String raizTest = System.getProperty("user.dir") + "\\src\\test\\java\\Test";
    static String raizProceso=System.getProperty("user.dir")+"\\src\\test\\java\\Proceso";
    static String rutaTest =raizTest+carpetaTest;
    static String rutaProceso =raizProceso+carpetaProceso;

    static int sumaParametro1=0;
    static int sumaParametro2=0;

    static List<String> archivos = new LinkedList<>();
    public static void main(String[] args) throws Exception {
        File carpetaT = new File(rutaTest);
        File carpetaP = new File(rutaProceso);

        System.out.print("dirección: " + raizTest);

        List<String> clases = listarFicherosPorCarpeta(carpetaT);

        String aux = "";
        for (String clase : clases) {
            if (!clase.split("\\\\")[clase.split("\\\\").length-2].equals(aux)) {
                System.out.println("\n#####   PACKAGE " + (aux = clase.split("\\\\")[clase.split("\\\\").length-2]) + "   #####");
            }
            int[] totalPorClase = contarCasosDePrueba(clase);
            System.out.println(clase.split("\\\\")[clase.split("\\\\").length-1] + ", ["+parametro1.replace("(","")+": " + totalPorClase[0] + "]" + ", ["+parametro2.replace("(","")+": " + totalPorClase[1] + "]");
        }
        System.out.println("\nTotal "+parametro1.replace("(","")+": " + sumaParametro1);
        System.out.println("Total "+parametro2.replace("(","")+": " + sumaParametro2);

        archivos = new LinkedList<>();
        List<String> clasesP = listarFicherosPorCarpeta(carpetaP);
        sumaParametro1=0;
        sumaParametro2=0;
        aux = "";
        for (String proceso : clasesP) {
            if (!proceso.split("\\\\")[proceso.split("\\\\").length-2].equals(aux)) {
                System.out.println("\n#####   PACKAGE " + (aux = proceso.split("\\\\")[proceso.split("\\\\").length-2]) + "   #####");
            }
            int[] totalPorClase = contarCasosDePrueba(proceso);
            System.out.println(proceso.split("\\\\")[proceso.split("\\\\").length-1] + ", ["+parametro1.replace("(","")+": " + totalPorClase[0] + "]" + ", ["+parametro2.replace("(","")+": " + totalPorClase[1] + "]");
        }
        System.out.println("\nTotal "+parametro1.replace("(","")+": " + sumaParametro1);
        System.out.println("Total "+parametro2.replace("(","")+": " + sumaParametro2);
    }

    public static List<String> listarFicherosPorCarpeta(File carpeta) {
        for (File ficheroEntrada : carpeta.listFiles()) {
            if (ficheroEntrada.isDirectory()) {
                listarFicherosPorCarpeta(ficheroEntrada);
            } else {
                archivos.add(ficheroEntrada.getAbsolutePath());
            }
        }
        return archivos;
    }

    public static int[] contarCasosDePrueba(String absolutePath) {
        File clase = new File(absolutePath);
        int[] contador = {0, 0};
        try {
            FileReader fileReader = new FileReader(clase);
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String linea;
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.contains(parametro1)) {
                    contador[0]++;
                    sumaParametro1++;
                }

                if (linea.contains(parametro2)) {
                    contador[1]++;
                    sumaParametro2++;
                }
            }
            fileReader.close();
        } catch (Exception e) {
            System.out.println("Excepción leyendo fichero " + clase + ": ");
            Assert.fail(e.getMessage());
        }
        return contador;
    }
}