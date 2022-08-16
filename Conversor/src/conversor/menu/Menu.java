/*
 * Desafio Alura Java 1
 */
package conversor.menu;

import conversor.conversores.Distancia;
import conversor.conversores.Moneda;
import conversor.conversores.Peso;
import javax.swing.ImageIcon;
import javax.swing.JOptionPane;

/**
 *
 * @author https://github.com/acamus79
 */
public class Menu {
    
    /**
     * Menu Principal
     */
    public void menu(){
     Object seleccion = JOptionPane.showInputDialog(
                null,
                "Seleccione opcion",
                "CONVERSOR",
                JOptionPane.QUESTION_MESSAGE,
                new ImageIcon("assets/conversor.png"), // null para icono defecto
                new Object[]{"Dolares U$$ a Pesos ARG$", "Conversor de Metros", "Conversor de Kilos"},
                "Conversor de Moneda");
        if (seleccion == null){
            this.exit();
        }
        
     Double val;
        
        switch ((String) seleccion) {
            case "Dolares U$$ a Pesos ARG$":
                System.out.println("El usuario ha elegido " + seleccion);
                val = this.valor();
                if(val > 0 && !val.isNaN()){
                    Moneda m = new Moneda();
                    this.resultado(m.cambio(val), new ImageIcon("assets/dolar.png"));
                }             
                break;
            case "Conversor de Metros":
                val = this.valor();
                if(val > 0 && !val.isNaN()){
                    Distancia d = new Distancia();
                    this.resultado(d.valor(val), new ImageIcon("assets/pesoMedida.png"));
                }
                break;
            case "Conversor de Kilos":
                val = this.valor();
                if(val > 0 && !val.isNaN()){
                    Peso p = new Peso();
                    this.resultado(p.valor(val), new ImageIcon("assets/pesoMedida.png"));
                }
                break;
            default:
                System.exit(0);
        }
        
        }
    
    /**
     * Pide un Valor al usuario mediante un imputDialog
     * Verifica que sea un numero y devuelve un valor Double
     * @return Double
     */
    private Double valor(){
        Double valor = 0.0;
        while(valor == 0.0){
            String seleccion = JOptionPane.showInputDialog(null,"Ingrese el valor distinto de cero", "0.0");
            if (seleccion == null){
                this.menu();
            }
            if(seleccion.isEmpty() || seleccion.matches(".*[^0-9].*")){
                JOptionPane.showMessageDialog(null, "Valor no válido", "Atención!", JOptionPane.WARNING_MESSAGE, new ImageIcon("assets/warning.png"));
                valor = 0.0;
            }else valor = Double.parseDouble(seleccion);
        }
        return valor;
    }
    
    /**
     * Muestra los Resultados de las conversionces
     * @param convertido 
     */
    private void resultado(String convertido, ImageIcon ic){
        Object[] options = {"SALIR", "CONVERSOR"};
        int x = JOptionPane.showOptionDialog(null, convertido,
                "RESULTADO",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, ic, options, options[1]);
		if(x == 1) {
		 this.menu();
		}else this.exit();
    }
    
    /**
     * Muestra mensaje de confirmacion para salir 
     */
    private void exit(){
        Object[] options = {"SALIR", "VOLVER"};
        int x = JOptionPane.showOptionDialog(null, "Está Seguro que desea Salir?",
                "SALIR",
                JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE, new ImageIcon("assets/warning.png"), options, options[1]);
		if(x == 1) {
		 this.menu();
		}else System.exit(0);
    }
}
