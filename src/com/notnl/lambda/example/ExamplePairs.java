package com.notnl.lambda.example;

import com.notnl.lambda.Expression;
import com.notnl.lambda.Lambda;

/**
 * Created by noti0 on 2016/11/19.
 */
public class ExamplePairs extends Lambda {

    public static void main(String[] args) {
        Expression True = λ("x", λ("y", "x"));
        Expression False = λ("x", λ("y", "y"));
        Expression ite = λ("cond", λ("then", λ("else", apply(apply("cond", "then"), "else"))));

        Expression cons = λ("x", λ("y", λ("p", apply(apply("p", "x"), "y"))));
        System.out.println("cons = " + cons);

        Expression car = λ("x", apply("x", True));
        System.out.println("car = " + car);

        Expression cdr = λ("x", apply("x", False));
        System.out.println("cdr = " + cdr);

        Expression myCons = apply(apply(cons, "left"), "right");
        System.out.println("myCons = " + myCons.deepReduce());
        System.out.println("myCons-car = " + apply(car, myCons).deepReduce());
        System.out.println("myCons-cdr = " + apply(cdr, myCons).deepReduce());
    }
}
