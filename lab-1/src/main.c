#include "../headers/main.h"

int main(void)
{
	char	gameStatus[64];
	int		canvasSize = 8; // minimum size is 8, recommended is 17
	int		canvasDivisions = 4;
	cellData data[16];
	
	// initalization
	strcpy(gameStatus, "mainMenu");
	initCellData(*(&data), canvasDivisions);

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
				showControls();
				continue;
			}
			else if (key == '3')
			{
				strcpy(gameStatus, "loadGame");
				continue;
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

			if (key == 'w'
			|| key == 's'
			|| key == 'a'
			|| key == 'd')
			{
				moveCells(key, *(&data), canvasDivisions);
			}
			if (key == 'r')
			{
				generateRandomPuzzle(*(&data), canvasDivisions);
			}
			else if (key == 'n')
			{
				initCellData(*(&data), canvasDivisions);
			}
			else if (key == 'q') // quit
			{
				strcpy(gameStatus, "mainMenu");
				getchar(); // Catch '\n'
				continue;
			}
		}
	}
	return (0);
}