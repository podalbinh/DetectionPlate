
import dao.LicensePlateDao;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import models.LicensePlate;

/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Admin
 */
public class UnregisteredLPHandler implements LicensePlateHandler {
    private LicensePlateHandler nextHandler;

    @Override
    public void setNextHandler(LicensePlateHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handle(List<LicensePlate> plates, HttpSession session) {
        List<LicensePlate> unregisteredPlates = new ArrayList<>();
        LicensePlateDao plateService = new LicensePlateDao();
        List<LicensePlate> listPlateRegistered = plateService.getPlateRegistered();

        for (LicensePlate pl : plates) {
            boolean isRegistered = listPlateRegistered.stream()
                .anyMatch(registeredPlate -> registeredPlate.getName().equals(pl.getName()));

            if (!isRegistered) {
                pl.setIsRegistered("0");
                unregisteredPlates.add(pl);
            }
        }

        session.setAttribute("unregisteredPlates", unregisteredPlates);
        System.out.println("Unregistered plates: " + unregisteredPlates.size());

        if (nextHandler != null) {
            nextHandler.handle(unregisteredPlates, session);
        }
    }
}

