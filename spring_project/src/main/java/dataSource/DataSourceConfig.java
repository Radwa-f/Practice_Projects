package dataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceConfig {

    @Bean(name = "datasource_mydb")
    @Qualifier("datasource_mydb")
    public DataSource dataSource() {
        DataSource dataSource = new DataSource();
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver"); // adjust for your DB
        dataSource.setUrl("jdbc:mysql://localhost:3306/recipe_db");
        dataSource.setUsername("root");
        dataSource.setPassword("");
        return dataSource;
    }

    @Bean
    @Qualifier("datasource_mydb")
    public JdbcTemplate jdbcTemplate(@Qualifier("datasource_mydb") DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }
}