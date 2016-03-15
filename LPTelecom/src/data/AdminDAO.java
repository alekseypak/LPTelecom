package data;

public class AdminDAO {
	// It's a kind of magic!
	private static final String ADMIN_LOGIN = "admin";
	private static final String ADMIN_PASSWORD = "admin";

	public static boolean AdminLoginAndPasswordMatch(String login, String password) {
		if (login != null && login.equals(ADMIN_LOGIN) && password != null && password.equals(ADMIN_PASSWORD)) {
			return true;
		}
		return false;
	}

}
