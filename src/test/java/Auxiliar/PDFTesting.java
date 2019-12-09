package Auxiliar;

import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;
import org.apache.pdfbox.text.PDFTextStripperByArea;

import java.io.File;
import java.io.IOException;
import java.util.List;

/**
 * @author Rafael Mazariegos
 *
 * Basado en https://www.mkyong.com/java/pdfbox-how-to-read-pdf-file-in-java/
 */

public class PDFTesting {

    public static boolean existeTextoEnPDF(String directorio, String nombreArchivo, List<String> textosABuscar) throws IOException {
        String directorioCompleto = directorio + nombreArchivo;

        System.out.println("Revisando el PDF.\n____________________________________________________________");

        try (PDDocument document = PDDocument.load(new File(directorioCompleto))) {
            document.getClass();
            if (!document.isEncrypted()) {
                PDFTextStripperByArea stripper = new PDFTextStripperByArea();
                stripper.setSortByPosition(true);
                PDFTextStripper tStripper = new PDFTextStripper();
                String pdfFileInText = tStripper.getText(document);
                pdfFileInText = pdfFileInText.replaceAll("(\\s|\n)", "");
                System.out.println(pdfFileInText);
                System.out.println("____________________________________________________________\n");
                for(String texto : textosABuscar) {
                    if(!pdfFileInText.contains(texto)) {
                        System.out.println("No se encontró la línea '" + texto + "'.");
                        return false;
                    }
                }

                return true;
            } else {
                System.out.println("El archivo PDF está encriptado, y no fue posible leerlo.");
                return false;
            }
        }
    }
}
