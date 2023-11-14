package model;

import static org.assertj.core.api.Assertions.*;

import controller.InputController;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Pattern;
import org.assertj.core.util.Strings;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

public class ServiceTest {
    @DisplayName("공백 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {
            "타파스-1, 제로콜라-1",
            "타 파스-1, 제로콜라-2",
            "타파스 -1, 제로콜라-3"
    })
    void testContainsWhitespace(String menuOrder) {
        assertThatThrownBy(() -> {
            Domain.getInstance().setMenuOrder(menuOrder);
        }).isInstanceOf(IllegalArgumentException.class);
    }

    @DisplayName("형식 확인 테스트")
    @ParameterizedTest
    @ValueSource(strings = {
            "타파스--1,제로콜라--1",
            "타파스1,제로콜라-",
            "타파스1,제로콜라-0",
            "타파스-1,제로콜라-2"
    })
    void testFormCheck(String menuOrder) {
        assertThatThrownBy(() -> {
            Pattern ORDER_PATTERN = Pattern.compile("([가-힣]+)-([1-9][0-9]*)");

            for (var menu : Service.getInstance().makeMenuOrderStringToList(menuOrder)) {
                if (!ORDER_PATTERN.matcher(menu).matches() || countHyphen(menu) != 1) {
                    continue;
                }
                throw new IllegalArgumentException();
                //임의로 모든 조건이 충족할 때 예외를 발생시킴
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    //'-' 개수를 검사 메서드
    private int countHyphen(String str) {
        int count = 0;
        for (char c : str.toCharArray()) {
            if (c == '-') {
                count++;
            }
        }
        return count;
    }

    @DisplayName("메뉴 중복 테스트")
    @ParameterizedTest
    @ValueSource(strings = {
            "타파스-1,제로콜라-2",
            "타파스-1,제로콜라-2,타파스-2"
    })
    void testMenuDuplication(String menuOrder) {
        Map<String, Integer> menu = Service.getInstance().makeMenuOrderListToMap(
                Service.getInstance().makeMenuOrderStringToList(menuOrder)
        );

        boolean hasDuplicates = hasDuplicates(menu.keySet());

        assertThatThrownBy(() -> {
            if (!hasDuplicates) {
                throw new IllegalArgumentException();
            }
        }).isInstanceOf(IllegalArgumentException.class);
    }

    boolean hasDuplicates(Collection<String> collection) {
        // 중복 여부를 확인하는 메서드
        Set<String> uniqueElements = new HashSet<>();
        for (String element : collection) {
            if (!uniqueElements.add(element)) {
                // 중복이 발견되면 true 반환
                return true;
            }
        }
        // 중복이 없으면 false 반환
        return false;
    }
}