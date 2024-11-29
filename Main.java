import services.ReservationService;

public class Main {
    public static void main(String[] args) {
        System.out.println("Welcome to the Hotel Reservation System!");
        ReservationService reservationService = new ReservationService();
        reservationService.run();
    }
}
