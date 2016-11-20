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
 * A lambda function
 */
public class Function extends Expression {

    private Variable name;

    private Expression body;

    /**
     * @param name
     * @param body
     */
    public Function(Variable name, Expression body) {
        this.name = name;
        this.body = body;
    }

    /**
     * @return
     */
    @Override
    public boolean reducible() {
        return this.body.reducible();
    }

    /**
     * @return
     */
    @Override
    public Expression reduce() {
        if (this.getBody().reducible()) {
            return new Function(this.name, this.body.reduce());
        }
        return this;
    }

    /**
     * @return
     */
    @Override
    public Expression deepReduce() {
        return new Function(this.name, this.body.deepReduce());
    }

    /**
     * @return
     */
    public Variable getName() {
        return name;
    }

    /**
     * @param name
     */
    public void setName(Variable name) {
        this.name = name;
    }

    /**
     * @return
     */
    public Expression getBody() {
        return body;
    }

    /**
     * @param body
     */
    public void setBody(Expression body) {
        this.body = body;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return 'λ' + this.name.toString() + '.' + this.body.toString();
    }
}
