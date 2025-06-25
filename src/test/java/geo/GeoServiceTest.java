package geo;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.geo.GeoServiceImpl;

import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.assertEquals;

class GeoServiceTest {

    static Stream<Arguments> locationIpProvider() {
        return Stream.of(
                Arguments.of("172.25.46.58", new Location("Moscow", Country.RUSSIA, null, 0)),
                Arguments.of("96.25.46.58", new Location("New York", Country.USA, null, 0))
        );
    }

    @ParameterizedTest
    @MethodSource("locationIpProvider")
    void locationIpTest(String ip, Location expected) {
        GeoService geoService = new GeoServiceImpl();
        Location actual = geoService.byIp(ip);
        assertEquals(expected.getCountry(), actual.getCountry());
    }
}
