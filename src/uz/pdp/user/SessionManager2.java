package uz.pdp.user;

import java.util.HashMap;
import java.util.Map;

public class SessionManager2 {
    private static final Map<Long, SessionData> sessions = new HashMap<>();

    public static SessionData getSession(Long chatId) {
        return sessions.computeIfAbsent(chatId, k -> new SessionData());
    }

    public static void resetSession(Long chatId) {
        sessions.remove(chatId);
    }
}
