package im.janke.jukeTube.config;

import static spark.Spark.before;
import static spark.Spark.get;
import static spark.Spark.halt;
import static spark.Spark.post;
import static spark.SparkBase.staticFileLocation;

import java.util.HashMap;
import java.util.Map;

import org.eclipse.jetty.util.MultiMap;
import org.eclipse.jetty.util.UrlEncoded;

import im.janke.jukeTube.model.impl.Song;
import im.janke.jukeTube.service.JukeBox;
import spark.ModelAndView;
import spark.template.freemarker.FreeMarkerEngine;

public class WebConfig {
	private JukeBox jukeBox;

	public WebConfig(JukeBox jukeBox) {
		this.jukeBox = jukeBox;
		staticFileLocation("/public");
		this.setupRoutes();
		this.initJukeBox();
	}

	// Until better way
	private void initJukeBox() {
		this.jukeBox.startJukeBox();
	}

	private void setupRoutes() {

		// HOME
		get("/", (req, res) -> {
			Map<String, Object> map = new HashMap<>();
			map.put("currentlyPlaying", this.getCurrentSong());
			return new ModelAndView(map, "home.ftl");
		}, new FreeMarkerEngine());
		before("/", (req, res) -> {
			System.out.println("[I]\tIncoming request from " + req.ip());
		});
		post("/", (req, res) -> {
			String song_link = null;
			try {
				MultiMap<String> params = new MultiMap<String>();
				UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
				song_link = params.getValue("song_link", 0);
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
			return new ModelAndView(map, "home.ftl");
		}, new FreeMarkerEngine());

		// TEMPORARY
		get("/u/:username", (req, res) -> {
			String username = req.params(":username");
			Map<String, Object> map = new HashMap<>();
			map.put("pageTitle", "Home");
			Song currentlyPlaying = this.jukeBox.getCurrentSong();
			map.put("currentlyPlaying", currentlyPlaying);
			map.put("user", username);
			return new ModelAndView(map, "home.ftl");
		}, new FreeMarkerEngine());

		// LOGIN
		/* Checks if the user is already authenticated */
		before("/login", (req, res) -> {
			// TODO
			// User authUser = getAuthenticatedUser(req);
			// if (authUser != null) {
			// res.redirect("/");
			// halt();
			// }
		});
		/* Present the login form */
		get("/login", (req, res) -> {
			res.redirect("/");
			return null;
			// TODO
			// Map<String, Object> map = new HashMap<>();
			// if (req.queryParams("r") != null) {
			// map.put("message", "You were successfully registered and can login now");
			// }
			// return new ModelAndView(map, "login.ftl");
		}, new FreeMarkerEngine());
		/* Log the user in */
		post("/login", (req, res) -> {
			res.redirect("/");
			return null;
			// TODO
			// Map<String, Object> map = new HashMap<>();
			// User user = new User();
			// try {
			// MultiMap<String> params = new MultiMap<String>();
			// UrlEncoded.decodeTo(req.body(), params, "UTF-8", -1);
			// BeanUtils.populate(user, params);
			// } catch (Exception e) {
			// halt(501);
			// return null;
			// }
			// LoginResult result = service.checkUser(user);
			// if (result.getUser() != null) {
			// addAuthenticatedUser(req, result.getUser());
			// res.redirect("/");
			// halt();
			// } else {
			// map.put("error", result.getError());
			// }
			// map.put("username", user.getUsername());
			// return new ModelAndView(map, "login.ftl");
		}, new FreeMarkerEngine());

		// LOGOUT
		get("/logout", (req, res) -> {
			// TODO
			// removeAuthenticatedUser(req);
			res.redirect("/");
			return null;
		});

		// SETTINGS
		get("/SETTINGS", (req, res) -> {
			// TODO
			res.redirect("/");
			return null;
		});
	}

	private Song getCurrentSong() {
		return this.jukeBox.getCurrentSong();
	}
}
