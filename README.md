# LambdaCalculus-java

A library implementing lambda calculus in Java.

## Information

Lambda calculus (also written as λ-calculus) is a formal system in
mathematical logic for expressing computation based on function
abstraction and application using variable binding and substitution.

* What is Lambda Calculus: https://en.wikipedia.org/wiki/Lambda_calculus

* More materials: [A Tutorial Introduction to the Lambda Calculus](www.inf.fu-berlin.de/lehre/WS03/alpi/lambda.pdf)

I write this library in order to understand lambda calculus deeper and,
meanwhile, recall how to white Java.

## Features

* Easy to write lambda expression
* It can reduce expression in one step or fully
* All expression can be pretty-printed
* Arithmetic, logic and pairs are implemented 
in [com.notnl.lambda.examples](https://github.com/noti0na1/LambdaCalculus-java/tree/master/src/com/notnl/lambda/example)

## How to use

First you need to add lambda-calculus.jar to your project libraries.
You can build it from the source.

The best way to create lambda expression is to extend the Lambda class
and use its handy functions.

```Java
public class ExampleUse extends Lambda {

}
```

## Examples

```Java
// create a variable x
Expression x = var("x");
System.out.println("x = " + x.toString());

// create a function
Expression fun = λ("x", apply(λ("x", apply("f", "x")), "x"));
// λx.(λx.f x) x
System.out.println("fun = " + fun.toString());
// λx.f x
System.out.println("fun2  (reduced) = " + fun2.reduce().toString());

// apply this function
Expression app = apply(fun, "z");
// (λx.(λx.f x) x) z
System.out.println("app = " + app.toString());
// f z
System.out.println("app (fully reduced) = " + app.deepReduce().toString());
// print reduce steps
// or app.printReduceSteps(2) to print certain steps
app.printReduceSteps();
// (λx.(λx.f x) x) ((λx.x) z)
// (λx.f x) ((λx.x) z)
// (λx.f x) z
// f z
```

See more examples in [com.notnl.lambda.examples](https://github.com/noti0na1/LambdaCalculus-java/tree/master/src/com/notnl/lambda/example)

## TODO 

* add more comments
* add documents
* ...
