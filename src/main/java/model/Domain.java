package model;

import java.util.Map;

public class Domain {
    private final Service SERVICE = Service.getInstance();
    private Map<String, Integer> menuOrder;
    private int date;

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = SERVICE.makeMenuOrderListToMap(
                SERVICE.makeMenuOrderStringToList(menuOrder));
    }

    public void setDate(int date) {
        this.date = date;
    }

    private Domain() {}

    private class SINGLETON {
        public static final Domain INSTANCE = new Domain();
    }

    public static Domain getInstance() {
        return SINGLETON.INSTANCE;
    }
}
