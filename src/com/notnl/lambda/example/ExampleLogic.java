package com.notnl.lambda.example;

import com.notnl.lambda.Expression;
import com.notnl.lambda.Lambda;

/**
 * Created by noti0 on 2016/11/19.
 */
public class ExampleLogic extends Lambda {

    public static void main(String[] args) {
        Expression True = λ("x", λ("y", "x"));
        System.out.println("true = " + True);

        Expression False = λ("x", λ("y", "y"));
        System.out.println("false = " + False);

        Expression and = λ("p", λ("q", apply(apply("p", "q"), "p")));
        System.out.println("and = " + and);

        Expression taf = apply(apply(and, True), False);
        System.out.println("true and false = " + taf.deepReduce());

        Expression or = λ("p", λ("q", apply(apply("p", "p"), "q")));
        System.out.println("or = " + or);

        Expression fot = apply(apply(or, False), True);
        System.out.println("false or true = " + fot.deepReduce());

        Expression not = λ("p", apply(apply("p", False), True));
        System.out.println("not = " + not);

        Expression notf = apply(not, False);
        System.out.println("not false = " + notf.deepReduce());

        // ifThenElse
        Expression ite = λ("cond", λ("then", λ("else", apply(apply("cond", "then"), "else"))));
        System.out.println("ifThenElse = " + ite);

        Expression iffalse = apply(apply(apply(ite, False), "then"), "else");
        System.out.println("ifFalse = " + iffalse.deepReduce());

        Expression isZero = λ("n", apply(apply("n", λ("x", False)), True)).deepReduce();
        System.out.println("isZero = " + isZero);

        Expression zero = λ("f", λ("x", "x")).deepReduce();
        Expression two = λ("f", λ("x", apply("f", apply("f", "x")))).deepReduce();

        System.out.println("0 isZero = " + apply(isZero, zero).deepReduce());
        System.out.println("2 isZero = " + apply(isZero, two).deepReduce());
    }
}
