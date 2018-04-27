package indi.welljun.dao;

import indi.welljun.domain.User;

public interface UserDao {
    void addUser(User c); //添加新用户
    User findUser(String uname, String password); //按照用户名和密码返回用户
    boolean findUser(String uname); //按照用户名判断用户是否存在
    User findUser(int uid); //按照用户id返回用户信息
    int getUserId(String uname); //根据用户姓名返回用户id
}
