package Client;

import Database.Cursor;
import Database.JDBCTemplate;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Modelo lógico de un coche
 */
public class CarModel {

    private String name;	// nombre del coche
    private String fuelType;	// tipo de combustible
    private int power;		// potencia (cv)
    private String category;	// categoria
    private int numberDoors;	// número de puertas
    private int cost;		// coste
    private double consumption;	// consumo (l/100km)
    private int numberSeats;	// número de asientos

    /**
     * Crea una instancia del modelo lógico a partir de datos existentes en
     * la base de datos.
     * @param name Nombre (clave) del modelo de coche
     */
    public CarModel(String name){
        this.name = name;
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        String query = "SELECT * FROM Car WHERE name='"+name+"'";
        Cursor c = template.executeQuery(query);
        ResultSet rs = c.getResultSet();
        try {
            rs.next();
            fuelType = rs.getString("fuel_type");
            power = Integer.parseInt(rs.getString("power"));
            category = rs.getString("category");
            numberDoors = Integer.parseInt(rs.getString("number_doors"));
            cost = Integer.parseInt(rs.getString("cost"));
            consumption = Double.parseDouble(rs.getString("consumption"));
            numberSeats = Integer.parseInt(rs.getString("number_seats"));
        } catch (SQLException ex) {}
    }
    
    /**
     * Crea una instancia del modelo lógico
     * @param name Nombre del coche
     * @param fuelType Tipo de combustible
     * @param power Potencia
     * @param category Categoría del coche
     * @param numberDoors Número de puertas
     * @param cost Precio
     * @param consumption Consumo
     * @param numberSeats Número de asientos
     */
    public CarModel(String name, String fuelType, int power, String category,
                int numberDoors, int cost, double consumption, int numberSeats) {
        this.name = name;
        this.fuelType=fuelType;
        this.power=power;
        this.category=category;
        this.numberDoors=numberDoors;
        this.cost=cost;
        this.consumption=consumption;
        this.numberSeats=numberSeats;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @return the fuelType
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @return the numberDoors
     */
    public int getNumberDoors() {
        return numberDoors;
    }
    
    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @return the consumption
     */
    public double getConsumption() {
        return consumption;
    }

    /**
     * @return the numberSeats
     */
    public int getNumberSeats() {
        return numberSeats;
    }
    
    @Override
    public String toString(){
        return name + " - " + category + " - " + cost + " euros - " + power + " CV - " +
                fuelType + " - " + consumption + " l/100 km - " + numberDoors + " puertas - " + 
                numberSeats + " asientos";
    }
}
