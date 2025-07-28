package com.DelivaryTrackingSystem;

import java.util.*;

public class Parcel {
    private String trackingId;
    private String sender;
    private String receiver;
    private String currentCity;
    private String status;

    public Parcel(String trackingId, String sender, String receiver, String currentCity, String status) {
        this.trackingId = trackingId;
        this.sender = sender;
        this.receiver = receiver; 
        this.currentCity = currentCity;
        this.status = status;
    }

    public String getTrackingId() { return trackingId; }
    public String getStatus() { return status; }
    public String getCurrentCity() { return currentCity; }

    public void updateStatus(String city, String status) {
        this.currentCity = city;
        this.status = status;
    }

    @Override
    public String toString() {
        return trackingId + "," + sender + "," + receiver + "," + currentCity + "," + status;
    }

    public static Parcel fromString(String line) {
        String[] parts = line.split(",");
        return new Parcel(parts[0], parts[1], parts[2], parts[3], parts[4]);
    }
}