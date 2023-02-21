/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.awt.event.ActionListener;
import java.lang.System.Logger.Level;
import org.jboss.logging.Logger;
import vistas.frmloading;
import vistas.frmsystem;

/**
 *
 * @author DETPC
 */
public class ctrlloading{
    frmloading frmloading = new frmloading();
    public ctrlloading() {
        //frmloading.btnIniciaSesion.addActionListener(this);
//        frmlogin.btnsalir.addActionListener(this);
    }
    public void iniciarCarga(){
        frmloading.setVisible(true);
        frmloading.setLocationRelativeTo(null);
        Thread hilo = new Thread(){
            @Override
            public void run(){
                for (int i = 1; i <= 100; i++) {
                    try {
                        frmloading.progressBar.setValue(i);
                        sleep(1);
                    } catch (InterruptedException e) {
                        //Logger.getLogger(ctrlloading.class.getName()).log(Level.ERROR, null, e);
                        System.out.println("ERROR "+e.getMessage());
                    }
                    
                }
            }
        };
        hilo.start();
    }
    public void cerrarCarga(){
        frmloading.dispose();
    }
}
