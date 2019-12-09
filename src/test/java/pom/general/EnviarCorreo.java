package pom.general;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.imageio.ImageIO;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.util.Properties;

public class EnviarCorreo {

    final static String DESTINATARIO = "isqtb2019xikufm@gmail.com";
    final static String REMITENTE = "testingxik@gmail.com";
    final static String CONTRASENA = "xikufm2015";

    public static void enviarCorreo(String asunto, String cuerpo) {
        capturarPantalla();
        Properties propiedades = new Properties();
        propiedades.put("mail.smtp.host", "smtp.gmail.com");
        propiedades.put("mail.transport.protocol", "smtp");
        propiedades.put("mail.smtp.starttls.enable", "true");
        propiedades.put("mail.smtp.port", "587");
        Session sesion = Session.getDefaultInstance(propiedades);
        try {
            InternetAddress fromAddress = new InternetAddress(REMITENTE);
            InternetAddress toAddress = new InternetAddress(DESTINATARIO);
            Message mensaje = new MimeMessage(sesion);
            mensaje.setFrom(fromAddress);
            mensaje.setRecipient(Message.RecipientType.TO, toAddress);
            mensaje.setSubject(asunto);
            mensaje.saveChanges();

            // Create the message part
            MimeBodyPart messageBodyPart = new MimeBodyPart();
            // Fill the message
            messageBodyPart.setContent(cuerpo, "text/html; charset=utf-8");
            // Create a multipar message
            Multipart multipart = new MimeMultipart();
            // Set text message part
            multipart.addBodyPart(messageBodyPart);
            // Part two is attachment
            messageBodyPart = new MimeBodyPart();
            String filename = System.getProperty("user.dir") + "\\testFiles\\screenshot.png";
            DataSource source = new FileDataSource(filename);
            messageBodyPart.setDataHandler(new DataHandler(source));
            messageBodyPart.setFileName(filename);
            multipart.addBodyPart(messageBodyPart);
            // Send the complete message parts
            mensaje.setContent(multipart);
            Transport.send(mensaje, REMITENTE, CONTRASENA);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }

    public static void capturarPantalla() {
        try {
            BufferedImage image = new Robot().createScreenCapture(new Rectangle(Toolkit.getDefaultToolkit().getScreenSize()));
            ImageIO.write(image, "png", new File(System.getProperty("user.dir") + "\\testFiles\\screenshot.png"));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}