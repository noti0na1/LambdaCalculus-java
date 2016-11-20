/*
 * Copyright [2016] [noti0na1]
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

        Expression f = apply(λ("x", apply("x", λ("x", "x"))), λ("x", apply("x", "x")));
        System.out.println("f = " + f.deepReduce().toString());
        f.printReduceSteps();
    }
}
