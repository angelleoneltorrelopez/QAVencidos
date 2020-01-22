package Auxiliar;

/**
 * @author Angel Torre
 */
public class Data {

    public static class URLS {
        /* FARMACIA */
        public final static String URL_FARMACIA = "http://localhost/";

        /* VENCIDOS */
        public final static String URL_VENCIOS = "http://52.39.84.134/vencidos/";

        /*IMPRESORAS 3D*/
        public final static String URL_IMPRESORAS_3D = "http://beta.akademeia.ufm.edu/cotizacionbeta/public";

        public static String obtenerURLControlWintel(String idSalon, String accion) {
            return "http://betaasistencias.ufm.edu/home/public/manualOnOffline/" + idSalon + "/" + accion;
        }

    }

    public static class AUTOMATIZACION {
        /* AUTOMATIZACION */
        public final static String USUARIO = "xik";
        public final static String CONTRASENA = "123456";
        //public final static String CODIGO_CIERRE_ASISTENCIA = "8989";
        public final static String CODIGO_CIERRE_ASISTENCIA = "1111";
        public final static String IP_WINTEL = "200.0.176.180";
    }

    public static class GRUPOS {
        public final static String recepciónCeta = "Recepción Ceta";
    }

    public static class TIPOASISTENCIA {
        public final static String checklist = "checklist";
        public final static String ordenDeCompra = "Orden de compra";
    }

    public static class FACULTADES {
        public final static String xik = "XIK";
    }

    public static class DESCARGAS {
        public final static String directorioDeDescargas = System.getProperty("user.dir")+"\\downloads\\";
    }
}