package com.assignment.autodesk.Controllers;

import com.assignment.autodesk.controllers.ReplyController;
import com.assignment.autodesk.model.ReplyMessage;
import com.assignment.autodesk.services.ReplyService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.lenient;

@ExtendWith(MockitoExtension.class)
public class ReplyControllerTest {
    @Mock
    private ReplyService replyService;
    @InjectMocks
    private ReplyController replyController;

    @BeforeEach
    public void setup(){
        lenient().when(replyService.isValidString(any())).thenReturn(true);
        lenient().when(replyService.processString("11", "aman")).thenReturn("aman");
    }

    @Test
    public void processStringV2_WithValidInput_Returns200() {

        // Arrange
        String request = "11-aman";
        String expectedResponse = "aman";

        // Act
        ResponseEntity<ReplyMessage> actualResponseEntity = replyController.processStringV2(request);

        // Assert
        Assertions.assertEquals(HttpStatus.OK, actualResponseEntity.getStatusCode());
        Assertions.assertEquals(expectedResponse, actualResponseEntity.getBody().getMessage().toString());
    }



}