package im.janke.jukeTube.service;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;

import im.janke.jukeTube.App;
import im.janke.jukeTube.model.impl.Song;
import uk.co.caprica.vlcj.component.AudioMediaPlayerComponent;
import uk.co.caprica.vlcj.player.MediaPlayer;
import uk.co.caprica.vlcj.player.MediaPlayerFactory;
import uk.co.caprica.vlcj.player.headless.HeadlessMediaPlayer;

// TODO implement missing functionality
@Service
public class JukeBox {
	/* The player that plays the tunes */
	private final HeadlessMediaPlayer mediaPlayer;

	private List<Song> playlist = new ArrayList<>();
	private List<Song> alreadyPlayedList = new ArrayList<>();
	private Song currentlyPlayedSong = null;

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
				JukeBox.this.alreadyPlayedList.add(JukeBox.this.currentlyPlayedSong);
				JukeBox.this.currentlyPlayedSong = null;
				if (JukeBox.this.finishedPlayback()) {
					System.out.println("Finished Playback.");
					// TODO and then?
					mediaPlayer.stop();
					return;
				}

				Song song = JukeBox.this.nextSong();
				JukeBox.this.playlist.remove(song);
				JukeBox.this.playSong(song);
			}

			@Override
			public void subItemPlayed(MediaPlayer mediaPlayer, int subItemIndex) {
				App.sleep(2500); // Wait for the item to actually start
				String title = JukeBox.this.getCurrentTitle();
				JukeBox.this.currentlyPlayedSong.setTitle(title);
				System.out.println("Playing now: " + title);
			}

		});
	}

	/**
	 * Starts the JukeBox (usually starts with playback then)
	 */
	public void startJukeBox() {
		// TODO
		this.setRepeatModeOn(false);
		this.setShuffleModeOn(false);
		// this.addLinkListToPlaylist(App.nightcorePlaylist());
	}

	public void stopJukeBox() {
		// TODO
		this.mediaPlayer.release();
	}

	private boolean finishedPlayback() {
		return this.playlist.isEmpty();
	}

	private Song nextSong() {
		// TODO
		if (!this.playlist.isEmpty()) {
			return this.playlist.get(0);
		} else {
			return null;
		}
	}

	private void playSong(Song song) {
		this.mediaPlayer.playMedia(song.getLink());
		this.currentlyPlayedSong = song;
	}

	/**
	 * Adds all Links in the List to the Playlist
	 *
	 * @param linkList
	 *            List of (YouTube-)Links to songs
	 */
	public void addLinkListToPlaylist(List<String> linkList) {
		if (linkList != null && !linkList.isEmpty()) {
			linkList.forEach(link -> this.addLinkToPlaylist(link));
		}
	}

	/**
	 * Adds a given Link to the Playlist
	 *
	 * @param link
	 *            (YouTube-)Link to the song
	 */
	public boolean addLinkToPlaylist(String link) {
		String watchURL = this.getYTVideoID(link);
		if (watchURL == null) {
			return false;
		}
		Song song = new Song("https://www.youtube.com/watch?v=" + watchURL);
		if (!this.songAlreadyContained(song)) {
			// start playing when nothing is playing yet
			if (!this.mediaPlayer.isPlaying()) {
				if (this.playlist.isEmpty() && this.currentlyPlayedSong == null) {
					this.playSong(song);
					return true;
				}
			}

			this.playlist.add(song);
			return true;
		}
		return false;
	}

	/**
	 * Takes the input link and checks whether it is a valid YouTube Link. If it is a valid YT Link it the extracts the
	 * watchURL, that is the id of the YouTube Video. If the Link is invalid <code>null</code> is returned.
	 *
	 * @param link
	 *            Link to a possible YouTube Video
	 * @return the ID of the YouTube Video, if it is a valid Link. <code>null</code> otherwise
	 */
	private String getYTVideoID(String link) {
		Matcher m = Pattern
				.compile("(http[s?]:\\/\\/)?(www\\.)?youtu(?:.*\\/v\\/|.*v\\=|\\.be\\/)([A-Za-z0-9_\\-]{11})")
				.matcher(link);
		String retString = null;
		if (m.matches()) {
			retString = m.group(3);
		}
		return retString;
	}

	private boolean songAlreadyContained(Song song) {
		if (song == null) {
			throw new IllegalArgumentException("Song must not be null!");
		}
		if (this.playlist.contains(song)) {
			return true;
		}
		if (this.alreadyPlayedList.contains(song)) {
			return true;
		}
		return false;
	}


	/**
	 * Removes a given Link from the PLaylist. Does nothing, when the Link is not found in the Playlist
	 *
	 * @param link
	 *            (YouTube-)Link that should be removed
	 */
	public void removeLinkFromPlaylist(String link) {
		// TODO
		this.playlist.remove(new Song(link));
	}

	/**
	 * Pauses the playback
	 */
	public void pause() {
		// TODO
		this.mediaPlayer.pause();
	}

	/**
	 * Unpauses the playback
	 */
	public void unpause() {
		// TODO
		this.mediaPlayer.play();
	}

	/**
	 * Returns the title of the currently played track. If no track is played, then an empty String is returned
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
	 * returns the currently played track. If no track is played, then null is returned
	 *
	 * @return Currently played Song
	 */
	public Song getCurrentSong() {
		return this.currentlyPlayedSong;
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
		this.mediaPlayer.setRepeat(on);
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
