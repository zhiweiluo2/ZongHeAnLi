package cn.walking.util;

import com.alibaba.druid.pool.DruidDataSourceFactory;

import javax.sql.DataSource;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

/**
 * JDBC 工具类 使用druid连接池技术
 * * Druid连接池的工具类：
 *  * 首先用 maven导入druid-1.0.9.jar  ；然后 定义配置文件 *.properties
 */
public class JDBCUtils4 {
    //1 定义一个私有成员变量
    private static DataSource ds;  //初始化是null.
// 根据druid.properties这个配置文件，有了这个配置文件之后，根据它可以使用 druid 的工厂创建出来这个 DataSource dataSource 连接池，所以使用静态代码块来加载

    static{
        try {
            //2 加载配置文件
            Properties pro = new Properties();
            InputStream is = JDBCUtils4.class.getClassLoader().getResourceAsStream("druid.properties");
            pro.load(is);
            //3 获取数据库连接池对象
            ds = DruidDataSourceFactory.createDataSource(pro);   //注意返回值啊！大哥！

        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
    // 获取连接池对象
    public static DataSource getDataSource(){
        return ds;
    }

    // 获取连接 getConnection 对象
    public static Connection getConnection() throws SQLException {
        return ds.getConnection();
    }
}
