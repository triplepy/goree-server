package com.goree.api.auth;

public class AuthTokenContext {
    private static ThreadLocal<String> token = new ThreadLocal<>();

    public static String token() {
        return token.get();
    }

    public static void token(String tokenString) {
        token.set(tokenString);
    }
}
