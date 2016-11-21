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
// TODO: check all! PROBLEM: rc gets us better(more) responses but lua_telnet has better controls
public class VLCPlayerRC extends Player {
	private PlayerConnection vlc_rc = null;

	/* Std values for Host and Port */
	private static final String STD_HOST = "localhost";
	private static final int STD_PORT = 4212;

	/**
	 * Creates a VLC Player connection with standard host and port values.
	 */
	public VLCPlayerRC() {
		this(STD_HOST, STD_PORT);
	}

	/**
	 * Creates a VLC Player connection with the given host and port values
	 *
	 * @param host
	 *            Host name of the machine the VLC player runs
	 * @param port
	 *            port of the VLC RC interface
	 */
	public VLCPlayerRC(String host, int port) {
		this.vlc_rc = new PlayerConnection(host, port);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#isPlaying()
	 */
	@Override
	public boolean isPlaying() {
		String output = this.vlc_rc.sendCmdAndAwaitOneLineResponse("is_playing");
		return output.equals("1");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#getStatusText()
	 */
	@Override
	public String getStatusText() {
		return this.vlc_rc.sendCmdAndAwaitResponse("status");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#enqueue(java.lang.String)
	 */
	@Override
	public void enqueue(String link) {
		this.vlc_rc.sendCmdAndAwaitResponse("enqueue " + link);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#pause()
	 */
	@Override
	public void pause() {
		this.vlc_rc.sendCmdAndAwaitResponse("pause");
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
		this.vlc_rc.sendCmdAndAwaitResponse("play");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#stop()
	 */
	@Override
	public void stop() {
		this.vlc_rc.sendCmdAndAwaitResponse("stop");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#setRepeatMode(boolean)
	 */
	@Override
	public void setRepeatMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_rc.sendCmdAndAwaitResponse("loop " + val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#setShuffleMode(boolean)
	 */
	@Override
	public void setShuffleMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_rc.sendCmdAndAwaitResponse("random " + val);
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
		this.vlc_rc.sendCmdAndAwaitResponse("volume " + volume_val);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#setVolumeUp()
	 */
	@Override
	public void setVolumeUp() {
		// TODO
		this.vlc_rc.sendCmdAndAwaitResponse("volup 1");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#setVolumeDown()
	 */
	@Override
	public void setVolumeDown() {
		// TODO
		this.vlc_rc.sendCmdAndAwaitResponse("voldown 1");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#getCurrentTitle()
	 */
	@Override
	public String getCurrentTitle() {
		return this.vlc_rc.sendCmdAndAwaitOneLineResponse("get_title");
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#getCurrentTitleLength()
	 */
	@Override
	public int getCurrentTitleLength() {
		return castStringToInt(this.vlc_rc.sendCmdAndAwaitOneLineResponse("get_length"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#getCurrentPlaytime()
	 */
	@Override
	public int getCurrentPlaytime() {
		return castStringToInt(this.vlc_rc.sendCmdAndAwaitOneLineResponse("get_time"));
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see im.janke.jukeTube.model.Player#getCurrentPlaylist()
	 */
	@Override
	public String getCurrentPlaylist() {
		return this.vlc_rc.sendCmdAndAwaitResponse("playlist");
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

}
