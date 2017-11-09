import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;

public class ListClient {

	public ServerList gui;

	private Socket socket;
	private ObjectOutputStream out;
	private ObjectInputStream in;
	
	private ArrayList<ServerInfo> serverList = 
			new ArrayList<ServerInfo>();

	public ListClient(String name, String server, int port) {

		// GUI Create GUI and handle events:
		// After text input, sendTextToChat() is called,
		// When closing the window, disconnect() is called. 

		gui = new ServerList();
		gui.btnCreateServer.addMouseListener(new ButtonListener(this, gui));
		gui.addWindowListener(new ExitListener(this));

		// create a socket, register and listen t the server
		try {
			
			System.out.println("entered try catch block to connect");
			
			socket = new Socket(server,port);
			System.out.println("socket created");
			out = new ObjectOutputStream(socket.getOutputStream());
			out.flush();
			System.out.println("Outputstream created");
			in = new ObjectInputStream(socket.getInputStream());
			System.out.println("Inputstream created");
			
			out.reset();
			out.writeObject(name);
	        while(in.available() > 0) {
				//serverList = (ArrayList<ServerInfo>) in.readObject();
				
	        	String a = (String) in.readObject();
	        	
	        	System.out.println("Client Recieved: " + a);
	        	
				//gui.updateServerList(serverList);
			}
		}	catch (Exception e)	{ 
			e.printStackTrace();
		}
	}
	
	protected void sendObjectToServer(ArrayList<ServerInfo> serverList) {
		try {
			out.reset();
			out.writeObject(serverList);
			
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }

	   protected void disconnect() {
	      try {
	  		socket.close();
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   }
			
//	   public static void main (String args[])throws IOException {
//	      if (args.length!=3) 
//	         throw new RuntimeException ("Syntax: java ChatClient <name> <serverhost> <port>"); 
//	      int port=Integer.parseInt(args[2]);
//	      ListClient c=new ListClient(args[0], args[1], port);
//	   }
}
