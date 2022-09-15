#include <stdio.h>
#include <string.h>
#include <stdbool.h>
#include <stdlib.h>

typedef struct cellData
{
	int x;
	int y;
	int value;
	bool isDoubleDigit;
} cellData;

void initCellData(cellData *data)
{
	for (int i = 1; i < 16; i++)
	{
		data[i].x = i % 4;
		data[i].y = i / 4;
		data[i].value = i;
		if (data[i].value > 9)
		{
			data[i].isDoubleDigit = true;
		}
		else
		{
			data[i].isDoubleDigit = false;
		}
			
	}
	data[15].value = 0;
}

void clearScreen()
{
	system("clear");
}

void applyColor(const char *style)
{
	if (strcmp(style, "red") == 0)
	{
		printf("\033[0;31m");
	}
	else if (strcmp(style, "green") == 0)
	{
		printf("\033[0;32m");
	}
	else if (strcmp(style, "blue") == 0)
	{
		printf("\033[0;34m");
	}
	else if (strcmp(style, "bgRed") == 0)
	{
		printf("\033[0;41m");
	}
	else if (strcmp(style, "bgGreen") == 0)
	{
		printf("\033[0;42m");
	}
	else if (strcmp(style, "bgBlue") == 0)
	{
		printf("\033[0;44m");
	}
	else if (strcmp(style, "regular") == 0)
	{
		printf("\033[0m");
	}
	else if (strcmp(style, "bold") == 0)
	{
		printf("\033[1;31m");
	}
}

void printMainMenu()
{
	printf("Greetings to my 15 Puzzle game variation\n");
	printf("This project is done by Dumitru Moraru, FAF-212\n");
	printf("It is made for the first laboratory work on OOP ^-^\n");
	printf("Have fun\n\n");
	printf("1 - Start game\n");
	printf("2 - Load game from file\n");
	printf("3 - Exit game\n\n");
}

void drawGame(cellData *data, int canvasSize, int canvasDivisions)
{
	int 	cellIndex = 0; // current cell we are
	int 	horPixGap = canvasSize * 2 / canvasDivisions; // pixels between celss horrizontally
	int 	verPixGap = canvasSize / canvasDivisions; // pixels between celss vertically
	bool 	wasPrinted = false; // variable to keep track, if we need to print another digit
	bool 	needsHighlight = false; // variable to keep track, if we need to go back to regular highlight

	// we multiply back due to rounding errors
	for (int i = 0; i < verPixGap * canvasDivisions + 1; i++)
	{
		for (int j = 0; j < horPixGap * canvasDivisions + 1; j++)
		{
			if (i == 0 								// top line
			|| i == verPixGap * canvasDivisions + 1 // bottom line
			|| j == 0 								// left line
			|| j == horPixGap * canvasDivisions + 1 // right line
			|| j % horPixGap == 0 					// vertical cell divisions
			|| i % verPixGap == 0) 					// horizontal cell divisions
			{
				printf("*");
			}
			// search the center of a cell
			else if (((j % horPixGap == horPixGap / 2) || (j % horPixGap == horPixGap / 2 - 1)) // center horizontally
			&& (i % verPixGap == verPixGap / 2)) // center vertically
			{
				// check if the cell is in the right place for green highlight
				if (cellIndex == data[cellIndex].value)
				{
					needsHighlight = true;
				}

				// check if the digit was printed already
				if (wasPrinted == false)
				{
					if (data[cellIndex].isDoubleDigit)
					{
						// check if the cell is in the right place for green highlight
						if (needsHighlight == true)
						{
							applyColor("green");
							// applyColor("bold");
						}
						printf("%d", data[cellIndex].value / 10);
						// if needs highlight, then get back to regular style
						if (needsHighlight == true)
						{
							applyColor("regular");
						}
					}
					else
					{
						printf(" ");
					}
					wasPrinted = !wasPrinted;
				}
				else
				{
					if (data[cellIndex].isDoubleDigit)
					{
						if (needsHighlight == true)
						{
							applyColor("green");
							// applyColor("bold");
						}
						printf("%d", data[cellIndex].value % 10);
						if (needsHighlight == true)
						{
							applyColor("regular");
							needsHighlight = false;
						}
					}
					else
					{
						if (data[cellIndex].value > 0)
						{
							if (needsHighlight == true)
							{
								applyColor("green");
								// applyColor("bold");
							}
							printf("%d", data[cellIndex].value);
							if (needsHighlight == true)
							{
								applyColor("regular");
								needsHighlight = false;
							}
						}
						else
						{
							applyColor("red");
							printf("X");
							applyColor("regular");
						}
					}
					wasPrinted = !wasPrinted;
					cellIndex++;
				}
			}
			else
			{
				printf(" ");
			}
		}
		printf("\n");
	}
}

