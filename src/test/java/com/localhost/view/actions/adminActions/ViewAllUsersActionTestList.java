package com.localhost.view.actions.adminActions;

import com.localhost.in.*;
import com.localhost.model.model.IModel;
import com.localhost.model.model.TestModelJdbc;
import com.localhost.view.TestInputOutput;
import com.localhost.view.actions.IAction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.testcontainers.containers.ComposeContainer;
import org.testcontainers.junit.jupiter.Container;
import org.testcontainers.junit.jupiter.Testcontainers;

import java.io.File;
import java.sql.Connection;
import java.time.Duration;

@Testcontainers
public class ViewAllUsersActionTestList {
    @Container
    public static ComposeContainer dockerComposeContainer = new ComposeContainer(new File("./src/test/resources/docker-compose.yml"))
//            .withExposedService("db", 5433)
            .withLocalCompose(true)
            .withStartupTimeout(Duration.ofSeconds(30));
    private IModel model = new TestModelJdbc();
    private Connection connection = model.getCon();
    private IUserSession userSession = new TestUserSession();

    private ViewAllUsersAction viewAllUsersAction;

    @BeforeEach
    public void setUp() {
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        viewAllUsersAction = new ViewAllUsersAction();
    }

    @Test
    public void adminPageTest() {
        TestInputOutput tio = new TestInputOutput("p");
        IAction actual = viewAllUsersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(AdminPageAction.class, actual);
    }

    @Test
    public void thisTest() {
        TestInputOutput tio = new TestInputOutput("q");
        IAction actual = viewAllUsersAction.execute(userSession, tio);
        Assertions.assertInstanceOf(ViewAllUsersAction.class, actual);
    }
}
