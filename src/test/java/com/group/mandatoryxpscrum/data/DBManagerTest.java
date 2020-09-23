package com.group.mandatoryxpscrum.data;

import org.junit.jupiter.api.Test;

import java.sql.Connection;

import static org.junit.jupiter.api.Assertions.*;

class DBManagerTest {

    @Test
    void getConnectionTest() {
        Connection connection = DBManager.getConnection();
        assertEquals(connection!=null, true);
    }

}