package data;

import javax.swing.JPanel;

import ui.helpers.RowHelper;
import auth.Auth;

public class Retrieve {
    /**
     * Retrieves a new ID from the database. This ID can be used to save a new complaint.
     * 
     * @return - a new ID from the database
     */
    public static int getNewID() {
        // return new ID from database
        int ID = 99;
        return ID;
    }

    public static void showComplaints(JPanel body, String username) {
        // add complaints to body using RowHelper.addRow(...)
        if (!Auth.isUser(username)) {
            // user not found
            return;
        }
        if (Auth.isAdmin(username)) {
            // show all complaints
            // TODO: get all complaints from database
            RowHelper.addRow(body, "Subject 1", "High", 1);
            RowHelper.addRow(body, "Subject 2", "Low", 2);
            RowHelper.addRow(body, "Subject 3", "Medium", 3);
        } else {
            // show only user's complaints
            // TODO: get user's complaints from database
            RowHelper.addRow(body, "Subject 1", "High", 1);
            RowHelper.addRow(body, "Subject 2", "Low", 2);
        }
    }
}
