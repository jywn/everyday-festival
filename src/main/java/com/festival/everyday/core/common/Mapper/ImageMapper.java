package com.festival.everyday.core.common.Mapper;

public class ImageMapper {

    private static final String LOCAL_PATH_PREFIX = "/home/ec2-user/";
    private static final String DOMAIN_PREFIX = "https://festival-everyday.duckdns.org/";

    public static String serverUrlToDomain(String serverUrl) {
        if (serverUrl == null) {
            return "";
        }
        return serverUrl.replace("/home/ec2-user/", "https://festival-everyday.duckdns.org/");
    }
}
