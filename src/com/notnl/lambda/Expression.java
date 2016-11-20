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

package com.notnl.lambda;

/**
 * An Expression
 */
public abstract class Expression {

    /**
     * return if it can be reduced
     *
     * @return
     */
    public abstract boolean reducible();

    /**
     * reduce it for one step
     *
     * @return
     */
    public abstract Expression reduce();

    /**
     * fully reduce it
     * caution: this may cause infinite loop
     *
     * @return
     */
    public abstract Expression deepReduce();

    /**
     * display reduce steps
     */
    public void printReduceSteps() {
        System.out.println(this.toString());
        if (this.reducible()) {
            this.reduce().printReduceSteps();
        }
    }

    /**
     * display certain reduce steps
     *
     * @param n number of steps
     */
    public void printReduceSteps(int n) {
        if (n == 0) {
            return;
        }
        System.out.println(this.toString());
        if (this.reducible()) {
            this.reduce().printReduceSteps(--n);
        }
    }
}
