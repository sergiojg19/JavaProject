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
    String estudiante, asignatura;
    Registroasistencias registroasistenciasModelo = null;
    public ctrlregistroasistencia(ctrlsystem ctrlSystem) {
        this.ctrlsystem = ctrlSystem;
        this.frmregAsistencia.btncancelar.addActionListener(this);
        this.frmregAsistencia.btnguardar.addActionListener(this);
    }
    
    public void inicio() {
        cargarEstudiantes();
        cargarAsignaturas();
        frmregAsistencia.setVisible(true);
        frmregAsistencia.setLocationRelativeTo(null);
    }
    public void patchValue(Registroasistencias registroasistencias){
        registroasistenciasModelo = registroasistencias;
        frmregAsistencia.txtfecha.setText(registroasistencias.getFecha().toString());
        frmregAsistencia.jcbestudiante.setSelectedItem(registroasistencias.getEstudiantes().getNombre());
        frmregAsistencia.jcbasignatura.setSelectedItem(registroasistencias.getAsignaturas().getNombre());
        ctrlconsulta.finalizarConexion();
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
                this.estudiante = frmregAsistencia.jcbestudiante.getSelectedItem().toString();
                this.asignatura = frmregAsistencia.jcbasignatura.getSelectedItem().toString();
                if(estudiante.equals("Seleccionar estudiante...") || asignatura.equals("Seleccionar asignatura...")){
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
                    Estudiantes estudiante =(Estudiantes) ctrlconsulta.obtenerEstudianteSegunNombre(this.estudiante);
                    Asignaturas asignatura =(Asignaturas) ctrlconsulta.obtenerAsingaturaSegunNombre(this.asignatura);
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
                    //Registroasistencias regAsistencia = new Registroasistencias(this.contadorInventario,nombre, stock,0,0);
    //                Registroasistencias regAsistencia = new Registroasistencias(0,null, null, new Date());
    //                ctrlsystem.listRegistroAsist.add(regAsistencia);
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
       

        //ctrladministracion.inventario.add(inventario);
        //DefaultTableModel modelo =(DefaultTableModel)this.frmsystem.tbldatos.getModel();
//       this.ctrlsystem.inventario.add(inventario);
        //modelo.addRow(new Object[]{nombre});
        // Inventario inventario = new Inventario(nombre, compra,serie, venta,cantidad);
        //DefaultTableModel modelo =(DefaultTableModel)this.frmsystem.tbldatos.getModel();
        // modelo.addRow(new Object[]{nombre});
    }

}
