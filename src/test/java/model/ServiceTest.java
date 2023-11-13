package model;

import static org.assertj.core.api.Assertions.*;

import java.util.regex.Pattern;
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
            Service.getInstance().containsWhitespaceException(menuOrder);
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
                if(!ORDER_PATTERN.matcher(menu).matches() || countHyphen(menu) != 1) {
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
        for(char c : str.toCharArray()) {
            if(c == '-') {
                count++;
            }
        }
        return count;
    }
}
