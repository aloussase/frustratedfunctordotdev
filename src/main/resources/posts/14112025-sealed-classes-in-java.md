# Practical use case for Sealed classes in Java

Author: Alexander Goussas

Date: 14 Nov. 2025

You can find the code for this post on [GitHub](https://github.com/aloussase/sealed-classes-example).

## What are sealed classes?

Let's start by establishing what sealed classes are not: they are not classes that have been sealed in the Prison
Realm or any other similar artifact. Rather, sealed class are classes that allow inheritance from a restricted set of
other classes. This restriction is indicated by a list of permitted classes that follow a `permits` clause in the class
defintion.

## Setting up the stage

In our demo application, we will be dealing with an eCommerce site that has two types of products: digital and physical
(yes, this is the best example I could come up with. Actually, I have a better and real example from my job that
inspired this post, but I don't know how much of that I can disclose without risking prison). We can model this with a
sealed class like so:

```java
public sealed interface Product permits DigitalProduct, PhysicalProduct {
}
```

And the corresponding implementors:

```java
public record DigitalProduct(
        BigDecimal basePrice,
        int quantity
) implements Product {
}
```

```java
public record PhysicalProduct(
        BigDecimal basePrice,
        BigDecimal shippingCost,
        int quantity
) implements Product {
}
```

Another restriction that sealed classes impose is that subclasses must be either final or sealed themselves.
The reason for this will become apparent shortly. Java records are final by default. We could still put the `final`
keyword, but it would be redundant.

## Sealed classes in action

Now, let's say we have some very important business requirement to calculate the price for a list of `Product`s. One way
of doing this in the traditional OO style would be to implement something like
the [Interpreter Pattern](https://en.wikipedia.org/wiki/Interpreter_pattern) and have the `Product` interface define a
`calculatePrice` method.

Sealed classes are a step towards a more data oriented or functional approach to programming. In that world, we
separate data (our product types) from the actions that can be performed upon them (calculating their price). This
will also allow us to showcase the utility of sealed classes (unexpected, I know!).

Let's create a `ProductPriceCalculator`:

```java
public class ProductPriceCalculator {
    public BigDecimal calculatePrice(Product product) {
        return switch (product) {
            case DigitalProduct dp -> dp.basePrice()
                    .multiply(BigDecimal.valueOf(dp.quantity()));
            case PhysicalProduct pp -> pp.basePrice()
                    .multiply(BigDecimal.valueOf(pp.quantity()))
                    .add(pp.shippingCost());
        };
    }
}
```

Wow! What's going on here? First of all, we have our price calculator service containing a method `calculatePrice`
for calculating the price of any arbitrary `Product`. As you can see, the implementation
uses [Pattern matching for switch](https://openjdk.org/jeps/441). If you are unfamiliar with this, I suggest you visit
the linked JEP. Basically, we are matching on the specific type of product. Right now, we have two possible cases:
`DigitalProduct`s and `PhysicalProduct`s. No more, no less. We know this and, more importantly, the Java compiler knows
this.

The Java compiler knows our switch statement performs an _exhaustive match_ because every possible option is handled.
This is also the reason why all subclasses of a sealed class must be either final or sealed themselves: otherwise, the
hierarchy would be opened by the subclasses and the compiler would not be able to determine that there are no more
possible subclasses in the tree.

## Sealed hierarchies as ADTs

Sealed classes have existed in Kotlin and Scala for a long time before Java included them. In these languages, they
are often used to emulate the _Algebraic Data Types_ (ADTs) found in functional programming languages such as Haskell.
In Haskell, our `Product` class might look something like this:

```haskell
data Product = PhysicalProduct ... | DigitalProduct ...
```

With sealed classes, the data type would be determined by the sealed class itself, and the variants by its subclasses.
I think this is a very elegant way of domain modeling, and with the
upcoming [Value classes](https://openjdk.org/jeps/401), Java would be taking yet
another step towards a data oriented / functional style of programming.

Some day I might look at two snippets of Java and Haskell code and not be able to tell them apart!

## Closing thoughts

If you are FP inclined like me, sealed classes are a blessing from the coffee gods. Nonetheless, it's important to note
that everything that we can do with them was already possible with traditional OO patterns in Java. Sealed classes
offer us a new way of doing things, but not new things to do. An implementation using the `Interpreter` or `Visitor`
pattern would have been just as correct. I think all options are elegant in their own way, and we as developers should
know to pick the right tool for the job and context in which we are working.
