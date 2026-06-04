## Technical Specification (TS): Development of a Modular Task Manager using Java Swing
#### Project Objective: Create a desktop application named "MyAssistantApp" for managing a list of tasks. The project must demonstrate the practical application of Layered Architecture and Clean Code principles.

### Functional Requirements:

#### 1. Add Task: The user enters text into a text field and saves it to the list by clicking the "Add" button. The input field must be cleared immediately after saving.

#### 2. Delete Task: The user selects a task from the list and removes it by clicking the "Delete" button.

#### 3. Edit Task: The user double-clicks an existing task in the list to modify its text. This action must trigger a modal window (`TaskDialog`), pre-populated with the current text of the selected task.

### Architectural and Technical Requirements:

#### 1. Single Responsibility Principle (SRP): The graphical `TaskPanel` container must focus exclusively on structural layouts and UI components. All action processing logic (adding, deleting, editing) must be decoupled into standalone command classes under the `ui.actions` package, extending Swing's `AbstractAction` (Command Pattern).

#### 2. Multi-Layered Architecture: The codebase must be strictly decoupled into isolated, independent architecture layers: `ui` (Presentation) ➡️ `controller` (Orchestration) ➡️ `service` (Business Logic) ➡️ `repository` (Data Access).

#### 3. Data Persistence Strategy: Implement a decoupled `TaskRepository` interface to enable the Dependency Inversion Principle. The baseline implementation must leverage an in-memory runtime cache (`InMemoryTaskRepositoryImpl`).

#### 4. Centralized Look & Feel Management: Global visual environment styling configurations, Look and Feel setups, and text anti-aliasing configurations must be isolated within a separate `util.Util` class. These parameters must initialize prior to assembling any graphical components to prevent host operating system rendering anomalies.
#### 5. Coding Standards: Source files, naming conventions, and inline documentation must be authored strictly in English, following industry-standard **Clean Code** conventions.
