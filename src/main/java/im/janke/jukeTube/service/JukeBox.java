package im.janke.jukeTube.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import im.janke.jukeTube.model.impl.Song;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

// TODO refactor everything completely with vlcj, implement missing functionality
@Service
public class JukeBox {
	/* The player that plays the tunes */
	private final HeadlessMediaPlayer mediaPlayer;

	private List<Song> playlist = new ArrayList<>();
	private int currPosition = 0;

	/** Playback mode repeat (don't limit playback to one playback per song) */
	private boolean repeatMode = false;
	/** Playback mode shuffle (play songs in random order) */
	private boolean shuffleMode = false;

	/**
	 * Creates a new JukeBox with default values.
	 */
	public JukeBox() {
		this.mediaPlayer = new MediaPlayerFactory("--vout", "dummy").newHeadlessMediaPlayer();
		this.mediaPlayer.setPlaySubItems(true);
		this.mediaPlayer.addMediaPlayerEventListener(new AudioMediaPlayerComponent() {

			@Override
			public void error(MediaPlayer mediaPlayer) {
				// TODO ?
			}

			@Override
			public void playing(MediaPlayer mediaPlayer) {
				// TODO ?
				// Needed? Because we operate on subItems and YouTube playing gets called often:
				// Once when URL was parsed and once when playback finished
			}

			@Override
			public void finished(MediaPlayer mediaPlayer) {
				// TODO ?
				// Needed? Because we operate on subItems and YouTube finished gets called often:
				// Once when URL was parsed and once when playback finished
			}

			@Override
			public void subItemFinished(MediaPlayer mediaPlayer, int subItemIndex) {
				// System.out.println("SubItemFinished");
				if (JukeBox.this.finishedPlayback()) {
					System.out.println("Finished Playback.");
					// TODO and then?
					mediaPlayer.stop();
					return;
				}
				mediaPlayer.playMedia(JukeBox.this.nextSong());

			}

		});
	}

	/**
	 * Starts the JukeBox (usually starts with playback then)
	 */
	public void startJukeBox() {
		// TODO
		this.mediaPlayer.playMedia(this.nextSong());
	}

	public void stopJukeBox() {
		this.mediaPlayer.release();
	}

	private boolean finishedPlayback() {
		return this.currPosition >= this.playlist.size();
		// return this.playlist.isEmpty();
	}

	private String nextSong() {
		// TODO
		return this.playlist.get(this.currPosition++).getLink();
	}

	/**
	 * Adds a given Link to the Playlist
	 *
	 * @param link
	 *            (YouTube-)Link to the song
	 */
	public boolean addLinkToPlaylist(String link) {
		if (!this.checkLink(link)) {
			return false;
		}
		Song song = new Song(link);
		if (!this.playlist.contains(song)) {
			this.playlist.add(song);
			return true;
		}
		return false;
	}

	private boolean checkLink(String link) {
		Pattern p = Pattern
				.compile("(http[s?]:\\/\\/)?(www\\.)?youtu(?:.*\\/v\\/|.*v\\=|\\.be\\/)([A-Za-z0-9_\\-]{11})");
		return p.matcher(link).matches();
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
	 * Pauses the playback
	 */
	public void pause() {
		// TODO
	}

	/**
	 * Unpauses the playback
	 */
	public void unpause() {
		// TODO
	}

	/**
	 * Returns the title of the currently played track.
	 *
	 * @return the title of the currently played track.
	 */
	public String getCurrentTitle() {
		// TODO is this okay with subItem 0?
		String title = "";
		if (this.mediaPlayer.isPlaying()) {
			title = this.mediaPlayer.getSubItemMediaMeta().get(0).getTitle();
		}
		return title;
	}

	/**
	 * Switches the repeat mode. If it was on, it will get turned off and vice versa.
	 */
	public void switchRepeatMode() {
		// TODO
		this.repeatMode = !this.repeatMode;
	}

	/**
	 * Sets the repeat mode to the given value. True means on.
	 */
	public void setRepeatModeOn(boolean on) {
		this.repeatMode = on;
		// TODO
	}

	/**
	 * Switches the shuffle mode. If it was on, it will get turned off and vice versa.
	 */
	public void switchShuffleMode() {
		this.shuffleMode = !this.shuffleMode;
		// TODO
	}

	/**
	 * Sets the shuffle mode. True means on.
	 */
	public void setShuffleModeOn(boolean on) {
		this.shuffleMode = on;
		// TODO
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
