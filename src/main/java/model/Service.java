package model;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import org.junit.platform.commons.util.StringUtils;

public class Service {
    private Service() {}

    private final class SINGLETON {
        private final static Service INStANCE = new Service();
    }

    //공백 검사
    private void containsWhitespaceException(String menuOrder) {
        if(StringUtils.containsWhitespace(menuOrder)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }

    public List<String> makeMenuOrderStringToList(String menuOrder) {
        containsWhitespaceException(menuOrder);

        List<String> menuOrderList = new ArrayList<>(List.of(menuOrder.split(",")));

        //예외처리
        formCheck(menuOrderList);

        return menuOrderList;
    }

    public Map makeMenuOrderListToMap(List<String> menuOrderList) {
        Map<String, Integer> menuOrderMap = menuOrderList.stream()
                .map(menuAndAmount -> menuAndAmount.split("-"))
                .collect(Collectors.toMap(parts -> parts[0], parts -> Integer.parseInt(parts[1])));
        //예외처리
        hasDuplicatesCheck(menuOrderMap.keySet());
        isValidMenuCheck(menuOrderMap.keySet());

        return menuOrderMap;
    }

    //정규화 형식 검사
    //0이여도 예외
    private void formCheck(List<String> menuOrder) {
        Pattern ORDER_PATTERN = Pattern.compile("([가-힣]+)-([1-9][0-9]*)");

        for (var menu : menuOrder) {
            if (!ORDER_PATTERN.matcher(menu).matches() || countHyphen(menu) != 1) {
                throw new IllegalArgumentException();
            }
        }
    }

    //'-' 개수 검사
    private int countHyphen(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '-') {
                count++;
            }
        }
        return count;
    }

    //중복 검사
    private void hasDuplicatesCheck(Collection<String> collection) {
        Set<String> uniqueElements = new HashSet<>();
        for (String element : collection) {
            if (!uniqueElements.add(element)) {
                throw new IllegalArgumentException();
            }
        }
    }

    //메뉴 있는지 검사
    private void isValidMenuCheck(Collection<String> collection) {
        for(String element : collection) {
            if(!Menu.isValidType(element)) {
                throw new IllegalArgumentException();
            }
        }
    }

    public static Service getInstance() {
        return SINGLETON.INStANCE;
    }
}
