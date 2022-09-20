#include "../headers/main.h"

int main(void)
{
	char		gameStatus[64];
	char		MOTD[128]; // message of the day (a.k.a. messages, instructions)
	int			canvasSize = 17; // minimum size is 8, recommended is 17
	int			canvasDivisions = 4;
	cellData	*data;
	stackData	*doStatus; // do feature
	stackData	*redoStatus; // redo feature
	
	// initalization
	data = (cellData *) malloc(sizeof(cellData) * canvasDivisions * canvasDivisions);
	strcpy(gameStatus, "mainMenu");
	strcpy(MOTD, "");
	initCellData(*(&data), canvasDivisions);

	// initialize stack for Do/Undo feature
	doStatus = initStack(data);
	redoStatus = initStack(data);

	// clear screen
	clearScreen();

	while (true)
	{
		// clear screen
		clearScreen();

		// system to show messages after screen clearing
		if (strcmp(MOTD, "") != 0)
		{
			applyColor("bold");
			printf("%s\n\n", MOTD);
			applyColor("regular");
			strcpy(MOTD, " ");
		}

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
			else if (key == '4')
			{
				strcpy(gameStatus, "removeSave");
				continue;
			}
			else
			{
				break;
			}
		}
		else if (strcmp(gameStatus, "startGame") == 0)
		{
			char key;

			// draw game
			drawGame(data, canvasSize, canvasDivisions);
			
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
					strcpy(MOTD, "Sorry, can't undo that");
					continue;
				}

				// push the undo to the stack
				stackPush(&redoStatus, 20, data);

				// apply the `undo` change
				data = tmp;

				strcpy(MOTD, "Reverted the last move");
			}
			// redo a move
			else if (key == 'r')
			{
				cellData *tmp = stackPop(redoStatus);

				// safety check, avoid seg. fault
				if (tmp == (void *)NULL)
				{
					strcpy(MOTD, "There is nothing to redo");
					continue;
				}

				// push the changes to doStatus, to avoid bug
				stackPush(&doStatus, 20, data);

				data = tmp;

				strcpy(MOTD, "Redid the undo move");
			}
			// generate random puzzle
			else if (key == 'g')
			{
				generateRandomPuzzle(*(&data), canvasDivisions);
				strcpy(MOTD, "Generating new puzzle..");
			}
			// save game
			else if (key == 'v')
			{
				gameSave(data, doStatus, redoStatus, canvasDivisions);
				strcpy(MOTD, "Game saved sucessfully");
			}
			// generate new canvas
			else if (key == 'n')
			{
				initCellData(*(&data), canvasDivisions);
				strcpy(MOTD, "New game generated");
			}
			// quit game to main menu
			else if (key == 'q') // quit
			{
				strcpy(gameStatus, "mainMenu");
				continue;
			}
		}
		else if (strcmp(gameStatus, "loadGame") == 0)
		{
			int code;

			code = loadSave(*(&data), *(&doStatus), *(&redoStatus), canvasDivisions);

			if (code == -1)
			{
				strcpy(MOTD, "Game save is broken. Creating new game.");
				initCellData(*(&data), canvasDivisions);
			}
			else if (code == -2)
			{
				strcpy(MOTD, "Game save corrupted. Creating new game.");
				initCellData(*(&data), canvasDivisions);
			}
			strcpy(MOTD, "Game loaded succseffully.");
			strcpy(gameStatus, "startGame");
		}
		else if (strcmp(gameStatus, "removeSave") == 0)
		{
			if (remove("gameSave.faf") == 0) 
			{
				strcpy(MOTD, "The file is deleted successfully");
			}
			else 
			{
				strcpy(MOTD, "The save file does not exist");
			}
			strcpy(gameStatus, "mainMenu");
			clearScreen();
		}
	}
	return (0);
}