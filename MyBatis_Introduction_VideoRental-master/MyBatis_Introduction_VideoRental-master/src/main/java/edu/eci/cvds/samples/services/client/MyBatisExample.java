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
 
 * Ayuda y coloboracion de https://github.com/juancamilo399/cvds_lab7
 */
package edu.eci.cvds.samples.services.client;

import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ClienteMapper;
import edu.eci.cvds.sampleprj.dao.mybatis.mappers.ItemMapper;
import edu.eci.cvds.samples.entities.Item;
import edu.eci.cvds.samples.entities.TipoItem;
import java.io.IOException;
import java.io.InputStream;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;


public class MyBatisExample {

    /**
     * Método que construye una fábrica de sesiones de MyBatis a partir del
     * archivo de configuración ubicado en src/main/resources
     *
     * @return instancia de SQLSessionFactory
     */
    public static SqlSessionFactory getSqlSessionFactory() {
        SqlSessionFactory sqlSessionFactory = null;
        if (sqlSessionFactory == null) {
            InputStream inputStream;
            try {
                inputStream = Resources.getResourceAsStream("mybatis-config.xml");
                sqlSessionFactory = new SqlSessionFactoryBuilder().build(inputStream);
            } catch (IOException e) {
                throw new RuntimeException(e.getCause());
            }
        }
        return sqlSessionFactory;
    }

    /**
     * Programa principal de ejempo de uso de MyBATIS
     *
     * @param args
     * @throws SQLException
     */
    public static void main(String args[]) throws SQLException {
        SqlSessionFactory sessionfact = getSqlSessionFactory();
        SqlSession sqlss = sessionfact.openSession();
        //Crear el mapper y usarlo: 
        ClienteMapper clienteMapper = sqlss.getMapper(ClienteMapper.class);
        imprimirEspacios("Clientes");
        System.out.println(clienteMapper.consultarClientes());
        imprimirEspacios("Clientes por ID");
        System.out.println(clienteMapper.consultarCliente(3));
        //Para la ejecucion final de las pruebas quitar el comentario de la siguiente instruccion
        //clienteMapper.agregarItemRentadoACliente(5, 1, parseDate("2020-03-12"), parseDate("2020-04-12"));
        ItemMapper itemMapper= sqlss.getMapper(ItemMapper.class);
        TipoItem tipoIt= new TipoItem(73,"coronavirus");
        Item it = new Item(tipoIt,39621635,"NuevoItemxxx","Este es el nuevo Item xxxx",parseDate("2020-03-12"),9999, "hola","pop"); 
        //Para la ejecucion final de las pruebas quitar el comentario de la siguiente instruccion
        itemMapper.insertarItem(it);
        
        imprimirEspacios("Items");
        System.out.println(itemMapper.consultarItems());
        imprimirEspacios("Items Por ID");
        System.out.println(itemMapper.consultarItem(9999));
        

        sqlss.commit();
        sqlss.close();
    }
    
    /**
     * Parse string to date
     * @param date
     * @return 
     */
    public static Date parseDate(String date) {
        try {
            return new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return null;
        }
    }
    
    public static void imprimirEspacios(String tipo){
        System.out.println("");
        System.out.println("");
        System.out.println("----------Resultados de la consulta de "+ tipo+ "--------------");
        System.out.println("");
        System.out.println("");

    }

}
