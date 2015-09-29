package com.dxkj.ejiaofei.ejiaofei.bean;

import android.widget.ImageView;

import com.dxkj.ejiaofei.R;
import com.dxkj.ejiaofei.ejiaofei.utils.Constant;

/**
 * 坐标类
 * Created by dxkj on 2015/9/29.
 */
public class GesturePoint {

    /**
     * 左边x的值
     */
    private int leftX;
    /**
     * 右边x的值
     */
    private int rightX;
    /**
     * 上边y的值
     */
    private int topY;
    /**
     * 下边y的值
     */
    private int bottomY;
    /**
     * 这个点对应的ImageView控件
     */
    private ImageView image;

    /**
     * 中心x值
     */
    private int centerX;

    /**
     * 中心y值
     */
    private int centerY;

    /**
     * 点的状态值
     */
    private int pointState;

    /**
     * 代表这个Point对象代表的数字，从1开始(直接感觉从1开始)
     */
    private int num;

    public GesturePoint(int leftX, int rightX, int topY, int bottomY,
                        ImageView image, int num) {
        super();
        this.leftX = leftX;
        this.rightX = rightX;
        this.topY = topY;
        this.bottomY = bottomY;
        this.image = image;

        this.centerX = (leftX + rightX) / 2;
        this.centerY = (topY + bottomY) / 2;

        this.num = num;
    }

    public int getTopY() {
        return topY;
    }

    public int getBottomY() {
        return bottomY;
    }

    public ImageView getImage() {
        return image;
    }

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public int getPointState() {
        return pointState;
    }

    public int getNum() {
        return num;
    }

    public int getRightX() {
        return rightX;
    }

    public int getLeftX() {
        return leftX;
    }

    public void setLeftX(int leftX) {
        this.leftX = leftX;
    }

    public void setRightX(int rightX) {
        this.rightX = rightX;
    }

    public void setTopY(int topY) {
        this.topY = topY;
    }

    public void setBottomY(int bottomY) {
        this.bottomY = bottomY;
    }

    public void setImage(ImageView image) {
        this.image = image;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setPointState(int pointState) {
        pointState = pointState;
        switch (pointState) {
            case Constant.POINT_STATE_NORMAL:
                this.image.setBackgroundResource(R.drawable.gesture_node_normal);
                break;
            case Constant.POINT_STATE_SELECTED:
                this.image.setBackgroundResource(R.drawable.gesture_node_pressed);
                break;
            case Constant.POINT_STATE_WRONG:
                this.image.setBackgroundResource(R.drawable.gesture_node_wrong);
                break;
            default:
                break;
        }
    }

    public void setNum(int num) {
        this.num = num;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + bottomY;
        result = prime * result + ((image == null) ? 0 : image.hashCode());
        result = prime * result + leftX;
        result = prime * result + rightX;
        result = prime * result + topY;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        GesturePoint other = (GesturePoint) obj;
        if (bottomY != other.bottomY)
            return false;
        if (image == null) {
            if (other.image != null)
                return false;
        } else if (!image.equals(other.image))
            return false;
        if (leftX != other.leftX)
            return false;
        if (rightX != other.rightX)
            return false;
        if (topY != other.topY)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Point [leftX=" + leftX + ", rightX=" + rightX + ", topY="
                + topY + ", bottomY=" + bottomY + "]";
    }
}
