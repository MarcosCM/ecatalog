package Admin;

import External.ButtonColumn;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

/**
 * Vista de la aplicación
 */
public class View {

    /**
     * Visualiza una lista de coches
     *
     * @param models Lista de coches
     * @param modelsList Lista que contiene los resultados de la búsqueda
     * @param numFeatured Número de coches destacados (los destacados siempre
     * salen al principio)
     */
    public static void list(final CarModel[] models, JTable modelsList, int numFeatured) {
        final DefaultTableModel tableModel = (DefaultTableModel) modelsList.getModel();
        //limpia la lista
        tableModel.setRowCount(0);
        //actualiza la tabla con nuevos coches
        for (final CarModel c : models) {
            String hidden = "Ocultar";
            if (c.getHidden()) {
                hidden = "Mostrar";
            }
            tableModel.addRow(new Object[]{c.getName(), c.getCategory(),
                c.getCost(), c.getPower(), c.getFuelType(), c.getConsumption(),
                "Modificar", "Borrar", hidden});
        }

        Action viewMore = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.valueOf(e.getActionCommand());
                CarWindow.openWindow(models[row], true);
            }
        };

        Action delete = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.valueOf(e.getActionCommand());
                Controller.borrar(models[row].getName());
            }
        };

        Action hide = new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int row = Integer.valueOf(e.getActionCommand());
                String name = models[row].getName();
                models[row].setHidden(!models[row].getHidden());
                if (models[row].getHidden()) {
                    Controller.hide(name, true);
                    tableModel.setValueAt("Mostrar", row, tableModel.getColumnCount() - 1);
                } else {
                    Controller.hide(name, false);
                    tableModel.setValueAt("Ocultar", row, tableModel.getColumnCount() - 1);
                }
            }
        };

        ButtonColumn columnViewMore = new ButtonColumn(modelsList, viewMore, tableModel.getColumnCount() - 3);
        ButtonColumn columnDelete = new ButtonColumn(modelsList, delete, tableModel.getColumnCount() - 2);
        ButtonColumn columnHide = new ButtonColumn(modelsList, hide, tableModel.getColumnCount() - 1);

        columnViewMore.setMnemonic(KeyEvent.VK_D);
        columnDelete.setMnemonic(KeyEvent.VK_D);
        columnHide.setMnemonic(KeyEvent.VK_D);

        modelsList.setDefaultRenderer(Object.class, new FeaturedCarRenderer(numFeatured));
    }

    /**
     * Muestra un mensaje informando del resultado de la operación de modificar
     *
     * @param car Coche modificado
     * @param stored true si ha sido actualizado con éxito, false en caso
     * contrario
     * @param accion true si es "modificar", false si es "introducir"
     */
    public static void modify(CarModel car, boolean stored, boolean accion) {
        String msg = "";
        if (accion) {
            if (stored) {
                msg = "¡El coche " + car.getName() + " ha sido actualizado con éxito!";
            } else {
                msg = "No ha sido posible actualizar el coche.\nPor favor, revisa los valores introducidos";
            }
        }
        else{
            if (stored) {
                msg = "¡El coche " + car.getName() + " ha sido introducido con éxito!";
            } else {
                msg = "No ha sido posible introducir el coche.\nPor favor, revisa los valores introducidos";
            }
        }
        JOptionPane.showMessageDialog(null, msg);
    }
}
