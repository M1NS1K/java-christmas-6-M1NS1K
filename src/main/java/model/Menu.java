package model;

import java.util.Collection;
import java.util.Map;

public enum Menu {
    MUSHROOM_SOUP(6000, "양송이수프", "appetizer"),
    TAPAS(5500, "타파스", "appetizer"),
    CAESAR_SALAD(8000, "시저샐러드", "appetizer"),
    T_BONE_STEAK(55000, "티본스테이크", "main"),
    BARBECUE_RIBS(54000, "바비큐립", "main"),
    SEAFOOD_PASTA(35000, "해산물파스타", "main"),
    CHRISTMAS_PASTA(25000, "크리스마스파스타", "main"),
    CHOCOLATE_CAKE (15000, "초코케이크", "dessert"),
    ICE_CREAM (5000, "아이스크림", "dessert"),
    ZERO_COLA(3000, "제로콜라", "drink"),
    RED_WINE(60000, "레드와인", "drink"),
    CHAMPAGNE(25000, "샴페인", "drink");

    private final int PRICE;
    private final String NAME;
    private final String TYPE;

    Menu(int price, String name, String type) {
        this.PRICE = price;
        this.NAME = name;
        this.TYPE = type;
    }

    public int getPrice() {
        return PRICE;
    }

    public String getName() {
        return NAME;
    }

    public String getType() {
        return TYPE;
    }

    public static boolean isValidName(String name) {
        for (Menu menu : Menu.values()) {
            if (menu.getType().equals(name)) {
                return true;
            }
        }
        return false;
    }

    public static int getTotalPrice(Map<String, Integer> orderMenu) {
        int sumPrice = 0;
        for(String menuName : orderMenu.keySet()) {
            sumPrice += findMenuPrice(menuName) * orderMenu.get(menuName);
        }
        return sumPrice;
    }

    private static int findMenuPrice(String name) {
        if(compareName(name) != null) {
            return compareName(name).getPrice();
        }

        return 0;
//        for(Menu menu : Menu.values()) {
//            if(menu.getName().equals(name)) {
//                return menu.getPrice();
//            }
//        }
//        return 0;
    }

    public static int getIsValidDessertSale(Map<String, Integer> orderMenu) {
        for(String name : orderMenu.keySet()) {
            if(compareName(name).getType() == "dessert") {
                return orderMenu.get(name) * 2023;
            }
        }
        return 0;
    }

    public static int getIsValidMainSale(Map<String, Integer> orderMenu) {
        for(String name : orderMenu.keySet()) {
            if(compareName(name).getType() == "main") {
               return orderMenu.get(name) * 2023;
            }
        }
        return 0;
    }

    private static Menu compareName(String name) {
        for(Menu menu : Menu.values()) {
            if(menu.getName().equals(name)) {
                return menu;
            }
        }
        return null;
    }

}
