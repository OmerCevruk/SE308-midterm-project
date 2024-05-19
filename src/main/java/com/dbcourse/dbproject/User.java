package com.dbcourse.dbproject;

import java.sql.SQLException;

public interface User {
    public double transaction(int isolationLevel) throws SQLException;
}
