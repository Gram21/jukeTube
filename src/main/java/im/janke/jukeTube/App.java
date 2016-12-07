package im.janke.jukeTube;

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
		boolean foundNative = new NativeDiscovery().discover(); // discover VLC
		if (foundNative) {
			AnnotationConfigApplicationContext ctx = new AnnotationConfigApplicationContext(App.class);
			new WebConfig(ctx.getBean(JukeBox.class));
			ctx.registerShutdownHook();
		} else {
			System.err.println("Could not find VLC. Exiting");
			System.exit(1);
		}

	}

}
