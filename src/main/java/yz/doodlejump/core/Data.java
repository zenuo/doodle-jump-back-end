package yz.doodlejump.core;

import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.mapping.Environment;
import org.apache.ibatis.session.Configuration;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.apache.ibatis.transaction.TransactionFactory;
import org.apache.ibatis.transaction.jdbc.JdbcTransactionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import javax.sql.DataSource;

/**
 * 数据库连接类
 */
public class Data {
    /**
     * SqlSessionFactory实例
     */
    private static SqlSessionFactory sqlSessionFactory;

    /**
     * 日志记录器
     */
    public static final Logger LOGGER = LoggerFactory.getLogger(Data.class);

    /**
     * 初始化数据库连接
     */
    public static void initialize() {
        try {
            DataSource dataSource = Data.getDataSource();
            TransactionFactory transactionFactory = new JdbcTransactionFactory();
            Environment MariaDBEnvironment = new Environment("mariadb", transactionFactory, dataSource);
            Configuration configuration = new Configuration(MariaDBEnvironment);
            configuration.addMappers("yz.gpslog.entity.mapper");
            sqlSessionFactory = new SqlSessionFactoryBuilder().build(configuration);
            LOGGER.info("Build SqlSessionFactory succeed");
        } catch (Exception e) {
            LOGGER.error("Build SqlSessionFactory error");
            e.printStackTrace();
        }
    }

    /**
     * 返回SqlSession实例
     *
     * @return SqlSession
     */
    public static SqlSession getSqlSession() {
        return sqlSessionFactory.openSession();
    }

    /**
     * 返回DataSource实例
     *
     * @return DataSource实例
     */
    private static DataSource getDataSource() {
        BasicDataSource mariadb = new BasicDataSource();
        mariadb.setDriverClassName("org.mariadb.jdbc.Driver");
        //服务器
        mariadb.setUrl("jdbc:mariadb://localhost:3306/DOODLEJUMP");
        //MariaDB账户密码
        mariadb.setUsername("doodle");
        mariadb.setPassword("123456");
        return mariadb;
    }
}
