package com.suchtool.nicelog.util.servlet;

import org.springframework.beans.factory.annotation.Autowired;

import java.util.Map;

/**
 * Servlet工具（区分SpringBoot2和SpringBoot3）
 */
public class NiceLogServletUtil {
    @Autowired(required = false)
    private NiceLogServletUtilJavax niceLogServletUtilJavax;

    @Autowired(required = false)
    private NiceLogServletUtilJakarta niceLogServletUtilJakarta;

    public String readTraceIdFromHeader() {
        if (javaxProcessRequired()) {
            return niceLogServletUtilJavax.readTraceIdFromHeader();
        }

        if (jakartaProcessRequired()) {
            return niceLogServletUtilJakarta.readTraceIdFromHeader();
        }

        return null;
    }

    public Map<String, String> buildRequestHeaders() {
        if (jakartaProcessRequired()) {
            return niceLogServletUtilJavax.buildRequestHeaders();
        }

        if (jakartaProcessRequired()) {
            return niceLogServletUtilJakarta.buildRequestHeaders();
        }

        return null;
    }

    public Map<String, String> buildResponseHeaders() {
        if (jakartaProcessRequired()) {
            return niceLogServletUtilJavax.buildResponseHeaders();
        }

        if (jakartaProcessRequired()) {
            return niceLogServletUtilJakarta.buildResponseHeaders();
        }

        return null;
    }

    private boolean javaxProcessRequired() {
        return niceLogServletUtilJavax != null
                && niceLogServletUtilJavax.inServletEnvironment();
    }

    private boolean jakartaProcessRequired() {
        return niceLogServletUtilJakarta != null
                && niceLogServletUtilJakarta.inServletEnvironment();
    }
}
