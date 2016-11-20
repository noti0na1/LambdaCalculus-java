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
