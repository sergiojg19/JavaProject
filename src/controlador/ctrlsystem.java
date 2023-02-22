/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import javaproject.Estudiantes;
import javaproject.Registroasistencias;
import javaproject.Usuarios;
import javax.swing.table.DefaultTableModel;
import org.hibernate.Session;
import vistas.frmsystem;

public class ctrlsystem implements ActionListener {
    private Session session;
    frmsystem frmsystem = new frmsystem();
    ctrlloading ctrlloading = new ctrlloading();
    public List<Registroasistencias> listRegistroAsist = new ArrayList<>();
    Estudiantes estudiante = null;
    ctrlconsultas ctrlconsulta = new ctrlconsultas();
      // modelo.addRow(new Object[]{nombre});
    public ctrlsystem(Estudiantes estudiante) {
        setEstudiante(estudiante);
        frmsystem.btnregistrar.addActionListener(this);
        frmsystem.btnsalir.addActionListener(this);
        cargarInformacionPersonal();
        cargarDatosTabla();
    }
    public void cargarDatosTabla(){
//        ctrlloading.iniciarCarga();
        List lista = llenartabla();
        if(lista != null && lista.size()>=0){
//            try {
//                sleep(1);            
//                ctrlloading.cerrarCarga();
                frmsystem.setVisible(true);
                frmsystem.setLocationRelativeTo(null);
//            } catch (InterruptedException e) {
//                System.out.println("ERROR "+e.getMessage());
//            }

        }
    }
    public List llenartabla() {
        try {
            DefaultTableModel modelo = (DefaultTableModel) this.frmsystem.tbldatos.getModel();
            modelo.setRowCount(0);
            ctrlconsultas consultas = new ctrlconsultas();
            List listado = consultas.obtenerListadoRegistroAsistencias();
            Iterator it = listado.iterator();
            while(it.hasNext()){
                Registroasistencias regAsistencia = (Registroasistencias) it.next();
                Object[] fila = new Object[4];
    //            var test = regAsistencia.getAsignaturas();
               //System.out.println(regAsistencia.getAsignaturas().getNombre());
    //            System.out.println(regAsistencia.getAsignaturas().getNombre());
                //System.out.println(regAsistencia.getFecha().toString());
    //            fila[0] = regAsistencia.getId();
    //            fila[1] = regAsistencia.getAsignaturas().getDocentes().getNombre();
    //            fila[2] = regAsistencia.getAsignaturas().getNombre();
    //            fila[3] = regAsistencia.getFecha().toString();
                fila[0] = regAsistencia.getId();
                fila[1] = regAsistencia.getAsignaturas().getDocentes().getNombre();
                fila[2] = regAsistencia.getAsignaturas().getNombre();
                fila[3] = regAsistencia.getFecha().toString();
                //modelo.addRow(new Object[]{regAsistencia.getId(), regAsistencia.getAsignaturas().getDocentes().getNombre(), regAsistencia.getAsignaturas().getNombre(), regAsistencia.getFecha().toString()});
                modelo.addRow(fila);
            }
            consultas.finalizarConexion();
            return listado;
        } catch (Exception e) {
            System.out.println("ERROR: "+e.getMessage());
            return null;
        }
    }
    public void cargarInformacionPersonal(){
        Estudiantes estudiante = ctrlconsulta.obtenerEstudiante(this.estudiante.getId());
        frmsystem.lblEstudiant.setText("Reporte de asistencias del estudiante "+estudiante.getNombre());
        //frmsystem.lblTitulo.setText(estudiante.getRegistroasistenciases());
//        frmsystem.lblEstudiant.setText("Información del estudiante "+estudiante.getNombre());
    }
    public void actualizarinventario(List<Registroasistencias> regAsitencia) {
        this.listRegistroAsist = regAsitencia;
    }
    public List<Registroasistencias> getListRegistroAsist(){
        return this.listRegistroAsist;
    }
    public void setEstudiante(Estudiantes estudiante){
        this.estudiante = estudiante;
    }
    @Override
    public void actionPerformed(ActionEvent e) {

        if (frmsystem.btnregistrar == e.getSource()) {
            ctrlregistroasistencia ctrlregAsistencia = new ctrlregistroasistencia(this);
            ctrlregAsistencia.inicio();
        }
//        if (frmsystem.btncomprar == e.getSource()) {
//            ctrlregistrarcompra ctrlcompra = new ctrlregistrarcompra(this, this.listRegistroAsist);
//            ctrlcompra.inicio();
//        }
//        if (frmsystem.btnventa == e.getSource()) {
//            ctrlregistrarventa ctrlventa = new ctrlregistrarventa(this, this.listRegistroAsist);
//            ctrlventa.inicio();
//        }
        if (frmsystem.btnsalir == e.getSource()) {
            System.exit(0);
        }
    }
}
