package jeong.pro.messenger.client.gui.messenger;

import javax.imageio.plugins.jpeg.JPEGHuffmanTable;
import javax.swing.*;
import javax.swing.event.ListDataListener;
import javax.swing.table.TableColumn;
import javax.swing.table.TableModel;
import java.awt.*;

public class SessionPanel extends JPanel {
    private JTable sessionTable = null;
    private JScrollPane jScrollPane = null;


    public SessionPanel() {
        setSessionTable();
        add(jScrollPane);
    }

    private void setSessionTable() {
        String[] columnNames = new String[] {"Friend Name", "Last Message", "Last Time"};
        sessionTable = new JTable(new Object[][] {new Object[] {"John", "Hi", "2018-05-15"}}, columnNames);

        sessionTable.setGridColor(Color.GRAY);
        sessionTable.setBorder(BorderFactory.createBevelBorder(0));
        sessionTable.setAutoResizeMode(1);
        sessionTable.setRowSelectionAllowed(true);
        sessionTable.setCellSelectionEnabled(false);

        TableColumn column = null;
        for(int colSize = 0; colSize < 3; colSize++) {
            column = sessionTable.getColumnModel().getColumn(colSize);
            column.setPreferredWidth(150);
        }

        sessionTable.setFillsViewportHeight(true);
        jScrollPane = new JScrollPane(sessionTable);
    }
}
