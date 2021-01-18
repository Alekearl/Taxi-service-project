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
        String query = "INSERT INTO taxi_service (name, country) VALUES (?, ?);";
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
        String query = "SELECT * FROM taxi_service "
                + "WHERE id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, manufacturerId);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseDataFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong."
                    + " Can't get manufacturer by index " + manufacturerId, e);
        }
        return Optional.empty();
    }

    @Override
    public Manufacturer update(Manufacturer manufacturer) {
        String query = "UPDATE taxi_service "
                + "SET name = ?, country = ? "
                + "WHERE id = ? AND deleted = FALSE";
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
    public boolean deleteById(Long manufacturerId) {
        String query = "UPDATE taxi_service "
                + "SET deleted = TRUE WHERE id = ?";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            //statement.setBoolean(1, true);
            statement.setLong(1, manufacturerId);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. "
                    + "Can't delete manufacturer " + manufacturerId, e);
        }
    }

    @Override
    public boolean delete(Manufacturer manufacturer) {
        return deleteById(manufacturer.getId());
    }

    @Override
    public List<Manufacturer> getAllManufactures() {
        List<Manufacturer> manufacturers = new ArrayList<>();
        String query = "SELECT * FROM taxi_service "
                + "WHERE deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                manufacturers.add(parseDataFromResultSet(resultSet));
            }
            return manufacturers;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. "
                    + "Can't get all manufacturers", e);
        }
    }

    private Manufacturer parseDataFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getLong("id");
        String name = resultSet.getString("name");
        String country = resultSet.getString("country");
        Manufacturer manufacturer = new Manufacturer(name, country);
        manufacturer.setId(id);
        return manufacturer;
    }
}
