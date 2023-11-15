package model;

import java.util.Map;

public class Domain {
    private final Service SERVICE = Service.getInstance();
    private final int[] SPECIAL_DAY = {3, 10, 17, 24, 25, 31};
    private Map<String, Integer> menuOrder;
    private Map<String, Integer> giftMenu;
    private Map<String, Integer> benefitHistory;
    private int appointmentDate;
    private int totalOrderAmount;
    private int totalBenefitAmount;
    private int amountAfterDiscount;
    private String eventBadge;
    private boolean eventActive = false;

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = SERVICE.makeMenuOrderListToMap(
                SERVICE.makeMenuOrderStringToList(menuOrder));
    }

    public void setDate(int date) {
        this.appointmentDate = date;
    }

    public void showMenuOrder() {
        for (String menuName : menuOrder.keySet()) {
            System.out.println(menuName + " " + menuOrder.get(menuName) + "개");
        }
    }

    private void setTotalOrderAmount() {
        totalOrderAmount = Menu.getTotalPrice(menuOrder);
        setEventActive();
    }

    public int getTotalOrderAmount() {
        setTotalOrderAmount();
        return totalOrderAmount;
    }

    private void setEventActive() {
        if (totalOrderAmount >= 10000) {
            eventActive = true;
        }
    }

    public void setBenefitHistory() {
        benefitHistory.put("크리스마스 디데이 할인", setChristmasSale());
        benefitHistory.put("평일 할인", setWeekSale());
        benefitHistory.put("주말 할인", setWeekendSale());
        benefitHistory.put("특별 할인", setSpecialSale());
        setTotalBenefitAmount();
        setAmountAfterDiscount();
    }

    public void setTotalBenefitAmount() {
        int totalSale = 0;
        for(int sale : benefitHistory.values()) {
            totalSale += sale;
        }
        totalBenefitAmount = totalSale;
    }

    public void setAmountAfterDiscount() {
        amountAfterDiscount = totalOrderAmount - totalBenefitAmount;
    }

    public void setEventBadge() {

    }

    private int setChristmasSale() {
        if (appointmentDate < 26) {
            int christmasSale = 1000 + 100 * (appointmentDate - 1);
            return christmasSale;
        }
        return 0;
    }

    private int setWeekSale() {
        if(appointmentDate % 7 == 1 || appointmentDate % 7 == 2) {
            return Menu.getIsValidDessertSale(menuOrder);
        }
        return 0;
    }

    private int setWeekendSale() {
        if (appointmentDate % 7 == 0 || appointmentDate % 7 == 3
                || appointmentDate % 7 == 4 || appointmentDate % 7 == 5
                || appointmentDate % 7 == 6) {
            return Menu.getIsValidMainSale(menuOrder);
        }
        return 0;
    }

    private int setSpecialSale() {
        for(int day : SPECIAL_DAY) {
            if(appointmentDate == day) {
                return 1000;
            }
        }
        return 0;
    }

    private Domain() {
    }

    private class SINGLETON {
        public static final Domain INSTANCE = new Domain();
    }

    public static Domain getInstance() {
        return SINGLETON.INSTANCE;
    }
}
