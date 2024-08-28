package com.example.OWASP10.DBTests;

import Database.PostgresJDBC;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.Statement;

import static org.mockito.Mockito.*;

public class Database_Tests {

    @InjectMocks
    private PostgresJDBC dbConnection;

    @Mock
    private Connection mockConnection;

    @Mock
    private Statement mockStatement;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testMockConnection() throws Exception {
//       // when(mockConnection.createStatement()).thenReturn(mockStatement);
//       // Connection c;
//        //when(dbConnection.QueryDB(Mockito.any(Integer[].class))).thenReturn(new int[]{1, 2, 3});
//        PostgresJDBC test = Mockito.mock(PostgresJDBC.class);
//        when(test.QueryDB(mockConnection,"mock")).thenReturn(new int[]{1, 2, 3});
//        int[] value = dbConnection.QueryDB(mockConnection,"mock");
////        Assert.assertArrayEquals(value, new int[]{1, 2,3});
////        Mockito.verify(mockConnection.createStatement(), Mockito.times(1));
//        Assert.assertArrayEquals(value,(new int[]{1,2,3}));
//        Mockito.when(dbConnection.connect()).thenReturn(mockConnection);
//        Connection c  = dbConnection.connect();
//        Assert.assertNotNull(c);

    }


}