import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;
import models.LicensePlate;
import dao.LicensePlateDao; // Giả sử bạn có một lớp dịch vụ để xử lý biển số

@WebServlet("/savePlates")
public class SaveLicensePlatesServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Lấy danh sách biển số từ session
        HttpSession session = request.getSession();
        List<LicensePlate> unregisteredPlates = (List<LicensePlate>) session.getAttribute("unregisteredPlates");
        System.out.println("Save " + unregisteredPlates);
        if (unregisteredPlates != null && !unregisteredPlates.isEmpty()) {
            LicensePlateDao plateService = new LicensePlateDao();
            for (LicensePlate plate : unregisteredPlates) {
                // Lưu từng biển số vào CSDL
                plateService.savePlate(plate);
            }
            // Chuyển hướng đến notice.jsp với thông báo thành công
            request.setAttribute("message", "Đã lưu thành công các biển số vào CSDL.");
            request.getRequestDispatcher("notice.jsp").forward(request, response);
        } else {
            // Chuyển hướng đến notice.jsp với thông báo không có biển số để lưu
            request.setAttribute("message", "Không có biển số nào để lưu.");
            request.getRequestDispatcher("notice.jsp").forward(request, response);
        }
    }
}