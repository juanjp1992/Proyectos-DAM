/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package empleadosbdoo;

/**
 *
 * @author juanj
 */
public class Empleados {
    
    //Atributos
    private int codEmpleado;
    private String nombre, apellidos, departamento;
    private double salario;

    //Constructor
    public Empleados(int codEmpleado, String nombre, String apellidos,
            String departamento, double salario) {
        this.codEmpleado = codEmpleado;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.departamento = departamento;
        this.salario = salario;
    }
    
    //Getters y Setters

    public int getCodEmpleado() {
        return codEmpleado;
    }

    public void setCodEmpleado(int codEmpleado) {
        this.codEmpleado = codEmpleado;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public double getSalario() {
        return salario;
    }

    public void setSalario(double salario) {
        this.salario = salario;
    }
    
    
    
}
