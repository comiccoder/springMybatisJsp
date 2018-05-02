package example.service;

import example.dao.PromotionDao;
import example.dao.UserDao;
import example.entity.Promotion;
import example.entity.PromotionVo;
import example.entity.User;
import example.entity.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by AB053735 on 2018/4/17.
 */

@Service
public class PromotionService
{

        @Autowired
        private PromotionDao promotionDao;

        public int getCount()
        {   return promotionDao.getCount();   }

        public List<Promotion> getPromotionByPage(int startRow, int pageSize)
        {
            return promotionDao.getPromotionByPage(startRow,pageSize);
        }

        public List<PromotionVo> getPromotionVoByPage(
                int startRow,
                int pageSize,
                int userId)
        {
            return promotionDao.getPromotionVoByPage(startRow,pageSize,userId);
        }

        public void addPromotion(Promotion promotion)
        {
            promotionDao.addPromotion(promotion);
        }

        public void deletePromotionById(int int_id)
        {
            promotionDao.deletePromotionById(int_id);
        }

        public void updatePromotion(Promotion promotion)
        {
            promotionDao.updatePromotion(promotion);
        }

        public Promotion getPromotionById(int promotionId)
        {
            return promotionDao.getPromotionById(promotionId);
        }

    }






