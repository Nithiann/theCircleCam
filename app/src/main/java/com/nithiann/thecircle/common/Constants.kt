package com.nithiann.thecircle.common

import com.nithiann.thecircle.domain.models.User

object Constants {
    const val BASE_URL= "http://thecircleapi.azurewebsites.net/swagger/"
    const val BASE_URL2= "http://thecircleapi.azurewebsites.net/"
    const val publicKeyServer = "-----BEGIN PUBLIC KEY-----\n" +
            "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAz23E5t78qPwPsvDVfk2w\n" +
            "+w4vMSRq89VO529kkp3C48pIZhG5BlIFwgxFiMVT66qkCLbQMIrzFDX5pmLKIVg9\n" +
            "1X8Yqxl/jYSvg9k59ZXNXS6vgIjHd502Zhq5BuszXLb/iQFN3sTA0p3RtvCATiZZ\n" +
            "ZSVXBWYq6KwIx6pZOyGbeElnIIzjp94D/nWH2KoCyQuN9kNGe19EgeDQQKFuc2AX\n" +
            "TJM55W1fhcl3B7MY5hxYHzEf0mYIOYV6cS/n0hFHnV8LRujBBs1ipoSkU8OsKDXH\n" +
            "/5UymPiY1GHMxp5sOAiKVcq63XB0Pl1eQtZHf3JeINXklIg3dvglXLijiYcjlPS3\n" +
            "MQIDAQAB\n" +
            "-----END PUBLIC KEY-----"

