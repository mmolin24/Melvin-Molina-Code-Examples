

CC = gcc

CFLAGS = -ansi -pedantic-errors -Wall -fstack-protector-all -Werror -Wshadow

PROGS= public01 public02 public03 public04 public05 

default: all

all: $(PROGS)

public01: public01.o calendar.o 
	$(CC) public01.o calendar.o my_memory_checker_216.o -o public01.x

public02: public02.o calendar.o 
	$(CC) public02.o calendar.o my_memory_checker_216.o -o public02.x

public03: public03.o calendar.o 
	$(CC) public03.o calendar.o my_memory_checker_216.o -o public03.x

public04: public04.o calendar.o 
	$(CC) public04.o calendar.o my_memory_checker_216.o -o public04.x

public05: public05.o calendar.o 
	$(CC) public04.o calendar.o my_memory_checker_216.o -o public05.x

calendar.o: calendar.c calendar.h event.h 
	$(CC) $(CFLAGS) -c calendar.c



public01.o: public01.c event.h calendar.h my_memory_checker_216.h
	$(CC) $(CFLAGS) -c public01.c

public02.o: public02.c calendar.h event.h my_memory_checker_216.h
	$(CC) $(CFLAGS) -c public02.c

public03.o: public03.c calendar.h event.h my_memory_checker_216.h
	$(CC) $(CFLAGS) -c public03.c

public04.o: public04.c calendar.h event.h my_memory_checker_216.h
	$(CC) $(CFLAGS) -c public04.c

public05.o: public05.c calendar.h event.h my_memory_checker_216.h
	$(CC) $(CFLAGS) -c public05.c


clean:
	rm -f *.o $(PROGS)
