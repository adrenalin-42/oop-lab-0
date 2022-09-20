#include "../headers/main.h"

int getStackSize(stackData *data)
{
    int size;
    stackData *current = data;

    // safety check
    // if (data == NULL)
    // {
    //     return (0);
    // }

    size = 0;
    while (current->next != NULL)
    {
        current = current->next;
        size = size + 1;
    }

    return (size);
}

stackData *initStack(void *data)
{
    stackData *ptr;

    ptr = (stackData *) malloc(sizeof(stackData));
    ptr->value = data;
    ptr->next = NULL;

    return (ptr);
}

void stackPush(stackData **data, int maxCount, void *stackValue)
{
    // check if the size of stack is bigger than allowed
    if (getStackSize(*data) >= maxCount)
    {
        stackData *tmp = *data;

        *data = (*data)->next;
        free(tmp);
    }

    stackData *current = *data;
    // get to the last element of stack
    while (current->next != NULL)
    {
        current = current->next;
    }

    // initialize next element of stack
    current->next = (stackData *) malloc(sizeof(stackData));
    current->next->value = stackValue;

    // make the next next value NULL
    current->next->next = NULL;
}

void *stackPop(stackData *data)
{
    void *retVal = (void *)NULL;

    // if stack is empty, exit
    if (getStackSize(data) == 0)
    {
        return (retVal);
    }

    // if there is only one item left, remove it
    if (data->next == NULL)
    {
        retVal = data->value;
        free(data);
        return (retVal);
    }

    stackData *current = data;

    // get to element before the last one
    while (current->next->next != NULL)
    {
        current = current->next;
    }

    retVal = current->next->value;
    free(current->next);
    current->next = NULL;

    return (retVal);
}

void stackPrint(stackData *data)
{
    stackData *current = data;

    if (getStackSize(current) == 0)
    {
        return;
    }

    while (current != NULL)
    {
        printf("%d\n", current->value);
        current = current->next;
    }
}