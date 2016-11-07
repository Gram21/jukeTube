package im.janke.jukeTube.model.impl;

import im.janke.jukeTube.model.Player;
import im.janke.jukeTube.service.TelnetConnection;

/**
 * Class to represent the VLC Player. This class works as an adapter to the VLC Interface. It passes the commands to VLC
 * and returns the feedback of VLC.
 *
 * @author Gram21
 *
 */
public class VLCPlayer extends Player {
	private TelnetConnection vlc_rc = null;

	private static final String HOST = "localhost";
	private static final int PORT = 4221;

	@Override
	public void initPlayer() {
		// TODO Auto-generated method stub
		// start VLC that listens to a special port
		// create a telnetConnection and connect to the VLC remote control
		// Process process = new ProcessBuilder("C:\\PathToExe\\MyExe.exe", "param1", "param2").start();
		// InputStream is = process.getInputStream();
		// InputStreamReader isr = new InputStreamReader(is);
		// BufferedReader br = new BufferedReader(isr);
		// String line;
		//
		// System.out.printf("Output of running %s is:", Arrays.toString(args));
		//
		// while ((line = br.readLine()) != null) {
		// System.out.println(line);
		// }
	}

	@Override
	public boolean isPlaying() {
		// TODO Auto-generated method stub
		String output = this.vlc_rc.sendCmdAndAwaitResponse("is_playing");
		return output.equals("1");
	}

	@Override
	public String getStatusText() {
		// TODO Auto-generated method stub
		return this.vlc_rc.sendCmdAndAwaitResponse("status");
	}

	@Override
	public void play(String link) {
		// TODO Auto-generated method stub

	}

	@Override
	public void pause() {
		// TODO
		this.vlc_rc.sendCmdAndAwaitResponse("pause");
	}

	@Override
	public void unpause() {
		this.pause();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		this.vlc_rc.sendCmdAndAwaitResponse("stop");
	}

	@Override
	public void setRepeatMode(boolean value) {
		// TODO Auto-generated method stub
		String val = value ? "on" : "off";
		this.vlc_rc.sendCmdAndAwaitResponse("loop " + val);
	}

	@Override
	public void setShuffleMode(boolean value) {
		// TODO Auto-generated method stub
		String val = value ? "on" : "off";
		this.vlc_rc.sendCmdAndAwaitResponse("random " + val);
	}

	@Override
	public void setVolume(short volume) {
		// TODO Auto-generated method stub
		short volume_val = (short) (volume * 2.56f); // not 100% perfectly accurate, but close enough
		this.vlc_rc.sendCmdAndAwaitResponse("volume " + volume_val);
	}

	@Override
	public void setVolumeUp() {
		// TODO Auto-generated method stub
		this.vlc_rc.sendCmdAndAwaitResponse("volup 1");
	}

	@Override
	public void setVolumeDown() {
		// TODO Auto-generated method stub
		this.vlc_rc.sendCmdAndAwaitResponse("voldown 1");
	}

	@Override
	public String getCurrentTitle() {
		return this.vlc_rc.sendCmdAndAwaitResponse("get_title");
	}

	@Override
	public int getCurrentTitleLength() {
		return castStringToInt(this.vlc_rc.sendCmdAndAwaitResponse("get_length"));
	}

	@Override
	public int getCurrentPlaytime() {
		return castStringToInt(this.vlc_rc.sendCmdAndAwaitResponse("get_time"));
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
