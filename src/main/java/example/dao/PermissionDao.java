package example.dao;

import example.entity.Permission;
import example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


@Mapper
public interface  PermissionDao {

    List<Permission> getPermissionByUserId(@Param("userId")int userId);

}
