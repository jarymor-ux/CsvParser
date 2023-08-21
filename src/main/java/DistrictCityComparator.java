import java.util.Comparator;

public class DistrictCityComparator implements Comparator<City> {
    @Override
    public int compare(City o1, City o2) {

        int districtComparison = o1.getDistrict().compareTo(o2.getDistrict());

        if (districtComparison != 0) {
            return districtComparison;
        }

        return o1.getName().compareTo(o2.getName());
    }
}
