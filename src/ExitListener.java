import java.awt.event.*;

public class ExitListener extends WindowAdapter {

   ListClient client;

   public ExitListener(ListClient client) {
      this.client = client;
   }
      
   public void windowClosing(WindowEvent e) {
      client.disconnect();
      System.exit(0);
   }
}
