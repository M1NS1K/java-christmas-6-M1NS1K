package controller;

import java.text.DecimalFormat;
import model.Domain;

public class OutputController {
    private final Domain DOMAIN = Domain.getInstance();

    public void showOrderMenu() {
        DOMAIN.showMenuOrder();
    }

    public int getTotalOrderAmount() {
        return DOMAIN.getTotalOrderAmount();
    }

    public String getMoneyExpression(int totalOrderAmount) {
        DecimalFormat decimalFormat = new DecimalFormat("#,###원");

        return decimalFormat.format(totalOrderAmount);
    }
}
