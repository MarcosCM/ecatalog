package Database;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Iterator;

/**
 * Emulacion de un Cursor de PL/SQL
 */
public class Cursor implements Iterable<Cursor> {

	private final ResultSet rs;
	
	public Cursor(ResultSet rs) {
		this.rs = rs;
	}

	public Iterator<Cursor> iterator() {
            return new Iterator<Cursor>() {

                public boolean hasNext() {
                    try {
                        if (rs.next())
                            return true;
                        rs.close();
                    } catch (SQLException e) {
                        try {
                            rs.close();
                        } catch (SQLException e2) {}
                    }
                    return false;
                }

                public Cursor next() {
                    return new Cursor(rs);
                }

                public void remove() {
                }

            };
	}

	public ResultSet getResultSet(){
            return rs;
        }
}