    const val privateKey = "-----BEGIN PRIVATE KEY-----\n" +
            "MIIEvQIBADANBgkqhkiG9w0BAQEFAASCBKcwggSjAgEAAoIBAQDEmwj9cJwubIDQ\n" +
            "OHYlfT5RkoEjvls9L9iDT4vcclh91AfXcS4cVj4eOOtGsOtYkFtdxPmpMQibd92V\n" +
            "XcI0cN8JVwr+B6+jTGtEu1AEBi0RhaFttemj4flsUXoaL2tQqeVIHmyqaaDU7Imh\n" +
            "+u8DlKt/lI6Ou0kJQwZymL4e17rJJJDLdRO1vcVE+yUDTBdqGxu//CFyHTjqcKB7\n" +
            "V69/m3TxdSs673DV6GWi99uxRnf3CRfmovxSGLRQxIwE7XTQU7QCUAQd5yrLxXMl\n" +
            "JTVdw/aWMuAfINA2PtNlfCTZRlAtvGznnS7nAg4kES1L33Q0f3jl30Ud4KxqOfnE\n" +
            "bMbBHMbhAgMBAAECggEABh0DWAzG284FlAlAFJAC6TrC0jo1OxSq86unCoPxtXlA\n" +
            "2dWhR0H1Qi+nPTZQyHBgruyU43UXXUI1En5tBKrCfKyyC6PF4IWGoQdUCHQACxdF\n" +
            "JZSuVFQOqJbAqGIeFk7aHCEkuYh5oZ/4nZ/1tlOf9jfH1loDfjVmf2y5c1sQ6ffa\n" +
            "OcmCB4GV2N3iHXiHSvHR6T6PzTFMyXb8UbeXOUg7Bdg2cZHES3DiKGE6crYm7Jh6\n" +
            "xYdOsMx0OqHWrwAdYs9HoLNUt/e6pDGurFw6X7iLlMXLOKT0PQVyqvy93Yk914kM\n" +
            "i0hKjK4gmgpJrrlQlOYUb2VwDM5AllhgCrf/wwjXWQKBgQDH1wpNsZYmDDj2o4kc\n" +
            "2alPhk6hJP5RglFfrd7EOhMOhbRJj9Ar/CG0c+RHIcvQD/m/I19t58yoDEByMkXX\n" +
            "Yzu2KnqbA/zPN1qyjM2f9X83MN/gdzGIQ4QazOTbiL/kUoXbjlPTzN2vrgrqPnZt\n" +
            "uYEWPhBOFns4zxjHTPYdIhje7QKBgQD7204MuDjJuvr3HjW+pSiDtiHdYJm+1sMi\n" +
            "pf73g9OxrlNH0+xjNF7J+4BZoS3yErnrHP25UsQuuDT1z186ZwBHCtIyRViOzU4F\n" +
            "qWscD7Fv22e6MaNV2oX/iifVuhPOGd8E4YER822CY6fJMi0rTZ+Pdtom9zHayyS/\n" +
            "7ZDGF3RVRQKBgGcM0chcsiOum9U7YWIaL7/Nb1CTpf1cKSAgpcYkeF09v0lLurpj\n" +
            "yvGl7Wps2A/TnSLeV8ByDsv9fWIl4HQAPPNkFlNHjB9C2SdHimVZEB/iuR+j90vg\n" +
            "HQhA7iby7pkLoPEmBL4sX4jPQ9ulGCbeyN0yZfAOkb4qtQlY+3Tsd0zFAoGALWyE\n" +
            "Sy7+rwOWN/Ou5c+L2xWCThcaI51AXINr1OBl0eoLAy1puQq8/djqcT/stXhDJ/B2\n" +
            "onIXCAYZJyxblID3P9jnyEFRk4/bvpGry8fYzL/ZmW9Sci2TdV9Jh/ajk8x+uLaj\n" +
            "PMWWvqmSnWr7UpARcyKQfe6fg0KYQjVqow+f37ECgYEAmdg3Fl7ti6q05zuuJHvo\n" +
            "Rqj8FTmOeShgWsh+CBbq6cXHaIZxhCobOtXZvf+kS03qASG7p0y9Iy1jlA4F4gZQ\n" +
            "l3EDSZs95n0ETWx1EMmzcZrDdVbiBE4kiWPSvBjtCJhVYRi0QZxbZvKBmkSpnYIQ\n" +
            "upy6f74TTHbF8pTv2O0e/JU=\n" +
            "-----END PRIVATE KEY-----"
    val users = arrayListOf(User("Lout Clercx", "lmj.clercx@student.avans.nl", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAtoULSFY2Lb2slTyaI9OwzyIdc/16wyjXUP58JFG2dGVsGRqG58bLKUI3qW+ZA+F9vhUkmUPqZX9GvpJfsaxy3/Qff8lXsilcOsumHm1GLcpXldF8cJiW8zkMLjBHLEtxpAPHqI3CbbLZ+0dq13zwW+ae14XIxiqP7DpBFkodgXUbcll8rmeDoRJ9lwJ8t/wH8PZyyKThkIzBLKryMsbPxI97djlcMxhHLjivuV+d1sKM7Fq5GZDvP4fVrtL8qP1OvFxTcr8Do1KaCq4hr32REED0iKT3xb1EEm5qh/Rdrc5PD5wH/QXv30zNq6G/hABFB1wh0E29IarkCuxkYusxDwIDAQAB"),
        User("Thijs van Rijsbergen", "Thijs.Rijsbergen@studentavans.nl", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnORgQJiJ4ET39Alt5vSr\\r\\n4TL83zWeViaH2EV8YiWqT2KuZrNu1VB5RpMUcVCVwuFOXogLPhjYq8lqV/51Hfmq\\r\\nGLxAeixZbfQE+svSyGrjKTtnIlGUV1By1z8gJn1kYweClAFM7xwamyj55vkHCE46\\r\\nfPljXuW8sQeywpRDTsnhPj1HFJQwuc7YW2j8p0KuRH9Bwyc7jeicumHEsMo7QTjp\\r\\nDA/IfyIZQuAsLnRfhIslZSsrdCUJdK2bphFxg6Ypx8Kz4Lw65B263vTX19s6yd7p\\r\\n8aHfab+fCXnpq+mjqH0w8l/sCIvFll+Ny1fOZMP3WD7NR/F0AebYSG0DNOMjnGWV\\r\\ngQIDAQAB"),
        User("Sven Colijn", "ss.colijn@student.avans.nl", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0BVc56o1QjBXanpz3YxP\\r\\nxfG3JHT7gv/yEeGtXhf4Xp7bPX3Zql0i/+8ZEThWiNd99fYtbixPx3hgzrttWHje\\r\\ndBPwqdqZpilhiEg5M5zZKIErv5ymjUReWOa/DH0uODdKU2uSDTtEGL2MJMCvZx6/\\r\\nYB72vmgH+xdrei3JgX3iekOZaM3QstK5shXrbnbbGL/T7g16LKDPjb3cmDStwUaB\\r\\nGbywANINi0wrXaVGQq8aSgFGD3n7hjKDgytLPEyc0AGPkIFFmJAbxwmHnK4LrJin\\r\\nziNgcK/hd8gBJlWC4vd/P0XE8OI2QHVhcoF6YwOyhTFs2OlJpKOHvT03ku9a6Bpu\\r\\nuQIDAQAB"),
        User("Ruben Kraaijeveld", "bj.kraaijeveld@student.avans.nl", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEArshwd5gEovNKZGkh3uiS\\r\\n7th29CrqIpzPjl30cN0n7qBVap5R/auKruTGBR/0HJS7XFaDqYiAH/RhpdGBHJTG\\r\\n7hEummMYzMNK06oJfYRHvZ+jyeVSM54ckUfQb8Y6q9p6mIIXLTtbEJjmp8kWK6Qg\\r\\njuBqldZPsWfgZmPAaV1PtZ+BiRVIsLmaPUvFrgyx3mqAJx0H0R2SSfSYn98i+H4N\\r\\nJgfmi+Wmyuu+1qHYe90zj4pbtYz/BRKvN7oQlXCIcgEBRq0BvlptiboGzn3vb5RQ\\r\\nhbISqLfCVUcZ0F5OC9bKHbwyKRPabWjC5X3vOFQPsHhfIKbCRx2MVoV/NQsa+Nsk\\r\\n0wIDAQAB"),
        User("Lars Jongenelen", "ljaw.jongenelen@student.avans.nl", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA7Gem45vxbzXO5vV8/Xk6\\r\\nCNzjFDyTY8w4CtBJ9w/iAWPMkkWdqy9z8g4YXU+JVdmvXxTvtzDt5dXJKguLyw9X\\r\\n3rIy+aUbFAMjZ2cO4Bu5knaMufbL+TXAlR3NqdHpEXNyZ+Y0aQ7lEJ1lg9oT68ZA\\r\\nXBpZ8Wv0ey9qcP+4+frU4vAHAdKzYEHlGjLmiiNhmWDOWzhcb5h6vMQQFb9HRT5C\\r\\ndCSBqoN9BgXxUvUTHkH1+DKK8dDRWc/Kk6Q272i/WszBqqaKwaJJViLwrQ7CirCA\\r\\nfrwC9CLkf2NXSk/ISQXrDvDoBArfRjRg77xh8hiLDRD+PUEUv2F6v4zbGIBRZK1h\\r\\n3QIDAQAB"),
        User("Hubert de Graaf", "hubert@email.nl", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAxJsI/XCcLmyA0Dh2JX0+\\r\\nUZKBI75bPS/Yg0+L3HJYfdQH13EuHFY+HjjrRrDrWJBbXcT5qTEIm3fdlV3CNHDf\\r\\nCVcK/gevo0xrRLtQBAYtEYWhbbXpo+H5bFF6Gi9rUKnlSB5sqmmg1OyJofrvA5Sr\\r\\nf5SOjrtJCUMGcpi+Hte6ySSQy3UTtb3FRPslA0wXahsbv/whch046nCge1evf5t0\\r\\n8XUrOu9w1ehlovfbsUZ39wkX5qL8Uhi0UMSMBO100FO0AlAEHecqy8VzJSU1XcP2\\r\\nljLgHyDQNj7TZXwk2UZQLbxs550u5wIOJBEtS990NH945d9FHeCsajn5xGzGwRzG\\r\\n4QIDAQAB"),
        User("Pascal van de Keere", "pascal@student.avans.nl", "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAsPITX9n1TKts5MSDb2e4\\r\\nTyLGpAnfsaloKvkRpCdkj2xmPEogcCpiFQ8ss2c53H2v3OOoHR6L/KNxyiEIfiR8\\r\\nqg8QD+1zYoYCpwvVG3B4eVh1ETFOHBPGtJKArCCMhtSgkMXsTS357eQQjZxtW2Ku\\r\\nJK3SC0+Ng4kNTbNtu/QYsNG2bM0ZAJTLC6Z5weS6JL+/FSP2zv4nvQ/tmZQfxXe2\\r\\n/bURXySo3fcMA5kdfjNvF+uVbXWbGWjbkI6SVVLSuGZWMEz360z+aZwPSrsGZowE\\r\\nFftIFjaREeWwRo8MSMoVwdJy8sGtFNGH1WktA17/w5Hh5T3Ylx2sd/LBDAtKsoxC\\r\\nKQIDAQAB"))
}