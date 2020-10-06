#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include "event.h"
#include "calendar.h"

int init_calendar(const char *name, int days,
		  int (*comp_func) (const void *ptr1, const void *ptr2),
		  void (*free_info_func) (void *ptr), Calendar ** calendar) {

    /* validates params */
    if ((*calendar) && name && days >= 1) {
        (*calendar) = malloc(sizeof(Calendar));

        /* check if return value of malloc was successful */
        if (calendar) {

            /* mallloc for the name and copy name to block of memory */
            (*calendar)->name = malloc(sizeof(char) * (strlen(name) + 1));
            strcpy((*calendar)->name, name);

            /* calloc for the amount of days there will be */
            (*calendar)->events = calloc(days, sizeof(Event));

            /* initialize the values for calendar*/
            (*calendar)->days = days;
            (*calendar)->total_events = 0;

            /* initialize pointers */
            (*calendar)->comp_func = comp_func;
            (*calendar)->free_info_func = free_info_func;
            return SUCCESS;
        }
    }
    return FAILURE;
}

int print_calendar(Calendar *calendar, FILE *output_stream, int print_all) {
    Event *ptr;
    int i = 0;

    /* validate params */
    if (calendar && output_stream) {

        /* checks to print all*/
        if (print_all) {
	  
            fprintf(output_stream, "Calendar's Name: \"%s\"\n", calendar->name);
	    
            fprintf(output_stream, "Days: %d\n", calendar->days);
	    
            fprintf(output_stream, "Total Events: %d\n\n", calendar->total_events);
	    
        }
        /* prints header */
        fprintf(output_stream, "**** Events ****\n");

        /* prints events */
        if (calendar->total_events > 0) {

            /* iterate through each day of the calendar */
            for (i = 0; i < calendar->days; i++) {
	      
                printf("Day %d\n", i + 1);

		/* iterates through day */
                ptr = calendar->events[i];
		
                while (ptr) {
                    fprintf(output_stream, "Event's Name: \"%s\", ", ptr->name);
		    
                    fprintf(output_stream, "Start_time: %d, ", ptr->start_time);
		    
                    fprintf(output_stream, "Duration: %d\n", ptr->duration_minutes);
                    ptr = ptr->next;
		    
                }
            }
        }
        return SUCCESS;
    }
    return FAILURE;
}

int add_event(Calendar *calendar, const char *name, int start_time,
	      int duration_minutes, void *info, int day) {
  Event *new, *prev_ptr, *iter_ptr;
  Event *event; 

    /* validates parameter and parameter values */
    if (calendar && name && (start_time >= 0 && start_time <= 2400)) {

      
        if (duration_minutes > 0 && day > 0 && day <= calendar->days) {
	  
            if (find_event(calendar, name, &event) == FAILURE) {

	      /* allocates memory for event and event name */
                new = malloc(sizeof(Event));
		
                new->name = malloc(sizeof(char) * (strlen(name) + 1));

		
                /* give new memory values based on parameters */
                strcpy(new->name, name);
		
                new->duration_minutes = duration_minutes;
		
                new->start_time = start_time;
		
                new->info = info;

                iter_ptr = calendar->events[day - 1];

                /* if start of list is empty*/
		
                if (iter_ptr == NULL) {
		  
                    calendar->events[day - 1] = new;
                    new->next = NULL;
		    
                }
                    /* if placed in middle of list*/
                else if (iter_ptr != NULL) {

                    /* to be placed in the front */
                    if (calendar->comp_func(iter_ptr, new) > 0) {
		      
                        calendar->events[day - 1] = new;
                        new->next = iter_ptr;
			
                    }
                        /* placing in middle or back of list*/
                    else {
		      
                        prev_ptr = iter_ptr;
			iter_ptr = iter_ptr->next;
			
                        while (iter_ptr && calendar->comp_func(iter_ptr, new) <= 0) {
			  
                            prev_ptr = iter_ptr;
                            iter_ptr = iter_ptr->next;
			    
                        }
			
                        new->next = iter_ptr;
                        prev_ptr->next = new;
			
                    }
                }
		
		/* add to total events int val */
		
                (calendar->total_events)++;
                return SUCCESS;
            }
        }
    }
    return FAILURE;
}

