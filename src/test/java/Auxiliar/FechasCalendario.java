package Auxiliar;

import org.testng.Assert;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.concurrent.ThreadLocalRandom;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @author Jhonatan Flores
 * @modified Rafael Mazariegos
 */

public class FechasCalendario {
    private static final java.util.logging.Logger LOGGER = java.util.logging.Logger.getLogger( FechasCalendario.class.getName() );
    private static String patron = "yyyy-MM-dd";

    public static int obtenerDiferenciaDeTiempoEntreFechas(String stringFechaInicial, String stringFechaFinal, String unidadDeTiempo) {
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        Date fechaInicial = null;
        Date fechaFinal = null;

        try{
            fechaInicial = formato.parse(stringFechaInicial);
            fechaFinal = formato.parse(stringFechaFinal);
        }catch (Exception ParseException) {
            Assert.fail(ParseException.getMessage());
        }

        long tiempoInicial = fechaInicial.getTime();
        long tiempoFinal = fechaFinal.getTime();
        long tiempoDiferencia = tiempoFinal - tiempoInicial;

        switch(unidadDeTiempo) {

            case "segundo":
            case "segundos":{
                tiempoDiferencia = tiempoDiferencia/1000;
                break;
            }

            case "minuto":
            case "minutos":{
                tiempoDiferencia = tiempoDiferencia/(1000*60);
                break;
            }

            case "hora":
            case "horas":{
                tiempoDiferencia = tiempoDiferencia/(1000*60*60);
                break;
            }

            case "día":
            case "dia":
            case "días":
            case "dias":{
                tiempoDiferencia = tiempoDiferencia/(1000*60*60*24);
                break;
            }
        }

        return (int) tiempoDiferencia;
    }

    public static String obtener(String opcion){
        Date d = new Date();
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(d);

        String dia, mes, annio, hora, minuto, segundo, seleccion;

        dia = Integer.toString(calendar.get(Calendar.DATE));
        mes = Integer.toString(calendar.get(Calendar.MONTH)+1);
        annio = Integer.toString(calendar.get(Calendar.YEAR));
        hora = Integer.toString(calendar.get(Calendar.HOUR_OF_DAY));
        minuto = Integer.toString(calendar.get(Calendar.MINUTE));
        segundo = Integer.toString(calendar.get(Calendar.SECOND));

        if(dia.length()==1){
            dia=0+dia;
        }
        if(mes.length()==1){
            mes=0+mes;
        }
        if(hora.length()==1){
            hora=0+hora;
        }
        if(minuto.length()==1){
            minuto=0+minuto;
        }


        opcion=opcion.toLowerCase();
        switch (opcion) {
            case "day": seleccion=dia;break;
            case "month": seleccion=mes;break;
            case "year": seleccion=annio;break;
            case "hour": seleccion=hora;break;
            case "minute": seleccion=minuto;break;
            case "second": seleccion=segundo;break;
            default: seleccion = "Invalid month";break;
        }

        return seleccion;
    }

    public static String[] obtenerArregloDeFecha(String fecha) {
        String regex = "(([0-2]\\d|3[01])-(0\\d|1[0-2])-(\\d\\d\\d\\d) ([01]\\d|2[0123]):([0-5]\\d):([0-5]\\d))";
        String[] arregloDeFecha = new String[6];

        Date temporal = dateFechaConFormato(fecha, patron);
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy hh:mm:ss");
        String fechaConFormato = sdf.format(temporal);

        Pattern patron = Pattern.compile(regex);
        Matcher matcher = patron.matcher(fechaConFormato);

        if(matcher.find()) {
            for(int i = 0; i < arregloDeFecha.length; i++) {
                arregloDeFecha[i] = matcher.group(i);
            }
        }

        return arregloDeFecha;
    }

    public static void definirPatron(String patronNuevo) {
        patron = patronNuevo;
    }

    public static String obtenerFechaYHoraActual() {
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(patron);
        Date date = new Date();
        String fechaYHoraFormato = simpleDateFormat.format(date);
        return fechaYHoraFormato;
    }

    public static String convertirDateAStringDeFecha(Date fecha) {
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        String fechaYHoraFormato = formato.format(fecha);
        return fechaYHoraFormato;
    }

    public static String sumarRestar(String fecha, String unidadDeTiempo, int cantidad) {
        Date temporal = dateFecha(fecha);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(temporal);

        switch(unidadDeTiempo) {

            case "minuto":
            case "minutos":{
                calendar.add(Calendar.MINUTE, cantidad);
                break;
            }

            case "hora":
            case "horas":{
                calendar.add(Calendar.HOUR, cantidad);
                break;
            }

            case "día":
            case "dia":
            case "días":
            case "dias":{
                calendar.add(Calendar.DAY_OF_YEAR, cantidad);
                break;
            }

            case "meses":
            case "mes":{
                calendar.add(Calendar.MONTH, cantidad);
                break;
            }

            case "año":
            case "anio":
            case "ano":
            case "años":
            case "anios":
            case "anos":{
                calendar.add(Calendar.YEAR , cantidad);
                break;
            }
        }

        temporal = calendar.getTime();
        SimpleDateFormat sdf = new SimpleDateFormat(patron);
        String mes = sdf.format(temporal);
        return mes;
    }

    public static String cambiarFormatoFecha(String fecha, String patronEntrada, String patronSalida) {
        Date temporal = dateFechaConFormato(fecha, patronEntrada);
        SimpleDateFormat sdf = new SimpleDateFormat(patronSalida);
        return sdf.format(temporal);
    }

    public static String obtenerFechaAleatoriaRango(String fechaInicial, String fechaFinal) {
        Date fechaInicialTemporal = dateFecha(fechaInicial);
        Date fechaFinalTemporal = dateFecha(fechaFinal);
        Date fechaSalida;
        SimpleDateFormat sdf = new SimpleDateFormat(patron);

        fechaSalida = new Date(ThreadLocalRandom.current().nextLong(fechaInicialTemporal.getTime(), fechaFinalTemporal.getTime()));
        return sdf.format(fechaSalida);
    }

    private static Date dateFechaConFormato(String fechaEntrada, String patronSalida) {
        SimpleDateFormat formato = new SimpleDateFormat(patronSalida);
        Date fecha = null;
        try {
            fecha = formato.parse(fechaEntrada);
        } catch (Exception ParseException) {
            Assert.fail(ParseException.getMessage());
        }
        return fecha;
    }

    private static Date dateFecha(String fechaCambio){
        SimpleDateFormat formato = new SimpleDateFormat(patron);
        Date fecha = null;
        try{
            fecha = formato.parse(fechaCambio);
        }catch (Exception ParseException){
            Assert.fail(ParseException.getMessage());
        }
        return fecha;
    }
}