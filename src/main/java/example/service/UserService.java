package example.service;

import example.dao.UserDao;
import example.entity.User;
import example.entity.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserDao userDao;

    public List<User> listAll() {
        List<User> returnUser=new ArrayList<User>();
        returnUser=userDao.selectAll();

        return returnUser;
    }

    public User findUser(int userId)
    {
        User returnUser = new User();
        returnUser = userDao.findUser(userId);
        return returnUser;
    }

    public void  addUser(User user)
    {
        //添加请求
         userDao.addUser(user);
    }

    public int getCount()
    {   return userDao.getCount();   }

    public List<User> getUserByPage(int startRow, int pageSize)
    {
        return userDao.getUserByPage(startRow,pageSize);
    }

    public List<UserVo> getUserVoByPage(@Param("startRow")int startRow, @Param("pageSize")int pageSize)
    {
        return userDao.getUserVoByPage(startRow,pageSize);
    }

    //通过用户名字查用户
    public User selectUserByName(String name, String pass)
    {
        return userDao.selectUserByName(name,pass);
    }

}






