package Aplicacion;

/**
 * Modelo lógico de un coche
 */
public class Car_Model {

    private String name;	// nombre del coche
    private String fuelType;	// tipo de combustible
    private int power;		// potencia (cv)
    private String category;	// categoria
    private int numberDoors;	// número de puertas
    private int cost;		// coste
    private double consumption;	// consumo (l/100km)
    private int numberSeats;	// número de asientos

    /**
     * Crea una instancia del modelo lógico
     * @param name Nombre del coche
     * @param fuelType Tipo de combustible
     * @param power Potencia
     * @param category Categoría del coche
     * @param numberDoors Número de puertas
     * @param numberSeats Número de asientos
     * @param consumption Consumo
     */
    public Car_Model(String name, String fuelType, int power, String category,
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
    public void setConsumption(int consumption) {
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
}