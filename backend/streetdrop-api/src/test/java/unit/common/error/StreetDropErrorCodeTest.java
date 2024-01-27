package unit.common.error;

import com.depromeet.common.error.dto.StreetDropErrorCode;
import com.depromeet.common.error.dto.StreetDropErrorCodeList;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertTrue;


@DisplayName("에러 코드 테스트")
public class StreetDropErrorCodeTest {
    @Test
    @DisplayName("다른 도메인의 에러 코드가 중복되지 않는지 테스트")
    public void testNoDuplicateErrorResponseCodes() {

        StreetDropErrorCodeList streetDropErrorCodeList = StreetDropErrorCodeList.getInstance();
        List<StreetDropErrorCode> errorCodes = streetDropErrorCodeList.getStreetDropErrorCodeList();

        List<String> errorResponseCodes = errorCodes.stream()
                .map(StreetDropErrorCode::getErrorResponseCode)
                .toList();

        var duplicatedList = errorResponseCodes.stream()
                .filter(i -> errorResponseCodes.stream().filter(i::equals).count() > 1)
                .distinct()
                .toList();

        assertTrue(duplicatedList.isEmpty(), "There are duplicated error response codes: " + duplicatedList);
    }

}
