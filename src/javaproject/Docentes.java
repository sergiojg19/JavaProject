package javaproject;
// Generated Feb 20, 2023, 7:40:20 PM by Hibernate Tools 4.3.1


import java.util.HashSet;
import java.util.Set;

/**
 * Docentes generated by hbm2java
 */
public class Docentes  implements java.io.Serializable {


     private long id;
     private String nombre;
     private int edad;
     private Set asignaturases = new HashSet(0);

    public Docentes() {
    }

	
    public Docentes(long id, String nombre, int edad) {
        this.id = id;
        this.nombre = nombre;
        this.edad = edad;
    }
    public Docentes(long id, String nombre, int edad, Set asignaturases) {
       this.id = id;
       this.nombre = nombre;
       this.edad = edad;
       this.asignaturases = asignaturases;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    public int getEdad() {
        return this.edad;
    }
    
    public void setEdad(int edad) {
        this.edad = edad;
    }
    public Set getAsignaturases() {
        return this.asignaturases;
    }
    
    public void setAsignaturases(Set asignaturases) {
        this.asignaturases = asignaturases;
    }




}


