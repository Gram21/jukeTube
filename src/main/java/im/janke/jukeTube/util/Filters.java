package im.janke.jukeTube.util;

import static im.janke.jukeTube.util.RequestUtil.getQueryLocale;

import spark.Filter;
import spark.Request;
import spark.Response;

public class Filters {

	// add trailing slashes if they are missing
	public static Filter addTrailingSlashes = (Request request, Response response) -> {
		if (!request.pathInfo().endsWith("/")) {
			response.redirect(request.pathInfo() + "/");
		}
	};

	// The locale is extracted from the request and saved to the user's session
	public static Filter handleLocaleChange = (Request request, Response response) -> {
		if (getQueryLocale(request) != null) {
			request.session().attribute("locale", getQueryLocale(request));
			response.redirect(request.pathInfo());
		}
	};

	// Enable GZIP for all responses
	public static Filter addGzipHeader = (Request request, Response response) -> {
		response.header("Content-Encoding", "gzip");
	};

}