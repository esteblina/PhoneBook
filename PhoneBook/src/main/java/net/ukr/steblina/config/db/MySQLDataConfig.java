package net.ukr.steblina.config.db;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.hibernate5.HibernateTransactionManager;
import org.springframework.orm.hibernate5.LocalSessionFactoryBean;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import net.ukr.steblina.config.BasicDataConfig;

@Profile("db")
@Configuration
@EnableTransactionManagement
@PropertySource("file:${lardi.conf}")
public class MySQLDataConfig implements BasicDataConfig {
	@Value("${db.user}")
	private String username;

	@Value("${db.pass}")
	private String password;

	@Value("${db.url}")
	private String url;

	private final String DRIVER = "com.mysql.jdbc.Driver";
	private final String HIBERNATE_DIALECT = "org.hibernate.dialect.MySQL5Dialect";
	private final String HIBERNATE_SHOW_SQL = "true";
	private final String HIBERNATE_HBM2DDL_AUTO = "update";
	private final String ENTITYMANAGER_PACKAGES_TO_SCAN = "net.ukr.steblina.models";

	public void setUsername(String username) {
		this.username = username;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	@Bean
	public DataSource dataSource() {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(DRIVER);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		initDB(dataSource);
		return dataSource;
	}

	@Bean
	public LocalSessionFactoryBean sessionFactory() {
		LocalSessionFactoryBean sessionFactoryBean = new LocalSessionFactoryBean();
		sessionFactoryBean.setDataSource(dataSource());
		sessionFactoryBean.setPackagesToScan(ENTITYMANAGER_PACKAGES_TO_SCAN);
		Properties hibernateProperties = new Properties();
		hibernateProperties.put("hibernate.dialect", HIBERNATE_DIALECT);
		hibernateProperties.put("hibernate.show_sql", HIBERNATE_SHOW_SQL);
		hibernateProperties.put("hibernate.hbm2ddl.auto", HIBERNATE_HBM2DDL_AUTO);
		sessionFactoryBean.setHibernateProperties(hibernateProperties);

		return sessionFactoryBean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		HibernateTransactionManager transactionManager = new HibernateTransactionManager();
		transactionManager.setSessionFactory(sessionFactory().getObject());
		return transactionManager;
	}

	private void initDB(DataSource dataSource) {

		try (Statement st = dataSource.getConnection().createStatement()) {
			ResultSet rs = st.executeQuery("SHOW TABLES LIKE 'users'");
			if (!rs.next())
				st.execute("CREATE TABLE `users` (`id` INT NOT NULL AUTO_INCREMENT,"
						+ "`login` VARCHAR(50) UNIQUE NOT NULL, `password` VARCHAR(50) NOT NULL,"
						+ "`fullname` VARCHAR(50) NOT NULL, PRIMARY KEY (`id`))");
			rs = st.executeQuery("SHOW TABLES LIKE 'phones'");
			if (!rs.next())
				st.execute("CREATE TABLE `phones` (`id` INT NOT NULL AUTO_INCREMENT,"
						+ "`user_id` INT NOT NULL DEFAULT '0'," + "`lastname` VARCHAR(50) NOT NULL DEFAULT '0',"
						+ "`firstname` VARCHAR(50) NOT NULL DEFAULT '0',"
						+ "`patronymic` VARCHAR(50) NOT NULL DEFAULT '0',"
						+ "`mobilephone` VARCHAR(50) NOT NULL DEFAULT '0'," + "`homephone` VARCHAR(50) DEFAULT '0',"
						+ "`address` VARCHAR(50) DEFAULT '0'," + "`email` VARCHAR(50) DEFAULT '0',"
						+ "PRIMARY KEY (`id`))");
		} catch (SQLException e) {
			System.err.println("DB problem");
			e.printStackTrace();
			System.exit(69);
		}
	}

}
