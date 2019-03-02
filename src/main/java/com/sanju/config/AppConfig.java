package com.sanju.config;


import com.sanju.dao.EmployeeDao;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.VendorDatabaseIdProvider;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.mapper.MapperFactoryBean;
import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

import javax.sql.DataSource;
import java.io.IOException;
import java.util.Properties;

import static javax.swing.text.html.HTML.Tag.H2;

@Configuration
@ComponentScan(basePackages = "com.sanju")
@MapperScan("com.sanju.dao")
@EnableTransactionManagement
@EnableWebMvc
public class AppConfig extends WebMvcConfigurationSupport {

  /*  @Bean
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("org.h2.Driver");
        basicDataSource.setUrl("jdbc:h2:./db/mybatisDb;AUTO_SERVER=TRUE");
        basicDataSource.setUsername("sa");
        basicDataSource.setPassword("");
        basicDataSource.setMaxIdle(2);
        return basicDataSource;
    }
*/

    @Bean
    public BasicDataSource dataSource() {
        BasicDataSource basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
        basicDataSource.setUrl("jdbc:mysql://localhost:3306/mybatis");
        basicDataSource.setUsername("root");
        basicDataSource.setPassword("Sanju@123");
        basicDataSource.setMaxIdle(2);
        return basicDataSource;
    }
    @Bean
    public DataSource db() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        builder.setType(EmbeddedDatabaseType.H2)
                .addScript("db/schema-create-db.sql")
                .addScript("db/schema-populate-db.sql");
        return builder.build();
    }

    @Bean
    public PropertiesFactoryBean vendorProperties() {
        PropertiesFactoryBean factoryBean = new PropertiesFactoryBean();

        Properties properties = new Properties();
        properties.put("SQL Server", "sqlserver");
        properties.put("DB2", "db2");
        properties.put("Oracle", "oracle");
        properties.put("MySQL", "mysql");
        properties.put("H2", "h2");
        factoryBean.setProperties(properties);

        return factoryBean;
    }

    @Bean
    public VendorDatabaseIdProvider databaseIdProvider() throws IOException {
        VendorDatabaseIdProvider vendorDatabaseIdProvider = new VendorDatabaseIdProvider();
        vendorDatabaseIdProvider.setProperties(vendorProperties().getObject());
        return vendorDatabaseIdProvider;
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws IOException {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(dataSource());
        factoryBean.setDatabaseIdProvider(databaseIdProvider());
        factoryBean.setEnvironment("development");
        org.apache.ibatis.session.Configuration configuration = new org.apache.ibatis.session.Configuration();
        configuration.setMapUnderscoreToCamelCase(true);
        factoryBean.setConfiguration(configuration);
        return factoryBean;
    }


}
