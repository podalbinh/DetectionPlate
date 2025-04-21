

import java.util.List;
import javax.servlet.http.HttpSession;
import models.LicensePlate;
/**
 *
 * @author Admin
 */

public interface LicensePlateHandler {
    void setNextHandler(LicensePlateHandler nextHandler);
    void handle(List<LicensePlate> plates, HttpSession session);
}

