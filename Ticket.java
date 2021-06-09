public class Ticket {
    // Private fields
    private String ticketNr, carrierNr, reservationNr, price, pesel, flightPath, departureTime, operatorNr;

    // Constructor without params
    Ticket(){}
    // Constructor with params
    Ticket(String ticketNr, String carrierNr, String reservationNr, String price, String pesel, String flightPath, String departureTime, String operatorNr){
        this.ticketNr = ticketNr;
        this.carrierNr = carrierNr;
        this.reservationNr = reservationNr;
        this.price = price;
        this.pesel = pesel;
        this.flightPath = flightPath;
        this.departureTime = departureTime;
        this.operatorNr = operatorNr;
    }

    // Getters
    public String getTicketNr(){ return this.ticketNr; }
    public String getCarrierNr(){ return this.carrierNr; }
    public String getReservationNr(){ return this.reservationNr; }
    public String getPrice(){ return this.price; }
    public String getPesel(){ return this.pesel; }
    public String getFlightPath(){ return this.flightPath; }
    public String getDepartureTime(){ return this.departureTime; }
    public String getOperatorNr(){ return this.operatorNr; }

    // Setters
    public void setTicketNr(String ticketNr){ this.ticketNr = ticketNr; }
    public void setCarrierNr(String carrierNr){ this.carrierNr = carrierNr; }
    public void setReservationNr(String reservationNr){ this.reservationNr = reservationNr; }
    public void setPrice(String price){ this.price = price; }
    public void setPesel(String pesel){ this.pesel = pesel; }
    public void setFlightPath(String flightPath){ this.flightPath = flightPath; }
    public void setDepartureTime(String departureTime){ this.departureTime = departureTime; }
    public void setOperatorNr(String operatorNr){ this.operatorNr = operatorNr; }

    // toString
    @Override
    public String toString(){        
        return (
            "TicketNr: " + ticketNr +
            "\nCarrierNr: " + carrierNr +
            "\nReservationNr: " + reservationNr +
            "\nPrice: " + price +
            "\nPESEL: " + pesel +
            "\nFlightPath: " + flightPath +
            "\nDepartureTime: " + departureTime +
            "\nOperatorNr: " + operatorNr +
            "\n");
    }
}
