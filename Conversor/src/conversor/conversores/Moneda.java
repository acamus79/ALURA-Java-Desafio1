/*
 * Desafio Alura Java 1
 */
package conversor.conversores;

import java.net.HttpURLConnection;
import java.net.URL;
import java.text.DecimalFormat;
import java.util.Scanner;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;
import org.json.JSONObject;

/**
 *
 * @author https://github.com/acamus79
 */
public class Moneda {
    
    /**
     * Metodo publico recibe un valor Double, solicita el tipo de cambio y
     * devuelve un String con el resultado
     * @param val
     * @return 
     */
    public String cambio(Double val) {
        Object[] options = {"Oficial", "Blue", "Bolsa"};
        int x = JOptionPane.showOptionDialog(null, "Qué Tipo de Dolar?",
                "En Argentina tenemos muchos Dolares!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, new ImageIcon("assets/arg.png"), options, options[0]);

        Double cambio = 0.0;
        String op = " ";
        switch (x) {//Cada Case del switch, llama a un Endpoint GET diferente
            case 0:
                cambio = this.dolar("dolaroficial");
                op = "Oficial";
                break;
            case 1:
                cambio = this.dolar("dolarblue");
                op = "Blue";
                break;
            case 2:
                cambio = this.dolar("dolarbolsa");
                op = "Bolsa";
                break;
        }
        DecimalFormat decimalFormat = new DecimalFormat("#,##0.00");
        return val.toString() + " US$ " + op + " en ARG es: $ " + decimalFormat.format(val * cambio);
    }
    
    /**
     * Metodo privado que recive un String endpoint y realiza la coneccion a la
     * API para obtener el valor del cambio peso/dolar del dia, transforma el 
     * resultado en un valor Double y lo devuelve
     * @param op
     * @return 
     */
    private Double dolar(String endpoint) {
        try {
            URL url = new URL("https://api-dolar-argentina.herokuapp.com/api/" + endpoint);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();

            //por si la API no me contesta con Ok 200
            if (conn.getResponseCode() != 200) {
                JOptionPane.showMessageDialog(null,
                        "Fallo la comunicacion a la API-dolar-arg",
                        "ERROR!", JOptionPane.WARNING_MESSAGE,
                        new ImageIcon("assets/warning.png"));
                throw new RuntimeException("Ocurrio un Error " + conn.getResponseCode());
            } else {
                StringBuilder info = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());

                while (sc.hasNext()) {
                    info.append(sc.nextLine());
                }

                sc.close();
                JSONObject json = new JSONObject(info.toString());
                return Double.parseDouble(json.getString("compra"));
                //si quisiera converti segun el valor de venta json.getString("venta"))
            }

        } catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }

}
