package Database;

import java.io.BufferedReader;
import java.io.FileReader;

/**
 * Métodos de ayuda que contienen los scripts SQL para crear la base de datos,
 * poblarla, etc...
 */
public class Manager {
    
    /**
     * Ejecuta los scripts sobre la base de datos de forma que deja las tablas
     * creadas y pobladas
     */
    public static void prepareDatabase(){
        final String inserts = "resources/queries/Inserts.txt";
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        
        dropTables(template);
        createTables(template);
        executeQueriesFromFile(template, inserts);
        template.close();
    }
    
    /**
     * Crea las tablas de la base de datos
     * @param template Conexión con la base de datos
     */
    public static void createTables(JDBCTemplate template){
        int res;
        
        res = template.executeSentence("CREATE TABLE Fuel_Types("
                + "name         VARCHAR(40) NOT NULL,"
                + "PRIMARY KEY (name)"
                + ")");
        System.out.println("CREATE TABLE Fuel_Types: "+res);
        
        res = template.executeSentence("CREATE TABLE Categories("
                + "name         VARCHAR(40) NOT NULL,"
                + "PRIMARY KEY (name)"
                + ")");
        System.out.println("CREATE TABLE Categories: "+res);
        
        res = template.executeSentence("CREATE TABLE Car("
                + "name         VARCHAR(80) NOT NULL,"
                + "fuel_type    VARCHAR(40) NOT NULL,"
                + "power        NUMBER(4) NOT NULL,"
                + "category     VARCHAR(40) NOT NULL,"
                + "number_doors NUMBER(1) NOT NULL,"
                + "cost         NUMBER(7) NOT NULL,"
                + "consumption  NUMBER(5,3) NOT NULL,"
                + "number_seats NUMBER(1) NOT NULL,"
                + "FOREIGN KEY (fuel_type)      REFERENCES Fuel_Types(name),"
                + "FOREIGN KEY (category)       REFERENCES Categories(name),"
                + "PRIMARY KEY (name)"
                + ")");
        System.out.println("CREATE TABLE Car: "+res);
        
        res = template.executeSentence("CREATE TABLE Requests("
                + "request_id       NUMBER NOT NULL,"
                + "requester_name   VARCHAR(40) NOT NULL,"
                + "requester_mail   VARCHAR(60) NOT NULL,"
                + "car_name         VARCHAR(40) NOT NULL,"
                + "request_date     TIMESTAMP NOT NULL,"
                + "city             VARCHAR(60) NOT NULL,"
                + "FOREIGN KEY (car_name)   REFERENCES Car(name),"
                + "PRIMARY KEY (request_id)"
                + ")");
        System.out.println("CREATE TABLE Requests: "+res);
        
        res = template.executeSentence("CREATE SEQUENCE requests_id");
        System.out.println("CREATE SEQUENCE requests_id: "+res);
    }
    
    /**
     * Borra las tablas de la base de datos
     * @param template Conexión con la base de datos
     */
    public static void dropTables(JDBCTemplate template){
        int res;
        
        res = template.executeSentence("DROP SEQUENCE requests_id");
        System.out.println("DROP SEQUENCE requests_id: "+res);
        
        res = template.executeSentence("DROP TABLE Requests");
        System.out.println("DROP TABLE Requests: "+res);
        
        res = template.executeSentence("DROP TABLE Car");
        System.out.println("DROP TABLE Car: "+res);
        
        res = template.executeSentence("DROP TABLE Categories");
        System.out.println("DROP TABLE Categories: "+res);
        
        res = template.executeSentence("DROP TABLE Fuel_Types");
        System.out.println("DROP TABLE Fuel_Types: "+res);
    }
    
    /**
     * Realiza las queries que contiene un fichero
     * @param template Conexión con la base de datos
     * @param fileName Fichero que contiene las queries a realizar
     */
    public static void executeQueriesFromFile(JDBCTemplate template, String fileName){
        try {
            BufferedReader input = new BufferedReader(new FileReader(fileName));
            
            String l;
            while ((l = input.readLine()) != null){
                System.out.println(l);
                if (l.startsWith("SELECT ")) template.executeQuery(l);
                else template.executeSentence(l);
            }
            
            input.close();
        } catch (Exception ex) {}
    }
}
