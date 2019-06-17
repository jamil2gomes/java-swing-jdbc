/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package conexao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author jamil
 */
public class ConnectionPool {
	
    private ConnectionPool(){}
    
    private static Connection conexao = null;

    public static Connection getConexao()
    {
        if(conexao == null){
            try{
                
                String url = "jdbc:mysql://localhost/lp3?useSSL=false&useTimezone=true&serverTimezone=UTC&allowPublicKeyRetrieval=true";
                String user = "root";
                String password = "81230268";
                conexao = DriverManager.getConnection(url, user, password);
                System.out.println("Conectado com sucesso");

            } catch (SQLException e) {
                throw new RuntimeException(e.getMessage());
            }
        }
        return conexao;
    }
    
   
    
}

