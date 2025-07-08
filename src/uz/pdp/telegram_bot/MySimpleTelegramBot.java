package uz.pdp.telegram_bot;

import org.telegram.telegrambots.bots.TelegramLongPollingBot;
import org.telegram.telegrambots.meta.TelegramBotsApi;
import org.telegram.telegrambots.meta.api.methods.ForwardMessage;
import org.telegram.telegrambots.meta.api.methods.ParseMode;
import org.telegram.telegrambots.meta.api.methods.send.*;
import org.telegram.telegrambots.meta.api.objects.*;
import org.telegram.telegrambots.meta.api.objects.media.InputMedia;
import org.telegram.telegrambots.meta.api.objects.media.InputMediaPhoto;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;
import org.telegram.telegrambots.meta.exceptions.TelegramApiException;
import uz.pdp.model.Category;
import uz.pdp.service.CategoryService;
import uz.pdp.telegram_bot.factory.CategoryInlineKeyboardMarkup;
import uz.pdp.telegram_bot.factory.ReplyAndInlineKeyboard;
import uz.pdp.telegram_bot.service.CategoryBotService;
import uz.pdp.telegram_group_bot.TelegramMessageSender;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MySimpleTelegramBot extends TelegramLongPollingBot {

    private static final String BOT_USERNAME = "soulsdushe_0207_bot";

    public MySimpleTelegramBot() {
        super("8007120507:AAG5k1Frzpb5HnDcP6uSUr-VaZ1X6OBYc_U");
    }

    @Override
    public String getBotUsername() {
        return BOT_USERNAME;
    }


    public void onUpdateReceived(Update update) {
        InlineKeyboardMarkup inlineKeyboardMarkup = new InlineKeyboardMarkup();
        if (update.hasMessage()) {
            String text = update.getMessage().getText();
                if (text.equals("/start")) {
                SendMessage sendMessage = new SendMessage();
                try {
                    Long chatId = update.getMessage().getChatId();
                    User user = update.getMessage().getFrom();
                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Hello, " + user.getFirstName());
                    sendMessage.setReplyMarkup(ReplyAndInlineKeyboard
                            .createReplyKeyboardMarkup(List.of("Iphone",
                                            "Car",
                                            "Notebook",
                                            "Macbook",
                                            "Samsung",
                                            "Book",
                                            "Airplane"),
                                    3));

                    if (text.equals("Iphone")) {
                        CategoryService categoryService = new CategoryService();
                        CategoryBotService categoryBotService = new CategoryBotService(categoryService);

                        Category parent = categoryService.getCategoryByName("Iphone");
                        List<Category> children = categoryService.getSubCategories(parent.getId());

                        sendMessage.setReplyMarkup(new CategoryInlineKeyboardMarkup(children, 2).createInlineKeyboardMarkup());
                    }

                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    throw new RuntimeException(e);
                }
            }

                if (update.getMessage().hasPhoto()) {
                    long chatId = update.getMessage().getChatId();
                    List<PhotoSize> photos = update.getMessage().getPhoto();
                    if (!photos.isEmpty()) {
                        PhotoSize photoSize = photos.get(photos.size() - 1);
                        TelegramMessageSender.sendTelegramPhoto(photoSize);
                        String photoId = photoSize.getFileId();
                        String text1 = "You sent me a photo! ";

                        SendPhoto sendPhoto = new SendPhoto();
                        sendPhoto.setChatId(chatId);
                        sendPhoto.setPhoto(new InputFile(photoId));
                        sendPhoto.setCaption(text1);
                        sendPhoto.setParseMode(ParseMode.HTML);
                        sendPhoto.setAllowSendingWithoutReply(true);
                        sendPhoto.setDisableNotification(true);
                        sendPhoto.setReplyToMessageId(update.getMessage().getMessageId());
                        sendPhoto.setProtectContent(true);


                        try {
                            execute(sendPhoto);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                if (update.getMessage().hasAudio()) {

                    Audio audio = update.getMessage().getAudio();
                    long chatId = update.getMessage().getChatId();
                    TelegramMessageSender.sendTelegramAudio(audio);

                    SendAudio sendAudio = new SendAudio();
                    sendAudio.setAudio(new InputFile(audio.getFileId()));
                    sendAudio.setChatId(chatId);
                    sendAudio.setCaption("you sent me an audio");

                    try {
                        execute(sendAudio);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }

                }

                if (update.getMessage().hasVideo()) {
                    long chatId = update.getMessage().getChatId();
                    Video video = update.getMessage().getVideo();
                    TelegramMessageSender.sendTelegramVideo(video.getFileName());

                    SendVideo sendVideo = new SendVideo();
                    sendVideo.setChatId(chatId);
                    sendVideo.setVideo(new InputFile(video.getFileId()));
                    sendVideo.setCaption("You sent me a video");

                    try {
                        execute(sendVideo);
                    } catch (TelegramApiException e) {
                        e.printStackTrace();
                    }
                }

                if (update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/picture")) {
                        InputMedia inputMedia1 = new InputMediaPhoto("https://www.google.com/imgres?q=photos&imgurl=https%3A%2F%2Fwww.telefonica.com%2Fen%2Fwp-content%2Fuploads%2Fsites%2F5%2F2023%2F09%2F10-Tips-to-improve-your-photos-e1696506323825.jpg%3Fw%3D1200&imgrefurl=https%3A%2F%2Fwww.telefonica.com%2Fen%2Fcommunication-room%2Fblog%2F10-tips-improve-your-photos%2F&docid=5NsK1yIFcRe8HM&tbnid=AdBEqy9uffMW5M&vet=12ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECFEQAA..i&w=1200&h=675&hcb=2&ved=2ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECFEQAA");
                        InputMedia inputMedia2 = new InputMediaPhoto("https://www.google.com/imgres?q=photos&imgurl=https%3A%2F%2Fimgv3.fotor.com%2Fimages%2Fblog-cover-image%2Fa-shadow-of-a-boy-carrying-the-camera-with-red-sky-behind.jpg&imgrefurl=https%3A%2F%2Fwww.fotor.com%2Fblog%2Fhow-to-take-professional-photos%2F&docid=eA3ymlwaFjfBuM&tbnid=91rejAtzC2sozM&vet=12ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECF8QAA..i&w=2160&h=1440&hcb=2&ved=2ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECF8QAA");
                        InputMedia inputMedia3 = new InputMediaPhoto("https://www.google.com/imgres?q=photos&imgurl=https%3A%2F%2Fimages.pexels.com%2Fphotos%2F1264210%2Fpexels-photo-1264210.jpeg&imgrefurl=https%3A%2F%2Fwww.pexels.com%2Fsearch%2Fphotography%2F&docid=EEE7gUX-LmxBUM&tbnid=Aww5d6yC8Cpn0M&vet=12ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECFMQAA..i&w=5420&h=3614&hcb=2&ved=2ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECFMQAA");
                        InputMedia inputMedia4 = new InputMediaPhoto("https://www.google.com/imgres?q=photos&imgurl=https%3A%2F%2Fburst.shopifycdn.com%2Fphotos%2Fphoto-of-a-cityscape-with-a-ferris-wheel.jpg%3Fwidth%3D1000%26format%3Dpjpg%26exif%3D0%26iptc%3D0&imgrefurl=https%3A%2F%2Fwww.shopify.com%2Fstock-photos&docid=lfDdrN4YmwUMQM&tbnid=P3d0HnuB66jaQM&vet=12ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECHMQAA..i&w=1000&h=667&hcb=2&ved=2ahUKEwj0lLX_24SOAxViU0EAHT_xBSgQM3oECHMQAA");
                        InputMedia inputMedia5 = new InputMediaPhoto("https://www.google.com/imgres?q=wednesday&imgurl=https%3A%2F%2Fvariety.com%2Fwp-content%2Fuploads%2F2025%2F04%2FWEDNESDAY_202_Unit_03439RC.jpg&imgrefurl=https%3A%2F%2Fvariety.com%2F2025%2Ftv%2Fnews%2Fwednesday-season-2-release-date-teaser-jenna-ortega-1236188770%2F&docid=_G3k1r_1Hbu79M&tbnid=DbUqTd7msoJKnM&vet=12ahUKEwi_wdT43ISOAxWQhP0HHQssF3kQM3oECFQQAA..i&w=3600&h=2400&hcb=2&ved=2ahUKEwi_wdT43ISOAxWQhP0HHQssF3kQM3oECFQQAA");
                        List<InputMedia> media = new ArrayList<>();
                        media.add(inputMedia1);
                        media.add(inputMedia2);
                        media.add(inputMedia3);
                        media.add(inputMedia4);
                        media.add(inputMedia5);


                        SendMediaGroup sendMediaGroup = new SendMediaGroup();
                        sendMediaGroup.setMedias(media);
                        sendMediaGroup.setChatId(update.getMessage().getChatId());
                        String messageText = "You sent me a media group!";
                        TelegramMessageSender.sendTelegramMediaGroup(messageText);
                        try {
                            execute(sendMediaGroup);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

                if (update.getMessage().hasText()) {
                    if (update.getMessage().getText().equals("/location")) {
                        SendVenue sendVenue = new SendVenue();
                        long chatId = update.getMessage().getChatId();
                        sendVenue.setChatId(chatId);
                        sendVenue.setLatitude(41.326531);
                        sendVenue.setLongitude(69.228359);
                        sendVenue.setTitle("PDP Academy");
                        sendVenue.setAddress("+1000 Bitiruvchilarimiz\n" +
                                "13 mln.dan ziyod maosh olishmoqda!");

                        try {
                            execute(sendVenue);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    }
                }

              Message message = update.getMessage();
              Long chatId = message.getChatId();

                if (message.hasText()) {
                String textFile = message.getText();
                    if (text.equalsIgnoreCase("/file")) {
                        SendDocument sendDocument = new SendDocument();
                        TelegramMessageSender.sendTelegramDocument(textFile);

                        sendDocument.setCaption("You want a file");
                        sendDocument.setChatId(chatId);
                        sendDocument.setDocument(new InputFile("https://www.williambertrand.fr/pdf/Exercises_Tests_Beginner_1.pdf"));
                        sendDocument.setParseMode(ParseMode.HTML);
                        sendDocument.setAllowSendingWithoutReply(true);
                        sendDocument.setDisableNotification(true);
                        sendDocument.setReplyToMessageId(message.getMessageId());
                        sendDocument.setProtectContent(true);
                        try {
                            execute(sendDocument);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                        if (message.hasDocument()) {
                            Document document = message.getDocument();
                            String log = """
                                    FileId: %s,
                                    FileName: %s,
                                    FileSize: %d,
                                    Thumbnail: %s
                                    FileUniqueId: %s,
                                    FileMimeType: %s
                                    """.formatted(
                                    document.getFileId(),
                                    document.getFileName(),
                                    document.getFileSize(),
                                    document.getThumbnail(),
                                    document.getFileUniqueId(),
                                    document.getMimeType()
                            );
                            System.out.println(log);
                        }
                    }

                    if (text.equals("/help")) {
                        List<List<InlineKeyboardButton>> rowList = new LinkedList<>();
                        List<InlineKeyboardButton> row = new LinkedList<>();
                        InlineKeyboardButton button = new InlineKeyboardButton();
                        button.setText("Adminga xabar yuborish");
                        button.setSwitchInlineQueryCurrentChat("Matnni kiriting: ");
                        row.add(button);
                        rowList.add(row);
                        inlineKeyboardMarkup.setKeyboard(rowList);
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setChatId(message.getChatId());
                        sendMessage.setText("Qandaydir savol va takliflaringiz bo'lsa admin bilan bog'laning");
                        sendMessage.setReplyMarkup(inlineKeyboardMarkup);
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                    } else if (text.startsWith("@soulsdushe_0207_bot")) {
                        ForwardMessage forwardMessage = new ForwardMessage();
                        forwardMessage.setChatId("6036441667");
                        forwardMessage.setMessageId(message.getMessageId());
                        forwardMessage.setFromChatId(message.getChatId());
                        try {
                            execute(forwardMessage);
                        } catch (TelegramApiException e) {
                            throw new RuntimeException(e);
                        }
                        SendMessage sendMessage = new SendMessage();
                        sendMessage.setText("Xabaringiz yuborildi tez orada aloqaga chiqamiz.");
                        sendMessage.setChatId(message.getChatId());
                        try {
                            execute(sendMessage);
                        } catch (TelegramApiException e) {
                        }
                    }
                }

            /*CopyMessage copyMessage = new CopyMessage();
            copyMessage.setChatId(6036441667L);
            copyMessage.setFromChatId(message.getChatId());
            copyMessage.setMessageId(message.getMessageId());
            try {
                MessageId response = execute(copyMessage);
                System.out.println(response);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }*/
            /*if (update.hasMessage() && update.getMessage().hasText()) {
            if (update.getMessage().getText().equals("/play")) {
                Long chatId = update.getMessage().getChatId();
                send(chatId, "Assalomalekom keling o'yin o'yaymiz\uD83D\uDE0E \n \uD83C\uDFB2 \n \uD83C\uDFAF \nO'yinlardan birini yuboring");
                TelegramMessageSender.sendTelegramEmojis("Assalomalekom keling o'yin o'yaymiz");
            }
        } else if (update.hasMessage() && update.getMessage().hasDice()) {
            Dice dice = update.getMessage().getDice();
            Long chatId = update.getMessage().getChatId();

            if (dice.getEmoji().equals("\uD83C\uDFB2")) {
                if (dice.getValue() == 6) {
                    send(chatId, "Tabriklaymiz siz g'alaba qozondingiz!");
                } else if (dice.getValue() == 5) {
                    send(chatId, "Azgina qolgan ediya");
                } else {
                    send(chatId, "Qayta urinib ko'ring");
                }
            } else if (dice.getEmoji().equals("\uD83C\uDFAF")) {
                if (dice.getValue() == 6) {
                    send(chatId, "Tabriklaymiz siz g'alaba qozondingiz!");
                } else if (dice.getValue() == 5) {
                    send(chatId, "Azgina qolgan ediya");
                } else {
                    send(chatId, "Qayta urinib ko'ring");
                }
            } else if (dice.getEmoji().equals("\uD83C\uDFB1")) {
                if (dice.getValue() == 6) {
                    send(chatId, "Tabriklaymiz siz g'alaba qozondingiz!");
                } else if (dice.getValue() == 5) {
                    send(chatId, "Azgina qolgan ediya");
                }
            } else if (dice.getEmoji().equals("\uD83C\uDFB3")) {
                if (dice.getValue() == 6) {
                    send(chatId, "Tabriklaymiz siz g'alaba qozondingiz!");
                } else if (dice.getValue() == 5) {
                    send(chatId, "Azgina qolgan ediya");
                } else {
                    send(chatId, "Qayta urinib ko'ring");
                }
            } else if (dice.getEmoji().equals("\uD83C\uDFB4")) {
                if (dice.getValue() == 6) {
                    send(chatId, "Tabriklaymiz siz g'alaba qozondingiz!");
                } else if (dice.getValue() == 5) {
                    send(chatId, "Azgina qolgan ediya");
                } else {
                    send(chatId, "Qayta urinib ko'ring");
                }
            }
        }

        SendChatAction action = new SendChatAction();
        action.setAction(ActionType.TYPING);
        action.setChatId(update.getMessage().getChatId());
        try {
            execute(action);
        } catch (TelegramApiException e) {
            throw new RuntimeException(e);
        } */
            /* if (update.hasCallbackQuery()) {
                String callBackData = update.getCallbackQuery().getData();
                Long chatID = update.getCallbackQuery().getMessage().getChatId();
                Integer messageId = update.getCallbackQuery().getMessage().getMessageId();

                if (callBackData.equals("button")) {
                    // yangi tugmalarni yaratish (inlineKeyboardMarkup)
                    InlineKeyboardButton backButton = new InlineKeyboardButton();
                    backButton.setText("Ortga");
                    backButton.setCallbackData("button-back");

                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(List.of(backButton));

                    inlineKeyboardMarkup.setKeyboard(rowList);

                    editMessageText.setChatId(chatID.toString()); // Long ni String ga o‘girish shart
                    editMessageText.setMessageId(messageId);
                    editMessageText.setText("Tugma bosildi va matn taxrirlandi");
                    editMessageText.setReplyMarkup(inlineKeyboardMarkup);

                    try {
                        execute(editMessageText);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }

                } else if (callBackData.equals("button-back")) {
                    // qaytish uchun tugma yaratish (inlineKeyboardMarkup)
                    InlineKeyboardButton forwardButton = new InlineKeyboardButton();
                    forwardButton.setText("Oldinga");
                    forwardButton.setCallbackData("button");
                    List<List<InlineKeyboardButton>> rowList = new ArrayList<>();
                    rowList.add(List.of(forwardButton));
                    inlineKeyboardMarkup.setKeyboard(rowList);


                    editMessageText.setChatId(chatID.toString());
                    editMessageText.setMessageId(messageId);
                    editMessageText.setText("Ortga qaytish tugmasi bosildi");
                    editMessageText.setReplyMarkup(inlineKeyboardMarkup);

                    try {
                        execute(editMessageText);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }*/

            /*DeleteMessage deleteMessage = new DeleteMessage();
            deleteMessage.setChatId(message.getChatId());
            deleteMessage.setMessageId(message.getMessageId());
            try {
                execute(deleteMessage);
            } catch (TelegramApiException e) {
                throw new RuntimeException(e);
            }*/

            }

        else if (update.hasCallbackQuery()) {
                SendMessage sendMessage = new SendMessage();
                Message message = update.getCallbackQuery().getMessage();
                Long chatId = message.getChatId();
                String data = update.getCallbackQuery().getData();
                if (data.startsWith("CATEGORY")) {
                    String categoryId = data.substring(8);

                    sendMessage.setChatId(chatId);
                    sendMessage.setText("Category id: " + categoryId);

                    CategoryService categoryService = new CategoryService();
                    CategoryBotService categoryBotService = new CategoryBotService(categoryService);
                    sendMessage.setReplyMarkup(categoryBotService.getRootCategories());
                    try {
                        execute(sendMessage);
                    } catch (TelegramApiException e) {
                        throw new RuntimeException(e);
                    }
                }


            }

             /*if (update.hasMessage() && update.getMessage().hasText()) {
            String msg = update.getMessage().getText();
            if (!msg.equals("/start") &&
                    !msg.equals("/location") &&
                    !msg.equals("/file") &&
                    !msg.equals("/picture") &&
                    !msg.equals("/help")) {
                String messageText = update.getMessage().getText();
                TelegramMessageSender.sendTelegramMessage(messageText);
                SendMessage sendMessage = new SendMessage();
                sendMessage.setChatId(update.getMessage().getFrom().getId());
                sendMessage.setText("Hi " + user.getFirstName() + " " + user.getLastName() + "!");
                sendMessage.setText("""
                         <i>Assalomu alaykum.</i>
                         <b>Welcome, you invite my bot</b>
                         <code>Copy text.</code>
                         <a href="https://dasturlash.uz/lesson/telegram_bot_with_java/article/java_bot_send_photo">Dasturlash</a>
                         <u>underline</u>
                         <s>strikethrough</s>
                         <span class="tg-spoiler">soulsdushe</span>
                         <pre>pre-formatted fixed-width code block</pre>
                        """);
                sendMessage.setParseMode(ParseMode.HTML);
                sendMessage.setAllowSendingWithoutReply(true);
                sendMessage.setDisableNotification(false);
                sendMessage.setReplyToMessageId(update.getMessage().getMessageId());
                sendMessage.setProtectContent(true);

                try {
                    execute(sendMessage);
                } catch (TelegramApiException e) {
                    e.printStackTrace();
                }
            }
        }*/
    }


    public MySimpleTelegramBot(TelegramBotsApi botsApi) throws TelegramApiException {
        super("8007120507:AAG5k1Frzpb5HnDcP6uSUr-VaZ1X6OBYc_U");

    }

    public void send(Long chatId, String text) {
        SendMessage sendMessage = new SendMessage();
        sendMessage.setChatId(chatId);
        sendMessage.setText(text);
        try {
            execute(sendMessage);
        } catch (TelegramApiException e) {
        }
    }

}


