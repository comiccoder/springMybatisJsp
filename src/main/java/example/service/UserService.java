package example.service;

import example.dao.UserDao;
import example.domain.User;
import example.domain.UserMapper;
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
}