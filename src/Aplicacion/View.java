package Aplicacion;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextPane;

/**
 * Vista de la aplicación
 */
public class View {
    
    /**
     * Visualiza una lista de coches
     * @param models Lista de coches
     * @param panel Panel donde se carga la lista
     * @param panelFicha Panel donde se carga la ficha de un coche
     */
    public static void list(CarModel[] models, JScrollPane panel, final JScrollPane panelFicha){
        JPanel model;
        JTextPane name, cost;
        JButton view;
        for (final CarModel c : models){
            model = new JPanel();
            name = new JTextPane();
            cost = new JTextPane();
            view = new JButton("Más detalles...");
            view.addActionListener(new java.awt.event.ActionListener() {
                @Override
                public void actionPerformed(java.awt.event.ActionEvent evt) {
                    btn_masDetallesActionPerformed(evt, c, panelFicha);
                }
            });
            name.setText(c.getName());
            cost.setText(""+c.getCost());
            model.add(name);
            model.add(cost);
            model.add(view);
            panel.add(model);
        }
    }
    
    /**
     * Cuando se pulsa el botón de más detalles se ejecuta este método
     * @param evt evento
     * @param c Coche a mostrar
     * @param panelFicha Panel en el que se mostrará el coche
     */
    private static void btn_masDetallesActionPerformed(java.awt.event.ActionEvent evt, CarModel c, JScrollPane panelFicha) {
         view(c, panelFicha);
    }
     
    /**
     * Muestra la ficha de un coche
     * @param model Coche a mostrar
     * @param panel Panel en el que se mostrará el coche
     */
    public static void view(CarModel model, JScrollPane panel){
        
    }
}
