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

package com.notnl.lambda;

/**
 * Created by noti0 on 2016/11/18.
 */
public abstract class Expression {

    /**
     * @return
     */
    public abstract boolean reducible();

    /**
     * @return
     */
    public abstract Expression reduce();

    /**
     * @return
     */
    public abstract Expression deepReduce();

    /**
     *
     */
    public void printReduceSteps() {
        System.out.println(this.toString());
        if (this.reducible()) {
            this.reduce().printReduceSteps();
        }
    }

    /**
     * @param n
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
