import java.io.IOException;

public class RunClient {

	public static void main (String args[]) throws IOException {
		ListClient client = new ListClient("Bob", "localhost", 5335);
		
//		ServerList client = new ServerList("bob", "localhost", 5335);
	}
}
