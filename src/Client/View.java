package Client;

import External.ButtonColumn;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
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
                CarWindow.openWindow(car);
            }
        };
        ButtonColumn buttonColumn = new ButtonColumn(modelsList, viewMore, tableModel.getColumnCount() - 1);
        buttonColumn.setMnemonic(KeyEvent.VK_D);
    }
}
