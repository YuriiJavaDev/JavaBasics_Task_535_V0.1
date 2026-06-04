package com.yurii.pavlenko.ui.frames;

import com.yurii.pavlenko.controller.TaskController;
import com.yurii.pavlenko.ui.panels.TaskPanel;

import javax.swing.*;

public class TaskFrame extends JFrame {

    public TaskFrame(TaskController controller) {

        super("Task Manager");

        TaskPanel panel = new TaskPanel(controller);

        add(panel);

        setSize(800, 640);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setVisible(true);
    }
}