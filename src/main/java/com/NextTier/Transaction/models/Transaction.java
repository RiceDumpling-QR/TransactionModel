package com.NextTier.Transaction.models; // adjust to match your structure

public class Transaction {
    private String transactionId;
    private String userId;
    private double amount;
    private long timestamp;

    public Transaction() {
    }

    public Transaction(String transactionId, String userId, double amount, long timestamp) {
        this.transactionId = transactionId;
        this.userId = userId;
        this.amount = amount;
        this.timestamp = timestamp;
    }

    // Getters and Setters
    public String getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(String transactionId) {
        this.transactionId = transactionId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
