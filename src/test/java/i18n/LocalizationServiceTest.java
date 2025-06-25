package i18n;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.i18n.LocalizationService;
import ru.netology.i18n.LocalizationServiceImpl;

import java.util.stream.Stream;

class LocalizationServiceTest {

    private static Stream<Arguments> localeMessage() {
        return Stream.of(
                Arguments.of(Country.RUSSIA, "Добро пожаловать"),
                Arguments.of(Country.USA, "Welcome"));
    }

    @ParameterizedTest
    @MethodSource("localeMessage")
    void localeMessageTest(Country country, String expected) {
        LocalizationService localizationService = new LocalizationServiceImpl();
        String actual = localizationService.locale(country);
        Assertions.assertEquals(expected, actual);
    }
}
