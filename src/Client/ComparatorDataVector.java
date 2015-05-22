package Client;

import java.util.Comparator;
import java.util.Vector;

/**
 * Comparator para modelos de coche
 */
public class ComparatorDataVector implements Comparator<Vector<Object>> {

    private final int index; //indice indicador del tipo de ordenación
    private final boolean ordenation;

    /**
     * Comparador de coches
     * @param index ordenar los modelos respecto a la columna index
     * @param ordenation true si ordenacion descendente y false si ordenacion ascendente
     */
    public ComparatorDataVector(int index, boolean ordenation) {
        this.index = index;
        this.ordenation = ordenation;
    }

    @Override
    public int compare(Vector<Object> o1, Vector<Object> o2) {
        int resul;
        Vector<Object> first, second;
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
                resul = ((String) first.get(index)).compareToIgnoreCase((String) second.get(index));
                break;
            case 2:
                /*
                 * Ordenación por precio
                 */
                resul = ((int) first.get(index)) - ((int) second.get(index));
                break;
            case 3:
                /*
                 * Ordenación por potencia
                 */
                resul = ((int) first.get(index)) - ((int) second.get(index));
                break;
            case 4:
                /*
                 * Ordenación por combustible
                 */
                resul = ((String) first.get(index)).compareToIgnoreCase((String) second.get(index));
                break;
            case 5:
                /*
                 * Ordenación por consumo
                 */
                resul = (int) (((double) first.get(index)) * 1000 - ((double) second.get(index) * 1000));
                break;
            default:
                /*
                 * Ordenación por modelo
                 */
                resul = ((String) first.get(index)).compareToIgnoreCase((String) second.get(index));
                break;
        }
        return resul;
    }
}
