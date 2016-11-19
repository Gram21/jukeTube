package im.janke.jukeTube.service;

import im.janke.jukeTube.model.Player;
import im.janke.jukeTube.model.impl.VLCPlayerRC;

public class JukeBox {
	/* The player that plays the tunes */
	private Player player;

	/* Playback modes */
	private boolean repeatMode = false;
	private boolean shuffleMode = false;

	public JukeBox() {
		this.player = new VLCPlayerRC();
		this.player.initPlayer(); // is this good here?
	}

	public JukeBox(Player player) {
		this.player = player;
		this.player.initPlayer();
	}

	/**
	 * Switches the repeat mode. If it was on, it will get turned off and vice versa.
	 */
	public void switchRepeatMode() {
		this.repeatMode = !this.repeatMode;
	}

	/**
	 * Switches the shuffle mode. If it was on, it will get turned off and vice versa.
	 */
	public void switchShuffleMode() {
		this.shuffleMode = !this.shuffleMode;
	}

	/**
	 * Returns if the repeat mode is on or off.
	 *
	 * @return the repeat mode
	 */
	public boolean isInRepeatMode() {
		return this.repeatMode;
	}

	/**
	 * Returns if the shuffle mode is on or off.
	 *
	 * @return the shuffle mode
	 */
	public boolean isInShuffleMode() {
		return this.shuffleMode;
	}

	@Deprecated
	public Player getPlayer() {
		return this.player;
	}
}
