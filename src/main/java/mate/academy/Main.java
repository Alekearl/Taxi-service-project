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
        Manufacturer volksWagenSedan = new Manufacturer("Passat B8", "Germany");
        Manufacturer volksWagen = new Manufacturer("Touareg 7P", "Germany");
        Manufacturer seat = new Manufacturer("Leon", "Spain");
        Manufacturer skoda = new Manufacturer("Superb", "Czech Republic");
        manufacturerService.create(volksWagenSedan);
        manufacturerService.create(volksWagen);
        manufacturerService.create(seat);
        manufacturerService.create(skoda);
        System.out.println(manufacturerService.getAllManufactures());
        Manufacturer landRover = new Manufacturer("Discovery", "Germany");
        manufacturerService.create(landRover);
        landRover.setCountry("England");
        System.out.println(manufacturerService.getAllManufactures());
        manufacturerService.update(landRover);
        System.out.println(manufacturerService.getAllManufactures());
        manufacturerService.deleteById(3L);
        System.out.println(manufacturerService.getAllManufactures());
        System.out.println(manufacturerService.getById(4L));
    }
}
