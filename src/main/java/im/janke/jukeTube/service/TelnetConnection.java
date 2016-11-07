package im.janke.jukeTube.service;

import java.io.IOException;

import org.apache.commons.net.telnet.TelnetClient;

public class TelnetConnection {

	TelnetClient telnet = new TelnetClient();

	public TelnetConnection(String server, int port) {
		try {
			this.telnet.connect(server, port);
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.telnet.disconnect();
		super.finalize();
	}

	public String sendCmdAndAwaitResponse(String cmd) {
		// TODO Auto-generated method stub
		return null;
	}

	// TODO
}
