# Lab 1 at OOP

## Puzzle 15

<img src="media/1.png">

This a terminal style game of famous Puzzle 15.

<img src="media/2.png">


## What is done:

- Menu
- Input handling
- Visual way to observe game progress
- Clear design
- Canvas resizing
- Constraints to not get out of bounds
- Random puzzle generator
- Player position color highlight
- Colorful output for the right cells
- Restart feature
- Do/undo feature
- Error, instruction messages 
- Implemented file saving and loading from file

## TO DO

- Algorithm that will solve the game
- Settings option in the Menu

## Known bugs

- The input can get stuck sometimes, due to `getchar()`.

> Fixed it by implementing inputCatch(), a small function that catches the new lines in a while.

## Building

~~~~
make
~~~~

## Launching

~~~~
make run
~~~~


