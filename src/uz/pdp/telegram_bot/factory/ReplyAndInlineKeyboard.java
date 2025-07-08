package uz.pdp.telegram_bot.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.ReplyKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardButton;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.KeyboardRow;

import java.util.ArrayList;
import java.util.List;

public class ReplyAndInlineKeyboard {
    public static ReplyKeyboardMarkup createReplyKeyboardMarkup(List<String> buttons,int colCount) {
        ReplyKeyboardMarkup r = new ReplyKeyboardMarkup();
        r.setResizeKeyboard(true);
        List<KeyboardRow> rows = new ArrayList<>();
        r.setKeyboard(rows);

        int index = 0;
        KeyboardRow row =new KeyboardRow();
        for(String s: buttons){
            index++;
            row.add(new KeyboardButton(s));
            if(index%colCount==0){
                rows.add(row);
                row  = new KeyboardRow();
            }
        }

        if(!rows.isEmpty()){
            rows.add(row);
        }

        return r;
    }
}
