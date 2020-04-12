package cn.walking.service.impl;

import cn.walking.dao.UserDao;
import cn.walking.dao.impl.UserDaoImpl;
import cn.walking.domain.User;
import cn.walking.service.UserService;

import java.util.List;

public class UserServiceImpl implements UserService {

    private UserDao dao = new UserDaoImpl();

    @Override
    public User login(User user) {
        return dao.findUserByUsernameAndPassword(user.getUsername(),user.getPassword());
    }

    @Override
    public List<User> findAll() {
        //调用Dao完成查询
        return dao.findAll();       //注意写向 UserDaoImpl实体类 操作
    }

    @Override
    public void addUser(User user) {

    }

    @Override
    public void deleteUser(String id) {

    }

    @Override
    public User findUserById(String id) {
        return null;
    }

    @Override
    public void updateUser(User user) {

    }

    @Override
    public void delSelectedUser(String[] ids) {

    }
}
