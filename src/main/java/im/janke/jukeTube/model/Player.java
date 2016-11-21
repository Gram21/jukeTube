package im.janke.jukeTube.model;

//TODO JavaDoc
public abstract class Player {

	/**
	 * Returns whether the player is currently playing a song.
	 *
	 * @return true if player is playing a song, false otherwise
	 */
	public abstract boolean isPlaying();

	/**
	 * Returns the status text of the player
	 *
	 * @return status of the player as String
	 */
	public abstract String getStatusText();

	/**
	 * Enqueues a Link to the playlist of the player.
	 *
	 * @param link
	 */
	public abstract void enqueue(String link);

	/**
	 * Pauses the playback.
	 */
	public abstract void pause();

	/**
	 * Unpauses the playback.
	 */
	public abstract void unpause();

	/**
	 * Starts the playback of the player.
	 */
	public abstract void play();

	/**
	 * Stops the playback of the player.
	 */
	public abstract void stop();

	/**
	 * Deletes the element at the given index position out of the players playlist
	 *
	 * @param index
	 *            position of the element in the players playlist
	 */
	public abstract void deleteFromPlaylist(int index);

	/**
	 * Sets the repeat mode to the given value
	 *
	 * @param value
	 *            True for repeat mode on, false for off.
	 */
	public abstract void setRepeatMode(boolean value);

	/**
	 * Sets the shuffle mode to the given value
	 *
	 * @param value
	 *            True for shuffle mode on, false for off.
	 */
	public abstract void setShuffleMode(boolean value);

	/**
	 * Sets the volume to the given percentage level.
	 *
	 * @param volume
	 *            Volume in percentage ranging from 0 to 100 (%);
	 */
	public abstract void setVolume(short volume);

	/**
	 * Raises the volume one step.
	 */
	public abstract void setVolumeUp();

	/**
	 * Lowers the volume one step.
	 */
	public abstract void setVolumeDown();

	/**
	 * Returns the Title of the currently played song.
	 *
	 * @return title of the currently played song.
	 */
	public abstract String getCurrentTitle();

	/**
	 * Returns the length of the current title in seconds
	 *
	 * @return lenght of the title in seconds
	 */
	public abstract int getCurrentTitleLength();

	/**
	 * Returns how long the current title is played in seconds.
	 *
	 * @return playtime in seconds of current title.
	 */
	public abstract int getCurrentPlaytime();

	/**
	 * Returns a String containing the current Playlist of the player. The output format will depend on the player used!
	 *
	 * @return current Playlist as String.
	 */
	public abstract String getCurrentPlaylist();

	/**
	 * Cast a given String into an int. If the cast is unsuccessful returns -1.
	 *
	 * @param in
	 *            String that should be cast
	 * @return the int represented by the String, -1 if there was an error.
	 */
	protected static int castStringToInt(String in) {
		int ret = -1;
		try {
			ret = Integer.parseInt(in);
		} catch (NumberFormatException e) {
			e.printStackTrace();
		}
		return ret;
	}

}
