

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();
        Map<String, Integer> regionCityCount = new HashMap<>(); // Создаем HashMap для подсчета городов по регионам

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Ostap\\IdeaProjects\\" +
                    "Sber\\untitled\\src\\main\\java\\file.csv"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";", -1);

                if (parts.length == 6) {
                    int id = Integer.parseInt(parts[0]);
                    String name = parts[1];
                    String region = parts[2];
                    String district = parts[3];
                    int population = Integer.parseInt(parts[4]);
                    String foundation = parts[5];

                    City city = new City(id, name, region, district, population, foundation);
                    cities.add(city);
                } else {
                    System.out.println("Invalid line format: " + line);
                }

            }
            scanner.close();




            System.out.println("Enter the number of the desired function.\n" +
                    " 1.Sort the list of cities by name in descending alphabetical order.\n" +
                    " 2.Sort the list of cities by federal district and city name within each federal district in \n" +
                    " descending alphabetical order in a case sensitive manner. \n" +
                    " 3.Get most populous city. \n" +
                    " 4. Get the number of cities in each region." +
                    " Type here:");
            int answ = new Scanner(System.in).nextInt();

            if (answ == 1){
                Collections.sort(cities, new CityComporator());
                for (City city : cities) {
                    System.out.println(city);
                }
            } else if (answ == 2) {
                Collections.sort(cities, new DistrictCityComparator());
                for (City city : cities) {
                    System.out.println(city);
                }
            } else if (answ == 3) {
                cities.sort(Comparator.comparing(City::getPopulation).reversed());
                System.out.println("[" + (cities.get(0).getId() - 1) + "] - " + cities.get(0).getPopulation());
            } else if (answ == 4){
                for (City city : cities) {
                    String region = city.getRegion();
                    regionCityCount.put(region, regionCityCount.getOrDefault(region, 0) + 1);
                }
                for (Map.Entry<String, Integer> entry : regionCityCount.entrySet()) {
                    System.out.println(entry.getKey() + " - " + entry.getValue());
                }
            }
            else {
                System.out.println("Error.");
            }

        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}