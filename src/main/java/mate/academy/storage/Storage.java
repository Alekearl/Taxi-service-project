package mate.academy.storage;

import java.util.ArrayList;
import java.util.List;
import mate.academy.model.Manufacturer;

public class Storage {
    public static final List<Manufacturer> manufacturers = new ArrayList<>();
    private static long manufactureId = 0L;

    public static Manufacturer addManufacturer(Manufacturer manufacturer) {
        manufacturer.setId(++manufactureId);
        manufacturers.add(manufacturer);
        return manufacturer;
    }
}
