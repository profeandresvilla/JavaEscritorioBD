package com.mycompany.clases;

import com.mycompany.formularios.frmLogin;

public class JavaEscritorio {

    public static void main(String[] args) {
        // Creo el objeto datos
        Datos misDatos = new Datos();
        
        // Llamos el formulario de login
        frmLogin miLogin = new frmLogin();
        miLogin.setDatos(misDatos);
        miLogin.setLocationRelativeTo(null);
        miLogin.setVisible(true);
    }
}
