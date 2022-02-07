package com.chat.service;

import com.chat.controller.ConnexionControllerImpl;
import com.chat.dao.ConnexionDaoImpl;
import com.chat.exception.PseudoAlreadyTakenException;
import com.chat.exception.PseudoTooLongException;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ConnexionServiceImpl.class, ConnexionDaoImpl.class,
ConnexionControllerImpl.class})
@SpringBootTest
public class ConnexionServiceTest {

    public static final String PSEUDO1 = "pseudo1";
    public static final String PSEUDO2 = "pseudoWayyyyyyyTooLong";
    public static final String PSEUDO3 = "pseudo3";

    @Autowired
    ConnexionServiceImpl connexionService;

    @Mock
    ConnexionDaoImpl connexionDao;

    @Mock
    ConnexionControllerImpl connexionController;

    @Before
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void connectTest()  throws PseudoAlreadyTakenException, PseudoTooLongException {
        String result = connexionService.connect(PSEUDO1);
        Assert.assertTrue(PSEUDO1.equals(result));
    }

    @Test(expected=PseudoTooLongException.class)
    public void connectPseudoTooLongTest()  throws PseudoAlreadyTakenException, PseudoTooLongException {
        connexionService.connect(PSEUDO2);
    }

    @Test(expected=PseudoAlreadyTakenException.class)
    public void connectPseudoAlreadyTakenTest()  throws PseudoAlreadyTakenException, PseudoTooLongException {
        connexionService.connect(PSEUDO3);
        connexionService.connect(PSEUDO3);
    }

    @Test
    public void disconnectTest() {
        connexionService.disconnect();
    }
}
