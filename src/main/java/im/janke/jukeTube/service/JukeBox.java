package im.janke.jukeTube.service;

import im.janke.jukeTube.model.Player;

public class JukeBox {
	/* The player that plays the tunes */
	private Player player;

	/* Playback modes */
	private boolean repeatMode = false;
	private boolean shuffleMode = false;

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
}
