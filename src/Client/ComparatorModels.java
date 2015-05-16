package Client;

import java.util.Comparator;
import java.util.Vector;

/**
 * Comparator para modelos de coche
 */
public class ComparatorModels implements Comparator<Vector<Object>> {

    private final int index; //indice indicador del tipo de ordenación
    private final boolean ordenation;//


    /*
     * Comparador de coches
     * @param index ordenar los modelos respecto a la columna index
     * @ordenation true si ordenacion descendente y false si ordenacion ascendente
     */
    public ComparatorModels(int index, boolean ordenation) {
        this.index = index;
        this.ordenation = ordenation;
    }

    @Override
    public int compare(Vector<Object> o1, Vector<Object> o2) {
        int resul;
        if (ordenation) {
            //ordenacion descendente
            switch (index) {
                case 1:
                    /*
                     * Ordenación por categoria
                     */
                    resul = ((String) o2.get(index)).compareToIgnoreCase((String) o1.get(index));
                    break;
                case 2:
                    /*
                     * Ordenación por precio
                     */
                    resul = ((int) o2.get(index)) - ((int) o1.get(index));
                    break;
                case 3:
                    /*
                     * Ordenación por potencia
                     */
                    resul = ((int) o2.get(index)) - ((int) o1.get(index));
                    break;
                case 4:
                    /*
                     * Ordenación por combustible
                     */
                    resul = ((String) o2.get(index)).compareToIgnoreCase((String) o1.get(index));
                    break;
                case 5:
                    /*
                     * Ordenación por consumo
                     */
                    resul = (int) (((double) o2.get(index)) * 1000 - ((double) o1.get(index) * 1000));
                    break;
                default:
                    /*
                     * Ordenación por modelo
                     */
                    resul = ((String) o2.get(index)).compareToIgnoreCase((String) o1.get(index));
                    break;
            }
        } 
        else {
            //ordenacion ascendente
            switch (index) {
                case 1:
                    /*
                     * Ordenación por categoria
                     */
                    resul = ((String) o1.get(index)).compareToIgnoreCase((String) o2.get(index));
                    break;
                case 2:
                    /*
                     * Ordenación por precio
                     */
                    resul = ((int) o1.get(index)) - ((int) o2.get(index));
                    break;
                case 3:
                    /*
                     * Ordenación por potencia
                     */
                    resul = ((int) o1.get(index)) - ((int) o2.get(index));
                    break;
                case 4:
                    /*
                     * Ordenación por combustible
                     */
                    resul = ((String) o1.get(index)).compareToIgnoreCase((String) o2.get(index));
                    break;
                case 5:
                    /*
                     * Ordenación por consumo
                     */
                    resul = (int) (((double) o1.get(index)) * 1000 - ((double) o2.get(index) * 1000));
                    break;
                default:
                    /*
                     * Ordenación por modelo
                     */
                    resul = ((String) o1.get(index)).compareToIgnoreCase((String) o2.get(index));
                    break;
            }
        }
        return resul;
    }
}
