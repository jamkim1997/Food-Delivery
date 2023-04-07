package service;

import model.Coupon;
import model.MenuItem;
import model.Restaurant;

import java.util.List;

public interface CouponService {
    List<Restaurant> resInfo();
    List<MenuItem> itemInfo(int resId);

    List<Coupon> getCoupons();
    Boolean addCoupon(String name, String scope, String resId, String itemId, String minMoney, String value, String description, String image);

    Boolean updateCoupon(String id, String name, String scope, String resId, String itemId, String minMoney, String value, String description, String image);
    Boolean deleteCoupons(String ids);

    String getARes(String couponId);
}
