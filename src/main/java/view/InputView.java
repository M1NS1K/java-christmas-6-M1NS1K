package view;

import camp.nextstep.edu.missionutils.Console;
import java.util.*;
import java.util.HashMap;
import java.util.stream.Collectors;

public class InputView {

    public int readDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        String input = Console.readLine();
        int date = Integer.parseInt(input);

        if(date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        return date;
    }
    // ...

    public HashMap<String, Integer> readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        String input = Console.readLine();

        return null;
    }

    public List<String> menuOrderSplit(String menuOrder) {
        List<String> menuOrderList = new ArrayList<>(List.of(menuOrder.split(",")));

        return menuOrderList;
    }

    public Map<String, Integer> convertMap(List<String> menuOrderList) {
        Map<String, Integer> menuOrderMap = menuOrderList.stream()
                .map(menuAndAmount -> menuAndAmount.split("-"))
                .collect(Collectors.toMap(parts -> parts[0], parts -> Integer.parseInt(parts[1])));

        return menuOrderMap;
    }
}