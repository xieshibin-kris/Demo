package com.miku.advs.core.util;

/*
 *  @项目名：  advs 
 *  @包名：    com.miku.advs.core.util
 *  @文件名:   KeyUtil
 *  @创建者:   jianxiong
 *  @创建时间:  2019/4/8 15:27
 *  @描述：    秘钥
 */

public class KeyUtil {
    //服务端的RSA公钥(Base64编码)
    public final static String SERVER_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqz3Zyn4XzrJZ34IkSGz1tPLYt057ozVu" +
            "irXFU4fHZWXTDOMZ/aqUNZXqACBtfv3IWQQHFg2JoGtGaM8iapjZOcdbmdxbILpQR6JLnAAfn+Ot" +
            "SchRIiTbNX1adMf2x1cmNnQoiPzsf3ddLGX7SdAg+FSs66wVXkVmJ/vnPc+8ebCpdEJPIwjwnRqw" +
            "9hZPPECMY7Xon3QbnqjEDTzkbfxrk1nglCO8ulYFyMOtXCb6RA+iOZUHtUM0i8AvEvbX5Ay5POtA" +
            "47i1MJShDsapfRKjaO8fBhTMYEtOCNfX9QDbH+SN/WMugN0TOSnxlpDL2EKrxW7fkhp5CiVPTEm3+8kd0QIDAQAB";

    //服务端的RSA私钥(Base64编码)
    public final static String SERVER_PRIVATE_KEY ="MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQCrPdnKfhfOslnfgiRIbPW08ti3" +
            "TnujNW6KtcVTh8dlZdMM4xn9qpQ1leoAIG1+/chZBAcWDYmga0ZozyJqmNk5x1uZ3FsgulBHokuc" +
            "AB+f461JyFEiJNs1fVp0x/bHVyY2dCiI/Ox/d10sZftJ0CD4VKzrrBVeRWYn++c9z7x5sKl0Qk8j" +
            "CPCdGrD2Fk88QIxjteifdBueqMQNPORt/GuTWeCUI7y6VgXIw61cJvpED6I5lQe1QzSLwC8S9tfk" +
            "DLk860DjuLUwlKEOxql9EqNo7x8GFMxgS04I19f1ANsf5I39Yy6A3RM5KfGWkMvYQqvFbt+SGnkK" +
            "JU9MSbf7yR3RAgMBAAECggEABM1xRDqRFFzwJFKUDevwYCjHZyyuTv96oC1E4XjXYMKgTqY33mNg" +
            "ULhpnaNLLtqTOdvhDv9GMtePW/UjM/YhcXtj2EbTz3EqOzgUAsYGHQfXnlAd5mU+OJvRY6EQDLsm" +
            "+TP8xwrA3zpLxKj/iSxrIEvPpbRnzQMJQGnSLGudD5SXDBZdfXUBWdkrZqeFAG15wn9uIMQ/ApQG" +
            "gBN06rCQAvHJOqS2HSQH6LAB39iklKvqpfjo6hiTvlEQcmbSHGlPFmSdpi8Du1B4W+gecB8MugyK" +
            "I7d5MQaKPbByeWAXZIfPC8qpK5DmMnke4HywMLQv80NHC6F1HgDTSEddgJgIAQKBgQDX/zOA9QmR" +
            "lVkDtv7JlTHhOwZm++yP3QeE4y1YQ9c63NTZMNHZI1Jy3sRCxknDTaIoyaACnOMDrpUNTxpbfOgx" +
            "hoYCNRJ1GxedH+15BA0EGVtuSP/5fHDwhrtYGQF0UCkAcqbF0Zzv1TqS+ggyrm1GLoIJJpe6ve1E" +
            "spZqAOuBwQKBgQDK9LjJv1a6R8DyE4H7NrrLedxEmx7WS+6JhtQTP2N9qkqBnhroEUiC75Sl8JqU" +
            "GS3HLAyTBchlY1LcMtTksVJIzHF+60iqK77NSDNlXctIzZcBw9Ie3lGxUvAvYRKD+VmOtjuUG+F1" +
            "p3FFURp4p/hnnbfeF98fUCB2h4DqQoWAEQKBgGudTn/Vlereyfa72b19g9h/m/T7VyVmLjTbKuBr" +
            "DHTbSYDcffD9VDq/GnFaca3IpB5GNl6W49QVy1VSMAOc++KQ73tyDbwiv2UdXyeICSQdrhwIauBW" +
            "S77KodSGwN6+gW8jsEum39XuGrzXrfXySadJzKfafoYf0nMDt0UhKDUBAoGBAIxJ7vWdqiBneuqa" +
            "K23vnd0KpgRSSVWN9RvhM/T+CxKRRKEmHakuUTYYz2KI1wDqCqezPhnelG/o4RzOkjRmkd86WnWF" +
            "w5vFU0Vo1AYMeHkhi3Ev361wtX1iPB4z9m19TbeFJdeyEf2WuYhVkHu4d89/yNnfHF7zrwzfi2/i" +
            "fY9RAoGAO4Jt3vor6y8l4uA+satU5jeowQSrtiK4qr4iFx12JOITEaSDSKQ8/RrCjauDzKQomuSS" +
            "f3RIroX3XVmbjK+tuvfQ6ciRVtClgg0bIFXVQMcHYo1SxmRnzlnAZUfkPD1/MnMIlRCgAXfUS7x0" +
            "//2toUiEMlEQyZ2JmtuseuXcNdY=";



