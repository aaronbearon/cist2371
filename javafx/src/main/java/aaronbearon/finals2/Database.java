package aaronbearon.finals2;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;

import java.net.URISyntaxException;
import java.net.URL;
import java.nio.file.Path;
import java.util.List;

public class Database {
    private final Jdbi jdbi;

    public Database() {
        String path;
        try {
            URL resource = getClass().getResource("/aaronbearon/finals2/cars.csv");
            assert (resource != null);
            path = Path.of(resource.toURI()).toString().replace('\\', '/');
        } catch (URISyntaxException e) {
            throw new AssertionError(e); // should not happen
        }

        jdbi = Jdbi.create("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        jdbi.registerRowMapper(Car.class, ConstructorMapper.of(Car.class));
        jdbi.withHandle(handle -> {
            handle.execute("CREATE TABLE cars AS SELECT * FROM CSVREAD('" + path + "');");
            return null;
        });
    }

    // Query the database for a list of cars matching the selected filters.
    public List<Car> getFilteredCars(List<String> selectedBrands,
                                     List<String> selectedTypes,
                                     List<String> selectedColors,
                                     double priceMin, double priceMax,
                                     double timeMin, double timeMax,
                                     Boolean isElectric) {

        return jdbi.withHandle(handle -> {
            // Build a query using strings because we need a dynamic number of filters.
            StringBuilder sql = new StringBuilder("SELECT * FROM cars WHERE 1=1");
            if (!selectedBrands.isEmpty()) {
                sql.append(" AND brand IN (<brands>)");
            }
            if (!selectedTypes.isEmpty()) {
                sql.append(" AND type IN (<types>)");
            }
            if (!selectedColors.isEmpty()) {
                sql.append(" AND color IN (<colors>)");
            }
            sql.append(" AND price >= :priceMin AND price <= :priceMax");
            sql.append(" AND zeroToSixty >= :timeMin AND zeroToSixty <= :timeMax");
            if (isElectric != null) {
                sql.append(" AND isElectric = :isElectric");
            }
            // TODO: more filters?

            // Bind the query values that we actually used.
            var query = handle.createQuery(sql.toString());
            if (!selectedBrands.isEmpty()) {
                query.bindList("brands", selectedBrands);
            }
            if (!selectedTypes.isEmpty()) {
                query.bindList("types", selectedTypes);
            }
            if (!selectedColors.isEmpty()) {
                query.bindList("colors", selectedColors);
            }
            query.bind("priceMin", priceMin);
            query.bind("priceMax", priceMax);
            query.bind("timeMin", timeMin);
            query.bind("timeMax", timeMax);
            if (isElectric != null) {
                query.bind("isElectric", isElectric);
            }
            // TODO: more filters?

            return query.mapTo(Car.class).list();
        });
    }
}
