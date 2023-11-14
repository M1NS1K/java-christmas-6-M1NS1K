package model;

public enum Menu {
    MUSHROOM_SOUP(6000, "양송이수프"),
    TAPAS(5500, "타파스"),
    CAESAR_SALAD(8000, "시저샐러드"),
    T_BONE_STEAK(55000, "티본스테이크"),
    BARBECUE_RIBS(54000, "바비큐립"),
    SEAFOOD_PASTA(35000, "해산물파스타"),
    CHRISTMAS_PASTA(25000, "크리스마스파스타"),
    CHOCOLATE_CAKE (15000, "초코케이크"),
    ICE_CREAM (5000, "아이스크림"),
    ZERO_COLA(3000, "제로콜라"),
    RED_WINE(60000, "레드와인"),
    CHAMPAGNE(25000, "샴페인");

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

    public static boolean isValidType(String type) {
        for (Menu menu : Menu.values()) {
            if (menu.getType().equals(type)) {
                return true;
            }
        }
        return false;
    }
}
