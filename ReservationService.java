package services;

import java.util.ArrayList;
import java.util.Scanner;

import models.Reservation;
import models.Room;

public class ReservationService {
    private ArrayList<Room> rooms;
    private ArrayList<Reservation> reservations;
    private int reservationCounter;

    public ReservationService() {
        rooms = new ArrayList<>();
        reservations = new ArrayList<>();
        reservationCounter = 1;

        // Sample rooms
        rooms.add(new Room(101, "Single", 50.0, true));
        rooms.add(new Room(102, "Double", 80.0, true));
        rooms.add(new Room(103, "Suite", 120.0, true));
    }

    public void run() {
        Scanner scanner = new Scanner(System.in);
        boolean exit = false;

        while (!exit) {
            System.out.println("\nMenu:");
            System.out.println("1. View Available Rooms");
            System.out.println("2. Make a Reservation");
            System.out.println("3. View Reservations");
            System.out.println("4. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    viewAvailableRooms();
                    break;
                case 2:
                    makeReservation(scanner);
                    break;
                case 3:
                    viewReservations();
                    break;
                case 4:
                    exit = true;
                    break;
                default:
                    System.out.println("Invalid option. Try again.");
            }
        }

        scanner.close();
    }

    private void viewAvailableRooms() {
        System.out.println("\nAvailable Rooms:");
        for (Room room : rooms) {
            if (room.isAvailable()) {
                System.out.printf("Room %d (%s): $%.2f per night\n",
                        room.getRoomNumber(), room.getType(), room.getPricePerNight());
            }
        }
    }

    private void makeReservation(Scanner scanner) {
        System.out.print("\nEnter customer name: ");
        scanner.nextLine(); // Consume newline
        String customerName = scanner.nextLine();

        System.out.print("Enter room number: ");
        int roomNumber = scanner.nextInt();

        System.out.print("Enter check-in date (yyyy-mm-dd): ");
        String checkInDate = scanner.next();

        System.out.print("Enter check-out date (yyyy-mm-dd): ");
        String checkOutDate = scanner.next();

        Room room = findRoomByNumber(roomNumber);
        if (room != null && room.isAvailable()) {
            double totalAmount = room.getPricePerNight(); // Simplified for now
            Reservation reservation = new Reservation(reservationCounter++, customerName,
                    roomNumber, checkInDate, checkOutDate, totalAmount);
            reservations.add(reservation);
            room.setAvailable(false);

            System.out.println("Reservation made successfully!");
        } else {
            System.out.println("Room is not available or invalid room number.");
        }
    }

    private void viewReservations() {
        System.out.println("\nReservations:");
        for (Reservation reservation : reservations) {
            System.out.printf("Reservation #%d: %s booked Room %d from %s to %s ($%.2f)\n",
                    reservation.getReservationId(), reservation.getCustomerName(),
                    reservation.getRoomNumber(), reservation.getCheckInDate(),
                    reservation.getCheckOutDate(), reservation.getTotalAmount());
        }
    }

    private Room findRoomByNumber(int roomNumber) {
        for (Room room : rooms) {
            if (room.getRoomNumber() == roomNumber) {
                return room;
            }
        }
        return null;
    }
}
