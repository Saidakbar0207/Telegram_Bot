package uz.pdp.user;

import uz.pdp.user.UserSession;

import java.util.HashMap;
import java.util.Map;

public class SessionManager {
    private static final Map<Long, UserSession> sessionMap = new HashMap<>();

    public static UserSession getSession(Long chatId) {
        return sessionMap.computeIfAbsent(chatId, id -> new UserSession());
    }

    public static void resetSession(Long chatId) {
        sessionMap.remove(chatId);
    }

}
