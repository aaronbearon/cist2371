package aaronbearon.finals2;

import org.jdbi.v3.core.Jdbi;
import org.jdbi.v3.core.mapper.reflect.ConstructorMapper;
import org.junit.jupiter.api.Test;

import java.net.URL;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class j2fpTest {

    @Test
    public void testCreateQuery() throws Exception {
        URL resource = getClass().getResource("/aaronbearon/finals2/cars.csv");
        assert (resource != null);
        String path = Path.of(resource.toURI()).toString();

        // The URL 'jdbc:h2:mem:testdb' tells H2 to run in-memory
        try (Connection conn = DriverManager.getConnection("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "")) {
            Statement st = conn.createStatement();
            st.execute("CREATE TABLE cars1 AS SELECT * FROM CSVREAD('" + path + "');");

            ResultSet rs = st.executeQuery("SELECT * FROM cars1");
            while (rs.next()) {
                System.out.println("Car: " + rs.getString("imageName"));
            }
        }
    }

    @Test
    public void testJDBI() throws Exception {
        URL resource = getClass().getResource("/aaronbearon/finals2/cars.csv");
        assert (resource != null);
        String path = Path.of(resource.toURI()).toString().replace('\\', '/');

        Jdbi jdbi = Jdbi.create("jdbc:h2:mem:testdb;DB_CLOSE_DELAY=-1", "sa", "");
        jdbi.registerRowMapper(Car.class, ConstructorMapper.of(Car.class));

        // Setup the table
        jdbi.withHandle(handle -> {
            handle.execute("CREATE TABLE cars2 AS SELECT * FROM CSVREAD('" + path + "');");
            return null;
        });

        List<Car> cars = jdbi.withHandle(handle -> {
            // Query and map directly to a List of Car records
            return handle.createQuery("SELECT * FROM cars2")
                    .mapTo(Car.class)
                    .list();
        });

        // 5. Verify results
        cars.forEach(car -> System.out.println("JDBI mapped car: " + car));
    }
}
