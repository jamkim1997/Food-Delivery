package service.impl;

import mapper.CouponMapper;
import model.Coupon;
import model.MenuItem;
import model.Restaurant;
import service.CouponService;
import utils.SqlSessionUtil;

import java.util.List;

public class CouponServiceImpl implements CouponService{
    @Override
    public List<Restaurant> resInfo() {
        return SqlSessionUtil.openSqlSession().selectList("selectResInfo");
    }

    @Override
    public List<MenuItem> itemInfo(int resId) { return SqlSessionUtil.openSqlSession().getMapper(CouponMapper.class).selectItemInfoByResId(resId); }

    @Override
    public List<Coupon> getCoupons() { return SqlSessionUtil.openSqlSession().getMapper(CouponMapper.class).selectAll(); }

    @Override
    public Boolean addCoupon(String name, String scope, String resId, String itemId, String minMoney, String value, String description, String image) {
        int intScope = 0;
        if (scope.equals("Specific store"))
            intScope = 1;
        else if (scope.equals("Specific items in specific store"))
            intScope = 2;
        Coupon coupon = new Coupon(name, intScope, Double.valueOf(minMoney), Double.valueOf(value), description, image);
        try{
            CouponMapper cMapper = SqlSessionUtil.openSqlSession().getMapper(CouponMapper.class);
            cMapper.insert(coupon);
            int couponId = coupon.getCouponId();
            switch (intScope)
            {
                case 1:
                    String[] resIds = resId.split(",");
                    for (int i = 0; i < resIds.length; i++)
                        cMapper.insertCouponR(couponId, Integer.parseInt(resIds[i]));
                    break;
                case 2:
                    cMapper.insertCouponR(couponId, Integer.parseInt(resId));
                    String[] items = itemId.split(",");
                    for (int i = 0; i < items.length; i++)
                        cMapper.insertCouponItem(couponId, Integer.parseInt(items[i]));
                    break;
                default:
                    break;
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean updateCoupon(String id, String name, String scope, String resId, String itemId, String minMoney, String value, String description, String image) {
        int intScope = 0;
        if (scope.equals("Specific store"))
            intScope = 1;
        else if (scope.equals("Specific items in specific store"))
            intScope = 2;
        Integer couponId = Integer.valueOf(id);
        Coupon coupon = new Coupon(couponId, name, intScope, Double.valueOf(minMoney), Double.valueOf(value), description, image);
        try{
            CouponMapper cMapper = SqlSessionUtil.openSqlSession().getMapper(CouponMapper.class);
            cMapper.updateByPrimaryKey(coupon);
            switch (intScope)
            {
                case 0:
                    cMapper.deleteCouponR(couponId);
                    cMapper.deleteCouponItem(couponId);
                    break;
                case 1:
                    String[] resIds = resId.split(",");
                    cMapper.deleteCouponR(couponId);
                    for (int i = 0; i < resIds.length; i++)
                        cMapper.insertCouponR(couponId, Integer.parseInt(resIds[i]));
                    break;
                case 2:
                    cMapper.updateCouponR(couponId, Integer.parseInt(resId));
                    cMapper.deleteCouponItem(couponId);
                    String[] items = itemId.split(",");
                    for (int i = 0; i < items.length; i++)
                        cMapper.insertCouponItem(couponId, Integer.parseInt(items[i]));
                    break;
                default:
                    break;
            }
            return true;
        } catch (Exception e){
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Boolean deleteCoupons(String ids) {
        int rowCount = 0;
        String[] allId = ids.split(",");
        for (int i = 0; i < allId.length; i++)
            rowCount += SqlSessionUtil.openSqlSession().getMapper(CouponMapper.class).deleteByPrimaryKey(Integer.valueOf(allId[i]));
        return rowCount > 0 ? true : false;
    }

    @Override
    public String getARes(String couponId) {
        return SqlSessionUtil.openSqlSession().getMapper(CouponMapper.class).selectOneResById(Integer.parseInt(couponId));
    }
}
