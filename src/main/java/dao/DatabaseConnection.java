/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;



/**
 *
 * @author Admin
 */
public class DatabaseConnection {
    public static Connection con;

    public DatabaseConnection() {
        if (con == null) {
            String dbClass = "com.mysql.cj.jdbc.Driver";
            try {
                Class.forName(dbClass);
                con = DriverManager.getConnection("jdbc:mysql://localhost:3306/detection_plate?useUnicode=true&characterEncoding=UTF-8", "root", "dangbinh1770");
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("Kết nối thành công");
    }
}
