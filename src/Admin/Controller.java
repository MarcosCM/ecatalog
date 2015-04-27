package Admin;

import Database.JDBCTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;
import javax.swing.JTable;

/**
 * Controlador de la aplicación
 */
public class Controller {

    /**
     * Obtiene un string que representa en SQL una condición para un determinado
     * par clave => valor
     *
     * @return String que representa una condición en SQL
     */

    private static String getSQLOption(String key, String value){
        if (key.endsWith("Min")) return key.substring(0, key.lastIndexOf("Min")) + ">='" + value + "'";
        else if (key.endsWith("Max")) return key.substring(0, key.lastIndexOf("Max")) + "<='" + value + "'";
        //NOTA: no hay problemas con que sea un tipo de dato distinto a string
        else if (key.contains("name")) {
            String respuesta="";

            String []todos =value.split(" ");
            for(int i=0; i<todos.length;i++){
                if(i!=0)respuesta+=" AND ";
                   respuesta+= key + " like'%" + value + "%'";

            }
            return respuesta;
        }
        else return key + "='" + value + "'";

    }
    
    
        /**
     * Obtiene un string que representa en SQL una condición para un determinado
     * par clave => valor
     *
     * @return String que representa una condición en SQL
     */


      private static String getSQLOptions2(Form form){
        if (form.isEmpty()) return "";
        else{
            String res = " AND  ";
            Map.Entry<String, String> aux;
            
            //condiciones de la forma campo='valor'
            Iterator<Map.Entry<String, String>> iterator = form.getIterator();
            aux = iterator.next();
            res += getSQLOption(aux.getKey(), aux.getValue());
            while (iterator.hasNext()){
                aux = iterator.next();
                res += " AND " + getSQLOption(aux.getKey(), aux.getValue());
            }
            
            return res;
        }
    }

    /**
     * Obtiene un string que representa en SQL un conjunto de condiciones para
     * determinados pares clave => valor
     *
     * @return String que representa un conjunto de condiciones en SQL
     */
    private static String getSQLOptions(Form form) {
        if (form.isEmpty()) {
            return "";
        } else {
            String res = "WHERE ";
            Map.Entry<String, String> aux;

            //condiciones de la forma campo='valor'
            Iterator<Map.Entry<String, String>> iterator = form.getIterator();
            aux = iterator.next();
            res += getSQLOption(aux.getKey(), aux.getValue());
            while (iterator.hasNext()) {
                aux = iterator.next();
                res += " AND " + getSQLOption(aux.getKey(), aux.getValue());
            }

            return res;
        }
    }

