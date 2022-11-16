/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;
     
import Modelo.Persona;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Modelo.PersonaCRUD;
import Vista.Interfaz;
import java.sql.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
/**
 *
 * @author Juan
 */
public class ControladorInterfaz implements ActionListener{
    private Interfaz ventana;
    private Persona persona;
    private PersonaCRUD personaCrud;
    DefaultTableModel modelo= new DefaultTableModel(); 
    /*Constructor*/
    
    public ControladorInterfaz(Interfaz ventana, Persona persona, PersonaCRUD personaCrud){
        this.ventana = ventana;
        this.persona= persona;
        this.personaCrud= personaCrud;
        
        this.ventana.ButtonGuardar.addActionListener(this);
        this.ventana.ButtonModificar.addActionListener(this);
        this.ventana.ButtonBuscar.addActionListener(this);
        this.ventana.ButtonListar.addActionListener(this);
        this.ventana.ButtonLimpiar.addActionListener(this);

    }
    /*Metodos*/
    
    public void iniciar(){
        ventana.setTitle("Interfaz Persona");
        ventana.setLocationRelativeTo(null);
    }
    
    public void limpiar(JTable tabla){
            ventana.jTextFieldCedula.setText(null);
            ventana.jTextFieldNombre.setText(null);
            ventana.jTextFieldApellido.setText(null);
            ventana.jTextFieldCorreo.setText(null);
            ventana.jTextFieldTelefono.setText(null);

            DefaultTableModel tb= (DefaultTableModel)tabla.getModel();
            int a= tabla.getRowCount()-1;
            System.out.println(tabla.getRowCount());
            for(int i=a; i>=0; i--){
                tb.removeRow(tb.getRowCount()-1);
              }
                ventana.ButtonListar.setVisible(true);
        }
    public void listarTabla(JTable tabla){
        modelo = (DefaultTableModel)tabla.getModel();
        List<Persona> lista= personaCrud.listarPersona();
        Object[] objeto= new Object[5];
        if (modelo.getRowCount()== 0) {
            for(int i=0; i<lista.size();i++){
            objeto[0]= lista.get(i).getCedula();
            objeto[1]= lista.get(i).getNombre();
            objeto[2]= lista.get(i).getApellido();
            objeto[3]= lista.get(i).getCorreo();
            objeto[4]= lista.get(i).getTelefono();   
            modelo.addRow(objeto);
        }
        ventana.jTable2.setModel(modelo);
            
        }
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource()==ventana.ButtonBuscar){
            persona.setCedula(Integer.parseInt(ventana.jTextFieldBuscar.getText()));
            try {
                if(personaCrud.buscarPersona(persona)){
                    ventana.jTextFieldCedula.setText(String.valueOf(persona.getCedula()));
                    ventana.jTextFieldNombre.setText(String.valueOf(persona.getNombre()));
                    ventana.jTextFieldCorreo.setText(String.valueOf(persona.getCorreo()));
                    ventana.jTextFieldApellido.setText(String.valueOf(persona.getApellido()));
                    ventana.jTextFieldTelefono.setText(String.valueOf(persona.getTelefono()));
                    
                }else{
                    JOptionPane.showMessageDialog(null, "no se encontro registro");
                    limpiar(ventana.jTable2);
                    
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControladorInterfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
    }else if(e.getSource()==ventana.ButtonGuardar){
            persona.setCedula(Integer.parseInt(ventana.jTextFieldCedula.getText()));
            persona.setNombre(ventana.jTextFieldNombre.getText());
            persona.setApellido(ventana.jTextFieldApellido.getText());
            persona.setCorreo(ventana.jTextFieldCorreo.getText());
            persona.setTelefono(Long.parseLong(ventana.jTextFieldTelefono.getText()));
            
            try{
                if(personaCrud.agregarPersona(persona)){
                   JOptionPane.showMessageDialog(null,"Se guardo la persona");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al guardar la persona"); 
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorInterfaz.class.getName()).log(Level.SEVERE, null, ex);
            }
        
        }else if(e.getSource()==ventana.ButtonModificar){
            persona.setCedula(Integer.parseInt(ventana.jTextFieldCedula.getText()));
            persona.setNombre(ventana.jTextFieldNombre.getText());
            persona.setApellido(ventana.jTextFieldApellido.getText());
            persona.setCorreo(ventana.jTextFieldCorreo.getText());
            persona.setTelefono(Long.parseLong(ventana.jTextFieldTelefono.getText()));
            
            try{
               if(personaCrud.actualizarPersona(persona)){
                    JOptionPane.showMessageDialog(null,"Se Actualizo la persona");
                }else{
                   JOptionPane.showMessageDialog(null, "Error al actualizar la persona"); 
                }
               } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControladorInterfaz.class.getName()).log(Level.SEVERE, null, ex);
            }      
        }else if(e.getSource()==ventana.ButtonListar){  
            listarTabla(ventana.jTable2);
        }else if(e.getSource()==ventana.ButtonLimpiar){
            limpiar(ventana.jTable2);
        }
    }     
 }   

