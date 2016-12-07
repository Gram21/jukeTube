/**
 *
 */
package im.janke.jukeTube.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import im.janke.jukeTube.model.impl.Song;

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

	public static List<Song> handleYouTubePlaylist(String playlist_id) {
		List<Song> retList = new ArrayList<>();
		String url = "https://www.youtube.com/watch?v=DLzxrzFCyOs&list=" + playlist_id;
		String html = fetchHtml(url);
		Matcher m = Pattern.compile("data-video-id=\"([^\"]+)\"").matcher(html);
		while (m.find()) {
			String vid_id = m.group(1);
			retList.add(new Song("https://www.youtube.com/watch?v=" + vid_id));
		}
		return retList;
	}

	private static String fetchHtml(String urlString) {
		StringBuilder html = new StringBuilder();
		java.net.URL url = null;
		try {
			url = new URL(urlString);
		} catch (MalformedURLException e1) {
			e1.printStackTrace();
		}
		try (BufferedReader input = new BufferedReader(new InputStreamReader(url.openStream()))) {
			String htmlLine;
			while ((htmlLine=input.readLine())!=null) {
				html.append(htmlLine);
			}
		} catch (IOException e) {
			System.err.println("Error in getting Youtube playlist");
			e.printStackTrace();
		}
		return html.toString();
	}

}
