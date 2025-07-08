package uz.pdp.telegram_bot;

import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import org.telegram.telegrambots.updatesreceivers.DefaultBotSession;


public class Main {
    public static void main(String[] args) {
     try{
         TelegramBotsApi botsApi = new TelegramBotsApi(DefaultBotSession.class);
         botsApi.registerBot(new MySimpleTelegramBot(botsApi));
         System.out.println("Bot muvaffaqiyatli ishga tushdi!");

     } catch (TelegramApiException e) {
        System.err.println("Botni ishga tushurishda xato : "+ e.getMessage());
     }

        }
    }
