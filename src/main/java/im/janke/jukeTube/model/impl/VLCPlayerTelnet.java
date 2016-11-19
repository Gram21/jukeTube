package im.janke.jukeTube.model.impl;

import im.janke.jukeTube.model.Player;
import im.janke.jukeTube.service.PlayerConnection;

/**
 * Class to represent the VLC Player. This class works as an adapter to the VLC Interface. It passes the commands to VLC
 * and returns the feedback of VLC.
 *
 * @author Gram21
 *
 */
public class VLCPlayerTelnet extends Player {
	private PlayerConnection vlc_telnet = null;

	/** Std values for Host and Port */
	private static final String HOST = "localhost";
	private static final int PORT = 4212;

	/** Password for the telnet connection to the player */
	private final String PASSWORD;

	public VLCPlayerTelnet() {
		this.vlc_telnet = new PlayerConnection(HOST, PORT);
		this.PASSWORD = "test";
	}

	public VLCPlayerTelnet(String password) {
		this.vlc_telnet = new PlayerConnection(HOST, PORT);
		this.PASSWORD = password;
	}

	public VLCPlayerTelnet(String host, int port) {
		this.vlc_telnet = new PlayerConnection(host, port);
		this.PASSWORD = "test";
	}

	public VLCPlayerTelnet(String host, int port, String password) {
		this.vlc_telnet = new PlayerConnection(host, port);
		this.PASSWORD = password;
	}

	@Override
	public void initPlayer() {
		this.vlc_telnet.readNLines(1);
		this.vlc_telnet.sendCmdAndAwaitNLinesResponse(this.PASSWORD, 2);
	}

	@Override
	public boolean isPlaying() {
		String output = this.vlc_telnet.sendCmdAndAwaitOneLineResponse("is_playing").replace(">", "").trim();
		return output.equals("1");
	}

	@Override
	public String getStatusText() {
		// TODO
		return this.vlc_telnet.sendCmdAndReadUntilLineContains("status", "( state ");
	}

	@Override
	public void enqueue(String link) {
		this.vlc_telnet.sendCmd("enqueue " + link);
	}

	@Override
	public void pause() {
		this.vlc_telnet.sendCmd("pause");
	}

	@Override
	public void unpause() {
		this.pause();
	}

	@Override
	public void play() {
		this.vlc_telnet.sendCmd("play");
	}

	@Override
	public void stop() {
		this.vlc_telnet.sendCmd("stop");
	}

	@Override
	public void setRepeatMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_telnet.sendCmdAndAwaitResponse("loop " + val);
	}

	@Override
	public void setShuffleMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_telnet.sendCmd("random " + val);
	}

	@Override
	public void setVolume(short volume) {
		// TODO
		short volume_val = (short) (volume * 2.56f); // not 100% perfectly accurate, but close enough
		this.vlc_telnet.sendCmd("volume " + volume_val);
	}

	@Override
	public void setVolumeUp() {
		// TODO
		this.vlc_telnet.sendCmd("volup 1");
	}

	@Override
	public void setVolumeDown() {
		// TODO
		this.vlc_telnet.sendCmd("voldown 1");
	}

	@Override
	public String getCurrentTitle() {
		return this.vlc_telnet.sendCmdAndAwaitOneLineResponse("get_title");
	}

	@Override
	public int getCurrentTitleLength() {
		return castStringToInt(this.vlc_telnet.sendCmdAndAwaitOneLineResponse("get_length"));
	}

	@Override
	public int getCurrentPlaytime() {
		return castStringToInt(this.vlc_telnet.sendCmdAndAwaitOneLineResponse("get_time"));
	}

	@Override
	public String getCurrentPlaylist() {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented");
	}

	static int castStringToInt(String in) {
		int ret = -1;
		try {
			ret = Integer.parseInt(in);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return ret;
	}

	@Override
	public void deleteFromPlaylist(int index) {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented");
	}

}
