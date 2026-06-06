## Java Packages and Project Structure.

### Packages in Java and Project Structure

#### What is it?

A **package** is a way to organize Java classes into meaningful groups.
Packages allow you to separate code into logical parts: separate interfaces, separate business logic, and separate data handling.

#### How is a package different from a folder?

This is a very important distinction that is often confused.

In Java, a **package** and a **directory/folder** are related, but they are not the same thing.

**Briefly**

- A **folder** is a file system object.
- A **package** is a logical part of Java code.

---

#### **Folder**

This is a regular directory on your computer:

```
src/
└── school/
    └── students/
        └── Main.java
```

The OS doesn't know anything about Java—to it, these are just folders.

---

#### Package

This declaration is inside a Java file:

```java
package school.students;
```

It tells Java:

> "This class belongs to the `school.students` package."

---

#### Why they usually match

In Java, it's common practice for the folder structure to correspond to packages.

Therefore:

```java
package school.students;
```

usually located in:

```
school/students/
```

---

**Example**

File:

```
src/school/students/Main.java
```

Contents:

```java
package school.students;

public class Main {
}
```

---

**What happens if they don't match**

For example:

The file is located here:

```
src/animals/Main.java
```

and inside:

```java
package school.students;
```

Then:

- The IDE will complain,
- The compiler may not find the class,
- Maven/Gradle may not work correctly.

---

### Important Difference

The folder exists independently.

A package exists only in Java code:

```java
package myapp.utils;
```

Without this line, the class is considered to be in the **default package** (without a package).

---

#### **Why do we need packages**

They help:

- group classes,
- avoid name conflicts,
- manage access (`package-private`),
- keep the project structured.

For example:

```java
java.util.List
java.awt.List
```

Both classes are named `List`, but they are in different packages.

- **How are they created**

The folder and package are related, but they don't automatically "magically" create each other. This is usually done by an IDE (e.g., IntelliJ).

**When creating a package**

If you click in IntelliJ:

```
New → Package
```

and type:

```
school.students
```

IDE:

1. Creates folders:

```
school/students
```

1. And new classes inside will automatically have:

```
packageschool.students;
```

---

**When creating a folder**

If you do:

```
New → Directory
```

then it's just a folder. Java doesn't yet consider it a package. Only when the following appears inside the class:

```
package ...
```

it will be used as a package.

---

**When placing a class in a folder**

IntelliJ usually helps automatically.

For example:

We moved `Main.java` to:

```
school/students
```

The IDE might suggest:

```
Update package statement?
```

and replace:

```
packageoldpackage;
```

with:

```
packageschool.students;
```

---

**Main**

**The package is defined by the line:**

```
package ...
```

not by the folder itself.


#### Our Project's Package Structure

```
com.example.myassistant/
├── ui/
│   ├── panels/
│   └── dialogs/
├── controller/
├── service/
│   └── impl/
├── repository/
│   └── impl/
├── model/
└── util/
```

#### `ui` — user interface

Everything the user sees on the screen: windows, panels, dialogs.
The classes here know **nothing** about where the data comes from—they only display what's passed to them.

- `ui/panels/` — application tabs: `TaskPanel`, `ToolsPanel`, `ChatPanel`
- `ui/dialogs/` — popup windows: for example, `EditTaskDialog` for editing a task

#### `controller` — event handling

This is the intermediary between the UI and the business logic. When the user clicks a button, the event is sent here.
The Controller takes data from the UI, passes it to the Service, receives the result, and returns it to the UI.
The Controller itself doesn't perform any logic—it only "orchestras."

#### `service` — business logic

This is where the application rules live: what does it mean to "add a task," "get the weather," or "send a message."
Each service is declared as an **interface** in the `service/` package, and its implementation is in `service/impl/`.
This is done so that the implementation can be replaced in the future (for example, by connecting a real server) without affecting the UI and Controller.

#### `repository` — data access

Responsible for saving and loading data—currently from a JSON file, later from a database.
Same as a service: interface in `repository/`, implementation in `repository/impl/`.
This way, when we explore databases, we can replace `JsonTaskRepository` with `DatabaseTaskRepository`—and we won't have to change anything else in the project.

#### `model` — data models

