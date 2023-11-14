package model;

import java.util.Map;

public class Domain {
    private final Service SERVICE = Service.getInstance();
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
        for(String menuName : menuOrder.keySet()) {
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
        if(totalOrderAmount >= 10000) {
            eventActive = true;
        }
    }

    private void setBenefitHistory() {
        benefitHistory.put("크리스마스 디데이 할인", setChristmasSale());
    }

    private int setChristmasSale() {
        if(appointmentDate < 26) {
            int christmasSale = 1000 + 100 * (appointmentDate - 1);
            return christmasSale;
        }
        return 0;
    }


    private Domain() {}

    private class SINGLETON {
        public static final Domain INSTANCE = new Domain();
    }

    public static Domain getInstance() {
        return SINGLETON.INSTANCE;
    }
}
