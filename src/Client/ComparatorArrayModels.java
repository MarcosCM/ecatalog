package Client;

import java.util.Comparator;

/**
 * Comparator para modelos de coche
 */
public class ComparatorArrayModels implements Comparator<CarModel> {

    private final int index; //indice indicador del tipo de ordenación
    private final boolean ordenation;

    /**
     * Comparador de coches
     * @param index ordenar los modelos respecto a la columna index
     * @param ordenation true si ordenacion descendente y false si ordenacion ascendente
     */
    public ComparatorArrayModels(int index, boolean ordenation) {
        this.index = index;
        this.ordenation = ordenation;
    }

    @Override
    public int compare(CarModel o1, CarModel o2) {
        int resul;
        CarModel first, second;
        //si hay destacados entonces se prioriza por ser destacado
        if (o1.getFeatured() && o2.getFeatured()){
            return 0;
        } else if (o1.getFeatured()){
            return Integer.MIN_VALUE;
        } else if (o2.getFeatured()){
            return Integer.MAX_VALUE;
        }
        if (ordenation) {
            //ordenacion descendente
            first = o2;
            second = o1;
        } else{
            //ordenacion ascendente
            first = o1;
            second = o2;
        }
        switch (index) {
            case 1:
                /*
                 * Ordenación por categoria
                 */
                resul = first.getCategory().compareToIgnoreCase(second.getCategory());
                break;
            case 2:
                /*
                 * Ordenación por precio
                 */
                resul = first.getCost() - second.getCost();
                break;
            case 3:
                /*
                 * Ordenación por potencia
                 */
                resul = first.getPower() - second.getPower();
                break;
            case 4:
                /*
                 * Ordenación por combustible
                 */
                resul = first.getFuelType().compareToIgnoreCase(second.getFuelType());
                break;
            case 5:
                /*
                 * Ordenación por consumo
                 */
                resul = (int) (first.getConsumption()*1000 - second.getConsumption()*1000);
                break;
            default:
                /*
                 * Ordenación por modelo
                 */
                resul = first.getName().compareToIgnoreCase(second.getName());
                break;
        }
        return resul;
    }
}
