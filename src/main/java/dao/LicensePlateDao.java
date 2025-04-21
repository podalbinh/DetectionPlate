/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import models.LicensePlate;
import static dao.DatabaseConnection.con;
// Giả sử bạn có một lớp để quản lý kết nối CSDL

public class LicensePlateDao extends DatabaseConnection {
    public LicensePlateDao(){
        super();
    }
    public void savePlate(LicensePlate plate) {
        String sql = "INSERT INTO license_plate (name, imageUrl, isRegistered) VALUES (?, ?,?)";

        try {
             PreparedStatement statement = con.prepareStatement(sql);
            statement.setString(1, plate.getName());
            statement.setString(2, plate.getImageUrl());
            statement.setString(3, plate.getIsRegistered());
            statement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi (có thể ném ra một ngoại lệ hoặc ghi log)
        }
    }
    public List<LicensePlate> getPlateRegistered(){
        List<LicensePlate> listPlate = new ArrayList<>();
        String sql = "Select * from license_plate";
        try {
        CallableStatement cstmt = con.prepareCall(sql);
        try(ResultSet rs = cstmt.executeQuery()){
            while(rs.next()){
                listPlate.add(new LicensePlate(rs.getInt("id"),rs.getString("name"),rs.getString("imageUrl"),rs.getString("isRegistered")));
            }
        }}
        catch (SQLException e) {
            e.printStackTrace();
            // Xử lý lỗi (có thể ném ra một ngoại lệ hoặc ghi log)
        }
        return listPlate;
    }
}