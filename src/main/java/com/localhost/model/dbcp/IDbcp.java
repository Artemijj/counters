package com.localhost.model.dbcp;

import java.sql.Connection;

public interface IDbcp {
//    BasicDataSource getDataSource();
    Connection getConnection();
}
