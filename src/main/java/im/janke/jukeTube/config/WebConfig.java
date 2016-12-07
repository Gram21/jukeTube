package im.janke.jukeTube.config;

import static spark.Spark.after;
import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.port;
import static spark.Spark.post;
import static spark.Spark.secure;
import static spark.Spark.staticFiles;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.http.HttpStatus;

import im.janke.jukeTube.model.impl.Song;
import im.janke.jukeTube.service.JukeBox;
import im.janke.jukeTube.util.Filters;
import spark.Filter;
import spark.ModelAndView;
import spark.Request;
import spark.Response;
import spark.Route;
import spark.TemplateViewRoute;
import spark.template.freemarker.FreeMarkerEngine;

// TODO: locale, implementing all routes, testing,...

/**
 * @author Gram21
 *
 */
public class WebConfig {
	private JukeBox jukeBox;

	public WebConfig(JukeBox jukeBox) {
		this.jukeBox = jukeBox;
		staticFiles.location("/public");
		staticFiles.expireTime(600L);
		port(4567);
		// this.setupSSL();
		this.setupRoutes();
		this.initJukeBox();
	}

	@SuppressWarnings("unused")
	private void setupSSL() {
		// TODO !! right now this is not working!
		String keyStoreLocation = "deploy/keystore.jks";
		String keyStorePassword = "password";
		String truststoreFilePath = null;
		String truststorePassword = null;
		secure(keyStoreLocation, keyStorePassword, truststoreFilePath, truststorePassword);
	}

	// Until better way
	private void initJukeBox() {
		this.jukeBox.startJukeBox();
	}

	private void setupRoutes() {
		//Set up before
		before(WebPath.LOGIN, this.beforeLogin);
		before("*", Filters.addTrailingSlashes);
		before("*", Filters.handleLocaleChange);

		// Set up routes
		get(WebPath.INDEX, this.serveHome, new FreeMarkerEngine());
		get(WebPath.LOGIN, this.serveLogin, new FreeMarkerEngine());
		get(WebPath.SETTINGS, this.serveSettings, new FreeMarkerEngine());
		get("*", this.notFound, new FreeMarkerEngine());

		post(WebPath.INDEX, this.handleHome, new FreeMarkerEngine());
		post(WebPath.SETTINGS, this.handleSettings, new FreeMarkerEngine());
		post(WebPath.LOGIN, this.handleLogin, new FreeMarkerEngine());
		post(WebPath.LOGOUT, this.handleLogout);

		//Set up after
		after("*", Filters.addGzipHeader);
	}

	private Song getCurrentSong() {
		return this.jukeBox.getCurrentSong();
	}

	private TemplateViewRoute serveHome = (Request req, Response res) -> {
		Map<String, Object> map = new HashMap<>();
		map.put("currentlyPlaying", this.getCurrentSong());
		return new ModelAndView(map, TemplatePath.INDEX);
	};

	private TemplateViewRoute handleHome = (req, res) -> {
		String song_link = null;
		try {
			song_link = req.queryParams("song_link");
			System.out.println("[I]\tGot link to song: " + song_link);
		} catch (Exception e) {
			halt(501);
			return null;
		}

		Map<String, Object> map = new HashMap<>();
		boolean success = false;
		if (song_link != null) {
			success = this.jukeBox.addLinkToPlaylist(song_link);
			if (success) {
				System.out.println("[I]\tAdded Link.");
				res.redirect("/");
				halt();
			} else {
				System.err.println("[I]\tCould not process link. Maybe the song was already added.");
				map.put("error", "Could not process link. Maybe the song was already added.");
				map.put("currentlyPlaying", this.getCurrentSong());
			}
		} else {
			System.err.println("[I]\tCould not process link.");
			map.put("error", "Could not process link.");
		}
		return new ModelAndView(map, TemplatePath.INDEX);
	};

	private Filter beforeLogin = (req, res) -> {
		// TODO
	};

	private TemplateViewRoute serveLogin = (req, res) -> {
		// TODO
		res.redirect("/");
		return null;
	};

	private TemplateViewRoute handleLogin = (req, res) -> {
		// TODO
		res.redirect("/");
		return null;
	};

	private Route handleLogout = (req, res) -> {
		// TODO
		// removeAuthenticatedUser(req);
		res.redirect("/");
		return null;
	};

	private TemplateViewRoute serveSettings = (req, res) -> {
		// TODO
		res.redirect("/");
		return null;
	};

	private TemplateViewRoute handleSettings = (req, res) -> {
		// TODO
		res.redirect("/");
		return null;
	};

	private TemplateViewRoute notFound = (Request request, Response response) -> {
		Map<String, Object> map = new HashMap<>();
		response.status(HttpStatus.NOT_FOUND_404);
		return new ModelAndView(map, TemplatePath.NOTFOUND);
	};

	private static class WebPath {
		public static final String INDEX = "/";
		public static final String LOGIN = "/login/";
		public static final String LOGOUT = "/logout/";
		public static final String SETTINGS = "/settings/";
	}

	private static class TemplatePath {
		public static final String INDEX = "home.ftl";
		public static final String LOGIN = "login.ftl";
		public static final String SETTINGS = "settings.ftl";
		public static final String NOTFOUND = "404.ftl";
	}
}
