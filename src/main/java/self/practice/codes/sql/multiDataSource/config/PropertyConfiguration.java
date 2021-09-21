package self.practice.codes.sql.multiDataSource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import self.practice.codes.sql.multiDataSource.DynamicDataSource;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;

@Configuration
public class PropertyConfiguration {
    @Autowired
    Environment env;

    @Bean
    @Primary
    public DynamicDataSource dataSource(DataSource master, DataSource slave) {
        Map<Object, Object> targetDataSources = new HashMap<>(5);
        targetDataSources.put("master", master);
        targetDataSources.put("slave", slave);
        return new DynamicDataSource(master, targetDataSources);
    }

    @Bean(name = "master")
    public DataSource masterDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("master.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("master.datasource.url"));
        dataSource.setUsername(env.getProperty("master.datasource.username"));
        dataSource.setPassword(env.getProperty("master.datasource.password"));
        return dataSource;

    }

    @Bean(name = "slave")
    public DataSource slaveDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("slave.datasource.driver-class-name"));
        dataSource.setUrl(env.getProperty("slave.datasource.url"));
        dataSource.setUsername(env.getProperty("slave.datasource.username"));
        dataSource.setPassword(env.getProperty("slave.datasource.password"));
        return dataSource;
    }
}
