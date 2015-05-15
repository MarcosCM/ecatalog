package Client;

import Database.Cursor;
import Database.JDBCTemplate;
import java.io.File;
import java.io.FileOutputStream;
import java.sql.PreparedStatement;
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
    private boolean hidden;     // si está oculto por el administrador
    private boolean featured;   // si está destacado por el administrador
    private byte[] img;         // imagen del coche en bytes
    
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
            hidden = Boolean.parseBoolean("hidden");
            img = rs.getBytes("img");
            
            query = "SELECT * FROM Featured_cars WHERE name='"+name+"'";
            c = template.executeQuery(query);
            rs = c.getResultSet();
            featured = rs.next();
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
     * @param hidden Oculto
     * @param featured Destacado
     * @param img Imagen en bytes
     */
    public CarModel(String name, String fuelType, int power, String category,
                int numberDoors, int cost, double consumption, int numberSeats,
                boolean hidden, boolean featured, byte[] img) {
        this.name = name;
        this.fuelType = fuelType;
        this.power = power;
        this.category = category;
        this.numberDoors = numberDoors;
        this.cost = cost;
        this.consumption = consumption;
        this.numberSeats = numberSeats;
        this.hidden = hidden;
        this.featured = featured;
        this.img = img;
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the fuelType
     */
    public String getFuelType() {
        return fuelType;
    }

    /**
     * @param fuelType the fuelType to set
     */
    public void setFuelType(String fuelType) {
        this.fuelType = fuelType;
    }

    /**
     * @return the power
     */
    public int getPower() {
        return power;
    }

    /**
     * @param power the power to set
     */
    public void setPower(int power) {
        this.power = power;
    }

    /**
     * @return the category
     */
    public String getCategory() {
        return category;
    }

    /**
     * @param category the category to set
     */
    public void setCategory(String category) {
        this.category = category;
    }

    /**
     * @return the numberDoors
     */
    public int getNumberDoors() {
        return numberDoors;
    }

    /**
     * @param numberDoors the numberDoors to set
     */
    public void setNumberDoors(int numberDoors) {
        this.numberDoors = numberDoors;
    }
    
    /**
     * @return the cost
     */
    public int getCost() {
        return cost;
    }

    /**
     * @param cost the cost to set
     */
    public void setCost(int cost) {
        this.cost = cost;
    }

    /**
     * @return the consumption
     */
    public double getConsumption() {
        return consumption;
    }

    /**
     * @param consumption the consumption to set
     */
    public void setConsumption(double consumption) {
        this.consumption = consumption;
    }

    /**
     * @return the numberSeats
     */
    public int getNumberSeats() {
        return numberSeats;
    }

    /**
     * @param numberSeats the numberSeats to set
     */
    public void setNumberSeats(int numberSeats) {
        this.numberSeats = numberSeats;
    }
    
    /**
     * @return true si es oculto, false en caso contrario
     */
    public boolean getHidden() {
        return hidden;
    }
    
    /**
     * @param hidden true si oculto, false en caso contrario
     */
    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }
    
    /**
     * @return true si es destacado, false en caso contrario
     */
    public boolean getFeatured() {
        return featured;
    }
    
    /**
     * @param featured true si destacado, false en caso contrario
     */
    public void setFeatured(boolean featured) {
        this.featured = featured;
    }
    
    /**
     * @return imagen en bytes
     */
    public byte[] getImg() {
        return img;
    }
    
    /**
     * @param img imagen en bytes
     */
    public void setImg(byte[] img) {
        this.img = img;
    }
    
    /**
     * Almacena el coche en la base de datos
     * @return true si se ha almacenado correctamente, false en caso contrario
     */
    public boolean store(){
        JDBCTemplate template = JDBCTemplate.getJDBCTemplate();
        int res = -1;
        
        try{
            String query = "INSERT INTO Car VALUES (?,?,?,?,?,?,?,?,?,?)";
            PreparedStatement ps = template.getConnection().prepareStatement(query);
            ps.setString(1, getName());
            ps.setString(2, getFuelType());
            ps.setInt(3, getPower());
            ps.setString(4, getCategory());
            ps.setInt(5, getNumberDoors());
            ps.setInt(6, getCost());
            ps.setDouble(7, getConsumption());
            ps.setInt(8, getNumberSeats());
            ps.setBoolean(9, getHidden());
            ps.setBytes(10, getImg());
            
            //probamos a insertar
            res = ps.executeUpdate();
            if (getFeatured()){
                query = "INSERT INTO Featured_Cars VALUES (?,?,?,?,?,?,?,?,?,?)";
                try{
                    ps = template.getConnection().prepareStatement(query);
                    ps.setString(1, getName());
                    ps.setString(2, getFuelType());
                    ps.setInt(3, getPower());
                    ps.setString(4, getCategory());
                    ps.setInt(5, getNumberDoors());
                    ps.setInt(6, getCost());
                    ps.setDouble(7, getConsumption());
                    ps.setInt(8, getNumberSeats());
                    ps.setBoolean(9, getHidden());
                    ps.setBytes(10, getImg());
                } catch(Exception e){}
                res = ps.executeUpdate();
            }

            //si ya existe entonces actualizamos
            if (res == -1){
                query = "UPDATE Car SET fuel_type = ?, power = ?, category = ?, number_doors = ?, cost = ?, consumption = ?, number_seats = ?, hidden = ?, img = ? WHERE name = ?";
                try{
                    ps = template.getConnection().prepareStatement(query);
                    ps.setString(1, getFuelType());
                    ps.setInt(2, getPower());
                    ps.setString(3, getCategory());
                    ps.setInt(4, getNumberDoors());
                    ps.setInt(5, getCost());
                    ps.setDouble(6, getConsumption());
                    ps.setInt(7, getNumberSeats());
                    ps.setBoolean(8, getHidden());
                    ps.setBytes(9, getImg());
                    ps.setString(10, getName());
                } catch(Exception e){}
                res = ps.executeUpdate();
                //no es necesario hacer UPDATE en la tabla Featured_Cars ya que el trigger lo hará solo
            }

            if (!getFeatured()){
                query = "DELETE FROM Featured_Cars WHERE name='"+getName()+"'";
                template.executeSentence(query);
            }
        } catch(Exception e){}
        
        return res != -1;
    }
    
    @Override
    public String toString(){
        return name + " - " + category + " - " + cost + " euros - " + power + " CV - " +
                fuelType + " - " + consumption + " l/100 km - " + numberDoors + " puertas - " + 
                numberSeats + " asientos" + " - destacado: " + featured;
    }
}