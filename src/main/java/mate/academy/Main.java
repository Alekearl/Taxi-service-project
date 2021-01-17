package mate.academy;

import mate.academy.lib.Injector;
import mate.academy.model.Manufacturer;
import mate.academy.service.ManufacturerService;

public class Main {
    private static final Injector injector
            = Injector.getInstance("mate.academy");

    public static void main(String[] args) {
        ManufacturerService manufacturerService
                = (ManufacturerService) injector.getInstance(ManufacturerService.class);
        Manufacturer manufacturer = new Manufacturer("Volvo", "China");
        manufacturerService.create(manufacturer);
        System.out.println(manufacturer);
        System.out.println(manufacturerService.getById(1L));
        manufacturer.setName("FORD");
        manufacturer.setCountry("USA");
        manufacturerService.update(manufacturer);
        System.out.println(manufacturer);
        manufacturerService.delete(manufacturer);
        manufacturerService.deleteById(3L);
        System.out.println(manufacturerService.getAllManufactures());
    }
}
