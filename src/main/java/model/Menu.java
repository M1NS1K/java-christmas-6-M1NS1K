package model;

public enum Menu {
    MUSHROOM_SOUP(6000, "APPETIZER"),
    TAPAS(5500, "APPETIZER"),
    CAESAR_SALAD(8000, "APPETIZER"),
    T_BONE_STEAK(55000, "MAIN"),
    BARBECUE_RIBS(54000, "MAIN"),
    SEAFOOD_PASTA(35000, "MAIN"),
    CHRISTMAS_PASTA(25000, "MAIN"),
    CHOCOLATE_CAKE (15000, "DESSERT"),
    ICE_CREAM (5000, "DESSERT"),
    ZERO_COLA(3000, "DRINK"),
    RED_WINE(60000, "DRINK"),
    CHAMPAGNE(25000, "DRINK");

    private final int PRICE;
    private final String TYPE;

    Menu(int price, String type) {
        this.PRICE = price;
        this.TYPE = type;
    }

    public int getPrice() {
        return PRICE;
    }

    public String getType() {
        return TYPE;
    }
}
