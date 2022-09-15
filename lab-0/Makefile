CC = gcc # Compiler to use
CFLAGS = -Wall -Wextra -Werror # Compilation flags
FILES = test.c
PROJNAME = test # Project name

compile:
	$(CC) -c $(FILES)
	$(CC) $(CFLAGS) -o $(PROJNAME) $(wildcard *.o)

clean:
	rm -f $(wildcard *.o) $(PROJNAME)