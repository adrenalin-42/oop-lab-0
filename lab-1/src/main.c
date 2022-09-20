#include "../headers/main.h"

int main(void)
{
	char	gameStatus[64];
	int		canvasSize = 17; // minimum size is 8, recommended is 17
	int		canvasDivisions = 4;
	cellData *data;
	stackData *doStatus; // do feature
	stackData *redoStatus; // redo feature
	
	// initalization
	data = (cellData *) malloc(sizeof(cellData) * canvasDivisions * canvasDivisions);
	strcpy(gameStatus, "mainMenu");
	initCellData(*(&data), canvasDivisions);

	// initialize stack for Do/Undo feature
	doStatus = initStack(data);
	redoStatus = initStack(data);

	// clear screen
	clearScreen();

	while (true)
	{
		if (strcmp(gameStatus, "mainMenu") == 0)
		{
			char key;

			printMainMenu();
			key = inputCatch();

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
			// printf("Stack size: %d", getStackSize(doStatus));
			key = inputCatch();

			// move in a specific direction
			if (key == 'w'
			|| key == 's'
			|| key == 'a'
			|| key == 'd')
			{
				// make a copy of data to undo later
				cellData *tmp;

				// memory per one `canvas` is sizeof(cellData) * canvas size * size of an integer
				tmp = (cellData *)malloc(sizeof(cellData) * canvasDivisions * canvasDivisions * sizeof(int));

				// copy everything in tmp, so later the copy will be saved
				memcpy(tmp, data, sizeof(data) * canvasDivisions * canvasDivisions * sizeof(int));

				// push the copy to the stack
				stackPush(&doStatus, 20, tmp);

				// apply the move
				moveCells(key, *(&data), canvasDivisions);

				// reset redoStatus on move
				redoStatus = initStack(data);
			}
			// undo a move
			else if (key == 'u')
			{
				cellData *tmp = stackPop(doStatus);

				// safety check, avoid seg. fault
				if (tmp == (void *)NULL)
				{
					continue;
				}

				// push the undo to the stack
				stackPush(&redoStatus, 20, data);

				// apply the `undo` change
				data = tmp;


				// printf("Here Stack size: %d", getStackSize(redoStatus));
			}
			// redo a move
			else if (key == 'r')
			{
				cellData *tmp = stackPop(redoStatus);

				// safety check, avoid seg. fault
				if (tmp == (void *)NULL)
				{
					continue;
				}

				// push the changes to doStatus, to avoid bug
				stackPush(&doStatus, 20, data);

				data = tmp;
				// printf("Stack size: %d", getStackSize(redoStatus));
			}
			// generate random puzzle
			else if (key == 'g')
			{
				generateRandomPuzzle(*(&data), canvasDivisions);
			}
			// generate new canvas
			else if (key == 'n')
			{
				initCellData(*(&data), canvasDivisions);
			}
			// quit game to main menu
			else if (key == 'q') // quit
			{
				strcpy(gameStatus, "mainMenu");
				continue;
			}
		}
	}
	return (0);
}