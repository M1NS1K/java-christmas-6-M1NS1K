package model;

import static org.assertj.core.api.Assertions.*;

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
}
