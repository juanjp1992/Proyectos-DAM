
package concesionario;
//Importo arraylist para almacenar los objetos y Scanner para poder teclear en consola
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Juan Jimenez Perez
 */
public class Concesionario {

    public static void main(String[] args) {
        //Instanciamos un objeto de la clase Scanner para poder escribir en consola.
        Scanner entrada = new Scanner(System.in);
        
        //Booleano para indicar cuando salir del programa
        boolean estadoPrograma = true;

        //Creo una variable para usarlo como opción del switch.
        int opcion = 0;
        
        //Creo una variable para Marcar cuantos vehículos vamos a crear.
        int inputNumCoches = 0;
        
        //Creo un arraylist para almacenar vehiculos.
        ArrayList<Coche> vehiculos = new ArrayList<Coche>();
        
        //Inicio la funcionalidad de selector de opciones usando un while para poder salir cuando se quiera.
        do{
            //Escribo las multiples opciones que tendremos.
            System.out.println("#######################\n"
                            + "## CONCESIONARIO FOC ##\n"
                            + "#######################\n\n"
                            + "# Bienvenido al Formulario de Pedidos de Vehículos.\n"
                            + "\n> Selecciona una opción:\n"
                            + "1. Pedir vehículos para el concesionario\n"
                            + "2. Mostrar Vehículos pedidos.\n"
                            + "3. Ver nº de vehículos pedidos.\n"
                            + "4. Salir");
            
            System.out.print("\n#######################"
                            + "\nOPCION: ");
            
            //Transformo el dato introducido en número para usarlo en el selector de opciones.
            opcion = Integer.parseInt(entrada.nextLine());
            
            System.out.println("#######################\n");
            
            //Empieza el SWITCH con las diferente sopciones (1. Pedir coches, 2. Ver listado de coches pedidos, 3. Ver núm de coches pedidos, 4. Salir). 
            switch(opcion){
                case 1:
                    //Pido que se introduzca el nº de coches para pedir
                    System.out.print("# Introduce el nº de coches a pedir: ");
                    inputNumCoches = Integer.parseInt(entrada.nextLine());
                    //Con un for recojo los datos de cada uno de los coche y los voy guardando en el array.
                    for(int i = 0; i < inputNumCoches; i++){
                        Coche c = new Coche();
                        System.out.println("\n######### COCHE " + (i+1) + " #########");
                        System.out.print("- Marca: ");
                        c.setMarca(entrada.nextLine());

                        System.out.print("- Modelo: ");
                        c.setModelo(entrada.nextLine());

                        System.out.print("- Año: ");
                        c.setAño(Integer.parseInt(entrada.nextLine()));

                        System.out.print("- Matricula: ");
                        c.setMatricula(entrada.nextLine());
                        // Con add añado al array el objeto.
                        vehiculos.add(c);
                        
                    }
                    System.out.println("\n");
                    break;
                // En el caso 2 muestro tanto el nº de coches pedidos, como el listado.
                case 2:
                    System.out.println("Nº Coches Pedidos: " + Coche.contador_de_coches);

                    for (int i = 0; i < vehiculos.size(); i++) {
                        System.out.println("\n######### COCHE " + (i+1) + " #########");
                        System.out.println("Marca: " + vehiculos.get(i).getMarca());
                        System.out.println("Modelo: " + vehiculos.get(i).getModelo());
                        System.out.println("Año: " + vehiculos.get(i).getAño());
                        System.out.println("Matricula: " + vehiculos.get(i).getMatricula());
                    }
                    System.out.println("\n");
                    break;
                //En el caso 3 muestro el nº de coches pedidos.    
                case 3:
                    System.out.println("Nº Coches Pedidos: " + Coche.contador_de_coches + "\n");
                    break;
                //En el caso 4 salgo del programa  
                case 4:
                    estadoPrograma = false;
                    break;
            }
         //Si la condición sigue siendo true, seguirá funcionando, si no, saldrá.   
        } while(estadoPrograma == true);
        
    } 
        
}
    

