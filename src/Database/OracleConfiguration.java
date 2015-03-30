package Database;

/**
 * Configuracion de una BD Oracle.
 */
public class OracleConfiguration {

    private static final int defaultPort = 1521;
    
    private final String host;
    private final String port;
    private final String sid;

    public OracleConfiguration(String host, String port, String sid) {
        this.host = host;
        this.port = port;
        this.sid = sid;
    }

    public String getDriver() {
        return "oracle.jdbc.driver.OracleDriver";
    }

    public String getURL() {
        return "jdbc:oracle:thin:@" + getHost() + ":" + getPort() +":" + getSid();
    }

    private String getSid() {
        return sid;
    }

    private String getPort() {
        return (port == null) ? ""+defaultPort : port;
    }

    private String getHost() {
        return host;
    }

}