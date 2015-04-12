package Aplicacion;

import Database.JDBCTemplate;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map;

/**
 * Controlador de la aplicaci�n
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
     * @param form Filtro de par�metros
     */
    public static void list(Form form){
    	JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
    	String options=getSQLOptions(form);
    	String query="SELECT * FROM Car "+options;
    	ResultSet rs =template.executeQuery(query).getResultSet();
    	CarModel car = null;
    	CarModel[]models = null;
    	int sizeVector=0;
    	try {
    		rs.first();
    		//calculamos el tamano que tendra el vector de modelos
			while(rs.next()){
				sizeVector++;
			}
    		models=new CarModel[sizeVector];
			rs.first();
			//introducimos cada coche en el vector
			for(int i=0;i<sizeVector;i++){
				rs.next();
				car = new CarModel(rs.getString("name"), rs.getString("fuel_type"), rs.getInt("power"), rs.getString("category"),
	                    rs.getInt("number_doors"), rs.getInt("cost"), rs.getDouble("consumption"), rs.getInt("number_seats"));
				models[i]=car;
			}
		} catch (SQLException e) {}
    	//pasamos el vector de modelos a la vista
    	View.list(models);
    	
    }
    
    /**
     * Accede a la ficha de un coche
     * @param carName Nombre identificador del coche
     */
    public static void view(String carName){
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        
        String query = "SELECT * FROM Car WHERE name='"+carName+"'";
        //la query s�lo devolver� una fila como resultado
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
     * Accede a la pantalla de edici�n de un coche
     * @param carName Nombre identificador del coche
     */
    public static void edit(String carName){
        
    }
    
    /**
     * Env�a una solicitud de contacto para concertar una cita y ver un coche
     * @param form Filtro de par�metros
     * @param carName Nombre identificador del coche que desea ver el cliente
     */
    public static void contactRequest(Form form, String carName){
        String insert="INSERT into Request (request_id,requester_name, requester_mail, car_name, request_date) "+
        		"VALUES ( requests_id.nextval, ";
        Map.Entry<String, String> aux;
        Iterator<Map.Entry<String, String>> iterator = form.getIterator();
        insert+=iterator.next();
        //requester_name y requester_mail
        while (iterator.hasNext()){
            aux = iterator.next();
            insert+=aux.getValue()+", ";
        }
        //momento de la compra
        insert+=carName+", CURRENT_STAMP);";
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        template.executeQuery(insert);
    }
}
