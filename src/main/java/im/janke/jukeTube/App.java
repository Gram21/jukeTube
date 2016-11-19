package im.janke.jukeTube;

import java.util.LinkedList;
import java.util.List;

import im.janke.jukeTube.model.Player;
import im.janke.jukeTube.service.JukeBox;

/**
 * Hello world!
 *
 */
public class App
{
	public static void main(String[] args) {
		JukeBox jukeBox = new JukeBox();
		Player p = jukeBox.getPlayer();

		for (String song : nightcorePlaylist()) {
			p.enqueue(song);
		}
		sleep();
		p.setShuffleMode(true);
		p.play();
	}

	// below are convenience methods that might be removed later on!
	private static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private static void sleep() {
		sleep(500);
	}

	@Deprecated
	private static List<String> nightcorePlaylist() {
		LinkedList<String> playlist = new LinkedList<>();
		playlist.addFirst("https://www.youtube.com/watch?v=D_IuxvnhCfA");
		playlist.addFirst("https://www.youtube.com/watch?v=phpChjc9ESY");
		playlist.addFirst("https://www.youtube.com/watch?v=k0lN5UnbGDA");
		playlist.addFirst("https://www.youtube.com/watch?v=dOtlh7E1T14");
		playlist.addFirst("https://www.youtube.com/watch?v=h8ZGzFqZO7k");
		playlist.addFirst("https://www.youtube.com/watch?v=mYM2efYY2Pg");
		playlist.addFirst("https://www.youtube.com/watch?v=jIM_R90JjHo");
		playlist.addFirst("https://www.youtube.com/watch?v=_buAVf9ax1g");
		playlist.addFirst("https://www.youtube.com/watch?v=YHQa5T7hEao");
		playlist.addFirst("https://www.youtube.com/watch?v=HQ8vtQ4eLjs");

		return playlist;
	}
}
