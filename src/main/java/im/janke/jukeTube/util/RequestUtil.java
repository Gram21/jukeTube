package im.janke.jukeTube.util;

import spark.Request;

//TODO refactor, delete unneeded etc
public class RequestUtil {

	public static String getQueryLocale(Request request) {
		return request.queryParams("locale");
	}

	public static String getQueryUsername(Request request) {
		return request.queryParams("username");
	}

	public static String getQueryPassword(Request request) {
		return request.queryParams("password");
	}

	public static String getSessionLocale(Request request) {
		return request.session().attribute("locale");
	}

	public static String getSessionCurrentUser(Request request) {
		return request.session().attribute("currentUser");
	}

	public static boolean clientAcceptsHtml(Request request) {
		String accept = request.headers("Accept");
		return accept != null && accept.contains("text/html");
	}

	public static boolean clientAcceptsJson(Request request) {
		String accept = request.headers("Accept");
		return accept != null && accept.contains("application/json");
	}

}
