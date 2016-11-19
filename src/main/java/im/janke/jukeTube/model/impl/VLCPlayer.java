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
// TODO: check all!
public class VLCPlayer extends Player {
	private PlayerConnection vlc_rc = null;

	/* Std values for Host and Port */
	private static final String HOST = "localhost";
	private static final int PORT = 4212;

	public VLCPlayer() {
		this.vlc_rc = new PlayerConnection(HOST, PORT);
	}

	public VLCPlayer(String host, int port) {
		this.vlc_rc = new PlayerConnection(host, port);
	}

	@Override
	public void initPlayer() {
		// TODO
	}

	@Override
	public boolean isPlaying() {
		String output = this.vlc_rc.sendCmdAndAwaitOneLineResponse("is_playing");
		return output.equals("1");
	}

	@Override
	public String getStatusText() {
		return this.vlc_rc.sendCmdAndAwaitResponse("status");
	}

	@Override
	public void enqueue(String link) {
		this.vlc_rc.sendCmdAndAwaitResponse("enqueue " + link);
	}

	@Override
	public void pause() {
		this.vlc_rc.sendCmdAndAwaitResponse("pause");
	}

	@Override
	public void unpause() {
		this.pause();
	}

	@Override
	public void play() {
		this.vlc_rc.sendCmdAndAwaitResponse("play");
	}

	@Override
	public void stop() {
		this.vlc_rc.sendCmdAndAwaitResponse("stop");
	}

	@Override
	public void setRepeatMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_rc.sendCmdAndAwaitResponse("loop " + val);
	}

	@Override
	public void setShuffleMode(boolean value) {
		String val = value ? "on" : "off";
		this.vlc_rc.sendCmdAndAwaitResponse("random " + val);
	}

	@Override
	public void setVolume(short volume) {
		// TODO
		short volume_val = (short) (volume * 2.56f); // not 100% perfectly accurate, but close enough
		this.vlc_rc.sendCmdAndAwaitResponse("volume " + volume_val);
	}

	@Override
	public void setVolumeUp() {
		// TODO
		this.vlc_rc.sendCmdAndAwaitResponse("volup 1");
	}

	@Override
	public void setVolumeDown() {
		// TODO
		this.vlc_rc.sendCmdAndAwaitResponse("voldown 1");
	}

	@Override
	public String getCurrentTitle() {
		return this.vlc_rc.sendCmdAndAwaitOneLineResponse("get_title");
	}

	@Override
	public int getCurrentTitleLength() {
		return castStringToInt(this.vlc_rc.sendCmdAndAwaitOneLineResponse("get_length"));
	}

	@Override
	public int getCurrentPlaytime() {
		return castStringToInt(this.vlc_rc.sendCmdAndAwaitOneLineResponse("get_time"));
	}

	@Override
	public String getCurrentPlaylist() {
		return this.vlc_rc.sendCmdAndAwaitResponse("playlist");
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

}
