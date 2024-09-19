  # 咩咩启示录 - 距骨骰

  这是一个基于 Java Swing 的桌面游戏应用，模拟了一场名为「咩咩启示录 - 距骨骰」的两方对战游戏，玩家分别扮演蓝方和红方，通过掷骰子进行游戏，最终比拼得分来决定胜负。

  [Click here read English version](README.en.md)

  ## 游戏规则

  - 游戏分为红方和蓝方两个阵营，初始状态下，蓝方先掷骰。
  - 双方通过掷骰获得点数，并在棋盘上放置相应的骰子。
  - 每个棋盘分为三列，每列最多容纳三个骰子。
  - 如果当前方放置的骰子与敌方相同点数，则可以消除敌方相应位置的骰子。
  - 通过不同颜色的骰子（普通白色、双倍绿色、三倍蓝色）来获得不同的加成效果。
  - 当一方的得分达到某个条件或棋盘满时，游戏结束，计算得分并决定胜负。

  ## 文件结构


    src/
    └── com/
        └── lau_zega/
            └── goat/
                └── frame/
                    └── GoatFrame.java       # 主游戏逻辑和UI

    images/
    └── background/
        └── jgtbg.png                       # 蓝方背景图
        └── jgtbg-red.png                   # 红方背景图
        └── start.png                       # 开始界面图片

    └── options/
        └── option_throw_dice.png           # 掷骰按钮图片
        └── option_restart.png              # 重开按钮图片

    └── dices/
        └── white/
            └── dice_1.png                  # 白色骰子图片
            └── ...

        └── green/
            └── dice_1.png                  # 绿色骰子图片
            └── ...

        └── blue/
            └── dice_1.png                  # 蓝色骰子图片
            └── ...


  ## 如何运行

  1. 确保你已经安装了 JDK 1.8 或更高版本。
  2. 使用 IDE 或命令行运行 `GoatFrame.java`。
  3. 游戏界面启动后，点击 “开始游戏” 按钮开始对战。

  ## 操作指南

  - 游戏开始后，蓝方玩家先掷骰，并通过点击棋盘来放置骰子。
  - 点击“掷骰”按钮获得随机骰子点数，然后选择棋盘列进行放置。
  - 如果敌方有相同点数的骰子，则可以消除该骰子。
  - 点击“重开游戏”按钮可重置游戏状态。


  ## 开发者

  - **作者**: Lau Zega / PleasantGoat17

  ## 游戏界面截图

  ![picture_1](https://img.picui.cn/free/2024/09/19/66ebdc7da1cb9.png)
  ![picture_2](https://img.picui.cn/free/2024/09/19/66ebdb8f3f978.png)
  ![picture_3](https://img.picui.cn/free/2024/09/19/66ebdbe5bcd62.png) 
  ![picture_4](https://img.picui.cn/free/2024/09/19/66ebdc7e4d7e5.png)   
  

  ## 免责声明

  本软件中使用了部分来自《咩咩启示录》游戏的艺术作品。本软件为非盈利项目，所有相关艺术作品及其著作权均归《咩咩启示录》及其版权所有者所有。本软件的使用仅为个人和非商业用途，任何未经授权的复制、分发或商业使用均可能侵犯版权。如有任何疑问或版权相关问题，请与我们联系。