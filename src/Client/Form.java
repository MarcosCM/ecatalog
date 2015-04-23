package Client;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

/**
 * Gestiona filtros de parámetros
 */
public class Form {
    
    /**
     * Objeto que gestiona los pares clave => valor
     */
    private Map<String, String> formParams;
    
    /**
     * Crea un filtro de parámetros vacío
     */
    public Form(){
        this(new HashMap<String, String>());
    }
    
    /**
     * Crea un filtro de parámetros a partir de pares clave => valor
     * @param formParams Objeto con los pares clave => valor
     */
    public Form(Map<String, String> formParams){
        this.formParams = formParams;
    }
    
    /**
     * Devuelve un iterador sobre los pares clave => valor
     * @return Iterador sobre pares clave => valor
     */
    public Iterator<Map.Entry<String, String>> getIterator(){
        return formParams.entrySet().iterator();
    }
    
    /**
     * Obtiene el objeto que gestiona el filtro
     * @return Objeto con datos clave => valor
     */
    public Map<String, String> getFormParams(){
        return formParams;
    }
    
    /**
     * Obtiene un parámetro del filtro
     * @param key Nombre del parámetro
     * @return Valor del parámetro
     */
    public String getParam(String key){
        return formParams.get(key);
    }
    
    /**
     * Actualiza el valor de un parámetro
     * @param key Nombre del parámetro
     * @param value Valor nuevo del parámetro
     */
    public void setParam(String key, String value){
        formParams.put(key, value);
    }
    
    /**
     * Determina si el filtro está vacío o no
     * @return true si, y solo si, está vacío, false en caso contrario
     */
    public boolean isEmpty(){
        return formParams.isEmpty();
    }
}