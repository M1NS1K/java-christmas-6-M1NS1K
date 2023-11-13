package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import org.junit.platform.commons.util.StringUtils;
import org.mockito.internal.util.StringUtil;

public class Service {
    private Service() {}

    private final class SINGLETON {
        private final static Service INStANCE = new Service();
    }

    public void containsWhitespaceException(String menuOrder) {
        if(StringUtils.containsWhitespace(menuOrder)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public List<String> makeMenuOrderStringToList(String menuOrder) {
        List<String> menuOrderList = new ArrayList<>(List.of(menuOrder.split(",")));

        //예외처리
        return menuOrderList;
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
