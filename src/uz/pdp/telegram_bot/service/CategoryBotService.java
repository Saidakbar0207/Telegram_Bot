package uz.pdp.telegram_bot.service;

import org.telegram.telegrambots.meta.api.objects.replykeyboard.InlineKeyboardMarkup;
import uz.pdp.model.Category;
import uz.pdp.service.CategoryService;
import uz.pdp.telegram_bot.factory.CategoryInlineKeyboardMarkup;

import java.util.List;

public class CategoryBotService {
private final CategoryService categoryService;

    public CategoryBotService(CategoryService categoryService) {
        this.categoryService = categoryService;
    }

    public InlineKeyboardMarkup getRootCategories(){
       List<Category> rootCategories = categoryService.getAllCategories().stream()
               .filter(c -> c.getParentId() == null)
               .toList();
       return new CategoryInlineKeyboardMarkup(rootCategories,3)
               .createInlineKeyboardMarkup();
    }


}
