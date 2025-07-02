package booking;

public class Booking {
    private String userEmail;
    private String eventId;
    private int seatNumber;

    public Booking(String userEmail, String eventId, int seatNumber) {
        this.userEmail = userEmail;
        this.eventId = eventId;
        this.seatNumber = seatNumber;
    }

    public String getUserEmail() { return userEmail; }
    public String getEventId() { return eventId; }
    public int getSeatNumber() { return seatNumber; }

    @Override
    public String toString() {
        return "Email: " + userEmail + ", EventID: " + eventId + ", Seat: " + seatNumber;
    }
}
