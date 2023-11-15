package view;

import camp.nextstep.edu.missionutils.Console;
import model.Service;

public class InputView {

    public String readDate() {
        System.out.println("안녕하세요! 우테코 식당 12월 이벤트 플래너입니다.");
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");

        String input;
        int date = 0;

        while (true) {
            try {
                input = Console.readLine();
                date = readUserInput(input);
                dateScopeException(date);
                break; // 잘못된 값이 입력되지 않았으면 반복문 탈출
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // 에러 메시지 출력
            }
        }

        return input;
    }

    private static int readUserInput(String userInput) {
        String input = userInput;

        if (!isValidInput(input)) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }

        return Integer.parseInt(input);
    }

    private static boolean isValidInput(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private void dateScopeException(int date) {
        if (date < 1 || date > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }
    // ...

    public String readMenu() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");

        String input;

        while (true) {
            try {
                input = Console.readLine();
                Service.getInstance().makeMenuOrderListToMap(
                        Service.getInstance().makeMenuOrderStringToList(input));
                break; // 잘못된 값이 입력되지 않았으면 반복문 탈출
            } catch (NumberFormatException e) {
                System.out.println("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage()); // 에러 메시지 출력
            }
        }

        return input;
    }
}