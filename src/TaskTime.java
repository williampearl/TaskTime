import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class TaskTime {
    public static void main(String[] args) throws IOException {
        if(!SystemTray.isSupported()) {
            System.out.println("System tray is not supported.");
            System.exit(0);
        }
        PopupMenu popup = new PopupMenu();
        SystemTray tray = SystemTray.getSystemTray();
        TrayIcon trayIcon = new TrayIcon(ImageIO.read(new File("src/images/trayicon.gif")), "TaskTime");
        trayIcon.setImageAutoSize(true);
        // Popup menu components
        MenuItem aboutItem = new MenuItem("About");
        CheckboxMenuItem cb1 = new CheckboxMenuItem("Set auto size.");

        popup.add(aboutItem);
        popup.add(cb1);
        trayIcon.setPopupMenu(popup);
        try {
            tray.add(trayIcon);
        } catch(AWTException e) {
            System.err.println("Could not add tray icon to tray!");
        }
        while(true);
    }
}