#include "../headers/main.h"

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
		printf("\033[1;34m");
	}
}

void clearScreen()
{
	system("clear");
}