Simple classes that describe data: `Task`, `WeatherData`.
No logic—just fields and, possibly, a constructor with getters/setters.

#### `util` — helper classes

Constants and utilities that are used in multiple places in the project.
For example, `AppConstants` stores file paths and URLs of external APIs to avoid duplicating strings throughout the code.

- **SimpleProject Example**

**🧱 1. MODEL (data, describes the entity)**

Task

```java 
package simpleproject.model; 
public class Task { 
    private String title; 
    
    public Task(String title) { 
        this.title = title; 
    } 
    
    public String getTitle() { 
        return title; 
    } 
} 
``` 

--- 

**💾 2. REPOSITORY (data storage)**

Repository pattern

```java 
package simpleproject.repository; 
import java.util.ArrayList; 
import java.util.List; 

public interface TaskRepository { 
    void save(Task task); 
    List<Task> findAll(); 
}
```

---

**implementation (while in memory)**

```java
package simpleproject.repository.impl;
import java.util.ArrayList;
import java.util.List;

public class InMemoryTaskRepository implements TaskRepository {
    
    private List<Task> tasks = new ArrayList<>();
    
    public void save(Task task) {
        tasks.add(task);
    }
    
    public List<Task> findAll() {
        return tasks;
    }
}
```

The class implementing Repository can work with a database, file, or memory, depending on the implementation.

- If JSON - class `JsonTaskRepository implements TaskRepository`

```java
public class JsonTaskRepository implements TaskRepository {
    
    @Override
    public void save(Task task) {
        
        System.out.println("Saving the task to a JSON file");
        
        // writing to tasks.json
    }
    
    @Override
    public List<Task> findAll() {
        
        System.out.println("Reading tasks from a JSON file");
        
        // reading from tasks.json
        
        return new ArrayList<>();
    }
}
```

- If the database is a `DatabaseTaskRepository` class, it implements TaskRepository.

```java
public class DatabaseTaskRepository implements TaskRepository {
    
    @Override
    public void save(Task task) {
        
        System.out.println("INSERT INTO tasks ...");
        
        // SQL INSERT
    }
    
    @Override
    public List<Task> findAll() {
        
        System.out.println("SELECT * FROM tasks");
        
        // SQL SELECT
        
        return new ArrayList<>();
    }
}
```

**Then you can easily change the data source in Main**

Today:

```java
TaskRepository repo = new InMemoryTaskRepository();
```

Tomorrow:

```java
TaskRepository repo = new JsonTaskRepository();
```

After examining the database:

```java
TaskRepository repo = new DatabaseTaskRepository();
```

---

**⚙️ 3. SERVICE (business logic)**

Service layer

```java
package simpleproject.service;

import java.util.List;

public class TaskService {
    
    private TaskRepository repo;
    
    public TaskService(TaskRepository repo) {
        this.repo = repo;
    } 
    
    public void addTask(String title) { 
        if (title == null || title.isEmpty()) { 
            return; // business rule 
        } 
        
        repo.save(new Task(title)); 
    } 
    
    public List<Task> getTasks() { 
        return repo.findAll(); 
    }
}
```

---

**🎮 4. CONTROLLER (UI ↔ service connection)**

Controller

```java
package simpleproject.controllers;
public class TaskController {
    
    //Controller stores a link to the Service 
    private TaskService service; 
    
    public TaskController(TaskService service) { 
        this.service = service; 
    } 
    
    public void onAddButtonClicked(String textFromUI) { 
        service.addTask(textFromUI); 
    }
}
```

- **Explanation**

Let's say the user entered: "*Buy milk*" and clicked the button.

Then the UI will call:

```java
controller.onAddButtonClicked("Buy milk");
```

---

**What happens next**

The Controller receives the text: `textFromUI` and passes it to the Service:

```java
service.addTask(textFromUI);
```

---

**Full data path**

**User**

```
Buy milk
```

↓

**UI**

```java
controller.onAddButtonClicked(input.getText());
```

↓

**Controller**

```java
service.addTask(text);
```

↓

**Service**

```java
repo.save(newTask(text));
```

↓

**Repository**

```java
tasks.add(task);
```

---

**Why can't I call the Service directly from the UI?**

