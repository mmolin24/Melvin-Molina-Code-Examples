# provide a makefile with a target called shell_jr that 
# creates the executable shell_jr
CC = gcc
CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror -Wshadow

PROGS = shell_jr

all: $(PROGS)

clean: rm -f *.o $(PROGS) a.out

$(PROGS): shell_jr.o

shell_jr: shell_jr.o
	$(CC) -o shell_jr shell_jr.o

shell_jr.o: shell_jr.c
	$(CC) $(CFLAGS) -c shell_jr.c


