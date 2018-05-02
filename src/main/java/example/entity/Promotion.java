package example.entity;

/**
 * Created by AB053735 on 2018/4/17.
 */


public class Promotion
{
    private int id;
    private String PromotionName;
    private String publicName;
    private String promotionContent;
    private String startTime;
    private String endTime;
    private int applyState;   //申请状态，0是没申请，1是已经申请，可能有2，3 什么的
    private String updateTime;  //创建时间

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getPromotionName() {
        return PromotionName;
    }

    public void setPromotionName(String promotionName) {
        PromotionName = promotionName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getPublicName() { return publicName;    }

    public void setPublicName(String publicName) {
        this.publicName = publicName;
    }

    public String getPromotionContent() {
        return promotionContent;
    }

    public void setPromotionContent(String promotionContent) {
        this.promotionContent = promotionContent;
    }

    public int getApplyState() {
        return applyState;
    }

    public void setApplyState(int applyState) {
        this.applyState = applyState;
    }

    public String getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(String updateTime) {
        this.updateTime = updateTime;
    }
}
