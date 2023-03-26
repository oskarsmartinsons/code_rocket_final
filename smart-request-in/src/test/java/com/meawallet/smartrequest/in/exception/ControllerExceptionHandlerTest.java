//package com.meawallet.smartrequest.in.exception;
//
//import org.junit.jupiter.api.Test;
//import org.mockito.InjectMocks;
//import org.mockito.Mock;
//import org.mockito.junit.jupiter.MockitoSettings;
//import org.springframework.http.HttpStatus;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.when;
//
//@MockitoSettings
//class ControllerExceptionHandlerTest {
//    @Mock
//    IllegalArgumentException illegalArgumentException;
//    @InjectMocks
//    ControllerExceptionHandler controllerExceptionHandler;
//    @Test
//    void handleExceptions() {
//    }
//
//    @Test
//    void handleIllegalArgumentException() {
//        var expectedMessage = "InvalidArgument";
//
//        when(illegalArgumentException.getMessage()).thenReturn(expectedMessage);
//
//        var actualMessage = controllerExceptionHandler.handleIllegalArgumentException(illegalArgumentException);
//
//        assertEquals(HttpStatus.NOT_FOUND, controllerExceptionHandler.);
//        assertEquals(expectedMessage, actualMessage);
//
//    }
//}