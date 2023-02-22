/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.util.List;
import javaproject.Asignaturas;
import javaproject.Estudiantes;
import javaproject.Registroasistencias;
import javaproject.Usuarios;
import javax.swing.JOptionPane;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.AnnotationConfiguration;

/**
 *
 * @author DETPC
 */
public class ctrlconsultas {
    private Session session;
    public void iniciarConexion(){
        SessionFactory sessionFactory = new AnnotationConfiguration().configure().buildSessionFactory();
        session = sessionFactory.openSession();
        session.getTransaction().begin();
    }
    public void finalizarConexion(){
        if (session.isOpen()){
            session.getTransaction().commit();
            session.close();
        }
    }
    
    public long guardarRegistroAsistencia(Registroasistencias registroasistencias){
            long id=0;
        try {
            iniciarConexion();
            id = (long)session.save(registroasistencias);
            finalizarConexion();
            return id;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Error al registrar asistencia. Error: "+e.getMessage(), "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
        return id;
    }
    public void editarRegistroAsistencia(Registroasistencias registroasistencias){
        iniciarConexion();
        session.update(registroasistencias);
        finalizarConexion();
    }
    public void eliminarRegistroAsistencia(Registroasistencias registroasistencias){
        iniciarConexion();
        session.delete(registroasistencias);
        finalizarConexion();
    }
    public Registroasistencias obtenerRegistroAsistencia(long registroAsistenciaId){
        Registroasistencias registroasistencias=null;
        iniciarConexion();
        registroasistencias = (Registroasistencias) session.get(Registroasistencias.class, registroAsistenciaId);
        finalizarConexion();
        return registroasistencias;
    }
    public List<Registroasistencias> obtenerListadoRegistroAsistenciasSegunEstudianteId(long estudianteId){
        List<Registroasistencias> listaRegistroAsistencias = null;
        iniciarConexion();
        String hql = "SELECT regas FROM Registroasistencias regas WHERE regas.estudiantes.id = "+estudianteId+" ORDER BY id ASC";
        Query query = session.createQuery(hql);
        listaRegistroAsistencias = query.list();
//        finalizarConexion();
        return listaRegistroAsistencias;
    }
    public List<Asignaturas> obtenerListadoAsignaturas(){
        List<Asignaturas> listaAsignaturas = null;
        iniciarConexion();
        String hql = "FROM Asignaturas ORDER BY id ASC";
        Query query = session.createQuery(hql);
        listaAsignaturas = query.list();
        return listaAsignaturas;
    }
    public List<Estudiantes> obtenerListadoEstudiantes(){
        List<Estudiantes> listaEstudiantes = null;
        iniciarConexion();
        String hql = "FROM Estudiantes ORDER BY id ASC";
        Query query = session.createQuery(hql);
        listaEstudiantes = query.list();
        return listaEstudiantes;
    }
    public List<Usuarios> obtenerListadoUsuarios(){
        List<Usuarios> listaUsuarios = null;
        iniciarConexion();
        String hql = "FROM Usuarios";
        Query query = session.createQuery(hql);
        listaUsuarios = query.list();
        finalizarConexion();
        return listaUsuarios;
    }
   public Estudiantes obtenerEstudiante(long estudianteId){
        Estudiantes estudiantes=null;
        iniciarConexion();
        estudiantes = (Estudiantes) session.get(Estudiantes.class, estudianteId);
        finalizarConexion();
        return estudiantes;
    }
   public Asignaturas obtenerAsignatura(long asignaturaId){
        Asignaturas asignatura=null;
        iniciarConexion();
        asignatura = (Asignaturas) session.get(Asignaturas.class, asignaturaId);
        finalizarConexion();
        return asignatura;
    }
   public Estudiantes obtenerEstudianteSegunNombre(String nombre){
        List<Estudiantes> listaEstudiantes = null;
        iniciarConexion();
        String hql = "SELECT est FROM Estudiantes est WHERE est.nombre = '"+nombre+"'";
        Query query = session.createQuery(hql);
        listaEstudiantes = query.list();
        return listaEstudiantes.get(0);
    }
   public Asignaturas obtenerAsingaturaSegunNombre(String nombre){
        List<Asignaturas> listaAsignaturas = null;
        iniciarConexion();
        String hql = "SELECT asig FROM Asignaturas asig WHERE asig.nombre = '"+nombre+"'";
        Query query = session.createQuery(hql);
        listaAsignaturas = query.list();
        return listaAsignaturas.get(0);
    }

}
