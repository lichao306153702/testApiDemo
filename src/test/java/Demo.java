import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;
import cn.hutool.http.HttpUtil;
import cn.hutool.json.JSONUtil;
import org.junit.Before;
import org.junit.Test;
import util.JdkSignatureUtil;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;

public class Demo {

    /**
     * 私钥
     */
    private final static String PRIVATE_KEY = "MIIBUwIBADANBgkqhkiG9w0BAQEFAASCAT0wggE5AgEAAkEAic7Gu9Jd+TxDm/dkLD7uDMyppvgSHZXwYDTyrIdyNTS4yB5lv6p96KmRPVcEiGS7xTOnlwchx5qOllhexIqtrwIDAQABAkAjGeUq8CF5m20JLBF656iQ4AySd/t9R7TLfJEXewSPInUGGOCZ5anP+tzvFrfqvpRIWlbJNp8EWOda+UHqq8HpAiEA8QLkZuEEvR+Gf0PdkHM2DXSehgKUtndRYIIVOzzeASUCIQCSYMtKkg58SUmkGC6Vic2iwK1vrS1jrUh+lBeCnOONQwIgRgx5Jg2wuuc2yDaJZzqVM0P57ylA3+e+Fza3xQfj3qECIApNn+GW2EgtTG6teRHzijLrhwm2UdyTROgL+n+qFWZLAiBNRhs0yM7Lxxz36PJvnd5piheiKJ4vdNsm8GC2r6h1EA==";


    @Before
    public void before() {


    }


    @Test
    public void getWalletAddress() throws Exception {

        // 请求所需的参数
        Map<String, Object> requestParam = new HashMap<>(16);
        requestParam.put("phoneNumber", "18811443592");
        // 将需要签名的参数内容按参数名的字典顺序进行排序，并拼接为字符串
        StringBuilder sb = new StringBuilder();
        requestParam.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(entry ->
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&")
        );
        String paramStr = sb.substring(0, sb.length() - 1);

        // 使用私钥生成签名字符串
        String sign = JdkSignatureUtil.executeSignature(PRIVATE_KEY, paramStr);

        // 对签名字符串进行url编码
        String urlEncodeSign = URLEncoder.encode(sign, StandardCharsets.UTF_8.name());
        // 请求参数中需带上签名字符串
        requestParam.put("sign", urlEncodeSign);


        HttpRequest post = HttpUtil.createPost("http://localhost:9320/nft-mall-api/meta/getWalletAddress");
        String rebody = JSONUtil.toJsonStr(requestParam);
        post.body(rebody);
        HttpResponse execute = post.execute();
        String resp = execute.body();
        Map<String, Object> map = JSONUtil.toBean(resp, Map.class);
        System.out.println(map);

    }

    @Test
    public void getInfoByWalletAddress() throws Exception {

        // 请求所需的参数
        Map<String, Object> requestParam = new HashMap<>(16);
        requestParam.put("page", "1");
        requestParam.put("pageSize", "10");
        requestParam.put("walletAddress", "0x0538cb79b7c6ce63e5dd0b441b8b5699244e598d");
        // 将需要签名的参数内容按参数名的字典顺序进行排序，并拼接为字符串
        StringBuilder sb = new StringBuilder();
        requestParam.entrySet().stream().sorted(Comparator.comparing(Map.Entry::getKey)).forEach(entry ->
                sb.append(entry.getKey()).append("=").append(entry.getValue()).append("&")
        );
        String paramStr = sb.substring(0, sb.length() - 1);

        // 使用私钥生成签名字符串
        String sign = JdkSignatureUtil.executeSignature(PRIVATE_KEY, paramStr);

        // 对签名字符串进行url编码
        String urlEncodeSign = URLEncoder.encode(sign, StandardCharsets.UTF_8.name());
        // 请求参数中需带上签名字符串
        requestParam.put("sign", urlEncodeSign);


        HttpRequest post = HttpUtil.createPost("http://localhost:9320/nft-mall-api/meta/getInfoByWalletAddress");
        String rebody = JSONUtil.toJsonStr(requestParam);
        post.body(rebody);
        HttpResponse execute = post.execute();
        String resp = execute.body();
        Map<String, Object> map = JSONUtil.toBean(resp, Map.class);
        System.out.println(map);

    }

}
