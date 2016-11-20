package com.notnl.lambda;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by noti0 on 2016/11/18.
 */
public class Application extends Expression {

    private Expression function;

    private Expression arguement;

    public Application(Expression function, Expression arguement) {
        this.function = function;
        this.arguement = arguement;
    }

    @Override
    public boolean reducible() {
        return this.function.reducible() || this.arguement.reducible() || this.function instanceof Function;
    }

    @Override
    public Expression reduce() {
        if (this.function.reducible()) {
            return new Application(this.function.reduce(), this.arguement);
        } else if (this.arguement.reducible()) {
            return new Application(this.function, this.arguement.reduce());
        } else if (this.function instanceof Function) {
            Function fun = (Function) this.getFunction();
            return subst(fun.getBody(), fun.getName(), this.getArguement());
        }
        return this;
    }

    @Override
    public Expression deepReduce() {
        Expression app = this.reduce();
        if (app.reducible()) {
            return app.deepReduce();
        }
        return app;
    }

    public Expression getFunction() {
        return function;
    }

    public void setFunction(Expression function) {
        this.function = function;
    }

    public Expression getArguement() {
        return arguement;
    }

    public void setArguement(Expression arguement) {
        this.arguement = arguement;
    }

    @Override
    public String abbreviate() {
        StringBuilder apply = new StringBuilder();
        if (this.function instanceof Function) {
            apply.append('(').append(this.function.abbreviate()).append(')');
        } else {
            apply.append(this.function.abbreviate());
        }
        apply.append(' ');
        if (this.arguement instanceof Variable) {
            apply.append(this.arguement.abbreviate());
        } else {
            apply.append('(').append(this.arguement.abbreviate()).append(')');
        }
        return apply.toString();
    }

    @Override
    public String toString() {
        StringBuilder apply = new StringBuilder();
        if (this.function instanceof Function) {
            apply.append('(').append(this.function.toString()).append(')');
        } else {
            apply.append(this.function.toString());
        }
        apply.append(' ');
        if (this.arguement instanceof Variable) {
            apply.append(this.arguement);
        } else {
            apply.append('(').append(this.arguement).append(')');
        }
        return apply.toString();
    }


    private static Expression subst(Expression expr, Variable name, Expression repl) {
        if (expr instanceof Variable) {
            if (name.equals(expr)) {
                return repl;
            } else {
                return expr;
            }
        } else if (expr instanceof Application) {
            Application app = (Application) expr;
            return new Application(
                    subst(app.getFunction(), name, repl),
                    subst(app.getArguement(), name, repl));
        } else if (expr instanceof Function) {
            Function fun = (Function) expr;
            Set<Variable> fvs = freeVariables(repl);
            fvs.add(name);
            Variable newName2 = uniqueName(fun.getName(), fvs);
            Expression newBody = subst(fun.getBody(), fun.getName(), newName2);
            return new Function(newName2, subst(newBody, name, repl));
        }
        // unexpected operation
        return null;
    }

    private static Variable uniqueName(Variable name, Set<Variable> usedNames) {
        if (usedNames.contains(name)) {
            return uniqueName(new Variable(name.getName() + "'"), usedNames);
        } else {
            return name;
        }
    }

    private static Set<Variable> freeVariables(Expression expr) {
        Set<Variable> vars = null;
        if (expr instanceof Variable) {
            vars = new HashSet<>();
            vars.add((Variable) expr);
        } else if (expr instanceof Application) {
            Application app = (Application) expr;
            vars = freeVariables(app.getFunction());
            vars.addAll(freeVariables(app.getArguement()));
        } else if (expr instanceof Function) {
            Function fun = (Function) expr;
            vars = freeVariables(fun.getBody());
            vars.remove(fun.getName());
        }
        return vars;
    }
}
