/**
 *
 */
package im.janke.jukeTube.util;

import java.util.LinkedList;
import java.util.List;

/**
 * @author Gram21
 *
 */
public class Util {

	@Deprecated
	public static List<String> samplePlaylist() {
		LinkedList<String> playlist = new LinkedList<>();
		// short songs:
		playlist.addFirst("https://www.youtube.com/watch?v=X30l7A8-BRM");
		playlist.addFirst("https://youtu.be/t6ZzNMCJFaY");

		// nightcore
		playlist.add("https://www.youtube.com/watch?v=D_IuxvnhCfA");
		playlist.add("https://www.youtube.com/watch?v=phpChjc9ESY");
		playlist.add("https://www.youtube.com/watch?v=k0lN5UnbGDA");
		playlist.add("https://www.youtube.com/watch?v=dOtlh7E1T14");
		playlist.add("https://www.youtube.com/watch?v=h8ZGzFqZO7k");
		playlist.add("https://www.youtube.com/watch?v=mYM2efYY2Pg");
		playlist.add("https://www.youtube.com/watch?v=jIM_R90JjHo");
		playlist.add("https://www.youtube.com/watch?v=_buAVf9ax1g");
		playlist.add("https://www.youtube.com/watch?v=YHQa5T7hEao");
		playlist.add("https://www.youtu.be.com/watch?v=HQ8vtQ4eLjs");

		// other stuff
		playlist.add("https://www.youtube.com/watch?v=jUNn-f8evlg");
		playlist.add("https://www.youtube.com/watch?v=8nSYawpLYd0");

		return playlist;
	}

	public static void sleep(int millis) {
		try {
			Thread.sleep(millis);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

}
