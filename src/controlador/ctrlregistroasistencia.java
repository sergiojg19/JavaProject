/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;
import java.util.Date;  
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
    
    public ctrlregistroasistencia(ctrlsystem ctrlSystem) {
        this.ctrlsystem = ctrlSystem;
        this.frmregAsistencia.btncancelar.addActionListener(this);
        this.frmregAsistencia.btnguardar.addActionListener(this);
    }
    
    public void inicio() {
        cargarEstudiantes();
        cargarAsignaturas();
        frmregAsistencia.show();
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
    @Override
    public void actionPerformed(ActionEvent e) {
        if (frmregAsistencia.btnguardar == e.getSource()) {
            String fecha = frmregAsistencia.txtfecha.getText();
            Estudiantes estudiante =(Estudiantes) frmregAsistencia.jcbestudiante.getSelectedItem();
            
            //Registroasistencias regAsistencia = new Registroasistencias(this.contadorInventario,nombre, stock,0,0);
            Registroasistencias regAsistencia = new Registroasistencias(1,null, null, new Date());
            ctrlsystem.listRegistroAsist.add(regAsistencia);
            this.ctrlsystem.llenartabla();
            JOptionPane.showMessageDialog(null, "Asistencia registrada correctamente");
            frmregAsistencia.dispose();
        }
        if (frmregAsistencia.btncancelar == e.getSource()) {
            frmregAsistencia.dispose();

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
