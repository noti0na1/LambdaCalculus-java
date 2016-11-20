package com.notnl.lambda;

/**
 * Created by noti0 on 2016/11/18.
 */
public abstract class Expression {

    public abstract boolean reducible();

    public abstract Expression reduce();

    public abstract Expression deepReduce();

    public abstract String abbreviate();

    public void printReduceSteps() {
        System.out.println(this.toString());
        if (this.reducible()) {
            this.reduce().printReduceSteps();
        }
    }

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
