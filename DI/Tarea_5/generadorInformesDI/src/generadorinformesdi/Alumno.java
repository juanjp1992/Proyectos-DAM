
package generadorinformesdi;

/**
 * @author Juan Jimenez Perez
 */
public class Alumno implements Comparable<Alumno> {
    
    //ATRIBUTOS
    private String DNI;
    private String siglasModulo;
    private double nota;
    private String provincia;
    
    //CONSTRUCTOR
    public Alumno(String DNI, String siglasModulo, double nota, String provincia) {
        this.DNI = DNI;
        this.siglasModulo = siglasModulo;
        this.nota = nota;
        this.provincia = provincia;
    }
    
    //GETTERS Y SETTERS
    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getSiglasModulo() {
        return siglasModulo;
    }

    public void setSiglasModulo(String siglasModulo) {
        this.siglasModulo = siglasModulo;
    }

    public double getNota() {
        return nota;
    }

    public void setNota(double nota) {
        this.nota = nota;
    }

    public String getProvincia() {
        return provincia;
    }

    public void setProvincia(String provincia) {
        this.provincia = provincia;
    }
    
    //Método sobreescrito para que nos permita la ordenación de los datos.
    @Override
    public int compareTo(Alumno a){
        return siglasModulo.compareTo(a.getSiglasModulo());
    }
            
}