int find_event(Calendar *calendar, const char *name, Event **event) {
    Event *ptr;
    int i = 0;

    /* validates param*/
    if (calendar && name) {
      
        /* scout for event searching based on name*/
        for (i = 0; i < calendar->days; i++) {

            ptr = calendar->events[i];
	    
            while (ptr && strcmp(ptr->name, name) != 0) {
	      
                ptr = ptr->next;
		
            }
	    
            /* if found check and return success  */
            if (ptr && event) {
	      
                *event = ptr;
                return SUCCESS;
            }
        }
    }
    return FAILURE;
}

int find_event_in_day(Calendar *calendar, const char *name, int day,
		      Event **event) {
    Event *ptr;
    int i = 0;

    /* validates params */
    if (calendar && name && day > 0 && day <= calendar->days) {

        /* search for event by name */
        for (i = 0; i < calendar->days; i++) {
	  
            if ((i + 1) == day) {

                ptr = calendar->events[i];
		
                while (ptr && strcmp(ptr->name, name) != 0) {
		  
                    ptr = ptr->next;
                }
		
                /* if found return success */
                if (ptr && event) {
		  
                    *event = ptr;
                    return SUCCESS;
                }
            }
        } 
    }
    return FAILURE;
}

int remove_event(Calendar *calendar, const char *name) {
    Event **event = NULL, *prev_ptr, *iter_ptr;
    int i = 0;

    /* validates params */
    if (calendar && name && find_event(calendar, name, event) == FAILURE) {

        /* iterate through Event lists in the calendar */
        for (i = 0; i < calendar->days; i++) {
	  
            iter_ptr = calendar->events[i];

            if (iter_ptr != NULL) {
	      
	      /* removing from front of list */
                if (strcmp(iter_ptr->name, name) == 0) {
		  
                    calendar->events[i] = iter_ptr->next;
                    iter_ptr->next = NULL;
                }
                   
                else {
                    while (iter_ptr && strcmp(iter_ptr->name, name) != 0) {
		      
                        prev_ptr = iter_ptr;
                        iter_ptr = iter_ptr->next;
                    }
		    
                    prev_ptr->next = iter_ptr->next;
                    iter_ptr->next = NULL;
		    
                }

                /* remove and free event */
                free(iter_ptr->name);
                if (calendar->free_info_func) {
		  
                    calendar->free_info_func(iter_ptr->info);
		    
                }
		
                free(iter_ptr);

                /* change num of events  */
                (calendar->total_events)--;
                return SUCCESS;
            }
        } 
    }
    return FAILURE;
}

void *get_event_info(Calendar *calendar, const char *name) {
   Event *event;

   /* if event is found, return info pointer from that event */
   if (find_event(calendar, name, &event) == FAILURE) {
      return NULL;
   }
   return event->info;
}

int clear_calendar(Calendar *calendar) {
    int i = 0;

    /* check that calendar isn't null */
    if (calendar) {
      
        /* clear dynamically allocated events in the calendar */
        for (i = 0; i < calendar->days; i++) {
	  
            clear_day(calendar, (i + 1));
	    
        }
        return SUCCESS;
    }
    return FAILURE;
}

int clear_day(Calendar *calendar, int day) {
    Event *temp, *ptr;

    /* validate params*/
    if (calendar && day > 0 && day <= calendar->days) {

        if (calendar->total_events > 0) {

            /* clear events on day*/
            ptr = calendar->events[day - 1];
	    
            while (ptr) {
	      
                temp = ptr;
                ptr = ptr->next;

                /* free everything from event */
                free(temp->name);
		
                if (calendar->free_info_func && temp->info) {
		  
                    calendar->free_info_func(temp->info);
		    
                }
		
                free(temp);
                (calendar->total_events)--;
            }
	    calendar->events[day - 1] = NULL;
            return SUCCESS;
        }
    }
    return FAILURE;
}

int destroy_calendar(Calendar *calendar) {

    /* validates params */
    if (calendar) {
        /* free name and event arr*/
        free(calendar->name);
        clear_calendar(calendar);
        free(calendar->events);

        /* free the calendar and then return success */
        free(calendar);
        return SUCCESS;
    }
    return FAILURE;
}
