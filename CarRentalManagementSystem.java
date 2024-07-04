import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;

// Kelas Car yang merepresentasikan sebuah mobil
class Car {
    String PlatMobil;
    String brand;
    String model;
    int Tahun;
    double HargaPerHari;
    boolean Rental;

    // Konstruktor Car yang menginisialisasi atribut-atribut mobil
    Car(String plate, String carBrand, String carModel, int carTahun, double pricePerDay) {
        PlatMobil = plate;
        brand = carBrand;
        model = carModel;
        Tahun = carTahun;
        HargaPerHari = pricePerDay;
        Rental = false;
    }

    // Getter untuk mendapatkan nomor plat mobil
    String getPlatMobil() {
        return PlatMobil;
    }

    // Getter untuk mendapatkan merk mobil
    String getBrand() {
        return brand;
    }

    // Getter untuk mendapatkan model mobil
    String getModel() {
        return model;
    }

    // Getter untuk mendapatkan tahun mobil
    int getTahun() {
        return Tahun;
    }

    // Getter untuk mendapatkan harga sewa per hari
    double getHargaPerHari() {
        return HargaPerHari;
    }

    // Getter untuk mendapatkan status rental mobil
    boolean Rental() {
        return Rental;
    }

    // Setter untuk mengubah status rental mobil
    void setRented(boolean rented) {
        Rental = rented;
    }
}

// Kelas CarRentalManagementSystem yang mengelola sistem rental mobil
public class CarRentalManagementSystem {
    ArrayList<Car> cars;
    Scanner scanner;

    // Konstruktor CarRentalManagementSystem yang menginisialisasi atribut-atribut sistem
    CarRentalManagementSystem() {
        cars = new ArrayList<>();
        scanner = new Scanner(System.in);

        // Menambahkan beberapa mobil secara otomatis
        cars.add(new Car("B 1234 CD", "Toyota", "Avanza", 2019, 500000));
        cars.add(new Car("B 5678 EF", "Honda", "Civic", 2020, 750000));
        cars.add(new Car("B 9101 GH", "Suzuki", "Ertiga", 2018, 350000));
        cars.add(new Car("B 1121 IJ", "Mitsubishi", "Pajero", 2021, 1000000));
        cars.add(new Car("B 3141 KL", "Nissan", "March", 2017, 400000));
    }

    // Metode untuk menampilkan daftar mobil yang tersedia
    void listCars() {
        System.out.println("Mobil yang tersedia:");
        for (Car car : cars) {
            if (!car.Rental()) {
                System.out.println("Nomor plat: " + car.getPlatMobil());
                System.out.println("Merk: " + car.getBrand());
                System.out.println("Model: " + car.getModel());
                System.out.println("Tahun: " + car.getTahun());
                System.out.println("Harga sewa per hari: " + car.getHargaPerHari());
                System.out.println();
            }
        }
    }

    // Metode untuk menyewa mobil
    void rentCar() {
        System.out.print("Masukkan nomor plat mobil yang ingin disewa: ");
        String PlatMobil = scanner.nextLine();
        System.out.print("Masukkan durasi sewa (dalam hari): ");
        int rentalDuration = scanner.nextInt();
        scanner.nextLine(); // consume newline left-over

        for (Car car : cars) {
            if (car.getPlatMobil().equals(PlatMobil) && !car.Rental()) {
                double totalCost = rentalDuration * car.getHargaPerHari();
                System.out.println("Total biaya sewa: " + totalCost);
                System.out.print("Konfirmasi sewa? (yes/no): ");
                String confirmation = scanner.nextLine();

                if (confirmation.equalsIgnoreCase("yes")) {
                    car.setRented(true);
                    System.out.println("Mobil berhasil disewa!");
                } else {
                    System.out.println("Sewa dibatalkan.");
                }
                return;
            }
        }

        System.out.println("Mobil tidak ditemukan atau sudah disewa!");
    }

    // Metode untuk mengembalikan mobil
    void returnCar() {
        System.out.print("Masukkan nomor plat mobil yang ingin dikembalikan: ");
        String PlatMobil = scanner.nextLine();

        for (Car car : cars) {
            if (car.getPlatMobil().equals(PlatMobil) && car.Rental()) {
                car.setRented(false);
                System.out.println("Mobil berhasil dikembalikan!");
                return;
            }
        }

        System.out.println("Mobil tidak ditemukan atau belum disewa!");
    }

    // Metode untuk mencari mobil berdasarkan merk
    void searchCar() {
        System.out.print("Masukkan merk mobil yang ingin dicari: ");
        String Brand = scanner.nextLine();

        for (Car car : cars) {
            if (car.getBrand().equalsIgnoreCase(Brand)) {
                System.out.println("Mobil ditemukan:");
                System.out.println("Nomor plat: " + car.getPlatMobil());
                System.out.println("Merk: " + car.getBrand());
                System.out.println("Model: " + car.getModel());
                System.out.println("Tahun: " + car.getTahun());
                System.out.println("Harga sewa per hari: " + car.getHargaPerHari());
                System.out.println("Status sewa: " + (car.Rental() ? "Disewa" : "Tersedia"));
                return;
            }
        }

        System.out.println("Mobil tidak ditemukan!");
    }

    // Metode untuk mengurutkan daftar mobil berdasarkan tahun
    void sortCarsByTahun() {
        Collections.sort(cars, Comparator.comparingInt(Car::getTahun));
        System.out.println("Daftar mobil berhasil diurutkan berdasarkan tahun.");
        listCars();
    }

    // Metode untuk mengurutkan daftar mobil berdasarkan harga sewa per hari
    void sortCarsByPrice() {
        Collections.sort(cars, Comparator.comparingDouble(Car::getHargaPerHari));
        System.out.println("Daftar mobil berhasil diurutkan berdasarkan harga sewa per hari.");
        listCars();
    }

    // Metode untuk menjalankan sistem rental mobil
    void run() {
        while (true) {
            System.out.println("Sistem Rental Mobil");
            
            System.out.println("1. Lihat daftar mobil");
            System.out.println("2. Sewa mobil");
            System.out.println("3. Kembalikan mobil");
            System.out.println("4. Cari mobil");
            System.out.println("5. Urutkan mobil berdasarkan tahun");
            System.out.println("6. Urutkan mobil berdasarkan harga sewa per hari");
            System.out.println("7. Keluar");
            System.out.print("Pilih opsi: ");
            int option = scanner.nextInt();
            scanner.nextLine(); // consume newline left-over

            switch (option) {
                
                case 1:
                    listCars();
                    break;
                case 2:
                    rentCar();
                    break;
                case 3:
                    returnCar();
                    break;
                case 4:
                    searchCar();
                    break;
                case 5:
                    sortCarsByTahun();
                    break;
                case 6:
                    sortCarsByPrice();
                    break;
                case 7:
                    System.out.println("Terima Kasih sudah mengunjungi tempat usaha kami");
                    return;
                default:
                    System.out.println("Opsi tidak valid. Silakan coba lagi.");
            }
        }
    }

    // Metode utama untuk menjalankan program
    public static void main(String[] args) {
        CarRentalManagementSystem system = new CarRentalManagementSystem();
        system.run();
}
}