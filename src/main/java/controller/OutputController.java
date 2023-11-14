package controller;

import model.Domain;

public class OutputController {
    private final Domain DOMAIN = Domain.getInstance();

    public void showOrderMenu() {
        DOMAIN.showMenuOrder();
    }

    public int getTotalOrderAmount() {
        return DOMAIN.getTotalOrderAmount();
    }
}
