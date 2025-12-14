import java.util.*;

class Menu {
    int id;
    String nama;
    double harga;

    Menu(int id, String nama, double harga) {
        this.id = id;
        this.nama = nama;
        this.harga = harga;
    }
}

class Pesanan {
    Menu menu;
    int jumlah;

    Pesanan(Menu menu, int jumlah) {
        this.menu = menu;
        this.jumlah = jumlah;
    }

    double getSubtotal() {
        return menu.harga * jumlah;
    }
}

public class SistemKantin {
    static Scanner scanner = new Scanner(System.in);
    static List<Menu> daftarMenu = new ArrayList<>();
    static List<Pesanan> daftarPesanan = new ArrayList<>();

    public static void main(String[] args) {
        initMenu();
        boolean running = true;

        while (running) {
            tampilkanMenuUtama();
            int pilihan = scanner.nextInt();

            switch (pilihan) {
                case 1:
                    tampilkanDaftarMenu();
                    break;
                case 2:
                    tambahPesanan();
                    break;
                case 3:
                    tampilkanPesanan();
                    break;
                case 4:
                    prosesPembayaran();
                    running = false;
                    break;
                case 0:
                    running = false;
                    break;
                default:
                    System.out.println("Pilihan tidak valid!");
            }
        }
        System.out.println("Terima kasih telah memesan di Kantin Kampus!");
    }

    static void initMenu() {
        daftarMenu.add(new Menu(1, "Nasi Goreng", 15000));
        daftarMenu.add(new Menu(2, "Mie Ayam", 12000));
        daftarMenu.add(new Menu(3, "Ayam Geprek", 18000));
        daftarMenu.add(new Menu(4, "Es Teh", 5000));
        daftarMenu.add(new Menu(5, "Es Jeruk", 7000));
    }

    static void tampilkanMenuUtama() {
        System.out.println("\n=== SISTEM PEMESANAN KANTIN KAMPUS ===");
        System.out.println("1. Lihat Daftar Menu");
        System.out.println("2. Tambah Pesanan");
        System.out.println("3. Lihat Pesanan");
        System.out.println("4. Bayar & Selesai");
        System.out.println("0. Keluar");
        System.out.print("Pilih menu: ");
    }

    static void tampilkanDaftarMenu() {
        System.out.println("\n--- DAFTAR MENU ---");
        for (Menu m : daftarMenu) {
            System.out.println(m.id + ". " + m.nama + " - Rp" + m.harga);
        }
    }

    static void tambahPesanan() {
        tampilkanDaftarMenu();
        System.out.print("Masukkan ID menu: ");
        int id = scanner.nextInt();
        System.out.print("Masukkan jumlah: ");
        int jumlah = scanner.nextInt();

        Menu menuDipilih = null;
        for (Menu m : daftarMenu) {
            if (m.id == id) {
                menuDipilih = m;
                break;
            }
        }

        if (menuDipilih != null) {
            daftarPesanan.add(new Pesanan(menuDipilih, jumlah));
            System.out.println("Pesanan berhasil ditambahkan!");
        } else {
            System.out.println("Menu tidak ditemukan!");
        }
    }

    static void tampilkanPesanan() {
        System.out.println("\n--- DAFTAR PESANAN ---");
        double total = 0;
        for (Pesanan p : daftarPesanan) {
            System.out.println(p.menu.nama + " x" + p.jumlah + " = Rp" + p.getSubtotal());
            total += p.getSubtotal();
        }
        System.out.println("Total sementara: Rp" + total);
    }

    static void prosesPembayaran() {
        tampilkanPesanan();
        double total = 0;
        for (Pesanan p : daftarPesanan) {
            total += p.getSubtotal();
        }
        System.out.println("\nTotal yang harus dibayar: Rp" + total);
        System.out.print("Masukkan uang pembayaran: Rp");
        double bayar = scanner.nextDouble();

        if (bayar >= total) {
            System.out.println("Pembayaran berhasil! Kembalian: Rp" + (bayar - total));
        } else {
            System.out.println("Uang tidak cukup!");
        }
    }
}
