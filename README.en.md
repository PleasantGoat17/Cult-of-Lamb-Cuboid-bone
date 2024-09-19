# Goat Apocalypse - Astragalus Dice

This is a Java Swing-based desktop game simulating a two-player battle called "Goat Apocalypse - Astragalus Dice". Players take turns playing as either the Blue or Red faction, rolling dice and competing for points to determine the winner.

## Game Rules

- The game features two factions: Blue and Red. The Blue faction rolls the dice first.
- Players roll dice to get points and place the corresponding dice on the board.
- The board consists of three columns, each of which can hold up to three dice.
- If the current player's dice match the enemy's dice in value, the enemy's dice are eliminated.
- Different colored dice (regular white, double green, and triple blue) offer different score multipliers.
- The game ends when one side meets the winning condition or the board is full. The winner is determined by total points.

## Directory Structure

```bash
src/
└── com/
    └── lau_zega/
        └── goat/
            └── frame/
                └── GoatFrame.java       # Main game logic and UI

images/
└── background/
    └── jgtbg.png                       # Blue faction background
    └── jgtbg-red.png                   # Red faction background
    └── start.png                       # Start screen image

└── options/
    └── option_throw_dice.png           # Dice throw button image
    └── option_restart.png              # Restart button image

└── dices/
    └── white/
        └── dice_1.png                  # White dice images
        └── ...

    └── green/
        └── dice_1.png                  # Green dice images
        └── ...

    └── blue/
        └── dice_1.png                  # Blue dice images
        └── ...
```

## How to Run

1. Make sure you have JDK 1.8 or higher installed.
2. Run `GoatFrame.java` using an IDE or the command line.
3. Once the game interface launches, click the "Start Game" button to begin the match.

## Gameplay Instructions

- The Blue player starts by rolling the dice and placing it on the board by clicking on one of the columns.
- Click the "Roll Dice" button to generate a random dice value, then choose a column to place the dice.
- If the enemy has dice with the same value in the same column, you can eliminate the enemy’s dice.
- Click the "Restart Game" button to reset the game.

## Developer

- **Author**: Lau Zega / PleasantGoat17

## Disclaimer

This software includes artwork from the game Cult of the Lamb. This is a non-profit project, and all rights to the artwork belong to Cult of the Lamb and its rightful owners. The use of this material is solely for personal and non-commercial purposes. Any unauthorized reproduction, distribution, or commercial use may infringe on copyright. If you have any questions or concerns regarding copyright, please contact us.

## License

This project has no specified license. Please contact the author for more details.