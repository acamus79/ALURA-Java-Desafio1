/*
 * Desafio Alura Java 1
 */
package conversor.conversores;

import java.text.DecimalFormat;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author https://github.com/acamus79
 */
public class Distancia {
    
     /**
     * Metodo público que recibe un valor Double solicita el tipo de conversión
     * y, devuelve un String con el resultado
     * @param val
     * @return 
     */
    public String valor(Double val){
        Object[] options = {"Milla's", "Yarda's", "Pulgada's"};
        int x = JOptionPane.showOptionDialog(null, "Selecciones a que convertir",
                "Conversion de Distancia",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, new ImageIcon("assets/pesoMedida.png"), options, options[2]);
       
        Double resultado;
        String ret = "";
        DecimalFormat df = new DecimalFormat("#,##0.00");
        switch (x) {
            case 0:
                resultado = this.conversion(val,0);
                ret = val.toString()+" mts en Millas es = "+df.format(resultado);
                break;
            case 1:
                resultado = this.conversion(val,1);
                ret = val.toString()+" mts en Yardas es = "+df.format(resultado);
                break;
            case 2:
                resultado = this.conversion(val,2);
                ret = val.toString()+" mts en Pulgadas es = "+df.format(resultado);
                break;
        }
        
        return ret;
        
    }
    
     /**
     * Metodo privado que recive un valor Double y una opcion int, segun la 
     * opcion realiza la conversion y devuelve el resultado convertido
     * @param val
     * @param op
     * @return Double
     */
    private Double conversion(Double val, int op){
        Double res = 0.0;
        switch (op){
            case 0:
                res = val / 1609;
                break;
            case 1:
                res = val * 1.094;
                break;
            case 2:
                res = val * 39.37;
                break;
        }
        return res;
    }
}
