import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) {
        List<City> cities = new ArrayList<>();

        try {
            Scanner scanner = new Scanner(new File("C:\\Users\\Ostap\\IdeaProjects\\" +
                    "Sber\\untitled\\src\\main\\java\\file.csv"));

            while (scanner.hasNextLine()) {
                String line = scanner.nextLine();
                String[] parts = line.split(";", -1);

                if (parts.length == 6) {
                    String name = parts[1];
                    String region = parts[2];
                    String district = parts[3];
                    int population = Integer.parseInt(parts[4]);
                    String foundation = parts[5];

                    City city = new City(name, region, district, population, foundation);
                    cities.add(city);
                } else {
                    System.out.println("Invalid line format: " + line);
                }

            }
            scanner.close();
            System.out.println("""
                    Enter the number of the desired function.
                     1.Sort the list of cities by name in descending alphabetical order.
                     2.Sort the list of cities by federal district and city name within each federal district in\s
                     descending alphabetical order in a case sensitive manner.\s
                     Type here: """);
            int answ = new Scanner(System.in).nextInt();

            if (answ == 1){
                Collections.sort(cities,new CityComporator());
            } else if (answ == 2) {
                Collections.sort(cities,new DistrictCityComparator());
            }else {
                System.out.println("Error.");
            }


            for (City city : cities) {
                System.out.println(city);
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found: " + e.getMessage());
        }
    }
}