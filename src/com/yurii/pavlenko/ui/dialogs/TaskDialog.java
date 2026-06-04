package com.yurii.pavlenko.ui.dialogs;

import java.awt.Component;
import javax.swing.JOptionPane;

/**
 * Visual modal helper wrapping standardized user entry forms.
 */
public class TaskDialog {

    /**
     * Dispatches a modal inputs dialog sequence and returns validated text modifications.
     *
     * @param parentComponent Visual parent layer anchor for centering dialog footprints.
     * @param currentText Base initial fallback text displayed within the entry form field.
     * @param title Custom display name header for the operational dialog border overlay.
     * @return Validated updated input string token, or null if the action path was dropped.
     */
    public static String showEditDialog(Component parentComponent, String currentText, String title) {
        // Используем перегруженный метод с 7 параметрами, чтобы передать текст строки
        Object inputResult = JOptionPane.showInputDialog(
                parentComponent,             // 1. Родительский компонент
                "Edit task description:",    // 2. Сообщение-подсказка над полем ввода
                title,                       // 3. Заголовок окна
                JOptionPane.PLAIN_MESSAGE,   // 4. Тип окна (без лишних иконок)
                null,                        // 5. Иконка (null - по умолчанию)
                null,                        // 6. Массив вариантов (null, чтобы было обычное текстовое поле)
                currentText                  // 7. ИСПРАВЛЕНО: Текст, который автоматически заполнит поле!
        );

        // Если пользователь нажал Cancel или закрыл крестиком, вернётся null
        if (inputResult == null) {
            return null;
        }

        return inputResult.toString();
    }
}