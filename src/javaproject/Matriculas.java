package javaproject;
// Generated Feb 20, 2023, 7:40:20 PM by Hibernate Tools 4.3.1


import java.math.BigDecimal;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

/**
 * Matriculas generated by hbm2java
 */
public class Matriculas  implements java.io.Serializable {


     private long id;
     private Date fechaInicio;
     private Date fechaFin;
     private BigDecimal precio;
     private Set cicloses = new HashSet(0);

    public Matriculas() {
    }

	
    public Matriculas(long id, Date fechaInicio, Date fechaFin, BigDecimal precio) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.precio = precio;
    }
    public Matriculas(long id, Date fechaInicio, Date fechaFin, BigDecimal precio, Set cicloses) {
       this.id = id;
       this.fechaInicio = fechaInicio;
       this.fechaFin = fechaFin;
       this.precio = precio;
       this.cicloses = cicloses;
    }
   
    public long getId() {
        return this.id;
    }
    
    public void setId(long id) {
        this.id = id;
    }
    public Date getFechaInicio() {
        return this.fechaInicio;
    }
    
    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }
    public Date getFechaFin() {
        return this.fechaFin;
    }
    
    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }
    public BigDecimal getPrecio() {
        return this.precio;
    }
    
    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }
    public Set getCicloses() {
        return this.cicloses;
    }
    
    public void setCicloses(Set cicloses) {
        this.cicloses = cicloses;
    }




}