    //客户端公钥
    public static final String APP_PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAqqKPH/L0AZyn1" +
            "fJ9xK2ol2nHY5jPu8qw7COwFukkRdr2j0oNJmD8vCTmxgzKWV0CkihiJ7Y0OekrGc78JL5tpL2SqeZ" +
            "TLa2bCJZJaTM3KFOXYb82nc8Xbr2caDnf7mgjyt0AALHG/YfYwd7hifZRB6Ct89uBTn6W5x/7oxGT6" +
            "D1C8siXKV+99AZPMv2HobglWyquyjIL5TZOhYmCMzFUPMOiXzzGYXMZj2gmfUFXMf/2jitMPGg3zQP" +
            "JxPSYunjoE1fMInk1obEhEfU8n2YxT5ZbGMWZGjt4hZwF+FJJLV+WOantfUJ4rMBB8qxgQtkT+Vzdd" +
            "fLCEoyy4Rl50fvjzwIDAQAB";
    //客户端秘钥
    public static final String APP_PRIVATE_KEY = "MIIEvgIBADANBgkqhkiG9w0BAQEFAASCBKgwggSkAgEAAo" +
            "IBAQCqoo8f8vQBnKfV8n3EraiXacdjmM+7yrDsI7AW6SRF2vaPSg0mYPy8JObGDMpZXQKSKGIntjQ56SsZz" +
            "vwkvm2kvZKp5lMtrZsIlklpMzcoU5dhvzadzxduvZxoOd/uaCPK3QAAscb9h9jB3uGJ9lEHoK3z24FOfpbn" +
            "H/ujEZPoPULyyJcpX730Bk8y/YehuCVbKq7KMgvlNk6FiYIzMVQ8w6JfPMZhcxmPaCZ9QVcx//aOK0w8aDf" +
            "NA8nE9Ji6eOgTV8wieTWhsSER9TyfZjFPllsYxZkaO3iFnAX4UkktX5Y5qe19QniswEHyrGBC2RP5XN118s" +
            "ISjLLhGXnR++PPAgMBAAECggEAAP6dkvQZlADTwZ1+Oi1A9FD7hosXeuK9kULL/fYx7e5OzZsC5JxgHMCiT" +
            "7k3XLn8D9oIaG7ZcxT22VmpgpVRkkpAlpjvFy8R3kTx/Jj901BZa4pvyQ+x9UVJqhncQkl9G+uZ2mcu379w" +
            "9gBUlDdJVaAMI4W+BTUbsBExqEur7wiZ8S3XK3rDltKINIguPRjwVTTuepzrTNd3k7WKPD8za/OV87bGEba" +
            "GLLo8KSKzm7bJnyMSbUwkjKA+WezNidUatB1fqaXDnlVYokCdfBD7xypbbVO+HoBNNBgAwT5p3dm3hbgYsb" +
            "9WNZLJXREMaNtp+AhWHGFj4qeYRl8dfGO7oQKBgQDYXTChQhKdo80nFWMtFvh1yw75uma0oOpnHEiIfYh1e" +
            "kcTBLlgsGTbg60t5Klb2lF1SdfhNXb+4WQNmSMlQYoKwakiRn2JyKbk+zs/j6q1r1PV1QAP5LJ28J/FjG5b" +
            "EyCXQdE9nVhTps1EX2Fuy6I9pKCQKuv86NPOsX7IMat4VwKBgQDJ5NFcORrRgxLwcx/eYGjyeyf7EDKacVp" +
            "d6SzobakXnvaX3WCxw2wjvbex+zL1s+XnLmp+4l87FfuP0+yd78pRHASXwDh6MNqs8bN2ksT4LOb7bImXyg" +
            "cxkX8Iog9qkydKZ1kUgLtDPZMzSUyic0+/6qVro9JR3aw0oJly9p4lSQKBgQCli6gJumRD+XCe1t5rQYgZm" +
            "KR8rwKmcfjnq9xTkrk2Kbj39EVilZSV4MpAsxRiE0kAVN+4kQ/bNNk5DlK1zs+wKz0d3JFxOvV3fkJ2/5W+" +
            "LcgXdEH35yQlnTaiEDDfvmLRWKqgWiOa3aVxCwmhnG0mfS/dHvoxKHPnUiePRXHNQQKBgQC7lZrgsT41xC9" +
            "osc6+c52PDtbK8vXRgdiQwQI0ww8FH3HHEK2y/PwRCUkQWXGz0P6fmgTg97u7zmT58dI7vHyieAHcbYEMJz" +
            "BG2BwC48OXQ0EqAmKlYdTlPWZmwwzH3Qn4m6Ws4x8bDq8iS8ykc7d5fa9NH91eqzRBgaaRpoqx4QKBgEzWh" +
            "wVHhTxdSBzqh6JaTKVUOrt1CwsbQOSlOy/Y8k/TJFJaQh+/yGKSBpkGLfWkY5HVra+nhAgWuCB2X301DpSM" +
            "CQtTiABYvjGNNysrkm40xQOuTOmO6OTqDfyVQZmi/xUXeztiT2vKjz0em+tButyg7OP7zKzYwqW3KhAxZ94t";
}
