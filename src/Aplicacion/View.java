/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
     */
    public static void list(CarModel[] models, JScrollPane panel){
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
                    btn_masDetallesActionPerformed(evt, c);
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
     * @param evt 
     */
    private static void btn_masDetallesActionPerformed(java.awt.event.ActionEvent evt, CarModel c) {
         view(c);
    }
     
    /**
     * Muestra la ficha de un coche
     * @param model Coche a mostrar
     */
    public static void view(CarModel model){
        
    }
    
    /**
     * Muestra la pantalla de edición de un coche
     * @param model Coche a editar
     */
    public static void edit(CarModel model){
        
    }
}
