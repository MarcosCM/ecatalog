package Aplicacion;

import External.ButtonColumn;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Vista de la aplicación
 */
public class View {
    
    /**
     * Visualiza una lista de coches
     * @param models Lista de coches
     * @param modelsList Lista que contiene los resultados de la búsqueda
     */
    public static void list(CarModel[] models, JTable modelsList){
        final DefaultTableModel tableModel = (DefaultTableModel) modelsList.getModel();
        //limpia la lista
        tableModel.setRowCount(0);
        
        //actualiza la tabla con nuevos coches
        for (final CarModel c : models){
            tableModel.addRow(new Object[]{c.getName(), c.getCategory(), c.getCost(), c.getPower(), c.getFuelType(), c.getConsumption(), "Ver más"});
        }
        
        Action viewMore = new AbstractAction(){
            @Override
            public void actionPerformed(ActionEvent e)
            {
                int row = Integer.valueOf(e.getActionCommand());
                String name = (String) tableModel.getValueAt(row, 0);
                CarModel car = new CarModel(name);
                //Descripcion.openWindow(car);
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(modelsList, viewMore, tableModel.getColumnCount() - 1);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }
    
    /**
     * Cuando se pulsa el botón de más detalles se ejecuta este método
     * @param evt evento
     * @param c Coche a mostrar
     */
    private static void btn_masDetallesActionPerformed(java.awt.event.ActionEvent evt, CarModel c) {
        //Descripcion.openWindow(c);
    }
     
    /**
     * Muestra la ficha de un coche
     * @param model Coche a mostrar
     * @param panel Panel en el que se mostrará el coche
     */
    public static void view(CarModel model, JScrollPane panel){
        
    }
}
