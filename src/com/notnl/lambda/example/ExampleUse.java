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
 * Show some examples of how to use this lib
 */
public class ExampleUse extends Lambda {

    public static void main(String[] args) {
        // create a variable x
        Expression x = var("x");
        System.out.println("x = " + x.toString());

        // create a lambda function
        // λx.x
        Expression fun = λ("x", "x");
        // or Expression fun = lambda("x", "x");
        System.out.println("fun = " + fun.toString());

        // apply a function
        Expression app = apply(fun, "z");
        // (λx.x) z
        System.out.println("app = " + app.toString());
        // reduce a application for one step
        // z
        System.out.println("app (reduced) = " + app.reduce().toString());

        // another function
        Expression fun2 = λ("x", apply(λ("x", apply("f", "x")), "x"));
        // λx.(λx.f x) x
        System.out.println("fun2 = " + fun2.toString());
        // λx.f x
        System.out.println("fun2  (reduced) = " + fun2.reduce().toString());

        // another application
        Expression app2 = apply(fun2, app);
        // (λx.(λx.f x) x) ((λx.x) z)
        System.out.println("app2 = " + app2.toString());
        // f z
        System.out.println("app2 (fully reduced) = " + app2.deepReduce().toString());
        // print reduce steps
        // or app2.printReduceSteps(2) to print certain steps
        app2.printReduceSteps();
        // (λx.(λx.f x) x) ((λx.x) z)
        // (λx.f x) ((λx.x) z)
        // (λx.f x) z
        // f z

        Expression f = apply(λ("x", apply("x", λ("x", "x"))), λ("x", apply("x", "x")));
        // (λx.x (λx.x)) (λx.x x)
        System.out.println("f = " + f.toString());
        // λx'.x'
        System.out.println("f (fully reduced) = " + f.deepReduce().toString());
        f.printReduceSteps();
        // (λx.x (λx.x)) (λx.x x)
        // (λx.x x) (λx'.x')
        // (λx'.x') (λx'.x')
        // λx'.x'
    }
}
