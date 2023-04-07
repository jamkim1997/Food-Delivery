package model;

import java.util.ArrayList;

public class AppStaff {

    private final int userID;
    private final int staffID;

    public AppStaff(int userID, int staffID) {
        this.userID = userID;
        this.staffID = staffID;
    }

    public int getUserID() {
        return userID;
    }

    public int getStaffID() {
        return staffID;
    }

}
