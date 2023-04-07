package mapper;

import java.util.List;
import model.Coupon;
import model.MenuItem;
import org.apache.ibatis.annotations.*;

public interface CouponMapper {
    int deleteByPrimaryKey(Integer couponId);

    int insert(Coupon row);

    Coupon selectByPrimaryKey(Integer couponId);

    List<Coupon> selectAll();

    List<MenuItem> selectItemInfoByResId(int resId);

    int updateByPrimaryKey(Coupon row);

    @Insert("insert into Coupon_R values(#{couponId},#{resId})")
    int insertCouponR(@Param("couponId")int couponId, @Param(("resId"))int resId);

    @Insert("insert into Coupon_Item values(#{couponId},#{itemId})")
    int insertCouponItem(@Param("couponId")int couponId, @Param(("itemId"))int itemId);

    @Update("update Coupon_R set Restaurant_ID=#{resId} where Coupon_ID=#{couponId}")
    int updateCouponR(@Param("couponId")int couponId, @Param(("resId"))int resId);

    @Update("update Coupon_Item set Item_ID=#{itemId} where Coupon_ID=#{couponId}")
    int updateCouponItem(@Param("couponId")int couponId, @Param(("itemId"))int itemId);

    @Delete("delete from Coupon_Item where Coupon_ID=#{couponId}")
    int deleteCouponItem(int couponId);

    @Delete("delete from Coupon_R where Coupon_ID=#{couponId}")
    int deleteCouponR(int couponId);

    String selectOneResById(int couponId);
}