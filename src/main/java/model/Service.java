package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Service {
    private Service() {}

    private final class SINGLETON {
        private final static Service INStANCE = new Service();
    }

    public void makeMenuOrderStringToList(String menuOrder) {
        List<String> menuOrderList = new ArrayList<>(List.of(menuOrder.split(",")));

        //예외처리

    }

    public void makeMenuOrderListToMap(List<String> menuOrderList) {
        Map<String, Integer> menuOrderMap = menuOrderList.stream()
                .map(menuAndAmount -> menuAndAmount.split("-"))
                .collect(Collectors.toMap(parts -> parts[0], parts -> Integer.parseInt(parts[1])));
        //예외처리

    }

    public static Service getInstance() {
        return SINGLETON.INStANCE;
    }
}
