package im.janke.jukeTube.model;

//TODO JavaDoc
public abstract class Player {

	public abstract void initPlayer();

	public abstract boolean isPlaying();

	public abstract String getStatusText();

	public abstract void enqueue(String link);

	public abstract void pause();

	public abstract void unpause();

	public abstract void play();

	public abstract void stop();

	public abstract void deleteFromPlaylist(int index);

	public abstract void setRepeatMode(boolean value);

	public abstract void setShuffleMode(boolean value);

	/**
	 *
	 * @param volume
	 *            Volume in percentage ranging from 0 to 100 (%);
	 */
	public abstract void setVolume(short volume);

	public abstract void setVolumeUp();

	public abstract void setVolumeDown();

	public abstract String getCurrentTitle();

	public abstract int getCurrentTitleLength();

	public abstract int getCurrentPlaytime();

	public abstract String getCurrentPlaylist();

}
