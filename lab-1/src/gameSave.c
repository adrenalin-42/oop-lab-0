#include "../headers/main.h"

void gameSave(cellData *data, stackData *doStatus, stackData *redoStatus, int canvasDivisions)
{
    FILE *fileSave;

    fileSave = fopen("gameSave.faf", "w");
    if (fileSave == NULL)
    {
        printf("Problem with the file. You did something wrong, drujok!\n");
    }

    // canvas divisions (& it's a check for file save compatibility)
    fprintf(fileSave, "%d\n", canvasDivisions);

    // current canvas
    for (int i = 0; i < canvasDivisions * canvasDivisions; i++)
    {
        fprintf(fileSave, "%d %d %d %d\n", data[i].x, data[i].y, data[i].value, data[i].isDoubleDigit);
    }

    // // do/undo canvas
    // fprintf(fileSave, "%d\n", getStackSize(doStatus));

    // for (int i = 0; i < getStackSize(doStatus); i++)
    // {
    //     for (int j = 0; j < canvasDivisions * canvasDivisions; j++)
    //     {
    //         fprintf(fileSave, "%d %d %d %d\n", data[j].x, data[j].y, data[j].value, data[j].isDoubleDigit);
    //     }
    // }

    // // redo canvas
    // fprintf(fileSave, "%d\n", getStackSize(redoStatus));

    // for (int i = 0; i < getStackSize(redoStatus); i++)
    // {
    //     for (int j = 0; j < canvasDivisions * canvasDivisions; j++)
    //     {
    //         fprintf(fileSave, "%d %d %d %d\n", data[j].x, data[j].y, data[j].value, data[j].isDoubleDigit);
    //     }
    // }

    // closing file
    fclose(fileSave);
}

int loadSave(cellData *data, stackData *doStatus, stackData *redoStatus, int canvasDivisions)
{
    FILE *fileSave;

    fileSave = fopen("gameSave.faf", "r");
    if (fileSave == NULL)
    {
        printf("Problem with the file. You did something wrong, drujok!\n");
        return (-1);
    }

    // security
    int tmp;

    // read canvas divisions
    fscanf(fileSave, "%d\n", &tmp);

    if (tmp != canvasDivisions)
    {
        printf("File save not compatible!\n");
        return (-2);
    }

    // current canvas
    for (int i = 0; i < canvasDivisions * canvasDivisions; i++)
    {
        fscanf(fileSave, "%d %d %d %d\n", &data[i].x, &data[i].y, &data[i].value, &data[i].isDoubleDigit);
    }

    // // do/undo canvas
    // int size;

    // fscanf(fileSave, "%d\n", &size);

    // for (int i = 0; i < size; i++)
    // {
    //     for (int j = 0; j < canvasDivisions * canvasDivisions; j++)
    //     {
    //         fscanf(fileSave, "%d %d %d %d\n", &data[j].x, &data[j].y, &data[j].value, &data[j].isDoubleDigit);
    //     }
    // }

    // // redo canvas
    // fscanf(fileSave, "%d\n", &size);

    // for (int i = 0; i < size; i++)
    // {
    //     for (int j = 0; j < canvasDivisions * canvasDivisions; j++)
    //     {
    //         fscanf(fileSave, "%d %d %d %d\n", &data[j].x, &data[j].y, &data[j].value, &data[j].isDoubleDigit);
    //     }
    // }

    // closing file
    fclose(fileSave);

    return (0);
}