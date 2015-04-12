package Aplicacion;

import Database.JDBCTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;
import java.util.Map;

/**
 * Controlador de la aplicación
 */
public class Controller {
    
    private static String getSQLOptions(Form form){
        if (form.isEmpty()) return "";
        else{
            String res = "WHERE ";
            Map.Entry<String, String> aux;
            
            //condiciones de la forma campo='valor'
            Iterator<Map.Entry<String, String>> iterator = form.getIterator();
            res+=iterator.next();
            while (iterator.hasNext()){
                aux = iterator.next();
                res+=" AND "+aux.getKey()+"='"+aux.getValue()+"'";
            }
            
            return res;
        }
    }
    
    /**
     * Accede al listado de coches
     * @param form Filtro de parámetros
     */
    public static void list(Form form){
        
    }
    
    /**
     * Accede a la ficha de un coche
     * @param carName Nombre identificador del coche
     */
    public static void view(String carName){
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        
        String query = "SELECT * FROM Car WHERE name='"+carName+"'";
        //la query sólo devolverá una fila como resultado
        ResultSet rs = template.executeQuery(query).getResultSet();
        CarModel car = null;
        try{
            car = new CarModel(rs.getString("name"), rs.getString("fuel_type"), rs.getInt("power"), rs.getString("category"),
                    rs.getInt("number_doors"), rs.getInt("cost"), rs.getDouble("consumption"), rs.getInt("number_seats"));
        } catch(SQLException e){}
        
        //pasamos el coche a la vista
        View.view(car);
    }
    
    /**
     * Accede a la pantalla de edición de un coche
     * @param carName Nombre identificador del coche
     */
    public static void edit(String carName){
        
    }
    
    /**
     * Envía una solicitud de contacto para concertar una cita y ver un coche
     * @param form Filtro de parámetros
     * @param carName Nombre identificador del coche que desea ver el cliente
     */
    public static void contactRequest(Form form, String carName){
        
    }
}
