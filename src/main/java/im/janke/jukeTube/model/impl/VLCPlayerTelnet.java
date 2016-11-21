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
	private static final String STD_HOST = "localhost";
	private static final int STD_PORT = 4212;

	/** Password for the telnet connection to the player */
	private final String PASSWORD;

	/**
	 * Creates a VLC Player Telnet connection with std connection info and the std password
	 */
	public VLCPlayerTelnet() {
		this(STD_HOST, STD_PORT, "test");
	}

	/**
	 * Creates a VLC Player Telnet connection with std connection info and the given password
	 *
	 * @param password
	 *            password for the connection
	 */
	public VLCPlayerTelnet(String password) {
		this(STD_HOST, STD_PORT, password);
	}

	/**
	 * Creates a VLC Player Telnet connection with given connection info (host and port) and the std password
	 *
	 * @param host
	 *            Host name of the machine the VLC player runs
	 * @param port
	 *            port of the VLC telnet interface
	 */
	public VLCPlayerTelnet(String host, int port) {
		this(host, port, "test");
	}

	/**
	 * Creates a VLC Player Telnet connection with given connection info (host and port) and the given password
	 *
	 * @param host
	 *            Host name of the machine the VLC player runs
	 * @param port
	 *            port of the VLC telnet interface
	 * @param password
	 *            password for the connection
	 */
	public VLCPlayerTelnet(String host, int port, String password) {
		this.vlc_telnet = new PlayerConnection(host, port);
		this.PASSWORD = password;
		this.initPlayer();
	}

	/**
	 * Initializes (and finalizes) the player (connection)
	 */
	private void initPlayer() {
		this.vlc_telnet.readNLines(1);
		this.vlc_telnet.sendCmdAndAwaitNLinesResponse(this.PASSWORD, 2);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#isPlaying()
	 */
	@Override
	public boolean isPlaying() {
		String output = this.vlc_telnet.sendCmdAndAwaitOneLineResponse("is_playing");
		return this.purgeResponse(output).equals("1");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#getStatusText()
	 */
	@Override
	public String getStatusText() {
		// TODO
		return this.vlc_telnet.sendCmdAndReadUntilLineContains("status", "( state ");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#enqueue(java.lang.String)
	 */
	@Override
	public void enqueue(String link) {
		this.vlc_telnet.sendCmd("enqueue " + link);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#pause()
	 */
	@Override
	public void pause() {
		this.vlc_telnet.sendCmd("pause");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#unpause()
	 */
	@Override
	public void unpause() {
		this.pause();
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#play()
	 */
	@Override
	public void play() {
		this.vlc_telnet.sendCmd("play");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#stop()
	 */
	@Override
	public void stop() {
		this.vlc_telnet.sendCmd("stop");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#setRepeatMode(boolean)
	 */
	@Override
	public void setRepeatMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_telnet.sendCmdAndAwaitResponse("loop " + val);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#setShuffleMode(boolean)
	 */
	@Override
	public void setShuffleMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_telnet.sendCmd("random " + val);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#setVolume(short)
	 */
	@Override
	public void setVolume(short volume) {
		// TODO
		short volume_val = (short) (volume * 2.56f); // not 100% perfectly accurate, but close enough
		this.vlc_telnet.sendCmd("volume " + volume_val);
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#setVolumeUp()
	 */
	@Override
	public void setVolumeUp() {
		// TODO
		this.vlc_telnet.sendCmd("volup 1");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#setVolumeDown()
	 */
	@Override
	public void setVolumeDown() {
		// TODO
		this.vlc_telnet.sendCmd("voldown 1");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#getCurrentTitle()
	 */
	@Override
	public String getCurrentTitle() {
		return this.purgeResponse(this.vlc_telnet.sendCmdAndAwaitOneLineResponse("get_title"));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#getCurrentTitleLength()
	 */
	@Override
	public int getCurrentTitleLength() {
		return castStringToInt(this.vlc_telnet.sendCmdAndAwaitOneLineResponse("get_length"));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#getCurrentPlaytime()
	 */
	@Override
	public int getCurrentPlaytime() {
		return castStringToInt(this.vlc_telnet.sendCmdAndAwaitOneLineResponse("get_time"));
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#getCurrentPlaylist()
	 */
	@Override
	public String getCurrentPlaylist() {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see im.janke.jukeTube.model.Player#deleteFromPlaylist(int)
	 */
	@Override
	public void deleteFromPlaylist(int index) {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Purges/Cleans the given response so possible leading ">" are removed and the resulting String is trimmed.
	 *
	 * @param response
	 *            The response string that should be cleaned
	 * @return the purged response
	 */
	private String purgeResponse(String response) {
		if (response == null) {
			throw new IllegalArgumentException("Provided String is null");
		}
		return response.replace(">", "").trim();
	}

}
