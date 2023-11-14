package model;

import java.util.Map;

public class Domain {
    private final Service SERVICE = Service.getInstance();
    private Map<String, Integer> menuOrder;

    public void setMenuOrder(String menuOrder) {
        this.menuOrder = SERVICE.makeMenuOrderListToMap(
                SERVICE.makeMenuOrderStringToList(menuOrder));
    }

}
