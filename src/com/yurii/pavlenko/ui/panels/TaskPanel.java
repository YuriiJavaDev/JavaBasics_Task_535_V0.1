package com.yurii.pavlenko.ui.panels;

import com.yurii.pavlenko.controller.TaskController;
import com.yurii.pavlenko.ui.actions.AddTaskAction;
import com.yurii.pavlenko.ui.actions.DeleteTaskAction;
import com.yurii.pavlenko.ui.actions.EditTaskAction;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * Structural view panel layout encapsulating core components and mounting command action maps.
 */
public class TaskPanel extends JPanel {

    private JTextField input;
    private JButton addButton;
    private JButton deleteButton;

    private DefaultListModel<String> listModel;
    private JList<String> taskList;

    private AddTaskAction addTaskAction;
    private DeleteTaskAction deleteTaskAction;
    private EditTaskAction editTaskAction;

    /**
     * Instantiates components and sets up clean behavioral command structures.
     */
    public TaskPanel(TaskController controller) {
        initializeComponents();
        initializeActions(controller);
        initializeButtons();
        initializeLayout();
        initializeListeners();

        refreshTasks(controller);
    }

    private void initializeComponents() {
        input = new JTextField(50);
        listModel = new DefaultListModel<>();
        taskList = new JList<>(listModel);
    }

    private void initializeActions(TaskController controller) {
        addTaskAction = new AddTaskAction(controller, input, () -> refreshTasks(controller));
        deleteTaskAction = new DeleteTaskAction(controller, taskList, () -> refreshTasks(controller));
        editTaskAction = new EditTaskAction(controller, taskList, listModel, this, () -> refreshTasks(controller));
    }

    private void initializeButtons() {
        addButton = new JButton(addTaskAction);
        deleteButton = new JButton(deleteTaskAction);
    }

    private void initializeLayout() {
        setLayout(new BorderLayout());

        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));

        topPanel.add(input);
        topPanel.add(addButton);
        topPanel.add(deleteButton);

        add(topPanel, BorderLayout.NORTH);
        add(new JScrollPane(taskList), BorderLayout.CENTER);
    }

    private void initializeListeners() {
        taskList.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    editTaskAction.actionPerformed(null);
                }
            }
        });
    }

    /**
     * Publicly accessible visibility method to push structural data models into the view list layout.
     */
    public void refreshTasks(TaskController controller) {
        listModel.clear();
        controller.getTasks().forEach(task -> listModel.addElement(task.getTitle()));
    }
}
