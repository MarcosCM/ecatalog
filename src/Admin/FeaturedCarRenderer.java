package Admin;

import java.awt.Color;
import java.awt.Component;
import javax.swing.JTable;
import javax.swing.table.DefaultTableCellRenderer;
 
public class FeaturedCarRenderer extends DefaultTableCellRenderer{
    
    /**
     * Número de filas a destacar
     */
    private final int numFeatured;
    
    public FeaturedCarRenderer(int numFeatured){
        this.numFeatured = numFeatured;
    }
    
    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, 
              boolean isSelected, boolean hasFocus, int row, int column) {
        DefaultTableCellRenderer renderer = (DefaultTableCellRenderer) super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
        
        if (row < numFeatured){
            renderer.setBackground(Color.YELLOW);
        } else {
            renderer.setBackground(Color.WHITE);
        }
        renderer.setForeground(Color.BLACK);
        renderer.setEnabled(true);
        
        return this;
    }
    
}
