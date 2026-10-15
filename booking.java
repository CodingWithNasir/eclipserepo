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

public class booking {
    public static void main(String[] args) {

        Hospital h = new Hospital();

        Doctor d1 = new Doctor(h, "Doctor A", 2);
        Doctor d2 = new Doctor(h, "Doctor B", 3);

        d1.start();
        d2.start();
    }
}
