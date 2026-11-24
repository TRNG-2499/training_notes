package com.revature.lambdas;

import com.revature.lambdas.interfaces.BinaryMathOps;
import com.revature.lambdas.interfaces.GenericFuncInter;
import com.revature.lambdas.interfaces.TernaryMathOps;
import com.revature.lambdas.interfaces.UnaryMathOps;

import java.util.function.UnaryOperator;

public class MathOpsDriver {
    public static void main(String[] args) {
        /*
         * When using Lambdas there are syntax several rules
         * to keep in mind. For the following examples, see the
         * 'MathOps' functional interfaces in the 'interfaces'
         * package
         */

        // Rule # 1: You NEVER need to state the datatype for lambda parameters
        UnaryMathOps unary_01 =
                (Double d) -> {
                    return ++d;
                };
        BinaryMathOps binary_01 = (x, y) -> { return x + y; };
        TernaryMathOps ternary_01 = (a, b, c) -> { return a + b + c; };

        Double answer_01 = unary_01.calculate(17.0);



        GenericFuncInter<String> myImpl = s -> "hello";




        /*
         * Rule # 2: You can omit the parameter parenthesis, if the functional
         *      interface being implemented has a single parameter AND you
         *      do not state the datatype in the lambda syntax
         */
        UnaryMathOps unary_02 = d -> { return d + 5; };











        /*
         * Rule # 3: You can omit the curly braces for the lambda body if the
         *      implementation is a single statement AND you DO NOT include
         *      the return keyword (if required)
         *
         * x -> x + 5 is equivalent to: x -> { return x+5; };
         */
        UnaryMathOps unary_03 = x -> x + 5; // same implementation as unary_02
        BinaryMathOps binary_03 = (a, b) -> a * b;

    }
}
