/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
import Modelo.Conexion;
import Modelo.Persona;
import Modelo.PersonaCRUD;
import Vista.Interfaz;

/**
 *
 * @author Juan
 */
public class Launcher {
    public static void main(String[] args) throws ClassNotFoundException {
        Interfaz ventana = new Interfaz();
        Persona persona = new Persona();
        PersonaCRUD personaCrud = new PersonaCRUD();
        ControladorInterfaz controlador = new ControladorInterfaz(ventana,persona,personaCrud);
        controlador.iniciar();
        Conexion.getConexionSinConector();
        ventana.setVisible(true);
    }
}

