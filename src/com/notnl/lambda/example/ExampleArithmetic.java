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

public class ExampleArithmetic extends Lambda {

    public static void main(String[] args) {
        Expression zero = λ("f", λ("x", "x"));
        System.out.println("zero = " + zero);

        Expression one = λ("f", λ("x", apply("f", "x")));
        System.out.println("one = " + one);

        Expression two = λ("f", λ("x", apply("f", apply("f", "x"))));
        System.out.println("two = " + two);

        Expression succ = λ("n", λ("f", λ("x", apply("f", apply(apply("n", "f"), "x")))));
        System.out.println("succ = " + succ);

        Expression twod = apply(succ, one);
        System.out.println("twod = " + twod);
        System.out.println("twod (reduced) = " + twod.deepReduce());

        //Expression plus = λ("m", λ("n", λ("f", λ("x", apply(apply("m", "f"), apply(apply("n", "f"), "x")))))).deepReduce();
        Expression plus = λ("m", λ("n", apply(apply("m", succ), "n")));
        System.out.println("plus = " + plus);

        Expression five = apply(apply(plus, two), apply(succ, two));
        System.out.println("five = " + five);
        System.out.println("five (reduced) = " + five.deepReduce());

        Expression mult = λ("m", λ("n", λ("f", apply("m", apply("n", "f")))));
        System.out.println("mult = " + mult);

        Expression six = apply(apply(mult, two), apply(succ, two));
        System.out.println("six = " + six);
        System.out.println("six (reduced) = " + six.deepReduce());

        Expression pred = λ("n", λ("f", λ("x", apply(apply(apply("n", λ("g", λ("h", apply("h", apply("g", "f"))))), λ("u", "x")), λ("u", "u")))));
        System.out.println("pred = " + pred);

        Expression fived = apply(pred, six);
        System.out.println("fived = " + fived);
        System.out.println("fived = " + fived.deepReduce());

        Expression sub = λ("m", λ("n", apply(apply("n", pred), "m")));
        System.out.println("sub = " + sub.deepReduce());

        Expression four = apply(apply(sub, five), one);
        System.out.println("four = " + four);
        System.out.println("four (reduced) = " + four.deepReduce());
        four.printReduceSteps();
    }
}
