package com.goree.api.auth;

import com.goree.api.domain.Member;

public class AuthContext {
    private static ThreadLocal<String> token = new ThreadLocal<>();
    private static ThreadLocal<Member> memberInfo = new ThreadLocal<>();

    public static String token() {
        return token.get();
    }

    public static void token(String tokenString) {
        token.set(tokenString);
    }

    public static void memberInfo(Member member) {
        memberInfo.set(member);
    }

    public static Member memberInfo() {
        return memberInfo.get();
    }

    public static void clear() {
        token.remove();
        memberInfo.remove();
    }
}
