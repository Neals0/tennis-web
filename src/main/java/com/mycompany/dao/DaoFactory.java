package com.mycompany.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DaoFactory {
    String url, username, password;

    public DaoFactory(String url, String username, String password) {
        super();
        this.url = url;
        this.username = username;
        this.password = password;
    }
    
    public static DaoFactory getInstance() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // ou Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            
        }
        DaoFactory instance = new DaoFactory("jdbc:mysql://localhost:3306/copie_tennis?useSSL=false&serverTimezone=Europe/Paris", "root", "root"); //?useSSL=false&serverTimezone=Europe
        return instance;
    }
    
    public Connection getConnection() throws SQLException {
        Connection conn = DriverManager.getConnection(url, username, password);
        return conn;
    }
    
    public static void main(String[] args) {
		System.out.println("c'est pété");
		
	}
}