    /**
     * Accede al listado de coches
     * @param form Filtro de parámetros
     * @param modelsList Lista que contiene los resultados de la búsqueda
     */
    public static void list(Form form, JTable modelsList){
        ArrayList<CarModel> models = new ArrayList<CarModel>();
    	JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
    	String options = getSQLOptions(form);
    	String query = "SELECT * FROM Car "+options;
        
        


/*
<<<<<<< HEAD
                    //SI ALFINAL ES SOLO UNA TABLA CON NOMBRE

=======
        
>>>>>>> f27a445c1a1412e90110319da7da1ac459e0583d
        //Primero hay que introducir los que están destacados
        options = getSQLOptions2(form);
        String query = "SELECT * FROM Car coche where coche.name in  (SELECT name FROM Destacados)"+options;
         ResultSet rs = template.executeQuery(query).getResultSet();
<<<<<<< HEAD
        try {
            while(rs.next()){
                models.add(new CarModel(rs.getString("name"), rs.getString("fuel_type"), rs.getInt("power"), rs.getString("category"),
                        rs.getInt("number_doors"), rs.getInt("cost"), rs.getDouble("consumption"), rs.getInt("number_seats")),true);
            }
        } catch (SQLException e){}
         query = "SELECT * FROM Car coche where coche.name not in (SELECT name FROM Destacados)"+options;
         rs = template.executeQuery(query).getResultSet();
                try {
            while(rs.next()){
                models.add(new CarModel(rs.getString("name"), rs.getString("fuel_type"), rs.getInt("power"), rs.getString("category"),
                        rs.getInt("number_doors"), rs.getInt("cost"), rs.getDouble("consumption"), rs.getInt("number_seats")),false);
            }
        } catch (SQLException e){}
        
        
        
                    //SI ALFINAL ES TODA  UNA TABLA CON TODOS LOS DATOS
  //Primero hay que introducir los que están destacados
        options = getSQLOptions2(form);
        //poner que destacado sea 1 o true, segun el tipo de dato
        String query = "SELECT * FROM Car coche where destacado ='' "+options;
         ResultSet rs = template.executeQuery(query).getResultSet();
=======
>>>>>>> f27a445c1a1412e90110319da7da1ac459e0583d
        try {
            while(rs.next()){
                models.add(new CarModel(rs.getString("name"), rs.getString("fuel_type"), rs.getInt("power"), rs.getString("category"),
                        rs.getInt("number_doors"), rs.getInt("cost"), rs.getDouble("consumption"), rs.getInt("number_seats")),true);
            }
        } catch (SQLException e){}
<<<<<<< HEAD
        //poner que destacado sea 0 o false, segun el tipo de dato
        String query = "SELECT * FROM Car coche where destacado ='' "+options;
=======
         query = "SELECT * FROM Car coche where coche.name not in (SELECT name FROM Destacados)"+options;
>>>>>>> f27a445c1a1412e90110319da7da1ac459e0583d
         rs = template.executeQuery(query).getResultSet();
                try {
            while(rs.next()){
                models.add(new CarModel(rs.getString("name"), rs.getString("fuel_type"), rs.getInt("power"), rs.getString("category"),
                        rs.getInt("number_doors"), rs.getInt("cost"), rs.getDouble("consumption"), rs.getInt("number_seats")),false);
            }
        } catch (SQLException e){}
        
<<<<<<< HEAD
        
=======
>>>>>>> f27a445c1a1412e90110319da7da1ac459e0583d
        */
        
        
    	ResultSet rs = template.executeQuery(query).getResultSet();
    	try {
            while(rs.next()){
                models.add(new CarModel(rs.getString("name"), rs.getString("fuel_type"), rs.getInt("power"), rs.getString("category"),
                        rs.getInt("number_doors"), rs.getInt("cost"), rs.getDouble("consumption"), rs.getInt("number_seats")));
            }
        } catch (SQLException e){}
        
    	//pasamos el vector de modelos a la vista
    	View.list(models.toArray(new CarModel[models.size()]), modelsList);
    }
    
    /**
     * Envía una solicitud de contacto para concertar una cita y ver un coche
     *
     * @param form Filtro de parámetros
     * @param carName Nombre identificador del coche que desea ver el cliente
     */
    public static void contactRequest(Form form, String carName) {
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        String insert = "INSERT INTO Requests VALUES (requests_id.nextval, ";
        insert += "'" + form.getParam("name") + " " + form.getParam("surname") + "'";
        insert += ", '" + form.getParam("mail") + "'";
        insert += ", '" + carName + "'";
        //momento de la compra
        insert += ", CURRENT_TIMESTAMP";
        insert += ", '" + form.getParam("city") + "')";
        template.executeQuery(insert);
    }

    /**
     * Borra el modelo pasado por parámetro de la base de datos
     *
     * @param model Nombre del modelo
     */
    public static void borrar(String model) {
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        //primero se borran las request de ese modelo
        String deleteRequests = "DELETE FROM Requests WHERE car_name ='" + model + "'";
        template.executeSentence(deleteRequests);
        //despues se borra el modelo concreto
        String delete = "DELETE FROM Car WHERE name ='" + model + "'";
        template.executeSentence(delete);
    }
}
