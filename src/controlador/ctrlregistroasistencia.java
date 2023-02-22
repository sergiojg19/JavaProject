/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.Date;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Iterator;
import java.util.List;
import javaproject.Asignaturas;
import javaproject.Estudiantes;
import javax.swing.JOptionPane;

import javaproject.Registroasistencias;

import vistas.frmregistrarasistencia;

/**
 *
 * @author Lenovo
 */
public class ctrlregistroasistencia implements ActionListener {
    frmregistrarasistencia frmregAsistencia = new frmregistrarasistencia();
    ctrlsystem ctrlsystem;
    ctrlconsultas ctrlconsulta = new ctrlconsultas();
    Date date;
    String strEstudiante, strAsignatura;
    Registroasistencias registroasistenciasModelo = null;
    public ctrlregistroasistencia(ctrlsystem ctrlSystem) {
        this.ctrlsystem = ctrlSystem;
        this.frmregAsistencia.btncancelar.addActionListener(this);
        this.frmregAsistencia.btnguardar.addActionListener(this);
    }
    
    public void inicio(Estudiantes estudiante) {
        cargarEstudiantes();
        cargarAsignaturas();
        frmregAsistencia.setVisible(true);
        frmregAsistencia.setLocationRelativeTo(null);
        frmregAsistencia.jcbestudiante.setSelectedItem(estudiante.getNombre());
        frmregAsistencia.lblTitulo.setText("Registrar nueva asistencia");
    }
    public void patchValue(Registroasistencias registroasistencias){
        try {
            frmregAsistencia.lblTitulo.setText("Actualizar asistencia");
            registroasistenciasModelo = registroasistencias;
            frmregAsistencia.txtfecha.setText(registroasistencias.getFecha().toString());
            frmregAsistencia.jcbestudiante.setSelectedItem(registroasistencias.getEstudiantes().getNombre());
            frmregAsistencia.jcbasignatura.setSelectedItem(registroasistencias.getAsignaturas().getNombre());
            ctrlconsulta.finalizarConexion();
        } catch (Exception e) {
        }
    }
    public void cargarEstudiantes(){
        frmregAsistencia.jcbestudiante.removeAll();
        frmregAsistencia.jcbestudiante.addItem("Seleccionar estudiante...");
        
        ctrlconsultas consultas = new ctrlconsultas();
        List listado = consultas.obtenerListadoEstudiantes();
        Iterator it = listado.iterator();
        while(it.hasNext()){
            Estudiantes asignatura = (Estudiantes) it.next();
            frmregAsistencia.jcbestudiante.addItem(asignatura.getNombre());
        }
    }
     public void cargarAsignaturas(){
        frmregAsistencia.jcbasignatura.removeAll();
        frmregAsistencia.jcbasignatura.addItem("Seleccionar asignatura...");
        
        ctrlconsultas consultas = new ctrlconsultas();
        List listado = consultas.obtenerListadoAsignaturas();
        Iterator it = listado.iterator();
            while(it.hasNext()){
                Asignaturas asignatura = (Asignaturas) it.next();
                frmregAsistencia.jcbasignatura.addItem(asignatura.getNombre());
            }
    }
     private boolean validarCampos(){
         try {
                String fecha = frmregAsistencia.txtfecha.getText();
                this.date =  new SimpleDateFormat("yyyy-MM-dd").parse(fecha);
                this.strEstudiante = frmregAsistencia.jcbestudiante.getSelectedItem().toString();
                this.strAsignatura = frmregAsistencia.jcbasignatura.getSelectedItem().toString();
                if(strEstudiante.equals("Seleccionar estudiante...") || strAsignatura.equals("Seleccionar asignatura...")){
                    return false;
                }
                return true;
         } catch (Exception e) {
             JOptionPane.showMessageDialog(null, "Formato de fecha incorrecto. Ajustar yyyy-MM-dd", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
         }
       return false;
     }
    @Override
    public void actionPerformed(ActionEvent e) {
        try {
             if (frmregAsistencia.btnguardar == e.getSource()) {
                if(validarCampos()){
                    Estudiantes estudiante =(Estudiantes) ctrlconsulta.obtenerEstudianteSegunNombre(this.strEstudiante);
                    Asignaturas asignatura =(Asignaturas) ctrlconsulta.obtenerAsingaturaSegunNombre(this.strAsignatura);
                    Registroasistencias registroasistencias = new Registroasistencias();
                    if(this.registroasistenciasModelo !=null){
                        long id = this.registroasistenciasModelo.getId();
                        registroasistencias.setId(id); // asignar el id del modelo porque va a editr
                    }else{
                        long id = ctrlconsulta.obtenerSiguienteIndexRegistroAsistencia();
                        registroasistencias.setId(id); // id para siguiente registro
                    }
                    registroasistencias.setFecha(this.date);
                    registroasistencias.setEstudiantes(estudiante);
                    registroasistencias.setAsignaturas(asignatura);
                    
                     if(this.registroasistenciasModelo !=null){
                         ctrlconsulta.editarRegistroAsistencia(registroasistencias); 
                         this.ctrlsystem.llenartabla();
                        JOptionPane.showMessageDialog(null, "Asistencia actualizada correctamente");
                     }else{
                         ctrlconsulta.guardarRegistroAsistencia(registroasistencias); 
                         this.ctrlsystem.llenartabla();
                        JOptionPane.showMessageDialog(null, "Asistencia registrada correctamente");
                     }
                    frmregAsistencia.dispose();
                }else{
                 JOptionPane.showMessageDialog(null, "Existen campos por completar o corregir", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
                }
            }
            if (frmregAsistencia.btncancelar == e.getSource()) {
                frmregAsistencia.dispose();
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, ex.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
       

    }

}
