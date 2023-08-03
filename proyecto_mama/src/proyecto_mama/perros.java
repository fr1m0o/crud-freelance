
package proyecto_mama;
import javax.swing.JTextField;
import java.sql.CallableStatement;
import javax.swing.JOptionPane;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableModel;
import javax.swing.table.TableRowSorter;

public class perros {
 int codigo_id;
 String nombre_perro;
 String nombre_dueño;
 String telefono;
 String pago;
 String mes;

    public int getCodigo_id() {
        return codigo_id;
    }

    public void setCodigo_id(int codigo_id) {
        this.codigo_id = codigo_id;
    }

    public String getNombre_perro() {
        return nombre_perro;
    }

    public void setNombre_perro(String nombre_perro) {
        this.nombre_perro = nombre_perro;
    }

    public String getNombre_dueño() {
        return nombre_dueño;
    }

    public void setNombre_dueño(String nombre_dueño) {
        this.nombre_dueño = nombre_dueño;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getPago() {
        return pago;
    }

    public void setPago(String pago) {
        this.pago = pago;
    }

   
    

    public String getMes() {
        return mes;
    }

    public void setMes(String mes) {
        this.mes = mes;
    }
 
 public void insertar_peshos(JTextField paramnombres_perro,JTextField paramnombres_dueño,JTextField paramtelefono,JTextField parampago,JTextField parammes)
 {
 setNombre_perro(paramnombres_perro.getText());
 setNombre_dueño(paramnombres_dueño.getText());
 setTelefono(paramtelefono.getText());
 setPago(parampago.getText());
 setMes(parammes.getText());
 
 conexion_docker docker = new conexion_docker();
 
 String consulta="insert into perros_mama1(nombre_perro,nombre_dueño,telefono,Pago,Mes)values(?,?,?,?,?);";
 
 try{
        CallableStatement cs = docker.estableconeccion().prepareCall(consulta);
        cs.setString(1,getNombre_perro());
       cs.setString( 2,getNombre_dueño());
       cs.setString( 3,getTelefono());
       cs.setString( 4,getPago());
       cs.setString( 5,getMes());
      
       cs.execute();
       JOptionPane.showMessageDialog(null,"registro exitoso");
    
    }catch(Exception e){
        JOptionPane.showMessageDialog(null,"Error en el registro, erro: "+ e.toString());
    
    
    }
 
 
 
 
 }
  
public void tablasperros(JTable paramtablaperros){
conexion_docker docker = new conexion_docker();
DefaultTableModel modelo= new  DefaultTableModel();
TableRowSorter<TableModel> ordenartabla= new  TableRowSorter <TableModel>(modelo);
paramtablaperros.setRowSorter(ordenartabla);
 String sql="";
  
 modelo.addColumn("id");
     
 modelo.addColumn("nombre_perro");
      
 modelo.addColumn("nombre_dueño");
 
 modelo.addColumn("telefono");

 modelo.addColumn("pago");
 
 modelo.addColumn("mes");
 
 paramtablaperros.setModel(modelo);
      
 sql="select*from perros_mama1;";
      
 String []datos= new String[6];
 
 Statement st;
 
 try{
      st= docker.estableconeccion().createStatement();
      //se utiliza para representar un conjunto de resultados obtenidos a partir de una consulta SQL ejecutada en una base de datos.
      ResultSet rs = st.executeQuery(sql);
      ///se utiliza para avanzar el cursor al siguiente registro en el conjunto de resultados.
      /////cuando es con un while el ciclo del rs.next() deberia terminar hasta que ya no hayan registros
      while(rs.next()){
      datos[0]=rs.getString(1);////recordar que si es un valor numero y esta en parentesis obedece el enumerado comun
      datos[1]=rs.getString(2);////si esta en llaves obdece el enumerado de arrays que comienza por el cero///
      datos[2]=rs.getString(3);
      datos[3]=rs.getString(4);
      datos[4]=rs.getString(5);
      datos[5]=rs.getString(6);
      
      
      modelo.addRow(datos);
      }///fin del while
       paramtablaperros.setModel(modelo);
      
      ////////////////////////////////////////
              
              
      }catch(Exception e){
      JOptionPane.showMessageDialog(null,"Error al mostrar el registro en la tabla,: "+ e.toString());
      
      }
 

} 

public void modificartablaperros(JTable paramtablaperros,JTextField paramtxt_id,JTextField paramnombres_perro,JTextField paramnombres_dueño,JTextField paramtelefono,JTextField parampago,JTextField parammes)
{
try{
 int fila= paramtablaperros.getSelectedRow();
 //getSelectedRow() sirve  para obtener el número de fila seleccionada y luego acceder a los datos correspondientes en esa fila.//
if(fila>=0){
 paramtxt_id.setText( paramtablaperros.getValueAt(fila, 0).toString());
 paramnombres_perro.setText( paramtablaperros.getValueAt(fila, 1).toString());
 paramnombres_dueño.setText( paramtablaperros.getValueAt(fila, 2).toString());
 paramtelefono.setText(paramtablaperros.getValueAt(fila,3).toString());
 
 }else{
JOptionPane.showMessageDialog(null,"fila no seleccionada");

}


}catch(Exception e){
 JOptionPane.showMessageDialog(null,"fallo en la seleccion de filas, erro: "+ e.toString());

}

}

public void modificarperros(JTextField paramcodigo,JTextField paramnombres_perro,JTextField paramnombres_dueño,JTextField paramtelefono,JTextField parampago,JTextField parammes)
{
setCodigo_id(Integer.parseInt(paramcodigo.getText()));
setNombre_perro(paramnombres_perro.getText());
setNombre_dueño(paramnombres_dueño.getText());
setTelefono(paramtelefono.getText());
 setPago(parampago.getText());
 setMes(parammes.getText());
 
 conexion_docker docker= new conexion_docker();
 
 String consulta="update perros_mama1 set perros_mama1.nombre_perro=?,perros_mama1.nombre_dueño=?,perros_mama1.telefono=?,perros_mama1.pago=?,perros_mama1.mes=? where perros_mama1.id=?;";

  try{
 CallableStatement cs= docker.estableconeccion().prepareCall(consulta);
 ///con esto podemos ejecutar el comando de el String consulta
 cs.setString(1,getNombre_perro());
 cs.setString(2,getNombre_dueño());
 cs.setString(3,getTelefono());
 cs.setString(4,getPago());
 cs.setString(5,getMes());
 cs.setInt(6,getCodigo_id());
 cs.execute();
   JOptionPane.showMessageDialog(null,"modificacion exitosa");
 
 }catch(SQLException e){
  JOptionPane.showMessageDialog(null,"error al modificar: "+ e.toString());
 
 }
 
}
 
public void eliminarperros(JTextField paramcodigo){
setCodigo_id(Integer.parseInt(paramcodigo.getText()));

conexion_docker docker = new conexion_docker();

String consulta="delete from perros_mama1 where perros_mama1.id=?;";

try{
 CallableStatement cs= docker.estableconeccion().prepareCall(consulta);
cs.setInt(1,getCodigo_id());
 cs.execute();
  JOptionPane.showMessageDialog(null,"se ah eliminado con éxito");
 
}catch(SQLException e){
 JOptionPane.showMessageDialog(null,"Error al eliminar erro: "+ e.toString());

}


}
 
}


