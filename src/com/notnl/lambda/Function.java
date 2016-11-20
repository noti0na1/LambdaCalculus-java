package com.notnl.lambda;

/**
 * Created by noti0 on 2016/11/18.
 */
public class Function extends Expression {

    private Variable name;

    private Expression body;

    public Function(Variable name, Expression body) {
        this.name = name;
        this.body = body;
    }

    @Override
    public boolean reducible() {
        return this.body.reducible();
    }

    @Override
    public Expression reduce() {
        if (this.getBody().reducible()) {
            return new Function(this.name, this.body.reduce());
        }
        return this;
    }

    @Override
    public Expression deepReduce() {
        return new Function(this.name, this.body.deepReduce());
    }

    public Variable getName() {
        return name;
    }

    public void setName(Variable name) {
        this.name = name;
    }

    public Expression getBody() {
        return body;
    }

    public void setBody(Expression body) {
        this.body = body;
    }

    @Override
    public String abbreviate() {
        return "λ" + this.functionAbbreviate();
    }

    private String functionAbbreviate(){
        if(this.body instanceof Function){
            Function subF = (Function) this.body;
            return this.name + subF.functionAbbreviate();
        }else{
            return this.name + "." + this.body;
        }
    }

    @Override
    public String toString() {
        return 'λ' + this.name.toString() + '.' + this.body.toString();
    }
}
