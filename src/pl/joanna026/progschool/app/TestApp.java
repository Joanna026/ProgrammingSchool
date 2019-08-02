package pl.joanna026.progschool.app;

import pl.joanna026.progschool.util.DBUtil;

import java.sql.SQLException;

public class TestApp {

    public static void main(String[] args) throws SQLException {
        DBUtil.connect();
    }
}
