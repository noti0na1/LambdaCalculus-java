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
public class Variable extends Expression {

    private String name;

    /**
     * @param name
     */
    public Variable(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    @Override
    public boolean reducible() {
        return false;
    }

    /**
     * @return
     */
    @Override
    public Expression reduce() {
        return this;
    }

    /**
     * @return
     */
    @Override
    public Expression deepReduce() {
        return this;
    }

    /**
     * @return
     */
    public String getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return name;
    }

    /**
     * @param o
     * @return
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;

        Variable variable = (Variable) o;

        return getName() != null ? getName().equals(variable.getName()) : variable.getName() == null;

    }

    /**
     * @return
     */
    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
