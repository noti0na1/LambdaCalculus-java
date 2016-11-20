package com.notnl.lambda;

/**
 * Created by noti0 on 2016/11/18.
 */
public class Variable extends Expression {

    private String name;

    public Variable(String name) {
        this.name = name;
    }

    @Override
    public boolean reducible() {
        return false;
    }

    @Override
    public Expression reduce() {
        return this;
    }

    @Override
    public Expression deepReduce() {
        return this;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String abbreviate() {
        return name;
    }

    @Override
    public String toString() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Variable)) return false;

        Variable variable = (Variable) o;

        return getName() != null ? getName().equals(variable.getName()) : variable.getName() == null;

    }

    @Override
    public int hashCode() {
        return getName() != null ? getName().hashCode() : 0;
    }
}
