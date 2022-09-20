#include "../headers/main.h"

void printMainMenu(void)
{
	printf("Greetings to my 15 Puzzle game variation\n");
	printf("This project is done by Dumitru Moraru, FAF-212\n");
	printf("It is made for the first laboratory work on OOP ^-^\n");
	printf("Have fun\n\n");
	printf("1 - Start game\n");
	printf("2 - Show controls\n");
	printf("3 - Load game from file\n");
	printf("4 - Delete save file\n");
	printf("5 - Exit game\n\n");
}

void showControls(void)
{
	clearScreen();
	applyColor("bgBlue");
	printf("w - move empty cell up\n");
	printf("s - move empty cell down\n");
	printf("a - move empty cell left\n");
	printf("d - move empty cell right\n");
	printf("u - undo last move\n");
	printf("r - redo last move\n");
	printf("g - generate randomly the grid\n");
	printf("n - start from the beginning\n");
	printf("v - save game to file\n");
	printf("q - quit game to main menu\n");
	applyColor("regular");

	inputCatch();
}

char inputCatch(void)
{
	char key;

	key = getchar();
	while (key == '\n')
	{
		key = getchar();
	}

	return (key);
}