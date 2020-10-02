/*
 * Copyright (C) 2015 hcadavid
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 */
package edu.eci.cvds.sampleprj.jdbc.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author hcadavid
 */
public class JDBCExample {
		private static final String SQL_REGISTRAR_NUEVO_PRODUCTO = "INSERT INTO ORD_PRODUCTOS(codigo, nombre, precio) VALUES (?,?,?)";
		private static final String SQL_NOMBRE_PRODUCTOS_PEDIDO = "SELECT nombre FROM ORD_PRODUCTOS WHERE codigo = ?";
		private static final String SQL_VALOR_TOTAL_PEDIDO = "SELECT SUM(cantidad*ORD_PRODUCTOS.precio) FROM ORD_DETALLE_PEDIDO,ORD_PRODUCTOS WHERE producto_fk = ORD_PRODUCTOS-codigo && pedido_fk = ?;";
		
    
    public static void main(String args[]){
        try {
            String url="jdbc:mysql://desarrollo.is.escuelaing.edu.co:3306/bdprueba";
            String driver="com.mysql.jdbc.Driver";
            String user="bdprueba";
            String pwd="prueba2019";
                        
            Class.forName(driver);
            Connection con=DriverManager.getConnection(url,user,pwd);
            con.setAutoCommit(false);
                 
            
            System.out.println("Valor total pedido 1:"+valorTotalPedido(con, 1));
            
            List<String> prodsPedido=nombresProductosPedido(con, 1);
            
            
            System.out.println("Productos del pedido 1:");
            System.out.println("-----------------------");
            for (String nomprod:prodsPedido){
                System.out.println(nomprod);
            }
            System.out.println("-----------------------");
            
            
            int suCodigoECI=2146684;
            registrarNuevoProducto(con, suCodigoECI, "LINA", 99999999);     
            nombresProductosPedido(con,2146684);
            con.commit();
                        
            
            con.close();
                                   
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(JDBCExample.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        
    }
    
    /**
     * Agregar un nuevo producto con los parámetros dados
     * @param con la conexión JDBC
     * @param codigo
     * @param nombre
     * @param precio
     * @throws SQLException 
     */
    public static void registrarNuevoProducto(Connection con, int codigo, String nombre,int precio) throws SQLException{
        //Crear preparedStatement
    	PreparedStatement st = null;
    	int filas = 0;
    	st = con.prepareStatement(SQL_REGISTRAR_NUEVO_PRODUCTO);
    	int index = 1;
    	
        //Asignar parámetros
    	st.setInt(index++, codigo);
    	st.setString(index++, nombre);
    	st.setInt(index++, precio);
    	
    	System.out.println("Ejecutando query: " + SQL_REGISTRAR_NUEVO_PRODUCTO);
        //usar 'execute'
    	filas = st.executeUpdate();
    	System.out.println("N. filas insertadas: " + filas);

        
        con.commit();
        
    }
    
    /**
     * Consultar los nombres de los productos asociados a un pedido
     * @param con la conexión JDBC
     * @param codigoPedido el código del pedido
     * @return 
     */
    public static List<String> nombresProductosPedido(Connection con, int codigoPedido){
        List<String> np=new LinkedList<>();
        
        //Crear prepared statement
        PreparedStatement st = null;
        ResultSet res = null;
        String nombre = null;
        //asignar parámetros
        //usar executeQuery
        try {
        		st = con.prepareStatement(SQL_NOMBRE_PRODUCTOS_PEDIDO);
        		st.setInt(1, codigoPedido);
        		res = st.executeQuery();
        		
        		while (res.next()) {
        			nombre = res.getString(1);
        			np.add(nombre);
        		}		
        }catch(SQLException e) {
        	e.printStackTrace();
        	
        }
        
        //Sacar resultados del ResultSet
        //Llenar la lista y retornarla
        System.out.println("Ejecutando query: " + SQL_NOMBRE_PRODUCTOS_PEDIDO);
        System.out.println(np);
        return np;
    }

    
    /**
     * Calcular el costo total de un pedido
     * @param con
     * @param codigoPedido código del pedido cuyo total se calculará
     * @return el costo total del pedido (suma de: cantidades*precios)
     */
    public static int valorTotalPedido(Connection con, int codigoPedido){
        
        //Crear prepared statement
    	PreparedStatement st = null;
    	ResultSet res = null;
    	int valorTotal = 0;
        //asignar parámetros
    	try {
    			st = con.prepareStatement(SQL_VALOR_TOTAL_PEDIDO);
    			st.setInt(1, codigoPedido);
    			res = st.executeQuery();
    			while(res.next()) {
    				valorTotal = res.getInt(1);
    			}
    	} catch(SQLException e) {
    		e.printStackTrace();
    	}
        //usar executeQuery
        //Sacar resultado del ResultSet
    	return valorTotal;
        
    }
    

    
    
    
}