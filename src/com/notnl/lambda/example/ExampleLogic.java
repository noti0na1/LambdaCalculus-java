/*
 * Copyright 2016 noti0na1 (i@notnl.com)
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.notnl.lambda.example;

import com.notnl.lambda.Expression;
import com.notnl.lambda.Lambda;

/**
 * Created by noti0 on 2016/11/19.
 */
public class ExampleLogic extends Lambda {

    public static void main(String[] args) {
        // λx.λy.x
        Expression True = λ("x", λ("y", "x"));
        System.out.println("true = " + True);

        // λx.λy.y
        Expression False = λ("x", λ("y", "y"));
        System.out.println("false = " + False);

        // λp.λq.p q p
        Expression and = λ("p", λ("q", apply(apply("p", "q"), "p")));
        System.out.println("and = " + and);

        Expression taf = apply(apply(and, True), False);
        // λx.λy.y
        System.out.println("true and false = " + taf.deepReduce());

        // λp.λq.p p q
        Expression or = λ("p", λ("q", apply(apply("p", "p"), "q")));
        System.out.println("or = " + or);

        Expression fot = apply(apply(or, False), True);
        // λx.λy.x
        System.out.println("false or true = " + fot.deepReduce());

        // λp.p (λx.λy.y) (λx.λy.x)
        Expression not = λ("p", apply(apply("p", False), True));
        System.out.println("not = " + not);

        Expression notf = apply(not, False);
        // λx.λy.x
        System.out.println("not false = " + notf.deepReduce());

        // ifThenElse
        Expression ite = λ("cond", λ("then", λ("else", apply(apply("cond", "then"), "else"))));
        // λcond.λthen.λelse.cond then else
        System.out.println("ifThenElse = " + ite);

        Expression iffalse = apply(apply(apply(ite, False), "then"), "else");
        // else
        System.out.println("ifFalse = " + iffalse.deepReduce());

        // λn.n (λx.λx.λy.y) (λx.λy.x)
        Expression isZero = λ("n", apply(apply("n", λ("x", False)), True)).deepReduce();
        System.out.println("isZero = " + isZero);

        Expression zero = λ("f", λ("x", "x")).deepReduce();
        Expression two = λ("f", λ("x", apply("f", apply("f", "x")))).deepReduce();

        // λx.λy.x
        System.out.println("0 isZero = " + apply(isZero, zero).deepReduce());
        // λx'.λy.y
        System.out.println("2 isZero = " + apply(isZero, two).deepReduce());
    }
}
