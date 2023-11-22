package com.assignment.autodesk.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(MockitoExtension.class)
public class ReplyServiceTest {

    @Test
    public void testProcessString() {
        // Test valid input
        ReplyService service = new ReplyService();
        String result = service.processString("12", "test123");
        assertEquals("6a5472077778e49ce2635dc1cde38506", result);

    }

    @Test
    public void testIsValidString() {
        ReplyService service = new ReplyService();

        // Test valid input
        boolean result = service.isValidString("11-test123");
        assertTrue(result);

        result = service.isValidString("22-abcd5678efgh");
        assertTrue(result);


    }

    @Test
    public void testIsInValidString() {
        ReplyService service = new ReplyService();

        // Test Invalid input
        boolean result = service.isValidString("33-test123");
        assertFalse(result);

        result=service.isValidString("23444444");
        assertFalse(result);

        result=service.isValidString("23");
        assertFalse(result);

        result=service.isValidString("AMANKUMAR");
        assertFalse(result);
    }

}