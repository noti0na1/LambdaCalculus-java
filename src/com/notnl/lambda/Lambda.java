package com.notnl.lambda;

/**
 * Created by noti0 on 2016/11/18.
 */
public class Lambda {

    /**
     * @param name
     * @return
     */
    public static Variable var(String name) {
        return new Variable(name);
    }

    /**
     * @param name
     * @param body
     * @return
     */
    public static Function lambda(String name, String body) {
        return new Function(new Variable(name), new Variable(body));
    }

    /**
     * @param name
     * @param body
     * @return
     */
    public static Function lambda(String name, Expression body) {
        return new Function(new Variable(name), body);
    }

    /**
     * @param name
     * @param body
     * @return
     */
    public static Function lambda(Variable name, Expression body) {
        return new Function(name, body);
    }

    /**
     * @param name
     * @param body
     * @return
     */
    public static Function λ(String name, String body) {
        return new Function(new Variable(name), new Variable(body));
    }

    /**
     * @param name
     * @param body
     * @return
     */
    public static Function λ(String name, Expression body) {
        return new Function(new Variable(name), body);
    }

    /**
     * @param name
     * @param body
     * @return
     */
    public static Function λ(Variable name, Expression body) {
        return new Function(name, body);
    }

    /**
     * @param function
     * @param arguement
     * @return
     */
    public static Application apply(String function, String arguement) {
        return new Application(new Variable(function), new Variable(arguement));
    }

    /**
     * @param function
     * @param arguement
     * @return
     */
    public static Application apply(String function, Expression arguement) {
        return new Application(new Variable(function), arguement);
    }

    /**
     * @param function
     * @param arguement
     * @return
     */
    public static Application apply(Expression function, String arguement) {
        return new Application(function, new Variable(arguement));
    }

    /**
     * @param function
     * @param arguement
     * @return
     */
    public static Application apply(Expression function, Expression arguement) {
        return new Application(function, arguement);
    }
}
