package com.redshield.envlope.service.impl;

import com.redshield.envlope.entity.EncParamet;
import com.redshield.envlope.service.EnvService;
import com.redshield.envlope.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Base64;

/*************************************************************************
 ******
 * - Copyright (c) 2021 redshield.com
 * - File Name: SendServiceImpl
 * - @Author: WangJiLIn
 * - Description:
 * 接⼝描述
 * - Functions:
 *
 * - History:
 * Date        Author          Modification
 * 2021/3/29   WangJiLin     Create the current class
 *************************************************************************
 ******/
@Service
public class SendServiceImpl implements SendService {
    @Autowired
    EnvService envService;
    @Autowired
    RestTemplate restTemplate;

    @Override
    public String openEnv(String data) {
        String auth = "MIIB+jCCAaGgAwIBAgIKEQEAICEDJhQUJzAKBggqgRzPVQGDdTA2MQswCQYDVQQGEwJDTjEnMCUGA1UEAwwe55S15a2Q6JCl5Lia5omn54Wn566h55CG57O757ufMB4XDTIxMDMyNjE0MTQyN1oXDTIxMDMyNjE0MTQyN1owfjEkMCIGA1UEAwwb5pWw5o2u5Lit5b+D5o6l5Y+j5pyN5Yqh5ZmoMRUwEwYDVQQLDAzkv6Hmga/kuK3lv4MxITAfBgNVBAoMGOW4guWcuuebkeedo+euoeeQhuaAu+WxgDEPMA0GA1UEBwwG5YyX5LqsMQswCQYDVQQGEwJDTjBZMBMGByqGSM49AgEGCCqBHM9VAYItA0IABFrYV+RjcIaO5AijcMEMEgtaO6n3shHGdYP9CvCtaAuJmtfFv19cVY8kr1wQ2mYJM0IlhqHYI/WkBQeIzRukMYajTzBNMB0GA1UdDgQWBBT3fYl9PDpIAoPI3g1iB3pM/z7m2zAfBgNVHSMEGDAWgBT0z3ajiyllSwL6iZID7jPBxb/AmTALBgNVHQ8EBAMCAaIwCgYIKoEcz1UBg3UDRwAwRAIgRrAYNZWicrACZeIyTe69sWxNWjDGziZ3tO0YE7xbiMcCIAYw8fXCmaop4VIXGEgnUhTVABzF8eod6dhUH/hX4H78";
        String env = "AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACzSWrB+gUd4FI897yID7WRdt0BNZxnAQqHisRAz409nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAkGRtxH0eExwf0TNzVnfwNqQKwDHCVw5k1Ko4idYTmIc2RuOTepBq8gOgV/ysOzpWO5cKVlYKNmNz3jMULzl/8hAAAAAjoXh9cBCkPmTx5BcykgPoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQQAAHqR0LaQ26980aZC/A7M5Ax1Dz5FOL8EBQ9/q1DBzFdXFLEPLL7sYq5OEVah9rCWf2q6pESCNSFkP8SGodl9Z0TsiJeZ+Q/Jv949NPFreREm";
//       String env= "{\"encData\":\"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACzSWrB+gUd4FI897yID7WRdt0BNZxnAQqHisRAz409nAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAkGRtxH0eExwf0TNzVnfwNqQKwDHCVw5k1Ko4idYTmIc2RuOTepBq8gOgV/ysOzpWO5cKVlYKNmNz3jMULzl/8hAAAAAjoXh9cBCkPmTx5BcykgPoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQQAAHqR0LaQ26980aZC/A7M5Ax1Dz5FOL8EBQ9/q1DBzFdXFLEPLL7sYq5OEVah9rCWf2q6pESCNSFkP8SGodl9Z0TsiJeZ+Q/Jv949NPFreREm\"}";
        String encDate = envService.makeEnvelope(auth,"原文");
        EncParamet envParamet = new EncParamet();
        envParamet.setEncData(encDate);
        HttpHeaders httpHeaders = httpHeader();
        HttpEntity<String> httpEntity = new HttpEntity(envParamet, httpHeaders);
        ResponseEntity<String> stringResponseEntity = restTemplate.postForEntity("http://192.168.3.114:8080/services/ws/posts/v1/enterprises", httpEntity, String.class);
        System.out.println(stringResponseEntity.getBody());
//{"message_content":{"encData":"AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAABwxk2VqaRJpALP2/gSl58ckyMad6FkU/k1ayJ0PtaOoAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAACzmQrykDgdGMB0g+Ux1hV9lb2agwidK+V4+AKL8Cwd9t92s9reLUFPQT8FodpI0KTjpnozJyHhC7TqxobKD0JBAAAAAl3LyJqm2lw4MHolUyyKD2AAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAAQQAAO/T/ajn2ON0Xpb8gHJq/2WE2tztzoinHzTHiZ58a6caZTxkqVD0IkzMUPTAxWqV+mJRh4tOVCQ/aza91m4IAbzYHUDc7h05HNctPsUNZn7/5X5S/qpYrDSZrrZ/vBq+5KZ0Iy/rowbSME1JaJmewrNQHg2gAYoGVi4bNTD1f9AMP2hPKf/PsxLfdc3wwdu+y8/E5aNlyUvK/E0oFo1c57Q1ws4+CkDFHoRUXPEt6+gY/nghDt4FiuLcUcPNV7S3+NNyNikTeZUhb6Y9AiC7TFORoOY4CsfLfe0yjQeqB5He81N5i8EAnVU6DaHBbgqIeyR8Qd9S2ctd2+LWjZ89MGAl1saD30FxhIJa8MbkZvjkbkBHCAiXtwU98gnPo18Tk8zK0X3Jkm3aNZQhk0wKY0ZSsUpttvyiWA6P8OPP6mJiTq9n0hihsjEMYJryJfPvOsse4LZYNncOeb8zmdgV6zQTcPbgswZyQ7ge3ZZntE13SHQ78+a9CQezTUuhwPOXfKsyEaz1jIgSq7CzIefBzb3oYfctIEWUeUqJNukprmqr3i90LYuKU+An+fou4MO1pgMp12XI4UM05C9AVlQVW8wa3NyGxSteBsKOVTC//wGXPAgKlKJSdEtZYdQnFMHtqLnSwiVyVbVhQDdQH8CfOOgsyTdrRk7wg4ejKjCb4niCZrZB2NtePtgLdfrXaTSzZtEfC8NA0I+y57BBxgwjRaIxEtn2b4EaZxPM+jYjzYnr/3f9JpYF63g6j9Rx5xYhNsVAyQdl7k7qPd5Y87CB04rCVbylJ69C6I6RPmcZbum+7QS8uHCFL+AMZZGQtYEGJOU1W51Q3yMV+QOeN2bAlCsj+DDNCmCKl39K++/siaN8HLOiiqAOonjbzNYYNwVoC3uRDbbgRmDdj3oO8f3UV9KNaxtT5ptHrb3McK8GcEzMvqzi9rPamQlF3XcRi9TjNczK0X3Jkm3aNZQhk0wKY0bveFY3UlCtZcuvvKspCFlk99sXe36nIje/rB/IlXo8g5F2Lx81WxJ/msSmOxwdOTDXGyuXDBAeKEI2GNcUNsiciP8o5coBfQ7ozhoK2KETXHesRdEpYYufXxf10D2FaFOdn2/6JdMwqUjNJIIvMXL74IWWcPzjyKtt9YUfcJZdlC4wWC/sf6m/Hegd3FhFD9ly3ccnKBoNRfX8tA9VaRkYH83dIPTgQp8+qz5GtCHsZZ3qtXuJ0HSmdlucNNc3sdHAcyjX59Ntas9eAwHs+cz0SPiMShL8Pj85VkZ5sYYCyCMlGhvtRlIUAltJf/KExVNhnS6z0yOmpjYZQutBB7UT8yj8R2xw11KeWHMZ3TZ7YaXKCD2ZrIlJzq1MmXdMiEVpPD6txwmRiWe/Z4KJ4OUc4wLrCc68E/HG8RLrwzkHWl7LEjwiTaO5ONqD1oHMMA0ayE1UcmCUEFs+Rzy8dZtdHnsWDzs07/Q4y/jD6TkrMd8ZzYZHk6nHZFoi5ZLWEdBb6D7gd++QM+nm9qgWFcKoZ3DHDWHjpgFvgTe7NyG95rykwkpbeh9oeOc1NCBDm3Fm2a3vsOobgZzZMcIjAcGDvZTvPOP7itHEVsNf4I47A6TeDO5y7IjGyLkgcih0CGtOJiBq0RW/TghK1uyfLvesYXB6tTA+H2FMkwhxTRfurJGrZtuzkziXlnx5OtwhY8NcpFpfT4E2Nl0aB4WIVvgFqD6IKzLr/7aBpiRt54jLLXVpIsbjjFSCDeU64RxYmV7WeBLYtJbWpZ4VvE1F5vYsGbm4W6ueh8vENmkHB0ehnVh4ic4gbY2PQGc1zszoMXENvOW+V6np+ykZKsPTIqGS1uvvI2D1ipCJbMb7PrzoGEQb6WxwbcF3IrcUJnn66JrL4PxhEzx/fjZkQj0IRNqgS1cncUf1XgDni9E5UKIPOqaQ3LTQrD+AlMenK62vAxHv70rZAxmbzuR7Gu9XgPWD64q/G34HzF1BQP6h2wvUCSjrm/I99C2TQo376VucyQjOv3AreVXiF22MUJy906dRJ4DOViscSYrcLA8n9goarye3yJVKrhqLC/6BHclMyCpAs7v0VGymCbBo4OOJtY6HLz+x7W2auB94AeJxTcb2QLBQEVcXy3x0y9xHaCSs3iXGkksKj0XfmuvWjiB4jDn6q9LB7bzxk2TFBsxTuR3VWCpaR2iD6LB4NC54W16SnXt+RW1SZob4DNTquMK0zMwUMYIW1vIkmM7F50FUhskghAl9gXehYCVce6J0csriKy6aDMDbH/iLcHs9ntePf119C4mOyqvhrkKu16k39QnspNF0ZMGIqpotJ0kd0pQmx5QbEatQviALvxGPbzjIuu+TLjBYL+x/qb8d6B3cWEUP2XLdxycoGg1F9fy0D1VpGRhuq2AhVKqxKsXex7a1kd7fGV8s65Q15OQK80bnP2uylhPEypj1oPygpoKPJ9f6ReASPLp+9J6/sPcFMi45JGE4IyUaG+1GUhQCW0l/8oTFU2GdLrPTI6amNhlC60EHtRNvc+YMpRLiLAEHaYZlX88QTDKrhDCJlGzeJkAOQrq+mfmqWnLoHS/RZzioGCjV6N4btGG4wr6iJYCvH1RvYzqbA4AGyEmE6pR7PZn4iyywL/n81xwD+JCWCxI4fmG5qOlfF50St6dmMNKR4DTTrhxgHKzWOMJSXfdvqes63p2GIWu4dNTt8+c4j09Ql4q9YY+p2aBmOdJZHmLm0FBvz4fz8vrER/pAnGL48CWHkepCcI8QqDsPmB2/2y9ByOediAN+UKq8/78lCxhiXQ/HCRxxAUfKdlmjFAZtLS5YNNl15lWrTdGDDqWYSfswSsbZZl3XGyuXDBAeKEI2GNcUNsiciP8o5coBfQ7ozhoK2KETXHesRdEpYYufXxf10D2FaFOdn2/6JdMwqUjNJIIvMXL7WjoMdQkKDYMHVqEPWoRBW15oCT1+kaNg0zxLCwvUdo9pHP2K8tNkFko3ZCpmwr4wavL0gJYDvskvnKK/mMypA0Fem9KkKz8ACaqtIJgNNeTkZMCVvdIv2B+LbzXn551vjA42shAUGe6EKI8LDMdE5dZ4Eti0ltalnhW8TUXm9ixRVmDpK6M+onfbiL2bbO9u5CfG1mMSya443OWHBOrrMqu/mjDnrBpUQH+eDUuBUCo1ws4+CkDFHoRUXPEt6+gY/nghDt4FiuLcUcPNV7S3+OIRdclnQqgRH0sKmjCB8hjoY5uzzQ2k/D7DMH3FAI0tCbQImzf3sDBncMZj6CPSS/D3V2t/90xImBfqGURZ6H2KSsPZsmF41Qha+TncztjSnbOxv69kqEwcXPIa/V5OycQ9khBN+zP0nCzDZrIxgfqNPJOc4Ou0Y8rTCEZbo9+UhEpVAlaz+VIie8gFktbN4ga3ZnLeESGN7TOLWnpMAGW487g+YbWHi8hTxBXV31u5PXL/TOuMej52c/FcRSLx6v0ENHHadeax1r50DkR/tDKWKJffkflfEPvz0mibh5uYw+KJEhQ5WPrCDhp2OA7FG/tD4TOxdv2D6SHZooiZJq3FsNu4+SH3NqpVBfhpSeB5cjdjTzO3RM3XD4p3AhnAiW7Rkgu/wcmaXqIquET3JUGD7KIA7mYkGjiNOd89d7AhJE1Rn4dmTtRznOeK7w73QQ6yRikbnr1ehNqzsPfyB8C53M9qbX0un+7JeQ+2KrI5JkGD1eSJdfIqRhjjilF2SeXEc+TWTxRxq2JU6+yNrsoX3MiAomDBBFJOIuKBZ6ZLcRw9pQ1lCyNg1hv0CE6QNFGL0siIa/lHnn9lV1rEXin8N8+j5t4JWd5CURplW4np2LHqhyYi3WRGghvwuaIVXaQtQuI3C+Eq/zroOItJID3IdwTdWipoM3MNJ/j55MUyfK7yxnrdZ2nABq4KbRfLQlW521qdKBTY3t7tQkIsbWTjnIh4AKBzL/4Ycgn3C52A4DkbluoVxIKcijuGlxo9L0xxRLk++g8nJgfjwcEtlBv4KymIGFKuqshTmppPcfz+3GV/86rtASZ/aLCmsi5eCwtLW64CsCxM+gBPNbNYaPoTZz0EINOrR9uRmaC5mdSkKi8sRBKs5rnl5+djXM+ODP+0u8+st951VR7uUfQxiX8HXKBq3YJae+FkzSpx17Z7mzRnZvLjXn0WQeasI2JOocKFCGqCciEJF7TZcHetvRw+T1a+hYWPEnAtGlAtNPGQ9xrDPJ4jjIurf1zH8QGhcXe6LUwYZgqAEIwlucGf7lLlflL+qlisNJmutn+8Gr7kpnQjL+ujBtIwTUlomZ7Cs1AeDaABigZWLhs1MPV/0AxpVdnUvtRKFq1t9avJdeexjh/9mCN0IGjP44MelkV1jeuKvxt+B8xdQUD+odsL1Ako65vyPfQtk0KN++lbnMkIzr9wK3lV4hdtjFCcvdOnUSeAzlYrHEmK3CwPJ/YKGq8nt8iVSq4aiwv+gR3JTMgq4iK/HxUPDSaxKpB+WFA3eS8/se1tmrgfeAHicU3G9kCwUBFXF8t8dMvcR2gkrN4l8wrNv47tPsboTiIxYeGS+avSwe288ZNkxQbMU7kd1VhZgtDE+neb5soFBqnQtnWsfkVtUmaG+AzU6rjCtMzMFDGCFtbyJJjOxedBVIbJIIQJfYF3oWAlXHuidHLK4isumgzA2x/4i3B7PZ7Xj39dfQuJjsqr4a5CrtepN/UJ7KTRdGTBiKqaLSdJHdKUJseUAAHZGPRNsfSX7KUhjY+dD5V7aMC/0bgEB4zcI8YGcCU1XQrogJC5bQnSkU+rm9cYsr5kWzJ6EvHh5JFMUe9owPpLftM26S5eARafsfK7H9kimJrgFuzwlEAT2H8sUubqQKGWlU5bzPBJMCu4VxlQgi+wDh4piEo9mMmfsck6DqWsHTdRlaeCJ2JLA0DKgNIPI6GcRUnKmBPrylna+jvy7JZzD3X1sY3wx2zvJ66Z0C38eroUDxjay/Tcku5gmFFBMszSdc4ok1K5YYWoH778NoZUhrvhZjdark/z8o3xTvqEQWSlgWMUz+yIO2KnzjAxVQljMOR4CK6ivMJx35Y91g0E7zNo3mNUjGZA5WPKbGcLnfhcZNmQqFPF0OvXpxa1f6nnr7rQs63m1pqFf12qUavUJ1/gIC2QniK5Iv6se/dOvbYRGncynzGbGKlVELtOKSgPxdnSu7/hUzfVrJdCLzIhbm8pA0xb1xrwEtg9LIL7DBAxOjvQO0Sjg8pFgw0m5OoHuyjxPuqDAxa8XodTh1pa4k7PBUglca85Rm7MgyX3gCT1/tyPLWxcGLcGc1u6lH5PB0YU1mdE4LJbVKb9kqxj4YSaUWzz6xFNMSwKct4qUvZ/bY9Q6BL/er+bqcXVvlRIcXqw79/hRUpCb0qZcDzR0tqDZjYOOgcSwUQ1XgVpop2lwMHh+aTPxCDt1Tr0vS8UnUOD2E4X9Sr93d8lam6YtMM1nA490iiDjaDth9LmDOTUGrgvoRbtqBy4JcBLHgpL3iprVlthqc796cttxD1qMTD6nAOrKR5G2KNwwhWE8Vuyi8gvK43JHwIr3DAaH/rA96dQgW1V3uG05rllV+JgdVRSmM9f9D2XnB5siuFE+xQ004Pu0ou2iTQcOIAJkaDmOArHy33tMo0HqgeR3jlC+bBosNySehgQVkJplOkkfEHfUtnLXdvi1o2fPTBgJdbGg99BcYSCWvDG5Gb45G3R9UER+F3KWgYcczEi0GpEywb/rNlkv5Yvqh6sLid8jmP5OgqpKz1MeG0v5TP5hsNDAo9cocMYbUJPitM7OrQRu/qPSye2C1Mxg3P3mn/I5OoHuyjxPuqDAxa8XodTh1pa4k7PBUglca85Rm7MgyX3gCT1/tyPLWxcGLcGc1u6lH5PB0YU1mdE4LJbVKb9kqxj4YSaUWzz6xFNMSwKct4qUvZ/bY9Q6BL/er+bqcXVvlRIcXqw79/hRUpCb0qZcHr08sHLdZCmNS+6FBCy/PVpop2lwMHh+aTPxCDt1Tr0LpTxFgxDoPHo7Bzuo5vE+W6YtMM1nA490iiDjaDth9LmDOTUGrgvoRbtqBy4JcBLM+AnIcJNHqWYNhALv73+HSSFTMA+KJRW0drY3q1FoDYnyiQqDWdJXHzZ8eDjYjz6Z+O4+qu/em6poy8CQm04TsD66iLeXBwnWtjYQnGNNYddPC7NfADZCyLppvNd217IftnUGvn9uDVbHehN8SYijK8bIlXAn8HtSvlA6ycka8RruHTU7fPnOI9PUJeKvWGPjO/YqNzlQ5d6ECyFx3XhktO6LbV26vU/bInNzAe6bQbMytF9yZJt2jWUIZNMCmNGUrFKbbb8olgOj/Djz+piYk6vZ9IYobIxDGCa8iXz7zrLHuC2WDZ3Dnm/M5nYFes0E3D24LMGckO4Ht2WZ7RNd0h0O/PmvQkHs01LocDzl3z654TmI1decxa/KkjTrIqE6GH3LSBFlHlKiTbpKa5qq94vdC2LilPgJ/n6LuDDtaYDKddlyOFDNOQvQFZUFVvMGtzchsUrXgbCjlUwv/8BlzwICpSiUnRLWWHUJxTB7ai50sIlclW1YUA3UB/AnzjoRCG+j0NtyabOnZUMQWMXlma2QdjbXj7YC3X612k0s2bRHwvDQNCPsuewQcYMI0Wi8TO5EFyKLoSBhF4p6PpASJuhc/eQzcBU0Q6iZ8rVgrZ931abtAtI4nMRUa72jUeVzbO2jog6+Wj3bI3SgiOrvzGghJ5ZfxRY9ZSEdTdpy6Pf8jM3+PruW+tOQyG0fKvtkD2bgp7sQhgVzhCVjS9Lvolhp9X++Gs4zRtlKpZtO22KSsPZsmF41Qha+TncztjSALKddc30YmIUXQtm1iqMuKKqd0GjmdsbFblKuLM5YfxmCIL1d/s5KnrG4KWviV4tuyHi6HZuuy8JzE4W8zW4NCFIzATnnNtHZSj5JEubIm85ircbMaUTWr8ai9Zi/PWxG2d+NjlE2MaowEvhoCvcHZuRgROv9o2iAaT+uuRdLWhxGYYujCO8mWf61gYPTy2Uef4iG5BVRGJ2P8KNryBXTi9D/VVbGTEPhTlwGGzoNDbC/lL+mPcN7LYENOBwBQMWtxbpDvweLDgfcBfoHDIByY/X3sVq1Q1uYXCm2prnclh6r8aZk0GCvRIZJVrmsK+i+pnzASWaXyhEXPjLCvvNdG3tTKJnbJas3ykIRFkH3UshE7aaNTC08nTSnG7mUUzqyzTXBySJdQ9V3x2YM7uCXoDgK6ChRNfh1uEa4WwUN51Qic+5YgkI9lD1gIV6vxtMaNtxy+vvmOUo5W3H2MDy2kylmbLNOwz/Z1/1ccvMRuCs1tdaC32ubNjcnasCdWafoDoNOJrLtCGxOgmNbEowDMhyNLfvihSB98iJcd0Z43iP3rR6oqAiIiOmxlxq37m+yAmLIiXJw9jETyTfz/8YGRdzNfcAK2h4baBsxDMQbx5fo3hNYXSKhdugMSnseQYDGUtNOGCw3gwIk9FD8HD6sZXNq2FUnHNtmEToNfIVxccQbK9qK1hQbjCNjSsf3dAvnTP0IxIKKxeAKeaajAwyxB4j8Nxy7rF8wHKZWi/wge7RbPbQtjd+kkqjW4lo85bc2ucNNhYbNrBj/9dXCg5z3D1Vixem0j2pfM7j3iXgw3drwsdrcSDD1ol5BtWqCdZNY7ocsLwtSS9aGjnSDNadPpPRFU+MpWuL0b74jgRR4iA="},"message_header":{"msg":"查询成功","code":"Z200"}}
        return envService.openEnvelope(stringResponseEntity.getBody());
    }

    /**
     * 设置请求header
     *
     * @return
     */
    private HttpHeaders httpHeader() {
        HttpHeaders httpHeaders = new HttpHeaders();
        ArrayList<MediaType> acceptList = new ArrayList<>(1);
        acceptList.add(MediaType.APPLICATION_JSON);
        // 设置contentType
        httpHeaders.setContentType(MediaType.APPLICATION_JSON_UTF8);
        String userAgent = "Mozilla/5.0 (Windows; U; Windows NT 5.1; en-US) AppleWebKit/525.13 (KHTML, like Gecko) Chrome/0.2.149.29 Safari/525.13";
        httpHeaders.set(httpHeaders.USER_AGENT, userAgent);
        return httpHeaders;
    }

    public static void main(String[] args) {
        final Base64.Decoder decoder = Base64.getDecoder();
        final Base64.Encoder encoder = Base64.getEncoder();
        final String text = "123456789";
        final byte[] textByte;
        try {
            textByte = text.getBytes("UTF-8");
            final String encodedText = encoder.encodeToString(textByte);
            System.out.println(encodedText);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
//编码
//解码
//        System.out.println(new String(decoder.decode(encodedText), "UTF-8"));
    }
}
