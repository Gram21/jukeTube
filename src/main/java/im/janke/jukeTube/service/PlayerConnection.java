package im.janke.jukeTube.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

/**
 * @author Gram21
 *
 */
public class PlayerConnection {

	private Socket connection;
	private BufferedReader con_reader;
	private PrintWriter con_writer;

	public PlayerConnection(String server, int port) {
		try {
			this.connection = new Socket(server, port);
			this.con_reader = new BufferedReader(new InputStreamReader(this.connection.getInputStream()));
			this.con_writer = new PrintWriter(this.connection.getOutputStream(), true);

		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.con_writer.close();
		this.con_reader.close();
		this.connection.close();
		super.finalize();
	}

	/**
	 * Sends an command and awaits no lines as response.
	 *
	 * @param cmd
	 *            the command that should be sent.
	 */
	public void sendCmd(String cmd) {
		this.sendCmdAndAwaitNLinesResponse(cmd, 0);
	}

	/**
	 * Sends the given command. Awaits exactly one line as response.
	 *
	 * @param cmd
	 *            the command that should be sent.
	 * @return the read line.
	 */
	public String sendCmdAndAwaitOneLineResponse(String cmd) {
		return this.sendCmdAndAwaitNLinesResponse(cmd, 1);
	}

	/**
	 * Sends the given command. Awaits exactly n lines. CARE: Is blocking when not enough lines are produced!!
	 *
	 * @param cmd
	 *            the command that should be sent
	 * @param n
	 *            the number of lines expected
	 * @return the read lines
	 */
	public String sendCmdAndAwaitNLinesResponse(String cmd, int n) {
		System.out.println("[>>]\t" + cmd);
		this.con_writer.println(cmd);
		return this.readNLines(n);
	}

	/**
	 * Sends the given command. Awaits until the response of the player says the command returned.
	 *
	 * @param cmd
	 *            the command that should be sent to the player
	 * @return the response of the player
	 */
	public String sendCmdAndAwaitResponse(String cmd) {
		System.out.println("[>>]\t" + cmd);
		this.con_writer.println(cmd);
		return this.readToImmediateStop();
	}

	public String sendCmdAndReadUntilLineContains(String cmd, String str) {
		System.out.println("[>>]\t" + cmd);
		this.con_writer.println(cmd);
		return this.readUntilLineContains(str);
	}

	/**
	 * Reads n lines from the server/player. CARE: Is blocking when no lines are produced!!
	 *
	 * @param n
	 *            the number of lines that should be read.
	 * @return the read lines
	 */
	// TODO maybe add a timeout to this method
	public String readNLines(int n) {
		String response = "";
		String line = null;
		int counter = 0;
		try {
			while (counter < n) {
				line = this.con_reader.readLine();
				response += line + "\n";
				System.out.println("[<<]\t" + line);
				counter++;
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

	/**
	 * Reads until the response has the word "return" in it, possibly marking the (temporary) end of an output.
	 *
	 * @return the read lines
	 */
	// TODO maybe add a timeout to this method
	public String readToImmediateStop() {
		return this.readUntilLineContains("returned ");
	}

	public String readUntilLineContains(String str) {
		String response = "";
		String line = null;
		try {
			do {
				line = this.con_reader.readLine();
				response += line + "\n";
				System.out.println("[<<]\t" + line);
			} while (!line.contains(str)); // TODO check and make failure proof
		} catch (IOException e) {
			e.printStackTrace();
		}
		return response;
	}

}
