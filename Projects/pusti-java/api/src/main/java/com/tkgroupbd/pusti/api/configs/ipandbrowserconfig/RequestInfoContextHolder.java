package com.tkgroupbd.pusti.api.configs.ipandbrowserconfig;

public class RequestInfoContextHolder {
    private static final ThreadLocal<String> ipHolder = new ThreadLocal<>();
    private static final ThreadLocal<String> browserHolder = new ThreadLocal<>();

    public static String getIp() {
        return ipHolder.get();
    }

    public static void setIp(String ip) {
        ipHolder.set(ip);
    }

    public static String getBrowser() {
        return browserHolder.get();
    }

    public static void setBrowser(String browser) {
        browserHolder.set(browser);
    }

    public static void clear() {
        ipHolder.remove();
        browserHolder.remove();
    }
}
