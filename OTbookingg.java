package main;

class Hospital {

    int availableOTs = 3;

    synchronized void bookOT(String doctor, int requestedOTs) {
        System.out.println(doctor + " checking available OTs...");

        if (availableOTs >= requestedOTs) {
            System.out.println(doctor + " booking " + requestedOTs + " OTs");

            try { Thread.sleep(100); } catch (Exception e) {}

            availableOTs = availableOTs - requestedOTs;
            System.out.println("Remaining OTs after " + doctor + ": " + availableOTs);
        } else {
            System.out.println("Not enough OTs for " + doctor);
        }
    }
}

class Doctor extends Thread {
    Hospital hospital;
    String name;
    int ots;

    Doctor(Hospital h, String name, int ots) {
        this.hospital = h;
        this.name = name;
        this.ots = ots;
    }

    public void run() {
        hospital.bookOT(name, ots);
    }
}

public class OTbookingg {
    public static void main(String[] args) {
        Hospital h = new Hospital();

        Doctor d1 = new Doctor(h, "Doctor A", 2);
        Doctor d2 = new Doctor(h, "Doctor B", 3);

        d1.start();
        d2.start();
    }
}

