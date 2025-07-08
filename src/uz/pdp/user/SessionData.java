package uz.pdp.user;

import java.util.Stack;
import java.util.UUID;

public class SessionData {
    private UUID currentCategoryId;
    private UUID currentProductId;
    private Stack<String> stepHistory = new Stack<>();

    public UUID getCurrentCategoryId() {        return currentCategoryId;
    }

    public void setCurrentCategoryId(UUID currentCategoryId) {
        this.currentCategoryId = currentCategoryId;
    }

    public UUID getCurrentProductId() {
        return currentProductId;
    }

    public void setCurrentProductId(UUID currentProductId) {
        this.currentProductId = currentProductId;
    }

    public Stack<String> getStepHistory() {
        return stepHistory;
    }

    public void setStepHistory(Stack<String> stepHistory) {
        this.stepHistory = stepHistory;
    }
}
