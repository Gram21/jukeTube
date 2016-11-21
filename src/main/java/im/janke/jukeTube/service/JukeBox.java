package im.janke.jukeTube.service;

import java.util.HashMap;
import java.util.List;

import im.janke.jukeTube.model.Player;
import im.janke.jukeTube.model.impl.VLCPlayerTelnet;

public class JukeBox {
	/* The player that plays the tunes */
	private Player player;

	private HashMap<String, Integer> playlist = new HashMap<>();
	private int songCounter = 0;

	/** Playback mode repeat (don't limit playback to one playback per song) */
	private boolean repeatMode = false;
	/** Playback mode shuffle (play songs in random order) */
	private boolean shuffleMode = false;

	/**
	 * Creates a new JukeBox. Default player is VLC player with telnet connection (with default settings).
	 */
	public JukeBox() {
		this.player = new VLCPlayerTelnet();
	}

	/**
	 * Creates a new JukeBox. The provided Player will be used.
	 *
	 * @param player
	 *            Player that should be used for playback
	 */
	public JukeBox(Player player) {
		this.player = player;
	}

	/**
	 * Adds a given Link to the Playlist
	 *
	 * @param link
	 *            (YouTube-)Link to the song
	 */
	public void addLinkToPlaylist(String link) {
		// TODO CHECK LINK
		this.player.enqueue(link);
		this.playlist.put(link, new Integer(this.songCounter++));
		// TODO check if successful?
	}

	/**
	 * Adds all Links in the List to the Playlist
	 *
	 * @param linkList
	 *            List of (YouTube-)Links to songs
	 */
	public void addLinkListToPlaylist(List<String> linkList) {
		linkList.forEach(link -> this.addLinkToPlaylist(link));
	}

	/**
	 * Removes a given Link from the PLaylist. Does nothing, when the Link is not found in the Playlist
	 *
	 * @param link
	 *            (YouTube-)Link that should be removed
	 */
	public void removeLinkFromPlaylist(String link) {
		// TODO
		throw new UnsupportedOperationException("Not yet implemented");
	}

	/**
	 * Starts the JukeBox (usually starts with playback then)
	 */
	public void startJukeBox() {
		// TODO
		this.player.play();
	}

	/**
	 * Pauses the playback
	 */
	public void pause() {
		this.player.pause();
	}

	/**
	 * Unpauses the playback
	 */
	public void unpause() {
		this.player.unpause();
	}

	/**
	 * Switches the repeat mode. If it was on, it will get turned off and vice versa.
	 */
	public void switchRepeatMode() {
		this.repeatMode = !this.repeatMode;
		this.player.setRepeatMode(this.repeatMode);
	}

	/**
	 * Sets the repeat mode to the given value. True means on.
	 */
	public void setRepeatModeOn(boolean on) {
		this.repeatMode = on;
		this.player.setRepeatMode(this.repeatMode);
	}

	/**
	 * Switches the shuffle mode. If it was on, it will get turned off and vice versa.
	 */
	public void switchShuffleMode() {
		this.shuffleMode = !this.shuffleMode;
		this.player.setShuffleMode(this.shuffleMode);
	}

	/**
	 * Sets the shuffle mode. True means on.
	 */
	public void setShuffleModeOn(boolean on) {
		this.shuffleMode = on;
		this.player.setShuffleMode(this.shuffleMode);
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
