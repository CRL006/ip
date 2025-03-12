# Bobby User Guide

Bobby is a greenfield Java project. It's name is inspired by *Bob* the minion. Given below are the instructions on how to use it.

## Quick Start

1. Ensure you have Java ```17``` or above installed in your Computer.
2. Download the latest.jar file
3. Copy the file to the folder you want to use as the *home folder* for you todo-list
4. Open a command terminal, ```cd``` into the folder you put the jar file in, and use the ```java -jar bobby,jar``` 
command to run the application.
5. Type the command in the command box and press Enter to execute it.  
Some examples commands you can try:  
   - ```list```: Lists all tasks
   - ```todo read book```: Adds the ```read book``` task to the todo-list
   - ```delete 3```: Deletes the third task shown in the current list
   - ```bye```: Exits the todo-list


## Adding tasks: ```todo```

Adds an item to the todo-list

Format: `todo TASK_NAME`

Examples:

```
todo read book
```

## Adding deadlines: ```deadline```

Adds a deadline to the todo-list

Format: `deadline DEADLINE_NAME /by DEADLINE_TIME`

Examples:

```
deadline return book /by Sunday
```

## Adding events: ```event```

Adds an event to the todo-list

Format: `event EVENT_NAME /from EVENT_START_TIME /to EVENT_END_TIME`

Examples:

```
event group meeting /from 2pm /to 4pm
```

## Listing all events: ```list```

Shows a list of all the tasks in the todo-list

Format: `list`

## Marking tasks as done: ```mark```

Marks a chosen task as done in todo-list

Format: `mark TASK_INDEX`

Examples:

```
mark 1
```

## Marking tasks as undone: ```unmark```

Marks a chosen task as undone in todo-list

Format: `unmark TASK_INDEX`

Examples:

```
unmark 1
```

## Deleting a task: ```delete```

Deletes a chosen task from the todo-list

Format: `delete TASK_INDEX`

Examples:

```
delete 1
```

## Finding a task: ```find```

Finds related tasks in the todo-list, given a keyword.

Format: `find KEYWORD`

Examples:

```
find book
```

## Quitting the programme: ```bye```

Exits the programme

Format: `bye`
