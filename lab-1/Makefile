CC = gcc# Compiler to use
CFLAGS = -Wall -Wextra -Werror# Compilation flags
FILES = main.c
PROJNAME = puzzle15# Project name

compile: clean
	$(CC) -c $(FILES)
	$(CC) $(CFLAGS) -o $(PROJNAME) $(wildcard *.o) 

clean:
	rm -f $(wildcard *.o) $(PROJNAME)
	
run:
	./$(PROJNAME)