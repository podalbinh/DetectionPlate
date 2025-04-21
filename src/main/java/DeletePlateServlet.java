/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import javax.servlet.http.HttpSession;
import models.LicensePlate;

@WebServlet("/deletePlate")
public class DeletePlateServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int plateId = Integer.parseInt(request.getParameter("plateId"));

            HttpSession session = request.getSession();
        // Cập nhật danh sách biển số không hợp lệ
        List<LicensePlate> unregisteredPlates = (List<LicensePlate>) session.getAttribute("unregisteredPlates");
        System.out.println("Delete " + unregisteredPlates);
        if (unregisteredPlates != null) {
            unregisteredPlates.removeIf(plate -> plate.getId() == plateId);
            request.getSession().setAttribute("unregisteredPlates", unregisteredPlates);
        }

        // Trả về phản hồi thành công
        response.setStatus(HttpServletResponse.SC_OK);
    }
}