package com.assignment.autodesk.controllers;

import com.assignment.autodesk.model.ReplyMessage;
import com.assignment.autodesk.services.ReplyService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v2/reply/")
@RequiredArgsConstructor
public class ReplyController {


    @Autowired
    private  ReplyService replyService;

    @GetMapping("{request}")
    public ResponseEntity<ReplyMessage> processStringV2(@PathVariable String request) {

        boolean isValid = replyService.isValidString(request);
        if (!isValid) {
            // Return 400 Bad Request with error message
            ReplyMessage errorMessage = new ReplyMessage("Invalid input");
            return ResponseEntity.badRequest().body(errorMessage);
        }

        String[] arr=request.split("-");
        String rule=arr[0];
        String string=arr[1];
        // Valid input - Process the string
        String processedString = replyService.processString(rule, string);
        ReplyMessage response = new ReplyMessage(processedString);
        return ResponseEntity.ok(response);

    }
}
