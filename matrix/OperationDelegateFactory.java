package matrix;

interface Addition<T> {
    T add(T a, T b);
}

interface Multiply<T> {
    T multiply(T a, T b);
}


public class OperationDelegateFactory {
    public static <T> Addition<T> getAdditionDelegate(T instance) {
        if (instance instanceof Integer) {
            return (Addition<T>) new IntegerAddition();
        } else if (instance instanceof Double) {
            return (Addition<T>) new DoubleAddition();
        }

        throw new RuntimeException("Not implemented behavior for type T.");
    }

    public static <T> Multiply<T> getMultiplyDelegate(T instance) {
        if (instance instanceof Integer) {
            return (Multiply<T>) new IntegerMultiply();
        } else if (instance instanceof Double) {
            return (Multiply<T>) new DoubleMultiply();
        }

        throw new RuntimeException("Not implemented behavior for type T.");
    }

}

class IntegerAddition implements Addition<Integer> {
    public Integer add(Integer a, Integer b) {
        return a + b;
    }
}

class DoubleAddition implements Addition<Double> {
    public Double add(Double a, Double b) {
        return a + b;
    }
}

class IntegerMultiply implements Multiply<Integer> {
    public Integer multiply(Integer a, Integer b) {
        return a * b;
    }
}

class DoubleMultiply implements Multiply<Double> {
    public Double multiply(Double a, Double b) {
        return a * b;
    }
}
