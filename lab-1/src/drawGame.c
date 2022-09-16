#include "../headers/main.h"

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
				if (cellIndex == data[cellIndex].value - 1)
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