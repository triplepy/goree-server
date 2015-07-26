package com.goree.api.auth;

public class TokenContext {
    private static ThreadLocal<String> longLivedToken = new ThreadLocal<>();

    public static String longLivedToken() {
        return longLivedToken.get();
    }

    public static void longLivedToken(String token) {
        longLivedToken.set(token);
    }
}
