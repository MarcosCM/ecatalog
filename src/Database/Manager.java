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
     * @param args Opciones del programa
     */
    public static void main(String[] args){
        final String dropTables = "resources/queries/Drop_Tables.txt";
        final String createTables = "resources/queries/Create_Tables.txt";
        final String createTriggers = "resources/queries/Create_Triggers.txt";
        final String inserts = "resources/queries/Inserts.txt";
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        
        executeQueriesFromFile(template, dropTables);
        executeQueriesFromFile(template, createTables);
        executeQueriesFromFile(template, createTriggers);
        executeQueriesFromFile(template, inserts);
        template.close();
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
