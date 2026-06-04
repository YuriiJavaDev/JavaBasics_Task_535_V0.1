package com.yurii.pavlenko.util;

import javax.swing.UIManager;

/**
 * Global operational utilities handling interface initialization, text styling configurations, and theme parameters.
 */
public class Util {

    /**
     * Bootstraps clean global Look and Feel themes and forces text edge smoothing routines.
     */
    public static void configureLookAndFeel() {
        try {
            // Force strict system rendering text antialiasing hints for sub-components
            System.setProperty("awtextra.robot.graphics.smoothing", "true");
            System.setProperty("swing.aatext", "true");

            // Iterate and assign clean modern Nimbus layout engine to bypass legacy Windows artifact anomalies
            for (UIManager.LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // Safe fallback trace context if the environment parameters reject modern themes
            System.err.println("Theme management execution failed. Falling back to core system themes.");
        }
    }
}