package com.DelivaryTrackingSystem;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ParcelManager manager = new ParcelManager();
        boolean running = true;

        while (running) {
            System.out.println("\n📦 Parcel Tracking System");
            System.out.println("1. Create new parcel");
            System.out.println("2. Track parcel");
            System.out.println("3. Simulate movement");
            System.out.println("4. Exit");
            System.out.print("Select option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            try {
                switch (choice) {
                    case 1:
                        System.out.print("Sender name: ");
                        String sender = scanner.nextLine();
                        System.out.print("Receiver name: ");
                        String receiver = scanner.nextLine();
                        String trackingId = UUID.randomUUID().toString().substring(0, 8).toUpperCase();
                        Parcel parcel = new Parcel(trackingId, sender, receiver, "Origin Warehouse", "Created");
                        manager.addParcel(parcel);
                        System.out.println("Parcel created. Tracking ID: " + trackingId);
                        break;
                    case 2:
                        System.out.print("Enter tracking ID: ");
                        String tid = scanner.nextLine().trim();
                        Parcel found = manager.findParcel(tid);
                        if (found != null) {
                            System.out.println("Current City: " + found.getCurrentCity());
                            System.out.println("Status: " + found.getStatus());
                        } else {
                            System.out.println("Parcel not found.");
                        }
                        break;
                    case 3:
                        System.out.print("Enter tracking ID to simulate: ");
                        String simId = scanner.nextLine().trim();
                        Parcel toUpdate = manager.findParcel(simId);
                        if (toUpdate != null) {
                            manager.simulateMovement(toUpdate);
                        } else {
                            System.out.println("Parcel not found.");
                        }
                        break;
                    case 4:
                        running = false;
                        break;
                    default:
                        System.out.println("Invalid option.");
                }
            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        System.out.println("Exiting... Thank you!");
        scanner.close();
    } 
}
