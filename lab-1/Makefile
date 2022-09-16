CC := gcc# Compiler to use
CFLAGS := -Wall -Wextra -Werror# Compilation flags
FILES := $(wildcard ./src/*.c)# Files to be compiled
PROJNAME := puzzle15# Project name

RED :=		'\033[0;31m'
GREEN :=	'\033[0;32m'
BLUE :=		'\033[0;34m'
YELLOW :=	'\033[0;33m'
REGULAR :=	'\033[0m'

compile: clean
	@$(CC) -c $(FILES)
	@echo -e $(YELLOW)"Creating objects.."$(REGULAR)
	@echo -e $(YELLOW)'Linking them..'$(REGULAR)
	@$(CC) $(CFLAGS) $(patsubst ./src/%.c, ./%.o, $(FILES)) -o $(PROJNAME)
	@echo -e $(GREEN)'Done'$(REGULAR)

clean:
	@echo -e $(BLUE)'Cleaning residual files..'$(REGULAR)
	@rm -f $(wildcard *.o) $(PROJNAME)
	
run:
	@echo 'Running $(PROJNAME)..'
	@./$(PROJNAME)