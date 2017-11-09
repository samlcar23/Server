import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class ButtonListener implements MouseListener {
   
   ListClient client;
   ServerList gui;
   
   private ArrayList<ServerInfo> serverList = 
			new ArrayList<ServerInfo>();

   public ButtonListener (ListClient client, ServerList gui) {
      this.client = client;
      this.gui = gui;
   }   

//   public void keyPressed(KeyEvent e) {
//      if (e.getKeyCode()==KeyEvent.VK_ENTER) {
//          client.sendTextToChat(gui.input.getText());
//          gui.input.setText("");
//      }
//   }

@Override
public void mouseClicked(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseEntered(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mouseExited(MouseEvent e) {
	// TODO Auto-generated method stub
	
}

@Override
public void mousePressed(MouseEvent e) {
	// TODO Auto-generated method stub
	if (e.getSource() == gui.btnCreateServer) {
		System.out.println("Create Server Button Pressed");

		JTextField name = new JTextField();
		JTextField password = new JTextField();
		Object[] message = {"Server Name: ", name,
				"Password (Optional): ", password
		};
		
		int n = JOptionPane.showConfirmDialog(null,
				message, "Create Server", 
				JOptionPane.OK_CANCEL_OPTION);
		System.out.println("Create Button pressed");
		
		if (n == JOptionPane.OK_OPTION) {
			String serverName = name.getText();
			String serverPassword = password.getText();
			ServerInfo server = new ServerInfo(
					serverName, serverPassword, null);
			serverList.add(server);
			String isPrivate = "";
			if (serverList.get(serverList.size() - 1)
					.getPassword().length() > 0) {
				isPrivate = "Yes";
			} else {
				isPrivate = "No";
			}
		}
		
		client.sendObjectToServer(serverList);
		//gui.updateServerList(serverList);
	}
}

@Override
public void mouseReleased(MouseEvent e) {
	// TODO Auto-generated method stub
	
}
}