package im.janke.jukeTube.service;

public class UserController {

	public static boolean authenticate(String username, String password) {
		// TODO
		return username.equals("admin") && password.equals("12345");
	}
}
