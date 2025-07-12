package com.NextTier.Transaction.controllers;

import com.NextTier.Transaction.models.Transaction;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.fasterxml.jackson.databind.ObjectMapper;
import software.amazon.awssdk.auth.credentials.DefaultCredentialsProvider;
import software.amazon.awssdk.services.dynamodb.DynamoDbClient;

import software.amazon.awssdk.services.dynamodb.model.AttributeValue;
import software.amazon.awssdk.services.dynamodb.model.PutItemRequest;
import software.amazon.awssdk.regions.Region;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin(origins = "http://localhost:3000")
@RequestMapping("/transactions")
@RestController
public class TransactionController {

    @PostMapping
    public ResponseEntity<String> receiveTransaction(@RequestBody Transaction txn) {
        try {

            // Put item into DynamoDB
            Region region = Region.US_EAST_1;
            DynamoDbClient ddb = DynamoDbClient.builder()
                    .region(region)
                    .build();

            // in aws dynamo document
            Map<String, AttributeValue> itemValues = new HashMap<>();
            itemValues.put("TransactionId", AttributeValue.builder().s(txn.getTransactionId()).build());
            itemValues.put("UserId", AttributeValue.builder().s(txn.getUserId()).build());
            itemValues.put("Amount", AttributeValue.builder().n(String.valueOf(txn.getAmount())).build());
            itemValues.put("Timestamp", AttributeValue.builder().n(String.valueOf(txn.getTimestamp())).build());

            PutItemRequest request = PutItemRequest.builder()
                    .tableName("transaction_logs")
                    .item(itemValues)
                    .build();

            ddb.putItem(request);

            return ResponseEntity.ok("Transaction uploaded to DynamoDB.");
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("Error uploading transaction: " + e.getMessage());
        }
    }

}
