import java.net.*; 
import java.io.*; 
import java.util.*;

public class ListServer {
	public ListServer(int port) throws IOException { 
	      ServerSocket server = new ServerSocket (port); 
	      while (true) { 
	    	 System.out.println("Entered while loop, listening for clients");
	         Socket client = server.accept(); 
	         System.out.println("client accepted");
	         ObjectOutputStream out = new ObjectOutputStream(client.getOutputStream());
	         out.flush();
	         System.out.println("Outputstream created");
	         ObjectInputStream in = new ObjectInputStream(client.getInputStream());
	         System.out.println("Inputstream created");
	         String name = "null";
	         System.out.println(name + " <-- should be null");
			 
	         try {
				name = (String) in.readObject();
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	         System.out.println(name);
			
			
	         System.out.println ("New client "+name+" from " + client);
	         ListHandler c = new ListHandler(name, client, out, in); 
	         c.start(); 
	      } 
	   }
	  
	   public static void main (String args[]) throws IOException { 
	      if (args.length != 1) 
	         throw new RuntimeException ("Syntax: java ChatServer <port>"); 
	      new ListServer (Integer.parseInt(args[0])); 
	   }
}
