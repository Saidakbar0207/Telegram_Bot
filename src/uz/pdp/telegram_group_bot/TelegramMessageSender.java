package uz.pdp.telegram_group_bot;

import org.telegram.telegrambots.meta.api.objects.Audio;
import org.telegram.telegrambots.meta.api.objects.PhotoSize;

public class TelegramMessageSender {

    public static void sendTelegramMessage(String messageText) {
        System.out.println("Telegram message: " + messageText);
    }
    public static void sendTelegramPhoto(PhotoSize photoSize) {
        System.out.println("Telegram photo: " + photoSize.getFileId());
    }
    public static void sendTelegramAudio(Audio audio) {
        System.out.println("Telegram audio: " + audio.getFileId());
    }
    public static void sendTelegramVideo(String videoId) {
        System.out.println("Telegram video: " + videoId);
    }
    public static void  sendTelegramMediaGroup(String mediaGroupId) {
        System.out.println("Telegram media group: " + mediaGroupId);
    }
    public static void sendTelegramLocation(String location) {
        System.out.println("Telegram location: " + location);
    }
    public static void sendTelegramDocument(String documentId) {
        System.out.println("Telegram document: " + documentId);
    }

    public static void sendTelegramEmojis(String emojis) {
        System.out.println("Telegram emojis: " + emojis);
    }
    public static void sendTelegramForwardMessage(String forwardMessageId) {
        System.out.println("Telegram forward message: " + forwardMessageId);
    }

}
