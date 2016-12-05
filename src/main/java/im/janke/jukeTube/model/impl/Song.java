/**
 *
 */
package im.janke.jukeTube.model.impl;

/**
 * @author Gram21
 *
 */
public class Song {
	private final String LINK;
	private String title = null;

	/**
	 * Creates a Song based on the provided link to the song.
	 *
	 * @param link
	 *            Link to the Song
	 */
	public Song(String link) {
		this.LINK = link;
	}

	/**
	 * Returns the link to the Song
	 *
	 * @return the link to the Song
	 */
	public String getLink() {
		return this.LINK;
	}

	/**
	 * Returns the title of the song
	 *
	 * @return the title of the song
	 */
	public String getTitle() {
		if (this.title == null) {
			this.fetchTitle();
		}
		return this.title;
	}

	/**
	 * Fetches the title of the song and sets the title attribute.
	 */
	private void fetchTitle() {
		String fetchedTitle = "";
		// TODO
		this.title = fetchedTitle;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + (this.LINK == null ? 0 : this.LINK.hashCode());
		return result;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj) {
			return true;
		}
		if (obj == null) {
			return false;
		}
		if (this.getClass() != obj.getClass()) {
			return false;
		}
		Song other = (Song) obj;
		if (this.LINK == null) {
			if (other.LINK != null) {
				return false;
			}
		} else if (!this.LINK.equals(other.LINK)) {
			return false;
		}
		return true;
	}

	/*
	 * (non-Javadoc)
	 *
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "Song: Link=" + this.LINK;
	}
}
