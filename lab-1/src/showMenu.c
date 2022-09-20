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
	printf("4 - Exit game\n\n");
}

void showControls(void)
{
	clearScreen();
	printf("w - move empty cell up\n");
	printf("s - move empty cell down\n");
	printf("a - move empty cell left\n");
	printf("d - move empty cell right\n");
	printf("r - generate randomly the grid\n");
	printf("n - start from the beginning\n");

	getchar(); // Catch '\n'
	getchar(); // Wait for input
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