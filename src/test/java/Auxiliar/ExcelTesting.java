package Auxiliar;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;

/**
 * @author Jhonatan Flores
 * @modified Rafael Mazariegos
 */

public class ExcelTesting {

    static Workbook cognitsWorkbook;

    public static void readExcel(String filePath,String fileName) throws IOException{

        System.out.println("Abriendo archivo " + filePath  + fileName);

        File file = new File(filePath + "\\" + fileName);
        FileInputStream inputStream = new FileInputStream(file);
        cognitsWorkbook = null;

        String fileExtensionName = fileName.substring(fileName.indexOf("."));

        if(fileExtensionName.equals(".xlsx")){
            cognitsWorkbook = new XSSFWorkbook(inputStream);

        }else if(fileExtensionName.equals(".xls")){
            cognitsWorkbook = new HSSFWorkbook(inputStream);
        }

    }

    public static int cantidadDeFilas(String nombreHoja) {
        Sheet hoja = cognitsWorkbook.getSheet(nombreHoja);

        System.out.println("El archivo de Excel contiene " + (hoja.getLastRowNum() - hoja.getFirstRowNum()) + " registros.");

        return hoja.getLastRowNum() - hoja.getFirstRowNum();
    }

    public static void imprimirHoja(String hojaName){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);

        try {
            System.out.println("Se obtuvo la hoja " + hojaName + " correctamente. La última fila es la " + hoja.getLastRowNum() + " y la primera es la " + hoja.getFirstRowNum());
        } catch (Exception e) {
            System.out.println("No fue posible imprimir. Mensaje de error: '" + e.getMessage() + "'");
        }

        int rowCount = hoja.getLastRowNum()-hoja.getFirstRowNum();
        String contenidoCelda;

        for (int numeroFila = 0; numeroFila < rowCount+1; numeroFila++) {
            Row row = hoja.getRow(numeroFila);
            for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                if(row.getCell(numeroColumna).getCellType()==1){
                    contenidoCelda=row.getCell(numeroColumna).getStringCellValue();
                    System.out.print(contenidoCelda+"|| ");//string
                }else{
                    contenidoCelda=row.getCell(numeroColumna).getNumericCellValue()+"";
                    System.out.print(contenidoCelda+"|| ");//double
                }

            }
            System.out.println();
            System.out.println();
        }
    }

    public static boolean existeCelda(String hojaName,String palabra){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);
        int rowCount = hoja.getLastRowNum()-hoja.getFirstRowNum();
        String contenidoCelda;
        boolean existePalabra=false;

        for (int numeroFila = 0; numeroFila < rowCount+1; numeroFila++) {
            Row row = hoja.getRow(numeroFila);
            for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                if(row.getCell(numeroColumna).getCellType()==1){
                    contenidoCelda=row.getCell(numeroColumna).getStringCellValue();
                    if(contenidoCelda.equals(palabra)){
                        System.out.println("Palabra encontrada en la Fila,Columna");
                        System.out.println(numeroFila+","+numeroColumna);
                        existePalabra = true;
                    }
                }else{
                    contenidoCelda=row.getCell(numeroColumna).getNumericCellValue()+"";
                    if(contenidoCelda.equals(palabra)){
                        System.out.println("Palabra encontrada en la Fila,Columna");
                        System.out.println(numeroFila+","+numeroColumna);
                        existePalabra = true;
                    }
                }
            }
        }
        if(existePalabra==false){
            System.out.println("No existe la palabra");
        }
        return existePalabra;
    }

    public static boolean existeColumna(String hojaName,List<String> columna, int numeroColumnaBuscado){
        Sheet hoja = cognitsWorkbook.getSheet(hojaName);
        int rowCount = hoja.getLastRowNum()-hoja.getFirstRowNum();
        String contenidoCelda;
        boolean existeColumna=false;

        for (int numeroFila = 0; numeroFila < rowCount+1; numeroFila++) {
            Row row = hoja.getRow(numeroFila);
            for (int numeroColumna = 0; numeroColumna < row.getLastCellNum(); numeroColumna++) {
                if(row.getCell(numeroColumna).getCellType()==1){
                    contenidoCelda=row.getCell(numeroColumna).getStringCellValue();
                    if(numeroColumna==numeroColumnaBuscado){
                        if(columna.get(numeroFila).equals(contenidoCelda)){
                            existeColumna=true;
                        }else{
                            existeColumna=false;
                            numeroFila=rowCount+1;
                        }
                    }
                }else{
                    contenidoCelda=row.getCell(numeroColumna).getNumericCellValue()+"";
                    if(numeroColumna==numeroColumnaBuscado){
                        if(columna.get(numeroFila).equals(contenidoCelda)){
                            existeColumna=true;
                        }else{
                            existeColumna=false;
                            numeroFila=rowCount+1;
                        }
                    }
                }
            }
        }
        if(existeColumna==false){
            System.out.println("No existe la columna");
        }else{
            System.out.println("Existe la columna");
        }
        return existeColumna;
    }

    public static boolean existeFila(List<String> fila, String nombreHoja, String ... regexIgnorarTexto) {
        Sheet hoja = cognitsWorkbook.getSheet(nombreHoja);
        int cantidadDeFilas = hoja.getLastRowNum()-hoja.getFirstRowNum();
        String contenidoCelda;
        String contenidoLista;
        boolean filaEncontrada;

        Row filaActual;

        for(int numeroFila = 1; numeroFila < cantidadDeFilas + 1; numeroFila++) {
            filaActual = hoja.getRow(numeroFila);
            filaEncontrada = true;

            for(int numeroCelda = 0; numeroCelda < fila.size(); numeroCelda++) {
                contenidoCelda = obtenerValorDeCeldaString(filaActual, numeroCelda);
                contenidoLista = fila.get(numeroCelda);

                for(int numeroRegexAIgnorar = 0; numeroRegexAIgnorar < regexIgnorarTexto.length; numeroRegexAIgnorar++) {
                    contenidoCelda = contenidoCelda.replaceAll(regexIgnorarTexto[numeroRegexAIgnorar], "");
                    contenidoLista = contenidoLista.replaceAll(regexIgnorarTexto[numeroRegexAIgnorar], "");
                }

                contenidoCelda = contenidoCelda.substring(0, Math.min(contenidoCelda.length(), 100));
                contenidoLista = contenidoLista.substring(0, Math.min(contenidoLista.length(), 100));

                if(!(contenidoCelda.contains(contenidoLista))) {
                    filaEncontrada = false;
                    break;
                }
            }
            if(filaEncontrada) {
                return true;
            }
        }

        System.out.print("No se encontró la siguiente fila en el Excel:\n[");
        for(String celda : fila) {
            System.out.print("|" + celda + "|");
        }
        System.out.println("|");
        return false;
    }

    public static String obtenerValorDeCeldaString(Row fila, int numeroCelda) {
        Cell cell = fila.getCell(numeroCelda);

        if(cell == null) {
            return "";
        } else {
            switch (cell.getCellType()) {
                case Cell.CELL_TYPE_STRING:
                    return (cell.getRichStringCellValue().getString());
                case Cell.CELL_TYPE_NUMERIC:
                    return (Integer.toString((int) ((cell.getNumericCellValue()))));
                default:
                    return "";
            }
        }
    }
}
