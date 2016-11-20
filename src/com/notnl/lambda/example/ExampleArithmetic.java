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
        // zero = λf.λx.x
        Expression zero = λ("f", λ("x", "x"));
        System.out.println("zero = " + zero);

        // one = λf.λx.f x
        Expression one = λ("f", λ("x", apply("f", "x")));
        System.out.println("one = " + one);

        // two = λf.λx.f (f x)
        Expression two = λ("f", λ("x", apply("f", apply("f", "x"))));
        System.out.println("two = " + two);

        // succ = λn.λf.λx.f (n f x)
        Expression succ = λ("n", λ("f", λ("x", apply("f", apply(apply("n", "f"), "x")))));
        System.out.println("succ = " + succ);

        Expression twod = apply(succ, one);
        // twod = (λn.λf.λx.f (n f x)) (λf.λx.f x)
        System.out.println("twod = " + twod);
        // twod (reduced) = λf.λx.f (f x)
        System.out.println("twod (reduced) = " + twod.deepReduce());

        // plus = λm.λn.m (λn.λf.λx.f (n f x)) n
        Expression plus = λ("m", λ("n", apply(apply("m", succ), "n")));
        // Expression plus = λ("m", λ("n", λ("f", λ("x", apply(apply("m", "f"), apply(apply("n", "f"), "x")))))).deepReduce();
        System.out.println("plus = " + plus);

        // five = λf.λx.f (f (f (f (f x))))
        Expression five = apply(apply(plus, two), apply(succ, two));
        // five = (λm.λn.m (λn.λf.λx.f (n f x)) n) (λf.λx.f (f x)) ((λn.λf.λx.f (n f x)) (λf.λx.f (f x)))
        System.out.println("five = " + five);
        // five (reduced) = λf.λx'.f (f (f (f (f x'))))
        System.out.println("five (reduced) = " + five.deepReduce());

        // mult = λm.λn.λf.m (n f)
        Expression mult = λ("m", λ("n", λ("f", apply("m", apply("n", "f")))));
        System.out.println("mult = " + mult);

        // six = λf.λx.f (f (f (f (f (f x)))))
        Expression six = apply(apply(mult, two), apply(succ, two));
        // six = (λm.λn.λf.m (n f)) (λf.λx.f (f x)) ((λn.λf.λx.f (n f x)) (λf.λx.f (f x)))
        System.out.println("six = " + six);
        // six (reduced) = λf.λx.f (f (f (f (f (f x)))))
        System.out.println("six (reduced) = " + six.deepReduce());

        // pred n = n -1
        // pred = λn.λf.λx.n (λg.λh.h (g f)) (λu.x) (λu.u)
        Expression pred = λ("n", λ("f", λ("x", apply(apply(apply("n", λ("g", λ("h", apply("h", apply("g", "f"))))), λ("u", "x")), λ("u", "u")))));
        System.out.println("pred = " + pred);

        Expression fived = apply(pred, six);
        // fived = (λn.λf.λx.n (λg.λh.h (g f)) (λu.x) (λu.u)) ((λm.λn.λf.m (n f)) (λf.λx.f (f x)) ((λn.λf.λx.f (n f x)) (λf.λx.f (f x))))
        System.out.println("fived = " + fived);
        // fived = λf.λx.f (f (f (f (f x))))
        System.out.println("fived = " + fived.deepReduce());

        // sub = λm.λn.n pred m
        Expression sub = λ("m", λ("n", apply(apply("n", pred), "m")));
        // sub = λm.λn.n (λn.λf.λx.n (λg.λh.h (g f)) (λu.x) (λu.u)) m
        System.out.println("sub = " + sub.deepReduce());

        // four = λf.λx.f (f (f (f x)))
        Expression four = apply(apply(sub, five), one);
        // four = (λm.λn.n (λn.λf.λx.n (λg.λh.h (g f)) (λu.x) (λu.u)) m) ((λm.λn.m (λn.λf.λx.f (n f x)) n) (λf.λx.f (f x)) ((λn.λf.λx.f (n f x)) (λf.λx.f (f x)))) (λf.λx.f x)
        System.out.println("four = " + four);
        // four (reduced) = λf.λx'.f (f (f (f x')))
        System.out.println("four (reduced) = " + four.deepReduce());
        four.printReduceSteps();
    }
}
