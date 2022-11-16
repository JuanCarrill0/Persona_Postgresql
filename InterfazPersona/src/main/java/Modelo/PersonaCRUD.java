/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Juan
 */
public class PersonaCRUD extends Conexion{
    
     public boolean agregarPersona(Persona persona) throws ClassNotFoundException{
         //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
        int cedula= persona.getCedula();
        String nombre= persona.getNombre();
        String apellido= persona.getApellido();
        String correo= persona.getCorreo();
        long telefono= persona.getTelefono();
        System.out.println(cedula+nombre+apellido+correo+telefono);
        String sql= "INSERT INTO persona (cedula,nombre,apellido,correo,telefono)"
                + "VALUES(?,?,?,?,?)";
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, cedula);
            ps.setString(2, nombre);
            ps.setString(3, apellido);
            ps.setString(4, correo);
            ps.setLong(5, telefono);
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
     }
     
     public List listarPersona(){
        List<Persona> datos= new ArrayList<>();
        String sql= "select * from persona";
        try{
            Connection con=getConexionSinConector();
            PreparedStatement ps= con.prepareStatement(sql);
            //Metodo para capturar resultado de una consulta a la BD
            ResultSet rs= ps.executeQuery();
            while(rs.next()){
                /* 1. 1-Halo-50000-Shooters-50
                   2. 3-Batalla Naval-80000-Estrategia-60    
                */
                Persona persona= new Persona();
                persona.setCedula(rs.getInt(1));
                persona.setNombre(rs.getString(2));
                persona.setApellido(rs.getString(3));
                persona.setCorreo(rs.getString(4));
                persona.setTelefono(rs.getLong(5));
                datos.add(persona);
                //datos= [[1,Halo,50000,Shooters,50],]
            }
               
        }catch(Exception e){
      
        }
        return datos;
        
    }
     
      public boolean actualizarPersona(Persona persona) throws ClassNotFoundException{
        //Metodo para definir una consulta
        PreparedStatement ps= null;
        Connection con=getConexionSinConector();
              
        String sql= "UPDATE persona SET cedula=?,nombre=?, apellido=?, correo=?, telefono=? where cedula=?";                                                                                                                      ;
        try{
            ps=con.prepareStatement(sql);
            ps.setInt(1, persona.getCedula());
            ps.setString(2, persona.getNombre());
            ps.setString(3, persona.getApellido());
            ps.setString(4, persona.getCorreo());
            ps.setLong(5, persona.getTelefono());
            ps.setInt(6, persona.getCedula());
            ps.execute();
            return true;
        }catch(SQLException e){
            e.printStackTrace();
            return false;
        }
        
    }
      
      public boolean buscarPersona(Persona persona) throws SQLException{
        
        PreparedStatement ps= null;
        ResultSet rs= null;
        Connection con = getConexionConConector();
        
        String sql= "SELECT * FROM persona WHERE cedula=?";
        
        
        try{
             ps= con.prepareStatement(sql);
             ps.setInt(1, persona.getCedula());
             rs= ps.executeQuery();
             
             if(rs.next()){
                 persona.setCedula(Integer.parseInt(rs.getString("cedula")));
                 persona.setNombre(rs.getString("nombre"));
                 persona.setApellido(rs.getString("apellido"));
                 persona.setCorreo(rs.getString("correo"));
                 persona.setTelefono(Long.parseLong(rs.getString("telefono")));
                 
                 return true;
             }
             return false;
            
            }catch(SQLException e){
               e.printStackTrace();
               return false; 
            } finally{
               try{
                   con.close();
               }catch(SQLException e){
                   e.printStackTrace();
               }
            }
        
        
    }
    
}
