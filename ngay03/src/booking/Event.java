package booking;

public class Event {
    private String eventId;
    private String eventName;
    private String location;
    private String date;
    private int maxSeats;

    public Event(String eventId, String eventName, String location, String date, int maxSeats) {
        this.eventId = eventId;
        this.eventName = eventName;
        this.location = location;
        this.date = date;
        this.maxSeats = maxSeats;
    }

    public String getEventId() { return eventId; }
    public String getEventName() { return eventName; }
    public String getLocation() { return location; }
    public String getDate() { return date; }
    public int getMaxSeats() { return maxSeats; }

    @Override
    public String toString() {
        return "EventID: " + eventId + ", Name: " + eventName + ", Location: " + location +
                ", Date: " + date + ", Max Seats: " + maxSeats;
    }
}
