package model;

import java.util.HashMap;
import java.util.Map;

public class Domain {
    private final Service SERVICE = Service.getInstance();
    private final int[] SPECIAL_DAY = {3, 10, 17, 24, 25, 31};
    private Map<String, Integer> menuOrder;
    private Map<String, Integer> benefitHistory;
    private int appointmentDate;
    private int totalOrderAmount;
    private int totalBenefitAmount;
    private int amountAfterDiscount;
    private String eventBadge;
    private boolean eventActive = false;
    private boolean isGiftMenu = false;

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = SERVICE.makeMenuOrderListToMap(
                SERVICE.makeMenuOrderStringToList(menuOrder));
    }

    public void setDate(String date) {
        if(!isNumeric(date)){
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        int appointmentDate = Integer.parseInt(date);
        dateScopeException(appointmentDate);
        this.appointmentDate = appointmentDate;
    }

    private void dateScopeException(int date) {
        if(date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    private boolean isNumeric(String date) {
        try {
            Integer.parseInt(date);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public void showMenuOrder() {
        for (String menuName : menuOrder.keySet()) {
            System.out.println(menuName + " " + menuOrder.get(menuName) + "개");
        }
    }

    private void setTotalOrderAmount() {
        totalOrderAmount = Menu.getTotalPrice(menuOrder);
        setEventActive();
        setGiftMenu();
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
        benefitHistory = new HashMap<>();
        benefitHistory.put("크리스마스 디데이 할인", setChristmasSale());
        benefitHistory.put("평일 할인", setWeekSale());
        benefitHistory.put("주말 할인", setWeekendSale());
        benefitHistory.put("특별 할인", setSpecialSale());
        setTotalBenefitAmount();
        setAmountAfterDiscount();
        setEventBadge();

        if(isGiftMenu) {
            benefitHistory.put("증정 이벤트", 25000);
        }
    }

    public Map<String, Integer> getBenefitHistory() {
        return benefitHistory;
    }

    public void setTotalBenefitAmount() {
        int totalSale = 0;
        for(int sale : benefitHistory.values()) {
            totalSale += sale;
        }

        totalBenefitAmount = totalSale;

        if(isGiftMenu) {
            totalBenefitAmount += 25000;
        }
    }

    public int getTotalBenefitAmount() {
        return totalBenefitAmount;
    }

    private void setAmountAfterDiscount() {
        if(isGiftMenu) {
            amountAfterDiscount = totalOrderAmount - totalBenefitAmount + 25000;
        }

        if(!isGiftMenu) {
            amountAfterDiscount = totalOrderAmount - totalBenefitAmount;
        }
    }

    public int getAmountAfterDiscount() {
        return amountAfterDiscount;
    }

    private void setEventBadge() {
        eventBadge = "없음";

        if(totalBenefitAmount >= 20000) {
            eventBadge = "산타";
        } else if(totalBenefitAmount >= 10000) {
            eventBadge = "트리";
        } else if(totalBenefitAmount >= 5000) {
            eventBadge = "별";
        }
    }

    public String getEventBadge() {
        return eventBadge;
    }

    private void setGiftMenu() {
        if(totalOrderAmount >= 120000) {
            isGiftMenu = true;
        }
    }

    public boolean getIsGiftMenu() {
        return isGiftMenu;
    }

    private int setChristmasSale() {
        if (appointmentDate < 26) {
            int christmasSale = 1000 + 100 * (appointmentDate - 1);
            return christmasSale;
        }
        return 0;
    }

    private int setWeekendSale() {
        if(appointmentDate % 7 == 1 || appointmentDate % 7 == 2) {
            return Menu.getIsValidMainSale(menuOrder);
        }
        return 0;
    }

    private int setWeekSale() {
        if (appointmentDate % 7 == 0 || appointmentDate % 7 == 3
                || appointmentDate % 7 == 4 || appointmentDate % 7 == 5
                || appointmentDate % 7 == 6) {
            return Menu.getIsValidDessertSale(menuOrder);
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

    public void setting() {
        setTotalOrderAmount();
        setBenefitHistory();
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
