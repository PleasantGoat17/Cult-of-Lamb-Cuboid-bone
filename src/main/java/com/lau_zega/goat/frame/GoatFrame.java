package com.lau_zega.goat.frame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;

import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class GoatFrame extends JFrame implements MouseListener {
    //获取屏幕的宽度和高度
    final int screen_width = Toolkit.getDefaultToolkit().getScreenSize().width;
    final int screen_height = Toolkit.getDefaultToolkit().getScreenSize().height;

    //窗体大小

    final int frame_width = 1306;
    final int frame_height = 768;

    Image game_background_blue = null;
    Image game_background_red = null;

    Image option_throw = null;
    Image option_restart = null;

    Image start_menu = null;



    // 保存棋子的坐标
    int x = 0;
    int y = 0;


    /**
     * 保存骰子点数和状态
     */

    final int[][] BlueDicePoint = new int [3][3];
    final int[][] RedDicePoint = new int [3][3];

    final int[][] BlueDiceStatus = new int [3][3];
    final int[][] RedDiceStatus = new int [3][3];

    // 记录双方分数
    int RedScore = 0;
    int BlueScore = 0;

    int point_temp = 0;

    // 下一步要下的是否为蓝方

    boolean isBlue = true;
    boolean isGetDice = false;

    /**
     *  游戏状态 默认0
     *   [0：未开始]
     *   [1： 已开始]
     *   [2： 游戏结束]
     */
    int game_status = 0;

    // 提示信息

    String message;



    public void initUI() {
        readImages();//读取图片资源
        setTitle("咩咩启示录-距骨骰"); //设置标题
        setIconImage(new ImageIcon("images/icon/9.png").getImage());//设置

        setSize(frame_width, frame_height);

        setLocation((screen_width - frame_width) / 2, (screen_height - frame_height) / 2); //设置窗体出现位置

        setResizable(false);  //设置窗体大小不可改变
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//设置窗体关闭方式为关闭直接退出程序
        addMouseListener(this); //为窗体添加监听器
        //将窗体显示出来
        this.setVisible(true);
    }

    //重写重绘方法,这里重写的是第一个大的JPanel的方法
    public void paint(Graphics g) {

        // 双缓冲技术防止屏幕闪烁
        BufferedImage bi = new BufferedImage(frame_width, frame_height,BufferedImage.TYPE_INT_ARGB);
        Graphics g2 = bi.createGraphics();

        if (game_status == 0) {
            g2.drawImage(start_menu,3,26,this);
        } else {
            if(isBlue)
            {
                g2.drawImage(game_background_blue, 3 , 26 ,this);
            }
            else {
                g2.drawImage(game_background_red, 3 , 26 ,this);
            }
            g2.drawImage(option_throw,1010,520,this);
            g2.drawImage(option_restart,1010,610,this);

        // 绘制红方骰子
        int piece_x,piece_y;
        for (int i = 0; i < 3; i++) {
            piece_x = i * 122 + 498;
            for (int j = 0; j < 3; j++) {
                piece_y = 231 - j * 78;
                if (RedDicePoint[i][j] != 0) {
                    drawDices(g2, RedDicePoint[i][j], piece_x, piece_y, RedDiceStatus[i][j]);
                }
            }
        }
        // 绘制蓝方骰子
        for (int i = 0; i < 3; i++) {
            piece_x = i * 120 + 500;
            for (int j = 0; j < 3; j++) {
                piece_y = j * 78 + 483;
                if (BlueDicePoint[i][j] != 0) {
                    drawDices(g2, BlueDicePoint[i][j], piece_x, piece_y, BlueDiceStatus[i][j]);
                }
            }
        }
        if(isGetDice) {
            if (!isBlue) {
                drawDices(g2, point_temp, 1083, 132, 1);
            } else {
                drawDices(g2, point_temp, 158, 575, 1);
            }
        }

        // 游戏提示信息
        g2.setFont(new Font("黑体",Font.PLAIN,40));//设置字体
        g2.setColor(Color.white);//设置红色
        g2.drawString(message,569,393);

        g2.setFont(new Font("黑体",Font.PLAIN,25));
        if(BlueScore!=0)
        {
            g2.drawString(Integer.toString(BlueScore),184,513);
        }
        if(RedScore!=0){
            g2.drawString(Integer.toString(RedScore),1113,513);
        }
        }
        g.drawImage(bi,0,0,this);
    }




    public void mouseClicked(MouseEvent e) {

    }



    public void mousePressed(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        // 退出游戏
        if (x >= 140 && x <= 220 && y >= 705 && y <= 740)
        {
            int result = JOptionPane.showConfirmDialog(this,"是否退出游戏");
            if (result == 0) {
                System.exit(0);
            }
            else
            {
                System.out.println("鼠标点击的坐标为[x:"+x+",y:"+y+"]，无操作。");
            }
        }
        if(game_status == 0)
        {
            // 开始游戏和重新开始
            if (x >= 555 && x <= 755 && y >= 545 && y <= 595) {
            message = "圣羊先掷骰";
            isBlue = true;
            game_status = 1;
            System.out.println("开始游戏");
            this.repaint();
            }
            else {
                System.out.println("鼠标点击的坐标为[x:"+x+",y:"+y+"]，无操作。");
            }
        }
        // 已经开始后
        else if (game_status == 1)
        {
            if (x >= 483 && x <= 828 && y >= 74 && y <= 710) {
                int i = (x - 483) / 113;
                if (i < 3 && isGetDice) {
                    if (isBlue) {
                        if(isEmptyRow(BlueDicePoint[i]))
                        {
                            int j = setDice(BlueDicePoint[i]);
                            BlueDicePoint[i][j] = point_temp;
                            BlueDiceStatus[i][j] = SameRowStatus(BlueDicePoint[i],point_temp,j);
                            SameRowKillEnemies(RedDicePoint[i],RedDiceStatus[i],point_temp);

                            for(int k = 0;k<3;k++)
                            {
                                if(k!=j)
                                {
                                    BlueDiceStatus[i][k] = SameRowStatus(BlueDicePoint[i],BlueDicePoint[i][k],k);
                                }
                            }

                            reCountPoints(BlueDicePoint,BlueDiceStatus,isBlue);
                            message = "拉陶掷骰。";
//                            CheckAlleys_test();
                            checkWin();
                            isGetDice = !isGetDice;
                            point_temp = 0;
                            isBlue = !isBlue;
                        }
                        else {
                            System.out.println("此列已满。");
                        }

                    }
                    else {
                        if(isEmptyRow(RedDicePoint[i]))
                        {
                            int j = setDice(RedDicePoint[i]);
                            RedDicePoint[i][j] = point_temp;
                            RedDiceStatus[i][j] = SameRowStatus(RedDicePoint[i],point_temp,j);
                            SameRowKillEnemies(BlueDicePoint[i],BlueDiceStatus[i],point_temp);

                            for(int k = 0;k<3;k++)
                            {
                                if(k!=j)
                                {
                                    RedDiceStatus[i][k] = SameRowStatus(RedDicePoint[i],RedDicePoint[i][k],k);
                                }
                            }
                            reCountPoints(RedDicePoint,RedDiceStatus,isBlue);
                            message = "圣羊掷骰。";
//                            CheckAlleys_test();
                            checkWin();
                            isGetDice = !isGetDice;
                            point_temp = 0;
                            isBlue = !isBlue;
                        }
                        else {
                            System.out.println("此列已满。");
                        }

                    }
                    // 重新执行paint（）方法
                    this.repaint();
                    // 判断游戏是否结束(是否有一方骰子已满)
                }
                System.out.println("鼠标点击的坐标为[x:"+x+",y:"+y+"],对应位置为[第: "+i+" 列]");
            }
            else if(x >= 1013 && x <= 1225 && y >= 616 && y <= 664)
            {
                int result = JOptionPane.showConfirmDialog(this,"是否重新开始游戏？");
                /**
                 *  重新开始游戏
                 *  1、"棋盘"数据清空
                 *  2、修改游戏状态
                 *  3、游戏提示信息修改为初始状态
                 *  4、下一步棋改为蓝方
                 */
                if (result == 0) {
                    for (int i = 0; i < 3; i++) {
                        Arrays.fill(BlueDicePoint[i], 0);
                    }
                    for (int i = 0; i < 3; i++) {
                        Arrays.fill(RedDicePoint[i], 0);
                    }
                    message = "圣羊先掷骰";
                    isBlue = true;
                    isGetDice = false;
                    BlueScore = RedScore = 0;
                    game_status = 1;
                    System.out.println("重新开始！");
                    this.repaint();
                }
                else {
                    System.out.println("鼠标点击的坐标为[x:"+x+",y:"+y+"]，无操作。");
                }

            }
            else if(x >= 1013 && x <= 1225 && y >= 525 && y <= 576)
            {
                if(isGetDice)
                {
                    message = "请勿重复掷骰";
                    System.out.println("重复掷骰");
                }
                else {
                    Random random = new Random();
                    point_temp = random.nextInt(6) + 1;
                    System.out.println("掷出骰子，点数为："+point_temp+"。");
                    isGetDice = !isGetDice;
                    this.repaint();
                }
            }
            else {
                System.out.println("鼠标点击的坐标为[x:"+x+",y:"+y+"]，无操作。");
            }
        }
        else if (game_status == 2)
        {
            if(x >= 1013 && x <= 1225 && y >= 616 && y <= 664)
            {
                int result = JOptionPane.showConfirmDialog(this,"是否重新开始游戏？");
                /**
                 *  重新开始游戏
                 *  1、"棋盘"数据清空
                 *  2、修改游戏状态
                 *  3、游戏提示信息修改为初始状态
                 *  4、下一步改为蓝方
                 */
                if (result == 0) {
                    for (int i = 0; i < 3; i++) {
                        Arrays.fill(BlueDicePoint[i], 0);
                    }
                    for (int i = 0; i < 3; i++) {
                        Arrays.fill(RedDicePoint[i], 0);
                    }
                    message = "圣羊先掷骰";
                    isBlue = true;
                    BlueScore = RedScore = 0;
                    game_status = 1;
                    System.out.println("重新开始！");
                    this.repaint();
                }
                else
                {
                    System.out.println("鼠标点击的坐标为[x:"+x+",y:"+y+"]，无操作。");
                }
            }
        }
        else {
                System.out.println("鼠标点击的坐标为[x:"+x+",y:"+y+"]，无操作。");
        }
    }

    public void mouseReleased(MouseEvent e) {

    }

    public void mouseEntered(MouseEvent e) {

    }

    public void mouseExited(MouseEvent e) {


    }

    public void checkWin() {
        int count = 0;
        for(int i=0;i<3;i++){
            if(isBlue) {
                if(!isEmptyRow(BlueDicePoint[i]))count++;
            }
            else {
                if(!isEmptyRow(RedDicePoint[i]))count++;
            }
        }
        if(count == 3)
        {
            if(BlueScore>RedScore)
            {
                System.out.println("蓝方胜利");
            }
            else
            {
                System.out.println("红方胜利");
            }
            if(BlueScore>RedScore)
            {
                message = "圣羊胜，";
            }
            else if(BlueScore == RedScore)
            {
                message+= "平局，";
            }
            else
            {
                message = "拉陶胜，";
            }
            message += BlueScore + "-" + RedScore;
            game_status = 2;
        }
    }


    public void readImages() {
        try {
            game_background_blue = ImageIO.read(new File("images/background/jgtbg.png"));
            game_background_red = ImageIO.read(new File("images/background/jgtbg-red.png"));
            start_menu = ImageIO.read(new File("images/background/start.png"));
            option_throw = ImageIO.read(new File("images/options/option_throw_dice.png"));
            option_restart = ImageIO.read(new File("images/options/option_restart.png"));
        } catch (
                IOException e) {
            e.printStackTrace();
        }
    }

    public void drawDices(Graphics g, int point, int x, int y,int status)
    {
        Image dice = null;
        String path = "images/dices";
        if(status<1||status>3||point<1||point>6)return;
        path = DiceStatus(status,path) + point + ".png";
        try{
            dice = ImageIO.read(new File(path));
        } catch (IOException e)
        {
            e.printStackTrace();
        }
        g.drawImage(dice,x,y,this);
    }

    public String DiceStatus(int status,String path)
    {
        switch (status)
        {
            case 1: path = path + "/white/dice_";break; // 普通白色骰子
            case 2: path = path + "/green/dice_";break; // 双倍绿色骰子
            case 3: path = path + "/blue/dice_";break; // 三倍蓝色骰子
        }
        return path;
    }

    public boolean isEmptyRow(int[] dice)
    {
        for(int i = 0;i<3;i++)
        {
            if(dice[i]==0)
            {
                return true;
            }
        }
        return false;
    }

    public void SameRowKillEnemies(int[] dice,int[] status,int point)
    {
        boolean iskill = false;
        for(int i = 0;i<3;i++)
        {
            if(dice[i] == point)
            {
                dice[i] = 0;
                status[i] = 0;
                iskill = true;
            }
        }
        if(iskill) {
            if (isBlue) {
                System.out.println("杀掉了红方点数为" + point + "的骰子。");
            } else {
                System.out.println("杀掉了蓝方点数为" + point + "的骰子。");
            }
        }
        this.repaint();
    }

    public int SameRowStatus(int[] dice,int point,int column)
    {
        int status = 1;
        for(int i =0;i<3;i++)
        {
            if(dice[i] == point && i != column)
            {
                status++;
            }
        }
        return status;
    }

    public int setDice(int[] dice)
    {
        for(int i = 0;i<3;i++)
        {
            if(dice[i] == 0)
            {
                return i;
            }
        }
        return -1;
    }

//    public void CheckAlleys_test()
//    {
//        System.out.println("红色：");
//        for(int i = 0;i<3;i++)
//        {
//            for(int j = 0;j<3;j++)
//            {
//                System.out.print(RedDicePoint[j][i]+" ");
//            }
//            System.out.println();
//        }
//        System.out.println("蓝色：");
//        for(int i = 0;i<3;i++)
//        {
//            for(int j = 0;j<3;j++)
//            {
//                System.out.print(BlueDicePoint[j][i]+" ");
//            }
//            System.out.println();
//        }
//    }

    public void reCountPoints(int[][] dice,int[][] status,boolean color)
    {
        int tempScore=0;

        for(int i=0;i<3;i++)
        {
            for(int j=0;j<3;j++)
            {
                tempScore += dice[i][j]*status[i][j];
            }
        }
        if(color) {
            BlueScore = tempScore;
        } else {
            RedScore = tempScore;
        }
    }
}
