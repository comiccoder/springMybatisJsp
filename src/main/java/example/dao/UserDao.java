package example.dao;

import example.entity.User;
import example.entity.UserVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface UserDao
{
    //选择出所有用户
    List<User> selectAll();
    User findUser(int userId);      //--- 用户的id
    void addUser(User user);        //添加用户
    int getCount();

    //查找用户是否存在
    User selectUserByName(@Param("userName")String name, @Param("pass")String pass);

    List<User> getUserByPage(@Param("startRow")int startRow, @Param("pageSize")int pageSize);

    //Vo分页
    List<UserVo> getUserVoByPage(@Param("startRow")int startRow, @Param("pageSize")int pageSize);

}






