
package concesionario;

/**
 *
 * @author Juan Jimenez Perez
 */
public class Coche {
    //Atributos
    private String marca, modelo, matricula;
    private int año;
    
    //Variable contador de coches
    public static int contador_de_coches = 0;

    
    //Constructor 1 por defecto
    public Coche() {
        contador_de_coches++;
        
        marca = "";
        modelo = "";
        matricula = "";
        año = 0;
        
        
    }

    //Constructor 2
    public Coche(String marca, String modelo, String matricula, int año) {
        contador_de_coches++;
        
        this.marca = marca;
        this.modelo = modelo;
        this.matricula = matricula;
        this.año = año;
                
    }
    
    
    //Getters y Setters

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getMatricula() {
        return matricula;
    }

    public void setMatricula(String matricula) {
        this.matricula = matricula;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

}
