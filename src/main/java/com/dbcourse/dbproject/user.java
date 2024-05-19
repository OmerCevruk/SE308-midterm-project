package com.dbcourse.dbproject;

import java.sql.Connection;
import java.sql.SQLException;

public interface user {
    public long transaction(int isolationLevel) throws SQLException;
}
