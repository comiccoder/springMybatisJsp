package example.service;

import example.dao.PermissionDao;
import example.dao.UserDao;
import example.entity.Permission;
import example.entity.User;
import example.entity.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PermissionService {

    @Autowired
    private PermissionDao permissionDao;

    public List<Permission> getPermissionByUserId(int userId)
    {
       return permissionDao.getPermissionByUserId(userId);
    }
}






