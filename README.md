# MyAssistant - Layered Desktop Architecture: Decoupled View Patterns and Command Actions (JavaBasics_Task_535_V0.1)

## 📖 Description
Building scalable enterprise desktop client environments requires a strict adherence to modular software boundaries. This project initializes **MyAssistant**, delivering a decoupled **Layered Architecture** implementation using Java Swing. The core GUI layer (**`TaskPanel`**) serves strictly as a presentation anchor, delegating interactive operational mutations (`addTask`, `deleteTask`, `editTask`) to standalone encapsulated objects extending **`AbstractAction`**. Business layers are mapped across distinct operational boundaries (`controller`, `service`, `repository`, `model`), separating data caching mechanics from interactive widgets. Visual look-and-feel configurations are centralized inside a dedicated utility framework to avoid hardcoded interface rules.

## 📋 Requirements Compliance
- **Initial Modular Workspace Setup**: Configured the foundation directory layout aligning to core production directory boundaries.
- **Strict View and Command Decoupling**: Isolated user button interactions away from layout blueprints into independent actions.
- **Centralized Environment Control**: Bound user appearance behaviors and anti-aliasing hints to a static configuration utility.
- **Multi-Layered Context Tracking**: Implemented structural data flows routing safely from view inputs down to memory repositories.

## 🚀 Architectural Stack
- Java 17+ (Java Swing GUI Component Subsystem, Layered Architecture, Command Design Patterns)

## 🏗️ Implementation Details
- **MyAssistantApp**: Main executable class acting as the root orchestrator and application bootstrapper.
- **ui**: Contains frame contexts, structural display panels, and isolated dialogue popups.
- **ui.actions**: Command entities managing standalone validation, selection checks, and backend trigger routing.
- **controller / service / repository / model**: Core business logic layers facilitating underlying entity mutations.
- **util**: Central configuration tool management for graphics smoothing and global theme parameters.

## 📋 Expected result
- Running the root entrypoint builds a uniform, clean interface layout free from rendering shadows or artifacts.
- Modifying tracking items safely executes validation schemes, updates downstream memory caches, and updates the list presentation.

## 💻 Code Example

Project Structure:

    JavaBasics_Task_535/
    ├── src/
    │   └── com/yurii/pavlenko/
    │                 ├── app/
    │                 │   └── MyAssistantApp.java
    │                 │
    │                 ├── ui/
    │                 │   ├── frames/
    │                 │   │   └── TaskFrame.java
    │                 │   ├── panels/
    │                 │   │   └── TaskPanel.java
    │                 │   ├── dialogs/
    │                 │   │   └── TaskDialog.java
    │                 │   └── actions/
    │                 │       ├── AddTaskAction.java
    │                 │       ├── DeleteTaskAction.java
    │                 │       └── EditTaskAction.java
    │                 │
    │                 ├── controller/
    │                 │   └── TaskController.java
    │                 │
    │                 ├── service/
    │                 │   ├── impl/
    │                 │   │   └── TaskServiceImpl.java
    │                 │   └── TaskService.java
    │                 │
    │                 ├── repository/
    │                 │   ├── impl/
    │                 │   │   ├── InMemoryTaskRepositoryImpl.java
    │                 │   │   ├── JsonTaskRepositoryImpl.java
    │                 │   │   └── DatabaseTaskRepositoryImpl.java
    │                 │   └── TaskRepository.java
    │                 │
    │                 ├── model/
    │                 │   └── Task.java
    │                 │
    │                 └── util/
    │                     └── Util.java
    │
    ├── LICENSE
    ├── TASK.md
    ├── THEORY.md
    └── README.md

Code
```java
package com.yurii.pavlenko.app;

import com.yurii.pavlenko.controller.TaskController;
import com.yurii.pavlenko.repository.TaskRepository;
import com.yurii.pavlenko.repository.impl.InMemoryTaskRepositoryImpl;
// import com.yurii.pavlenko.repository.impl.JsonTaskRepositoryImpl;
// import com.yurii.pavlenko.repository.impl.DatabaseTaskRepositoryImpl;
import com.yurii.pavlenko.service.TaskService;
import com.yurii.pavlenko.service.impl.TaskServiceImpl;
import com.yurii.pavlenko.ui.frames.TaskFrame;
import com.yurii.pavlenko.util.Util;

import javax.swing.*;

public class MyAssistantApp {

    public static void main(String[] args) {

        Util.configureLookAndFeel();

        TaskRepository repo = new InMemoryTaskRepositoryImpl();

        // TaskRepository repo = new JsonTaskRepositoryImpl();
        // TaskRepository repo = new DatabaseTaskRepositoryImpl();

        TaskService service = new TaskServiceImpl(repo);
        TaskController controller = new TaskController(service);

        SwingUtilities.invokeLater(() -> new TaskFrame(controller));
    }
}
```

## ⚖️ License
This project is licensed under the **MIT License**.

Copyright (c) 2026 Yurii Pavlenko

Permission is hereby granted, free of charge, to any person obtaining a copy of this software and associated documentation files...

License: [MIT](LICENSE)
