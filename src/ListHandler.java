import java.net.*; 
import java.io.*; 
import java.util.*;

public class ListHandler extends Thread { 
    
   Socket socket; 
   ObjectInputStream in; 
   ObjectOutputStream out;
   String name;
   protected static Vector<ListHandler> handlers = new Vector<ListHandler> ();
   
   /**
	 * Arraylist of serverInfo.
	 */
	private static ArrayList<ServerInfo> serverList = 
			new ArrayList<ServerInfo>();
    
   public ListHandler(String name, Socket socket, ObjectOutputStream out, ObjectInputStream in) throws IOException { 
      this.name = name;
      this.socket = socket;
      this.out = out;
      this.in= in;
//      out = new ObjectOutputStream(socket.getOutputStream());
//      out.flush();
//      in = new ObjectInputStream(socket.getInputStream());
      
   } 
    
   public void run () { 

      try { 
         //broadcast(name+" entered");
         handlers.addElement (this); 

         while(in.available() > 0) {
        	 @SuppressWarnings("unchecked")
			 ArrayList<ServerInfo> list = (ArrayList<ServerInfo>) in.readObject();
        	 serverList = list;
        	 
        	 broadcast(serverList);
         } 

      } catch (IOException ex) { 
         System.out.println("-- Connection to user lost.");
      } catch (ClassNotFoundException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	} finally { 
         handlers.removeElement (this); 
         //broadcast(name+" left");
         try { 
            socket.close();
         } catch (IOException ex) { 
            System.out.println("-- Socket to user already closed ?");
         }  
      }
   }
    

   protected static void broadcast (ArrayList<ServerInfo> serverList) { 
      synchronized (handlers) { 
         Enumeration<ListHandler> e = handlers.elements (); 
         while (e.hasMoreElements()) { 
            ListHandler handler = (ListHandler) e.nextElement(); 
            try { 
            	
               handler.out.reset();
               handler.out.writeObject(serverList);
               
               handler.out.close();
            } catch (IOException ex) { 
               handler.stop(); 
            } 
         }
      }
   } 
}


