package uz.pdp.telegram_bot.factory;

import uz.pdp.model.Category;

import java.util.List;

public class CategoryInlineKeyboardMarkup extends InlineKeyboardMarkupFactory<Category> {

     public CategoryInlineKeyboardMarkup(List<Category> records, int colCount){
         super(records,colCount);
     }

    @Override
    protected RecordWrapper wrapper(Category category) {
        return new RecordWrapper(
                category.getId(),
                category.getName(),
                "category"
        );
    }
}
