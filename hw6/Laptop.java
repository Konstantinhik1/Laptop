import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

class Laptop {
    private String model;
    private int ram;
    private int hdd;
    private String os;
    private String color;

    public Laptop(String model, int ram, int hdd, String os, String color) {
        this.model = model;
        this.ram = ram;
        this.hdd = hdd;
        this.os = os;
        this.color = color;
    }

    public String getModel() {
        return model;
    }

    public int getRam() {
        return ram;
    }

    public int getHdd() {
        return hdd;
    }

    public String getOs() {
        return os;
    }

    public String getColor() {
        return color;
    }
}

class Main {
    public static void main(String[] args) {
        List<Laptop> laptops = new ArrayList<>();
        laptops.add(new Laptop("Dell", 8, 500, "Windows", "Black"));
        laptops.add(new Laptop("HP", 16, 1000, "Windows", "Silver"));
        laptops.add(new Laptop("Lenovo", 12, 750, "Windows", "Gray"));
        laptops.add(new Laptop("Asus", 8, 1000, "Windows", "White"));
        laptops.add(new Laptop("Acer", 16, 1000, "Windows", "Black"));
        laptops.add(new Laptop("Apple", 8, 500, "macOS", "Silver"));

        Map<String, Integer> filterCriteria = getUserFilterCriteria();
        List<Laptop> filteredLaptops = filterLaptops(laptops, filterCriteria);

        System.out.println("Найденные ноутбуки:");
        for (Laptop laptop : filteredLaptops) {
            System.out.println("Модель: " + laptop.getModel());
            System.out.println("ОЗУ: " + laptop.getRam());
            System.out.println("Объем ЖД: " + laptop.getHdd());
            System.out.println("ОС: " + laptop.getOs());
            System.out.println("Цвет: " + laptop.getColor());
            System.out.println("-------------");
        }
    }

    public static Map<String, Integer> getUserFilterCriteria() {
        Map<String, Integer> filterCriteria = new HashMap<>();
        Map<Integer, String> criteriaMap = new HashMap<>();
        criteriaMap.put(1, "ram");
        criteriaMap.put(2, "hdd");
        criteriaMap.put(3, "os");
        criteriaMap.put(4, "color");

        Scanner scanner = new Scanner(System.in);

        System.out.println("Введите цифру, соответствующую необходимому критерию:");
        System.out.println("1 - ОЗУ");
        
        System.out.println("2 - Объем ЖД");
        System.out.println("3 - Операционная система");
        System.out.println("4 - Цвет");

        int userInput = scanner.nextInt();
        while (userInput < 1 || userInput > 4) {
            System.out.println("Некорректный ввод. Попробуйте снова.");
            userInput = scanner.nextInt();
        }

        String selectedCriteria = criteriaMap.get(userInput);

        System.out.print("Введите минимальное значение для " + selectedCriteria + ": ");
        int minValue = scanner.nextInt();

        filterCriteria.put(selectedCriteria, minValue);
        return filterCriteria;
    }

    public static List<Laptop> filterLaptops(List<Laptop> laptops, Map<String, Integer> filterCriteria) {
        List<Laptop> filteredLaptops = new ArrayList<>();
        for (Laptop laptop : laptops) {
            boolean passCriteria = true;
            for (Map.Entry<String, Integer> entry : filterCriteria.entrySet()) {
                String criteria = entry.getKey();
                int minValue = entry.getValue();

                if (criteria.equals("ram") && laptop.getRam() < minValue) {
                    passCriteria = false;
                    break;
                } else if (criteria.equals("hdd") && laptop.getHdd() < minValue) {
                    passCriteria = false;
                    break;
                } else if (criteria.equals("os") && !laptop.getOs().equals(String.valueOf(minValue))) {
                    passCriteria = false;
                    break;
                } else if (criteria.equals("color") && !laptop.getColor().equals(String.valueOf(minValue))) {
                    passCriteria = false;
                    break;
                }
            }
            if (passCriteria) {
                filteredLaptops.add(laptop);
            }
        }
        return filteredLaptops;
    }
}
