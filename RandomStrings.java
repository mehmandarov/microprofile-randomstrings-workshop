import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

class RandomStrings {

    public String[] generateRandomStringsPair() {
        List<String> adjectives = readFromEnum(Adjectives.values());
        List<String> nouns = readFromEnum(Nouns.values());

        return new String[] {getRandomElement(adjectives), getRandomElement(nouns)};
    }

    private List<String> readFromEnum(Enum<?>[] values) {
        return Arrays.stream(values).map(Enum::name).map(str -> str.replaceAll("_", "-")).collect(Collectors.toList());
    }

    private String getRandomElement(List<String> list) {
        Random rand = new Random();
        return list.get(rand.nextInt(list.size()));
    }

    public static void main(String[] args) {
        RandomStrings rs = new RandomStrings();
        for (String s : rs.generateRandomStringsPair()){
            System.out.println(s);
        }
    }

}
