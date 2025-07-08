package uz.pdp.telegram_bot.factory;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import org.telegram.telegrambots.meta.api.objects.replykeyboard.buttons.InlineKeyboardButton;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public abstract class InlineKeyboardMarkupFactory<T> {
    private List<T> records;
    private int colCount;

    public InlineKeyboardMarkupFactory(List<T> records, int colCount) {
        this.records = Objects.requireNonNull(records, "Records bo'sh bo'lishi mumkin emas");
        this.colCount = colCount;
    }

    public InlineKeyboardMarkup createInlineKeyboardMarkup () {
        if (records == null || colCount <= 0) {
            throw new IllegalStateException("createInlineKeyboardMarkup uchun 'records' va 'colCount' to‘g‘ri qiymatga ega bo‘lishi kerak");
        }
        InlineKeyboardMarkup markup = new InlineKeyboardMarkup();
        List<List<InlineKeyboardButton>> rows = new ArrayList<>();

        int index = 0;
        List<InlineKeyboardButton> row = new ArrayList<>();

        for (T record : records) {
            index++;
            RecordWrapper wrapper = wrapper(record);
            String btnText = wrapper.getName();
            if (btnText == null || btnText.isEmpty()) {
                btnText = wrapper.getId().toString();
            }
            InlineKeyboardButton btn = new InlineKeyboardButton();
            btn.setText(btnText);
            btn.setCallbackData(wrapper.getCommand() + " " + wrapper.getName());
            row.add(btn);

            if (index % colCount == 0) {
                rows.add(row);
                row = new ArrayList<>();
            }
        }

        if (!row.isEmpty()) {
            rows.add(row);
        }

        markup.setKeyboard(rows);
        return markup;
    }

    protected abstract RecordWrapper wrapper(T t);
}
