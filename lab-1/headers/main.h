#ifndef HEADER_FILE
#define HEADER_FILE

// initial includes
#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

// data type that responds for data in cells
typedef struct struct_cellData
{
	int 	x;
	int 	y;
	int 	value;
	bool 	isDoubleDigit;
} cellData;

// stack for do/undo feature in the game
typedef struct struct_stackData
{
	void                	*value;
	struct struct_stackData	*next;
} stackData;

// push element to the top
void stackPush(stackData **data, int maxCount, void *stackValue);

// pop element from stack
void *stackPop(stackData *data);

// print the content of stack
void stackPrint(stackData *data);

// get the size of stack
int getStackSize(stackData *data);

// initialize stack
stackData *initStack(void *data);

// draw the game in terminal
void drawGame(cellData *data, int canvasSize, int canvasDivisions);

// print the main menu options
void printMainMenu(void);

// show control options
void showControls(void);

// little modification to getchar(), to catch '\n'
char inputCatch();

// apply color to an output
void applyColor(const char *style);

// clear the screen
void clearScreen();

// set the default cell values (a.k.a. the solved grid)
void initCellData(cellData *data, int canvasDivisions);

// swap the values of two cells by their indexes
void swapValues(cellData *data, int nextCell, int currentCell);

// move the cell based on user input
void moveCells(char key, cellData *data, int canvasDivisions);

// generate a random puzzle, based on simulating random user input
void generateRandomPuzzle(cellData *data, int canvasDivisions);

// save game data
void gameSave(cellData *data, stackData *doStatus, stackData *redoStatus, int canvasDivisions);

// load game data
int loadSave(cellData *data, stackData *doStatus, stackData *redoStatus, int canvasDivisions);

#endif