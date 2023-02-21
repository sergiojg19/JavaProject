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
    
    public int guardarRegistroAsistencia(Registroasistencias registroasistencias){
        int id=0;
        iniciarConexion();
        id = (int)session.save(registroasistencias);
        finalizarConexion();
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
    public Registroasistencias obtenerRegistroAsistencia(int registroAsistenciaId){
        Registroasistencias registroasistencias=null;
        iniciarConexion();
        registroasistencias = (Registroasistencias) session.get(Registroasistencias.class, registroAsistenciaId);
        finalizarConexion();
        return registroasistencias;
    }
    public List<Registroasistencias> obtenerListadoRegistroAsistencias(){
        List<Registroasistencias> listaRegistroAsistencias = null;
        iniciarConexion();
        String hql = "FROM Registroasistencias ORDER BY id ASC";
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
}