Yes. For example:

```java
addButton.addActionListener(e -> {
    service.addTask(input.getText());
});
```

For a small program, this is fine.

---

**Then why do I need a Controller?**

So the UI doesn't know about the business logic.

---

**Without Controller**

```
    UI
    ↓
 Service
    ↓
Repository
```

---

**With Controller**

```
    UI
    ↓
Controller
    ↓
Service
    ↓
Repository
```

---

**Let's imagine a more realistic situation**

When a button is clicked, we need to:

1. Get the text from the field
2. Check that the user is logged in
3. Call the Service
4. Update the task list
5. Show an error message

Then the Controller might look like this:

```java
public void onAddButtonClicked(String text) {
    
    if(text.isBlank()) {
        view.showError("Enter a task");
        return;
    }
    
    service.addTask(text);
    
    view.refreshTasks();
    
    view.showMessage("Task added");
}
```

Here, the Controller makes sense.

---

You can think of the Controller as:

> "a translator between the UI and the Service"

It receives events from the interface and decides which Service method to call.

---

**🖼️ 5. UI (Swing)**

JButton

JTextField

```java
package simpleproject.ui;

import javax.swing.*;
import java.awt.*;

public class TaskPanel extends JPanel { 
    
    private JTextField input = new JTextField(10); 
    private JButton addButton = new JButton("Add"); 
    
    public TaskPanel(TaskController controller) { 
        
        setLayout(new FlowLayout()); 
        
        add(input); 
        add(addButton); 
        
        addButton.addActionListener(e -> { 
        controller.onAddButtonClicked(input.getText()); 
        input.setText(""); 
        }); 
    }
}
```

---

**🚀 6. MAIN (assembling everything)**

```java
import javax.swing.*;

public class Main { 
    
    public static void main(String[] args) { 
        
        TaskRepository repo = new InMemoryTaskRepository(); 
        TaskService service = new TaskService(repo); 
        TaskController controller = new TaskController(service); 
        
        JFrame frame = new JFrame("Task Manager"); 
        
        TaskPanel panel = new TaskPanel(controller); 
        
        frame.add(panel); 
        
        frame.setSize(300, 200); 
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); 
        frame.setVisible(true); 
    }
}
```

---

**Now the main thing is how it works**

**👉 The user clicked a button**

```
UI (TaskPanel)
    ↓
Controller
    ↓
Service
    ↓
Repository
    ↓
List<Task>
```

---

**📌 What each part does**

**🖼 UI**

- takes text
- shows buttons
- DOESN'T know about logic

---

**🎮 Controller**

- receives an event
- passes it on
- DOESN'T think

---

**⚙ Service**

- checks rules
- creates a Task
- decides "allowed or not"

---

**💾 Repository**

- stores data
- currently in memory
- later there will be a database Data

---

**🧠 The Most Important Understanding**

❌ The UI doesn't know where the data is stored

❌ The Service doesn't know about Swing

❌ The Repository doesn't know about buttons


---

💡 **Tasks 0_1 SimpleProject - show all**

Add a button:

```
"Show All Tasks"
```

When clicked, **the entire current collection of tasks** should be displayed.

**Architectural Requirements**

**UI (TaskPanel)**

- Add an `allTasks` button
- When clicked, the Controller is called
- The result is displayed in a `JLabel`

---

💡 **Tasks 0_2 SimpleProject - delete all**

Add a button:

```
"Delete All"
```

When clicked:

- Deletes all tasks from the storage
- Refreshes the UI

---

#### Package Naming Convention

According to the Java standard, the package name starts with the company domain in reverse order:

```
com.google.gson ← Google library
org.springframework ← Spring library
com.example.myassistant ← our sample project
```

For sample projects, it is common to use `com.example` as a placeholder. All letters are lowercase, without spaces or hyphens.

#### Common Mistakes

- Forgetting to write `package` at the beginning of a file means the class will end up in an unnamed package and can't be imported.
- Creating a folder in the file system but not marking it as the Sources Root in IntelliJ means Java won't recognize it.
- Putting everything in one package "to keep things simple" means the project will be impossible to understand within a month.
- Capitalizing package names is a violation of the Java standard (`Model` instead of `model`).
