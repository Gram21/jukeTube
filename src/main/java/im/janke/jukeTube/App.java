package im.janke.jukeTube;

import java.util.LinkedList;
import java.util.List;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import im.janke.jukeTube.config.WebConfig;
import im.janke.jukeTube.service.JukeBox;
import uk.co.caprica.vlcj.discovery.NativeDiscovery;

@Configuration
@ComponentScan({ "im.janke.jukeTube" })
public class App
{
	public static void main(String[] args) {
		new NativeDiscovery().discover(); // discover VLC
		AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
		new WebConfig(ctx.getBean(JukeBox.class));
		ctx.registerShutdownHook();
	}

	// below are convenience methods that might be removed later on!
	public static void sleep(int millis) {
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
	public static List<String> nightcorePlaylist() {
		LinkedList<String> playlist = new LinkedList<>();
		// playlist.addFirst("https://www.youtube.com/watch?v=D_IuxvnhCfA");
		// playlist.addFirst("https://www.youtube.com/watch?v=phpChjc9ESY");
		// playlist.addFirst("https://www.youtube.com/watch?v=k0lN5UnbGDA");
		// playlist.addFirst("https://www.youtube.com/watch?v=dOtlh7E1T14");
		// playlist.addFirst("https://www.youtube.com/watch?v=h8ZGzFqZO7k");
		// playlist.addFirst("https://www.youtube.com/watch?v=mYM2efYY2Pg");
		// playlist.addFirst("https://www.youtube.com/watch?v=jIM_R90JjHo");
		// playlist.addFirst("https://www.youtube.com/watch?v=_buAVf9ax1g");
		// playlist.addFirst("https://www.youtube.com/watch?v=YHQa5T7hEao");
		// playlist.addFirst("https://www.youtube.com/watch?v=HQ8vtQ4eLjs");

		// short songs:
		playlist.addFirst("https://www.youtube.com/watch?v=X30l7A8-BRM");
		playlist.addFirst("https://www.youtube.com/watch?v=t6ZzNMCJFaY");

		return playlist;
	}

}
