package com.tkgroupbd.pusti.api.utils;

import jakarta.servlet.http.HttpServletRequest;

public class URLHelper {

    /*
     * @param HttpServletRequest Object
     * 
     * @returns a string representation of the full base url and port
     * e.g. http://localhost:9000
     */
    public static String getBaseUrl(HttpServletRequest request) {
        if (request.getServerName().contains("localhost")) {
            return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        }
        return request.getScheme() + "://" + request.getServerName();
    }
}
