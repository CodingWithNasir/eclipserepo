package main;
class Hospital {

    int availableOTs = 3;

    void bookOT(String doctor, int reqOTs) {
        System.out.println(doctor + " checking OTs... Available = " + availableOTs);

        if (availableOTs >= reqOTs) {
            try {
                Thread.sleep(100); // delay to force race condition
            } catch (Exception e) {}

            availableOTs = availableOTs - reqOTs;
            System.out.println(doctor + " booked " + reqOTs +
                               " OTs | Remaining = " + availableOTs);
        } else {
            System.out.println("Not enough OTs for " + doctor);
        }
    }
}

class Doctor extends Thread {
    Hospital h;
    String name;
    int ots;

    Doctor(Hospital h, String name, int ots) {
        this.h = h;
        this.name = name;
        this.ots = ots;
    }

    public void run() {
        h.bookOT(name, ots);
    }
}

public class RaceConditionDemo {
    public static void main(String[] args) {

        Hospital h = new Hospital();

        Doctor d1 = new Doctor(h, "Doctor A", 2);
        Doctor d2 = new Doctor(h, "Doctor B", 3);

        d1.start();
        d2.start();
    }
}

public class GenericCalculator<T> {

    public double add(T a, T b) {
        if (a instanceof Number && b instanceof Number) {
            Number n1 = (Number) a;
            Number n2 = (Number) b;
            return n1.doubleValue() + n2.doubleValue();
        }
        throw new IllegalArgumentException("Parameters must be Numbers");
    }
}

import static org.junit.jupiter.api.Assertions.assertEquals;
import org.junit.jupiter.api.Test;

public class GenericCalculatorTest {

    @Test
    void testAddIntegers() {
        GenericCalculator<Integer> calculator = new GenericCalculator<>();
        double result = calculator.add(5, 10);
        assertEquals(15.0, result);
    }

    @Test
    void testAddDoubles() {
        GenericCalculator<Double> calculator = new GenericCalculator<>();
        double result = calculator.add(2.5, 3.5);
        assertEquals(6.0, result);
    }
}





