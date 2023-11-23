package com.assignment.autodesk.services;

import com.assignment.autodesk.exception.EncodingException;
import org.springframework.stereotype.Service;
import javax.xml.bind.DatatypeConverter;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;


@Service
public class ReplyService {

    public  String processString(String rule, String string) {

        char[] ruleComponents = rule.toCharArray();
        int[] ruleNumbers = new int[ruleComponents.length];
        for (int i = 0; i < ruleNumbers.length; i++) {
            ruleNumbers[i] = Integer.parseInt(String.valueOf(ruleComponents[i]));
        }

        // Apply the rules
        for (int ruleNumber : ruleNumbers) {
            if(ruleNumber==1){
                string=reverseString(string);
            }
            else if(ruleNumber==2){
                string=encodeString(string);
            }
        }

        return string;
    }

    private String reverseString(String string) {
        return new StringBuilder(string).reverse().toString();
    }

    private String encodeString(String string) {
        try {
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] digest = md.digest(string.getBytes(StandardCharsets.UTF_8));
            return DatatypeConverter.printHexBinary(digest).toLowerCase();
        } catch (Exception e) {
            throw new EncodingException("Failed to encode string");
        }
    }

    public  boolean isValidString(String input) {
        // Check length
        if (input.length() < 4) {
            // Input is too short
            return false;
        }

        // Check first two characters
        String firstTwoChars = input.substring(0, 2);
        if (!(firstTwoChars.equals("11") || firstTwoChars.equals("12") || firstTwoChars.equals("21") || firstTwoChars.equals("22"))) {
            // First two characters are not formed from the combination of 1 and 2
            return false;
        }

        // Check third character
        if (!input.substring(2, 3).equals("-")) {
            // Third character is not '-'
            return false;
        }

        // Check rest of string
        String restOfString = input.substring(3);
        if (!restOfString.matches("[a-z0-9]+")) {
            // Rest of string contains invalid characters
            return false;
        }

        // Input is valid
        return true;
    }
}
