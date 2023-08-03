
package proyecto_mama;
import java.sql.Connection;
import javax.swing.JOptionPane;
import java.sql.DriverManager;


public class conexion_docker {
     Connection conectar = null;
     String usuario = "root";
    String contrasenia = "chuletonga";
    String bd = "perros_chule";
    String ip = "127.0.0.1";
    String puerto = "3307";
    
    String cadena = "jdbc:mysql://"+ip+":"+puerto+"/"+bd;
    
    public Connection estableconeccion(){
    
        try {
            
            Class.forName("com.mysql.cj.jdbc.Driver");
            conectar = DriverManager.getConnection(cadena,usuario,contrasenia);
            JOptionPane.showMessageDialog(null,"La conexión se ha realizado con éxito");
            
            
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null,"Error al conectarse a la base de datos, erro: "+ e.toString());
        }
        return conectar;
}
}
