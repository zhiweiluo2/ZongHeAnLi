package cn.walking.dao.impl;

import cn.walking.dao.UserDao;
import cn.walking.domain.User;
import cn.walking.util.JDBCUtils4;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Map;

public class UserDaoImpl implements UserDao {
    private JdbcTemplate template =  new JdbcTemplate(JDBCUtils4.getDataSource());

    @Override
    public User findUserByUsernameAndPassword(String username, String password) {
        try {
            String sql = "select * from user where username = ? and password = ?";
            User user = template.queryForObject(sql, new BeanPropertyRowMapper<User>(User.class), username, password);
            return user;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> findAll() {
        //使用JDBC操作数据库...
        //1.定义sql
        String sql = "SELECT * FROM USER";
        List<User> users = template.query(sql, new BeanPropertyRowMapper<User>(User.class));

        return users;
    }


    @Override
    public void add(User user) {

    }

    @Override
    public void delete(int id) {

    }

    @Override
    public User findById(int i) {
        return null;
    }

    @Override
    public void update(User user) {

    }

    @Override
    public int findTotalCount(Map<String, String[]> condition) {
        return 0;
    }

    @Override
    public List<User> findByPage(int start, int rows, Map<String, String[]> condition) {
        return null;
    }
}
