package com.DelivaryTrackingSystem;

import java.io.*;
import java.util.*;

public class ParcelManager {
    private static final String FILE_NAME = "parcels.txt";
    private static final String[] cities = {"Delhi", "Mumbai", "Bangalore","Hyderabad", "Chennai", "Kolkata"};

    public void addParcel(Parcel parcel) throws IOException {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
            writer.write(parcel.toString());
            writer.newLine();
        }
    }

    public List<Parcel> getAllParcels() throws IOException {
        List<Parcel> parcels = new ArrayList<>();
        File file = new File(FILE_NAME); 
        if (!file.exists()) return parcels;

        try (BufferedReader reader = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = reader.readLine()) != null) {
                parcels.add(Parcel.fromString(line));
            }
        }
        return parcels;
    }

    public Parcel findParcel(String trackingId) throws IOException {
        for (Parcel p : getAllParcels()) {
            if (p.getTrackingId().equals(trackingId)) {
                return p;
            }
        }
        return null;
    }

    public void updateParcel(Parcel updatedParcel) throws IOException {
        List<Parcel> parcels = getAllParcels();
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
            for (Parcel p : parcels) {
                if (p.getTrackingId().equals(updatedParcel.getTrackingId())) {
                    writer.write(updatedParcel.toString());
                } else {
                    writer.write(p.toString());
                }
                writer.newLine();
            }
        }
    }

    public void simulateMovement(Parcel parcel) throws IOException {
        Random random = new Random();
        String newCity = cities[random.nextInt(cities.length)];
        String[] statuses = {"In Transit", "Arrived at Hub", "Out for Delivery", "Delivered"};
        String newStatus = statuses[random.nextInt(statuses.length)];

        parcel.updateStatus(newCity, newStatus);
        updateParcel(parcel);
        System.out.println("Parcel updated: " + newCity + " - " + newStatus);
    }
}
