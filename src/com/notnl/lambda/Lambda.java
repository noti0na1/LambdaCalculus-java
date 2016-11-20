package com.notnl.lambda;

/**
 * Created by noti0 on 2016/11/18.
 */
public class Lambda {

    public static Variable var(String name) {
        return new Variable(name);
    }

    public static Function lambda(String name, String body) {
        return new Function(new Variable(name), new Variable(body));
    }

    public static Function lambda(String name, Expression body) {
        return new Function(new Variable(name), body);
    }

    public static Function lambda(Variable name, Expression body) {
        return new Function(name, body);
    }

    public static Function λ(String name, String body) {
        return new Function(new Variable(name), new Variable(body));
    }

    public static Function λ(String name, Expression body) {
        return new Function(new Variable(name), body);
    }

    public static Function λ(Variable name, Expression body) {
        return new Function(name, body);
    }

    public static Application apply(String function, String arguement) {
        return new Application(new Variable(function), new Variable(arguement));
    }

    public static Application apply(String function, Expression arguement) {
        return new Application(new Variable(function), arguement);
    }

    public static Application apply(Expression function, String arguement) {
        return new Application(function, new Variable(arguement));
    }

    public static Application apply(Expression function, Expression arguement) {
        return new Application(function, arguement);
    }
}
