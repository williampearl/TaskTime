package TaskTime;
import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

/**
 * TaskTimeTray is a singleton class which represents the system tray icon.
 */
public class TaskTimeTray {
    private static final String title = "TaskTime";
    private static final SystemTray tray = SystemTray.getSystemTray();
    private static final PopupMenu popup = new PopupMenu();
    public static TaskTimeTray instance = new TaskTimeTray();
    private TaskTimeTray() {
        // TODO think of a better thing to do if the image is not found. It will crash if the image isn't found.

        try {
            URL trayiconURL = getClass().getResource("/trayicon.gif");
            TrayIcon trayIcon = new TrayIcon(ImageIO.read(new File(trayiconURL.toURI())), title);
            trayIcon.setImageAutoSize(true);
            trayIcon.setPopupMenu(popup);

            try {
                tray.add(trayIcon);
            } catch(AWTException e) {
                System.err.println("Could not add tray icon to tray!");
            }
        } catch(IOException | URISyntaxException e) {
            // could not load image
            System.err.println("Unable to load icon.");
        }
    }

    /**
     * @return singleton TaskTimeTray
     */
    public static TaskTimeTray get() {
        return instance;
    }

    /**
     * Enables or disables the icon.
     * @param state - true = enable, false = disable
     */
    public static void setEnabled(boolean state) {
        popup.setEnabled(state);
    }

}
