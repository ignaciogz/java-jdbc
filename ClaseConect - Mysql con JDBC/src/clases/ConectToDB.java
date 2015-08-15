
package clases;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 *
 * @author Ignaciogz
 * La clase ConectToDB representa el "MODELO" de la aplicacion!
 */
public class ConectToDB {
    
    //declarar objetos de coneccion
    Connection miConexion;
    PreparedStatement consulta;
    ResultSet datos;
    
    private Connection getConnection(String BD, String usuario, String contraseña){
        try {
            //llamar a la clase o driver de jdbc
            Class.forName("com.mysql.jdbc.Driver");
            String servidor = "jdbc:mysql://localhost/" + BD ;
            
            miConexion = DriverManager.getConnection(servidor,usuario,contraseña);
        } 
        catch (ClassNotFoundException e)
        {
            System.err.println("No se encontro Driver");
            miConexion = null;
        }
        catch(SQLException ex)
        {
            System.err.println("No se pudo conectar a la base de datos");
            miConexion = null;
        }
        
        return miConexion;
    }
    
    //Ejecuta una consulta. 
    //Le podrian pasar la consulta por parametro!
    public void consulta(String query)
    {
        try {
            miConexion = (Connection) this.getConnection("prueba", "root", "");
            consulta = miConexion.prepareStatement(query) /*"SELECT nombreDelCocinero FROM cocinero"*/;
            datos = consulta.executeQuery();
            
            while(datos.next())
            {
                System.out.println("Nombre = " + datos.getString("cocinero_id") + "\n");
                    /*+ "Edad = " + datos.getInt("edad")*/    //  <--- Si fuera numerico
            }

        }
        catch (SQLException e)
        {
            System.err.println("No se pudo conectar o hacer consulta");
        }
        finally{
            this.desconectar();
        }
    }
    

    public void desconectar()
    {
        try {
            miConexion.close();
            consulta.close();
            datos = null;
        } catch (Exception e) {
        }
    }
    
    // OTROS METODOS AQUI
}