void swapValues(cellData *data, int nextCell, int currentCell)
{
	cellData tmp;

	tmp = data[nextCell];
	data[nextCell] = data[currentCell]; 
	data[currentCell] = tmp;
}

void moveCells(char key, cellData *data, int canvasDivisions)
{
	int currentCell;
	
	currentCell = 0;
	while (data[currentCell].value != 0)
	{
		currentCell += 1;
	}

	if (key == 'w') // move up
	{
		// safety check
		if (data[currentCell].y == 0)
		{
			return;
		}

		// find cell to swap with
		int nextCell = 0;
		// check to see if next cell meets the requirements
		while (data[currentCell].x != data[nextCell].x // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y + 1) // above currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].y -= 1;
		data[nextCell].y += 1;

		// swap values
		swapValues(*(&data), nextCell, currentCell);
	}
	else if (key == 's') // move down
	{
		// safety check
		if (data[currentCell].y == canvasDivisions - 1)
		{
			return;
		}

		// find cell to swap with
		int nextCell = 0;
		while (data[currentCell].x != data[nextCell].x // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y - 1) // below currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].y += 1;
		data[nextCell].y -= 1;

		// swap values
		swapValues(*(&data), nextCell, currentCell);
	}
	else if (key == 'a') // move left
	{
		// safety check
		if (data[currentCell].x == 0)
		{
			return;
		}

		// find cell to swap with
		int nextCell = 0;
		// check to see if next cell meets the requirements
		while (data[currentCell].x != data[nextCell].x + 1 // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y) // above currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].x -= 1;
		data[nextCell].x += 1;

		// swap values
		swapValues(*(&data), nextCell, currentCell);
	}
	else if (key == 'd') // move right
	{
		// safety check
		if (data[currentCell].x == canvasDivisions - 1)
		{
			return;
		}

		// find cell to swap with
		int nextCell = 0;
		while (data[currentCell].x != data[nextCell].x - 1 // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y) // below currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].x += 1;
		data[nextCell].x -= 1;

		// swap values & positions
		swapValues(*(&data), nextCell, currentCell);
	}
}

void generateRandomPuzzle(cellData *data, int canvasDivisions)
{
	int tmp;
	char key;

	for (int i = 0; i < 1000; i++)
	{
		tmp = rand() % 4;
		if (tmp == 0)
		{
			key = 'w';
		}
		else if (tmp == 1)
		{
			key = 's';
		}
		else if (tmp == 2)
		{
			key = 'a';
		}
		else
		{
			key = 'd';	
		}
		moveCells(key, *(&data), canvasDivisions);
	}
}

int main(void)
{
	char	gameStatus[64];
	int		canvasSize = 17; // minimum size is 8, recommended is 17
	int		canvasDivisions = 4;
	cellData data[16];
	
	// initalization
	strcpy(gameStatus, "mainMenu");
	initCellData(*(&data));

	// clear screen
	clearScreen();

	while (true)
	{
		if (strcmp(gameStatus, "mainMenu") == 0)
		{
			char key;

			printMainMenu();
			key = getchar();

			if (key == '1')
			{
				strcpy(gameStatus, "startGame");
				continue;
			}
			if (key == '2')
			{
				strcpy(gameStatus, "loadGame");
				continue;
			}
			else if (key == 't')
			{
				char color[64];
				strcpy(color, "\033[0;34m");
				printf("%sHello", color);
			}
			else
			{
				break;
			}
		}
		if (strcmp(gameStatus, "startGame") == 0)
		{
			char key;

			// clear screen
			clearScreen();

			// draw game
			drawGame(data, canvasSize, canvasDivisions);
			getchar(); // To consume '\n'
			key = getchar();

			// find current cell (a.k.a. empty cell)

			if (key == 'r')
			{
				generateRandomPuzzle(*(&data), canvasDivisions);
			}
			else if (key == 'q') // quit
			{
				strcpy(gameStatus, "mainMenu");
				getchar(); // Catch '\n'
				continue;
			}
			else
			{
				moveCells(key, *(&data), canvasDivisions);
			}
		}
	}
	return (0);
}