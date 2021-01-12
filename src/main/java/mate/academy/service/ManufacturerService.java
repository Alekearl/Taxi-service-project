package mate.academy.service;

import java.util.List;
import java.util.Optional;
import mate.academy.model.Manufacturer;

public interface ManufacturerService {
    Manufacturer create(Manufacturer manufacturer);

    Optional<Manufacturer> getById(Long manufactureId);

    Manufacturer update(Manufacturer manufacturer);

    boolean deleteById(Long manufactureId);

    boolean delete(Manufacturer manufacturer);

    List<Manufacturer> getAllManufactures();
}
