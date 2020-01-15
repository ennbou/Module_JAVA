package app;

import java.util.ArrayList;
import java.util.List;

public class App {
    public static void main(String[] args) {
        CC c = new CC();
        Calcul calcul1 = new Calcul(2, 4, '*');
        Calcul calcul2 = new Calcul(8, 12, '*');
        Calcul calcul3 = new Calcul(20, 40, '+');
        Calcul calcul4 = new Calcul(60, 80, '+');

        c.add(calcul1);
        c.add(calcul2);
        c.add(calcul3);
        c.add(calcul4);

        c.calcul();

        double montant = c.getMontant();
        System.out.println(montant);

        DD d = new DD();

        Number n1 = new Number(2);
        Number n2 = new Number(5);
        Number n3 = new Number(2);
        Number n4 = new Number(7);
        Number n5 = new Number(6);
        Number n6 = new Number(1);

        d.add(n1);
        d.add(n2);
        d.add(n3);
        d.add(n4);
        d.add(n5);
        d.add(n6);

        d.calcul();

        montant = d.getMontant();

        System.out.println(montant);

    }
}

interface Strategy {
    double doOperation(int a, int b);
}

class Sum implements Strategy {

    @Override
    public double doOperation(int a, int b) {
        return a + b;
    }

}

class Multiply implements Strategy {

    @Override
    public double doOperation(int a, int b) {
        return a * b;
    }

}

class Context {
    private Strategy strategy;

    public Context(Strategy strategy) {
        this.strategy = strategy;
    }

    public double executStrategy(int a, int b) {
        return strategy.doOperation(a, b);
    }

}

class Calcul {
    private int a;
    private int b;
    private char c;
    private Context context;

    public Calcul(int a, int b, char c) {
        this.a = a;
        this.b = b;
        this.c = c;
        if (c == '+') {
            context = new Context(new Sum());
        } else if (c == '*') {
            context = new Context(new Multiply());
        }
    }

    public double doCalcul() {
        return context.executStrategy(a, b);
    }
}

interface X {
    void calcul();

    double getMontant();
}

class CC implements X {
    private List<Calcul> listCalcul = new ArrayList<>();
    private double montant;

    public void add(Calcul c) {
        listCalcul.add(c);
    }

    @Override
    public void calcul() {
        for (Calcul calcul : listCalcul) {
            montant += calcul.doCalcul();
        }
    }

    @Override
    public double getMontant() {
        return montant;
    }

}

class Number {

    private int value;

    public Number(int v) {
        value = v;
    }

    public int getValue() {
        return value;
    }
}

class DD implements X {
    private List<Number> listNumber = new ArrayList<>();
    private double montant;

    public void add(Number n) {
        listNumber.add(n);
    }

    @Override
    public void calcul() {
        for (Number n : listNumber) {
            montant += n.getValue();
        }
    }

    @Override
    public double getMontant() {
        return montant;
    }

}