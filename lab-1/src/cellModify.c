#include "../headers/main.h"

void initCellData(cellData *data, int canvasDivisions)
{
	// iterate through all cells
	for (int i = 0; i < canvasDivisions * canvasDivisions; i++)
	{
		// assign values uniformly
		data[i].x = (i % canvasDivisions);
		data[i].y = i / canvasDivisions;
		// make the last cell empty
		if (i == canvasDivisions * canvasDivisions - 1)
		{
			data[canvasDivisions * canvasDivisions - 1].value = 0;
			data[canvasDivisions * canvasDivisions - 1].isDoubleDigit = false;
		}
		else
		{
			data[i].value = i + 1;
		}
		// find if the value is doubleDigit, for rendering
		if (data[i].value > 9)
		{
			data[i].isDoubleDigit = true;
		}
		else
		{
			data[i].isDoubleDigit = false;
		}
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
	// player cell (a.k.a. value 0 cell)
	int currentCell;
	// cell to swap with
	int nextCell;
	
	currentCell = 0;
	nextCell = 0;
	// find current cell (a.k.a. empty cell)
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

		// check to see if next cell meets the requirements
		while (data[currentCell].x != data[nextCell].x // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y + 1) // above currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].y -= 1;
		data[nextCell].y += 1;
	}
	else if (key == 's') // move down
	{
		// safety check
		if (data[currentCell].y == canvasDivisions - 1)
		{
			return;
		}

		while (data[currentCell].x != data[nextCell].x // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y - 1) // below currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].y += 1;
		data[nextCell].y -= 1;
	}
	else if (key == 'a') // move left
	{
		// safety check
		if (data[currentCell].x == 0)
		{
			return;
		}

		// check to see if next cell meets the requirements
		while (data[currentCell].x != data[nextCell].x + 1 // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y) // above currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].x -= 1;
		data[nextCell].x += 1;
	}
	else if (key == 'd') // move right
	{
		// safety check
		if (data[currentCell].x == canvasDivisions - 1)
		{
			return;
		}

		// check to see if next cell meets the requirements
		while (data[currentCell].x != data[nextCell].x - 1 // need to be the same on x axis
		|| data[currentCell].y != data[nextCell].y) // below currentCell on y axis
		{
			nextCell += 1;
		}

		// swap positions
		data[currentCell].x += 1;
		data[nextCell].x -= 1;
	}

	// swap values
	swapValues(*(&data), nextCell, currentCell);
}

void generateRandomPuzzle(cellData *data, int canvasDivisions)
{
	int tmp;
	char key;

	// make 1000 random moves
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