package ue06.demo.list;

import java.util.Random;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;

import inout.In;
import inout.Out;

public class MiscDemo {

    private static final Random RAND = new Random();

    static public <A, B> void doGetTestMapUse(
            Supplier<A> getter,
            Predicate<A> condition,
            Function<A, B> processor,
            Consumer<B> user) {
        A a = getter.get();
        if (condition.test(a)) {
            B b = processor.apply(a);
            user.accept(b);
        }
    }

    public static void main(String[] args) {
        doGetTestMapUse(
                () -> In.readLine(),
                s -> s.length() != 0,
                s -> s.length(),
                i -> Out.println("Length = " + i)
        );

        Function<Integer, Integer> summer = new Function<Integer, Integer>() {
            private int sum = 0;

            @Override
            public Integer apply(Integer i) {
                sum += i;
                return sum;
            }

        };

        for (int i = 0; i < 3; i++) {
            doGetTestMapUse(
                    () -> {
                        Out.println("Input number: ");
                        return In.readInt();
                    },
                    x -> x != 0,
                    summer,
                    sum -> Out.println("Sum = " + sum)
            );
        }

        List<Integer> list = List.of(1, 2, 3);
        // this does not work
        int sum = 0;
        //list.forEach(x -> sum = sum + x);

        class MutInt {
            int x;
        }
        MutInt bSum = new MutInt();
        int[] aSum = new int[1];
        list.forEach(x -> {
            bSum.x = bSum.x + x;
            aSum[0] = aSum[0] + x;
        });
    }
}