/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import static java.lang.Thread.sleep;
import java.util.Iterator;
import java.util.List;
import javaproject.Estudiantes;
import javaproject.Usuarios;
import javax.swing.JOptionPane;
import org.hibernate.Session;
import vistas.frmlogin;
import utils.Hasher;
/**
 *
 * @author DETPC
 */
public class ctrllogin implements ActionListener {
    frmlogin frmlogin = new frmlogin();
    ctrlconsultas ctrlconsultas = new ctrlconsultas();
    Hasher hasher = new Hasher();
    boolean estaAutenticado = false;
      // modelo.addRow(new Object[]{nombre});
    public ctrllogin() {
        frmlogin.btnIniciaSesion.addActionListener(this);
//        cerrarCarga();
//        frmlogin.btnsalir.addActionListener(this);
        frmlogin.setVisible(true);
        frmlogin.setLocationRelativeTo(null);
        frmlogin.loadingBar.setVisible(false);
    }
    private boolean validarCampos(){
        String username = frmlogin.txtusername.getText();
        String password = new String(frmlogin.txtpassword.getPassword());
        if(username.isEmpty() || password.isEmpty()){
            return false;
        }
        return true;
    }
    private Usuarios autenticarUsuario(){
        estaAutenticado = false;
        String username = frmlogin.txtusername.getText();
        String password = new String(frmlogin.txtpassword.getPassword());
        List listado = ctrlconsultas.obtenerListadoUsuarios();
        Iterator it = listado.iterator();
        while(it.hasNext()){
            Usuarios usuario = (Usuarios) it.next();
            if(username.equals(usuario.getUsername()) && hasher.checkPassword(password, usuario.getPassword())){
                estaAutenticado = true;
                return usuario;
            };
        }
//        cerrarCarga();
        return null;
    }
    private Estudiantes getEstudianteSegunUsuario(Usuarios usuario){
        List listado = ctrlconsultas.obtenerListadoEstudiantes();
        Iterator it = listado.iterator();
        while(it.hasNext()){
            Estudiantes estudiante = (Estudiantes) it.next();
            long usuarioId = estudiante.getUsuarios().getId();
            if(usuarioId == usuario.getId()){
                return estudiante;
            }
        }
        return null;
    }
    private void testHasher(){
        Hasher hasher = new Hasher();
        hasher.runTests();
    }
     public void iniciarCarga(){
        frmlogin.loadingBar.setVisible(true);
        Thread hilo = new Thread(){
            @Override
            public void run(){
                for (int i = 1; i <= 100; i++) {
                    try {
                        frmlogin.loadingBar.setValue(i);
                        sleep(9);
                    } catch (InterruptedException e) {
                        //Logger.getLogger(ctrlloading.class.getName()).log(Level.ERROR, null, e);
                        System.out.println("ERROR "+e.getMessage());
                    }
                    if(frmlogin.loadingBar.getValue()==99){
                        cargarSistema();
                    }
                }
            }
        };
        hilo.start();
    }
    public void cerrarCarga(){
        frmlogin.loadingBar.setVisible(false);
    }
    public void cargarSistema(){
        Usuarios usuario = autenticarUsuario();
        if(usuario !=null){
            ctrlsystem ctrlsystem = new ctrlsystem(getEstudianteSegunUsuario(usuario));
            frmlogin.dispose();
        }else{
            JOptionPane.showMessageDialog(null, "Usuario o contraseña incorrectos.", "ERROR_MESSAGE", JOptionPane.ERROR_MESSAGE);
        }
    }
    
     @Override
    public void actionPerformed(ActionEvent e) {

        if (frmlogin.btnIniciaSesion == e.getSource()) {
            if (validarCampos()){
                iniciarCarga();
                //cargarSistema();
            }else{
             JOptionPane.showMessageDialog(null, "Existen campos por completar o corregir", "WARNING_MESSAGE", JOptionPane.WARNING_MESSAGE);
            }
        }
        
//        if (frmsystem.btnsalir == e.getSource()) {
//            System.exit(0);
//        }
    }
}
