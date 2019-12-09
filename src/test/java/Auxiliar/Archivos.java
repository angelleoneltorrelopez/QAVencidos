package Auxiliar;

import java.io.*;
import java.util.ArrayList;

/**
 * @author Rafael Mazariegos
 */

public class Archivos {

    public static void esperarDescargaDeArchivo(String nombre, int intentos) {
        File archivoDescargado = new File(Data.DESCARGAS.directorioDeDescargas + "\\" + nombre);

        System.out.print("Esperando carga de archivo");

        for(int i = 0; i < intentos; i++) {
            System.out.print(".");
            if (archivoDescargado.exists() && !archivoDescargado.isDirectory()) {
                System.out.println("\nArchivo descargado.");
                break;
            }
            esperar(1000);
        }
    }

    public static String esperarDescargaDeArchivoConExtension(String extension, int intentos) {
        for(int i = 0; i < intentos; i++) {
            File archivosDescargas = new File(Data.DESCARGAS.directorioDeDescargas);
            String[] archivos = archivosDescargas.list();
            for (String archivo : archivos) {
                if (archivo.endsWith(extension)) {
                    System.out.println("Se encontró el archivo descargado: " + archivo);
                    return archivo;
                }
            }
            esperar(500);
        }
        return null;
    }

    public static void borrarArchivo(String nombreArchivo) {
        File archivo = new File(Data.DESCARGAS.directorioDeDescargas + nombreArchivo);
        archivo.delete();
        System.out.println(nombreArchivo + " borrado.");
    }

    public static void limpiarCarpetaDeDescargas(String[] extensionesTiposDeArchivoABorrar) {
        File archivosDescargas = new File(Data.DESCARGAS.directorioDeDescargas);
        String[] archivos = archivosDescargas.list();
        for(String archivo : archivos) {
            for(String extension : extensionesTiposDeArchivoABorrar) {
                if(archivo.endsWith(extension)) {
                    borrarArchivo(archivo);
                }
            }
        }
    }

    public static void crearArchivoDePruebaBytes(String directorio, String nombre, int tamanoEnBytes) {
        try {
            ArrayList<String> listaComandos = new ArrayList<>();
            System.out.println("Creando un archivo de prueba, de " + tamanoEnBytes + " Bytes, en " + directorio);
            listaComandos.add("cmd.exe");
            listaComandos.add("/c");
            listaComandos.add("cd \"" + directorio + "\" && fsutil file createnew " + nombre + " " + tamanoEnBytes);
            for (int i = 0; i < listaComandos.size(); i++) {
                System.out.print("  >> ");
                System.out.println(listaComandos.get(i));
            }
            correrSerieDeComandosCMD(listaComandos);
        } catch (Exception e) {
            System.out.println("No fue posible crear el archivo: " + e.getMessage());
        }
    }

    public static boolean validarPresenciaDeTextoEnArchivo(String directorio, String nombre, String textoABuscar) {
        String linea = null;

        try {

            File file = new File(directorio + nombre);
            FileReader fileReader = new FileReader(file);
            BufferedReader bufferedReader = new BufferedReader(fileReader);
            while ((linea = bufferedReader.readLine()) != null) {
                if (linea.contains(textoABuscar)) {
                    bufferedReader.close();
                    return true;
                }
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("No fue posible leer los archivos en el directorio. " + e.getMessage());
        }
        return false;
    }

    public static void eliminarArchivoPermanentemente(String directorio, String nombre) {
        try {
            ArrayList<String> listaComandos = new ArrayList<>();
            System.out.println("Eliminando permanentemente el archivo " + nombre);
            listaComandos.add("cmd.exe");
            listaComandos.add("/c");
            listaComandos.add("cd \"" + directorio + "\" && del " + nombre);
            for (int i = 0; i < listaComandos.size(); i++) {
                System.out.print("  >> ");
                System.out.println(listaComandos.get(i));
            }
            correrSerieDeComandosCMD(listaComandos);
        } catch (Exception e) {
            System.out.println("No fue posible eliminar los archivos. " + e.getMessage());
        }
    }

    public static void esperar(int milisegundos) {
        try {
            Thread.sleep(milisegundos);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void correrSerieDeComandosCMD(ArrayList<String> comandos) throws IOException {
        ProcessBuilder builder = new ProcessBuilder(comandos);
        builder.redirectErrorStream(true);
        Process process = builder.start();
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String line;
        while(true) {
            line = reader.readLine();
            if(line == null) { break; }
            System.out.print("  >> ");
            System.out.println(line);
        }
    }
}
