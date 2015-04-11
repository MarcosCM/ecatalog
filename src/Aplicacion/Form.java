package Aplicacion;

import java.util.HashMap;
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
}
