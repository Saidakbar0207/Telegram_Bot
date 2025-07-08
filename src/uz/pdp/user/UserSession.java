package uz.pdp.user;

import java.util.UUID;

public class UserSession {
    private UUID currentCategoryId;
    private UUID currentSubCategoryId;



    public UUID getCurrentCategoryId() {
        return currentCategoryId;
    }

    public void setCurrentCategoryId(UUID currentCategoryId) {
        this.currentCategoryId = currentCategoryId;
    }

    public UUID getCurrentSubCategoryId() {
        return currentSubCategoryId;
    }

    public void setCurrentSubCategoryId(UUID currentSubCategoryId) {
        this.currentSubCategoryId = currentSubCategoryId;
    }

    public void reset() {
        this.currentCategoryId = null;
        this.currentSubCategoryId = null;
    }
}
