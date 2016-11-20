package com.notnl.lambda.example;

import com.notnl.lambda.Expression;
import com.notnl.lambda.Lambda;

/**
 * Created by noti0 on 2016/11/19.
 */
public class ExampleUse extends Lambda {

    public static void main(String[] args) {
        // create a variable
        Expression x = var("x");
        System.out.println("x = " + x.toString());

        // create a lambda function
        Expression fun = λ("x", "x");
        // or Expression fun = lambda("x", "x");
        System.out.println("fun = " + fun.toString());

        // apply a function
        Expression app = apply(fun, "z");
        System.out.println("app = " + app.toString());
        // reduce a application for one step
        System.out.println("app (reduced) = " + app.reduce().toString());

        // another function
        Expression fun2 = λ("x", apply(λ("x", apply("f", "x")), "x"));
        System.out.println("fun2 = " + fun2.toString());
        System.out.println("fun2  (reduced) = " + fun2.reduce().toString());
        System.out.println("fun2 = " + fun2.toString());

        // another application
        Expression app2 = apply(fun2, app);
        System.out.println("app2 = " + app2.toString());
        System.out.println("app2 (fully reduced) = " + app2.deepReduce().toString());
        // print reduce steps
        app2.printReduceSteps();
        // or app2.printReduceSteps(2) to print certain steps
    }
}
