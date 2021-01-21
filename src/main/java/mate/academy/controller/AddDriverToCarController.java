package mate.academy.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mate.academy.lib.Injector;
import mate.academy.model.Car;
import mate.academy.model.Driver;
import mate.academy.service.CarService;
import mate.academy.service.DriverService;

public class AddDriverToCarController extends HttpServlet {
    private static final Injector injector = Injector.getInstance("mate.academy");
    private final CarService carService = (CarService) injector.getInstance(CarService.class);
    private final DriverService driverService = (DriverService) injector
            .getInstance(DriverService.class);

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        req.getRequestDispatcher("/WEB-INF/views/cars/drivers/insert.jsp")
                .forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp)
            throws ServletException, IOException {
        Long id = Long.parseLong(req.getParameter("id"));
        Long driverId = Long.parseLong(req.getParameter("id"));
        Car car = carService.get(id);
        Driver driver = driverService.get(driverId);
        carService.addDriverToCar(driver, car);
        req.setAttribute("notification", "Driver inserted");
        req.getRequestDispatcher("/WEB-INF/views/cars/drivers/insert.jsp").forward(req, resp);
    }
}
