public class Main {
    public static void main(String[] args) {
        // javax.swing.SwingUtilities.invokeLater(Login::createLoginWindow);
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run(){
                LoginFrame.createTicketReservationFrame("12287952565");
            }
        });
    }
}
