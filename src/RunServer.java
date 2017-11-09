import java.io.IOException;

public class RunServer{

	public static void main (String args[]) throws IOException {
		
		System.out.println("starting server" );
		ListServer server = new ListServer(5335);
		
	}
}
