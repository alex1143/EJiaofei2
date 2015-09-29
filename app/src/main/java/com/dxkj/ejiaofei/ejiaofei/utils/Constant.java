package com.dxkj.ejiaofei.ejiaofei.utils;

/**
 * url
 * Created by sh on 2015/9/17.
 */
public class Constant {


    /**
     * 手势密码点的状态
      */
    public static final int POINT_STATE_NORMAL = 0; // 正常状态

    public static final int POINT_STATE_SELECTED = 1; // 按下状态

    public static final int POINT_STATE_WRONG = 2; // 错误状态

    /**
     * 服务器IP地址
     */
    private static String IP = "http://172.16.1.175:8888";

    /**
     * 登陆地址
     */
    public static String LONGIN_URL = IP + "/huiyuanhoutai_V3.0/user/login.do";

    /**
     * 公告地址
     */
    public static String PUBLIC_URL = IP + "/huiyuanhoutai_V3.0/announcement/all.do";

    /**
     * 首页轮播图片地址
     */
    public static String PICTURE_URL1 = "http://img5.imgtn.bdimg.com/it/u=3580210867,3098509580&fm=21&gp=0.jpg";
    public static String PICTURE_URL2 = "http://img2.imgtn.bdimg.com/it/u=2199763593,4070991891&fm=21&gp=0.jpg";
    public static String PICTURE_URL3 = "http://img5.imgtn.bdimg.com/it/u=1483291903,4252403030&fm=21&gp=0.jpg";

    /**
     * 话费充值
     */
    public static String RECHARGE_URL = IP + "/huiyuanhoutai_V3.0/recharge/phone.do";
    /**
     * Q币充值接
     */
    public static String ORDER_URL = IP + "/huiyuanhoutai_V3.0/order/myHistoryOrders.do";
}
