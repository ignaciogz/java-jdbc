
package clases;

/**
 *
 * @author Ignaciogz
 */
public class Main {
    public static void main(String[] args) {
    	//Se crea un objeto de la clase, que contiene el modelo de la aplicacion:
        ConectToDB db = new ConectToDB();
        
        //Se ejecuta una consulta:
        db.consulta("SELECT nombreDelCocinero FROM cocinero");
    }
}
