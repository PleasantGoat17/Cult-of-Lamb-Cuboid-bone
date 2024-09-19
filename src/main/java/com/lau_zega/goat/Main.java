package com.lau_zega.goat;

import com.lau_zega.goat.frame.GoatFrame;

/**
 * 咩咩启示录-距骨骰游戏 实现功能如下:
 *  1、有良好的UI界面，用户体验良好
 *  2、鼠标点击进行出下骰子，玩家两人轮流下
 *  3、能够判断是否格子满及输赢
 *  4、玩家能够重新开始游戏
 *  5、有退出游戏的功能，并有用户手误操作的退出提示
 */

// 主函数入口
public class Main {
    public static void main(String[] args) {
        // 初始化一个距骨骰界面对象
       GoatFrame gf = new GoatFrame();
       gf.initUI();
    }
}
