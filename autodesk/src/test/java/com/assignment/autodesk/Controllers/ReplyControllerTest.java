package com.assignment.autodesk.Controllers;

import com.assignment.autodesk.controllers.ReplyController;
import com.assignment.autodesk.model.ReplyMessage;
import com.assignment.autodesk.services.ReplyService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Objects;

import static org.junit.jupiter.api.Assertions.assertEquals;;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class ReplyControllerTest {
    @Mock
    ReplyService replyService = Mockito.mock(ReplyService.class);

    @InjectMocks
    ReplyController replyController;

    @Test
    public void testProcessStringV2WithInvalidInput() {
        String invalidInput = "13-aman";
        when(replyService.isValidString(invalidInput)).thenReturn(false);

        ResponseEntity<ReplyMessage> responseEntity = replyController.processStringV2(invalidInput);

        assertEquals(HttpStatus.BAD_REQUEST, responseEntity.getStatusCode());
        assertEquals("Invalid input", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }

    @Test
    public void testProcessStringV2WithValidInput() {
        String validInput = "11-aman";
        when(replyService.isValidString(validInput)).thenReturn(true);
        when(replyService.processString("11", "aman")).thenReturn("aman");

        ResponseEntity<ReplyMessage> responseEntity = replyController.processStringV2(validInput);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("aman", Objects.requireNonNull(responseEntity.getBody()).getMessage());
    }



}