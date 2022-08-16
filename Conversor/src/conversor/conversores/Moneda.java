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
    
    public String cambio(Double val){
        Object[] options = {"Oficial", "Blue", "Bolsa"};
        int x = JOptionPane.showOptionDialog(null, "Qué Tipo de Dolar?",
                "En Argentina tenemos muchos Dolares!",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, new ImageIcon("assets/arg.png"), options, options[0]);
       
        Double cambio = 0.0;
        String op = " ";
        switch (x) {
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
        return val.toString()+" US$ " + op + " en ARG es: $ "+decimalFormat.format(val*cambio);
    }
 
    public Double dolar(String op){
        try{
            URL url = new  URL("https://api-dolar-argentina.herokuapp.com/api/"+op);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("GET");
            conn.connect();
          
            if(conn.getResponseCode()!=200){
                throw new RuntimeException("Ocurrio un Error "+ conn.getResponseCode());
            }else{
                StringBuilder info = new StringBuilder();
                Scanner sc = new Scanner(url.openStream());
                while(sc.hasNext()){
                    info.append(sc.nextLine());
                }
                sc.close();
                JSONObject json = new JSONObject(info.toString());
                //System.out.println(json.getString("compra"));
                return Double.parseDouble(json.getString("compra"));
            }
            
        }catch (Exception e) {
            e.getMessage();
            e.printStackTrace();
            return null;
        }
    }
    
    
}
