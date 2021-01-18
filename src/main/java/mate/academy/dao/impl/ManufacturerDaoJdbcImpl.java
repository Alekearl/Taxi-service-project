package mate.academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.ManufacturerDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Manufacturer;
import mate.academy.util.ConnectionUtil;

@Dao
public class ManufacturerDaoJdbcImpl implements ManufacturerDao {

    @Override
    public Manufacturer create(Manufacturer manufacturer) {
        String query = "INSERT INTO manufacturer.manufacturers (manufacturer_name, "
                + "manufacturer_country) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                manufacturer.setId(resultSet.getLong(1));
            }
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't create manufacturer "
                    + manufacturer, e);
        }
    }

    @Override
    public Optional<Manufacturer> getById(Long manufacturerId) {
        String query = "SELECT * FROM manufacturer.manufacturers "
                + "WHERE manufacturer_id = ? AND manufacturer_delete = FALSE";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, manufactureId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                Long id = resultSet.getLong("manufacturer_id");
                String name = resultSet.getString("manufacturer_name");
                String country = resultSet.getString("manufacturer_country");
                Manufacturer manufacturer = new Manufacturer(name, country);
                manufacturer.setId(id);
                return Optional.of(manufacturer);
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong."
                    + " Can't get ID dy index " + manufactureId, e);
        }
        return Optional.empty();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE manufacturer.manufacturers "
                + "SET manufacturer_name = ?, manufacturer_country = ? "
                + "WHERE manufacturer_id = ? AND manufacturer_delete = FALSE";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setString(1, manufacturer.getName());
            statement.setString(2, manufacturer.getCountry());
            statement.setLong(3, manufacturer.getId());
            statement.executeUpdate();
            return manufacturer;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong."
                    + " Can't update manufacturer " + manufacturer, e);
        }
    }

    @Override
    public boolean deleteById(Long manufactureId) {
        String query = "UPDATE manufacturer.manufacturers "
                + "SET manufacturer_delete = TRUE WHERE manufacturer_id = ?";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setBoolean(1, true);
            statement.setLong(2, manufactureId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. "
                    + "Can't delete manufacturer " + manufactureId, e);
        }
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        return deleteById(manufacturer.getId());
    }

    @Override
    public List<Manufacturer> getAllManufactures() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = "SELECT * FROM manufacturer.manufacturers "
                + "WHERE manufacturer_delete = FALSE";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                Manufacturer manufacturer =
                        new Manufacturer(resultSet.getString("manufacturer_name"),
                                resultSet.getString("manufacturer_country"));
                manufacturer.setId(resultSet.getLong("manufacturer_id"));
                manufacturers.add(manufacturer);
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. "
                    + "Can't get all manufacturers", e);
        }
    }
}
