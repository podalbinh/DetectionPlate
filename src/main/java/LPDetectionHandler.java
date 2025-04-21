/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
import dao.LicensePlateDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import models.LicensePlate;

public class LPDetectionHandler implements LicensePlateHandler {
    private LicensePlateHandler nextHandler;

    @Override
    public void setNextHandler(LicensePlateHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(List<LicensePlate> plates, HttpSession session) {

        System.out.println("Plates detected: " + plates.size());

        if (nextHandler != null) {
            nextHandler.handle(plates, session);
        }
    }
}

