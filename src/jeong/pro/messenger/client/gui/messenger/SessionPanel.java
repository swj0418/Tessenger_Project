package jeong.pro.messenger.client.gui.messenger;

import javax.swing.*;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class SessionPanel extends JPanel {
    private JTable sessionTable = new JTable();

    public SessionPanel() {
        setSessionTable();
        add(sessionTable);
    }

    private void setSessionTable() {
        sessionTable.setAutoResizeMode(1);
        sessionTable.addColumn(new TableColumn(0, 100));
        sessionTable.addColumn(new TableColumn(1, 100));
        sessionTable.setGridColor(Color.GRAY);

        sessionTable.setRowSelectionAllowed(true);
        sessionTable.setBorder(BorderFactory.createBevelBorder(0));

    }
}
