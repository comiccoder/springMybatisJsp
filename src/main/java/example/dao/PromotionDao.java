package example.dao;

import example.entity.Permission;
import example.entity.Promotion;
import example.entity.PromotionVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import java.util.List;

/**
 *******
 */
@Mapper
public interface  PromotionDao {

    //选择出所有用户
    List<Promotion> selectAll();
    int getCount();

    //查找用户是否存在
    Promotion selectUserByName(@Param("userName")String name, @Param("pass")String pass);

    List<Promotion> getPromotionByPage(@Param("startRow")int startRow, @Param("pageSize")int pageSize);

    //Vo分页；1/用来显示当前用户是否做了申请
    List<PromotionVo> getPromotionVoByPage(
            @Param("startRow")int startRow,
            @Param("pageSize")int pageSize,
            @Param("userId") int userId
            );

    int addPromotion(Promotion promotion);
    void deletePromotionById(@Param("promotionId")int promotionId);
    void updatePromotion(Promotion promotion);

    Promotion getPromotionById(@Param("promotionId") int promotionId);
}