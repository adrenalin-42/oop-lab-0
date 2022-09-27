COMPILER := kotlinc# Compiler to use
EXEC := kotlin# Execute
FILES := $(wildcard *.kt)# Files to be compiled
PROJNAME := discord# Project name

RED :=		'\033[0;31m'
GREEN :=	'\033[0;32m'
BLUE :=		'\033[0;34m'
YELLOW :=	'\033[0;33m'
REGULAR :=	'\033[0m'

compile: clean
	@echo -e $(YELLOW)"Compiling.."$(REGULAR)
	@$(COMPILER) $(FILES)
	@echo -e $(GREEN)'Done'$(REGULAR)

clean:
	@echo -e $(BLUE)'Cleaning residual files..'$(REGULAR)
	@rm -rf $(wildcard *.class) ./META-INF
	
run:
	@echo -e 'Running $(PROJNAME)..\n'
	@$(EXEC) MainKt.class