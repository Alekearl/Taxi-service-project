package mate.academy.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import mate.academy.dao.DriverDao;
import mate.academy.exception.DataProcessingException;
import mate.academy.lib.Dao;
import mate.academy.model.Driver;
import mate.academy.util.ConnectionUtil;

@Dao
public class DriverDaoImplJdbc implements DriverDao {
    @Override
    public Driver create(Driver driver) {
        String query = "INSERT INTO drivers (name, license_number) VALUES (?, ?);";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.executeUpdate();
            ResultSet resultSet = statement.getGeneratedKeys();
            if (resultSet.next()) {
                driver.setId(resultSet.getLong(1));
            }
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't create driver "
                    + driver, e);
        }
    }

    @Override
    public Optional<Driver> get(Long id) {
        String query = "SELECT * FROM drivers WHERE id = ?"
                + " AND deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, id);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                return Optional.of(parseDataFromResultSet(resultSet));
            }
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't get driver id "
                    + id, e);
        }
        return Optional.empty();
    }

    @Override
    public List<Driver> getAll() {
        List<Driver> drivers = new ArrayList<>();
        String query = "SELECT * FROM drivers"
                + " WHERE deleted = FALSE;";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                drivers.add(parseDataFromResultSet(resultSet));
            }
            return drivers;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't get all drivers "
                    + drivers, e);
        }
    }

    @Override
    public Driver update(Driver driver) {
        String query = "UPDATE drivers SET name = ?, license_number = ? "
                + "WHERE id = ? AND deleted = FALSE";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setString(1, driver.getName());
            statement.setString(2, driver.getLicenseNumber());
            statement.setLong(3, driver.getId());
            statement.executeUpdate();
            return driver;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't update driver "
                    + driver, e);
        }
    }

    @Override
    public boolean delete(Long id) {
        String query = "UPDATE drivers SET deleted = TRUE "
                + " WHERE id = ?;";
        try (Connection connection = ConnectionUtil.getConnection(); PreparedStatement statement =
                connection.prepareStatement(query)) {
            statement.setLong(1, id);
            return statement.executeUpdate() > 0;
        } catch (SQLException e) {
            throw new DataProcessingException("Something went wrong. Can't delete driver by ID "
                    + id, e);
        }
    }

    private Driver parseDataFromResultSet(ResultSet resultSet) throws SQLException {
        Long id = resultSet.getObject("id", Long.class);
        String name = resultSet.getString("name");
        String licenseNumber = resultSet.getString("license_number");
        Driver driver = new Driver(name, licenseNumber);
        driver.setId(id);
        return driver;
    }
}
