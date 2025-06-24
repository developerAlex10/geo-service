package sender;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.netology.entity.Country;
import ru.netology.entity.Location;
import ru.netology.geo.GeoService;
import ru.netology.i18n.LocalizationService;
import ru.netology.sender.MessageSender;
import ru.netology.sender.MessageSenderImpl;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@ExtendWith(MockitoExtension.class)
public class MessageSenderImplTest {

    private final static String ipRus = "172.123.12.19";
    private final static String ipUsa = "96.44.183.149";
    private final static String textRus = "Добро пожаловать";
    private final static String textUsa = "Welcome";


    @Mock
    private GeoService geoService;

    @Mock
    private LocalizationService localizationService;

    @Test
    void messageSendRusTxt() {
        Mockito.when(geoService.byIp(ipRus))
                .thenReturn(new Location("Moscow", Country.RUSSIA, null, 0));

        Mockito.when(localizationService.locale(Country.RUSSIA))
                .thenReturn(textRus);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipRus);

        assertEquals(textRus,
                messageSender.send(headers));
    }

    @Test
    void messageSendUsaTxt() {
        Mockito.when(geoService.byIp(ipUsa))
                .thenReturn(new Location("New York", Country.USA, null, 0));

        Mockito.when(localizationService.locale(Country.USA))
                .thenReturn(textUsa);

        MessageSender messageSender = new MessageSenderImpl(geoService, localizationService);
        Map<String, String> headers = new HashMap<>();
        headers.put(MessageSenderImpl.IP_ADDRESS_HEADER, ipUsa);

        assertEquals(textUsa,
                messageSender.send(headers));
    }
}