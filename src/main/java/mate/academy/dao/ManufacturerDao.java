package mate.academy.dao;

import java.util.List;
import java.util.Optional;
import mate.academy.model.Manufacturer;

public interface ManufacturerDao extends GenericDao<Manufacturer, Long> {
    Optional<Manufacturer> getById(Long manufacturerId);

    boolean deleteById(Long manufacturerId);

    List<Manufacturer> getAllManufactures();
}
