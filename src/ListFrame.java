import java.awt.*;

public class ListFrame extends Frame { 

   protected TextArea output; 
   protected TextField input;

   protected Thread listener;
  
   public ListFrame(String title){
      super (title); 
    
      setLayout (new BorderLayout()); 
      add ("Center", output = new TextArea()); 
      output.setEditable (false); 
      add ("South", input = new TextField()); 
    
      pack(); 
      show();
      input.requestFocus(); 
      
      this.setVisible(true);
   }
  
//   public static void main (String args[]) { 
//      new ListFrame("Chat "); 
//   }
}
