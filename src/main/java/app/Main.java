package app;

import com.github.javafaker.Faker;
import com.opencsv.CSVWriter;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.SecureRandom;
import java.util.Locale;


public class Main {

    private static Locale createLocale(String region) {
        //Splitting region string to receive arguments for locale
        String[] fakerArgs = region.split("-");
        return new Locale(fakerArgs[0], fakerArgs[1]);
    }

    private static Faker createFaker(Locale locale) {
        return new Faker(locale);
    }

    private static String[] createFakeRecord(Faker faker, Locale locale, SecureRandom randomizer) {
        String[] recordData = new String[] {
                faker.name().firstName(),
                faker.name().lastName(),
                generateCountryName(locale, randomizer),
                faker.address().state(),
                faker.address().city(),
                faker.address().streetAddress(),
                faker.address().zipCode(),
                faker.phoneNumber().phoneNumber()
        };

        return recordData;
    }

    private static String generateCountryName(Locale locale, SecureRandom randomizer) {
        String countryName = null;

        if (randomizer.nextInt(10) >= 5) {
            countryName = locale.getCountry();
        } else {
            countryName = locale.getDisplayCountry();
        }

        return countryName;
    }

    public static void main(String[] args) throws IOException {

        ArgumentsChecker argChecker = new ArgumentsChecker();
        PrintWriter printWriter = new PrintWriter(System.out);
        CSVWriter writer = new CSVWriter(printWriter, ';', CSVWriter.NO_QUOTE_CHARACTER,
                CSVWriter.DEFAULT_ESCAPE_CHARACTER, CSVWriter.DEFAULT_LINE_END);
        //For random form of country name
        SecureRandom randomizer = new SecureRandom();
        int numberOfRecords = 0;
        String region = null;
        Locale regionLocale = null;
        Faker faker = null;

        if (argChecker.checkNumberOfArgs(args.length) && argChecker.checkNumberOfRecords(args[0])
                && argChecker.checkRegionName(args[1])) {

            numberOfRecords = Integer.parseInt(args[0]);
            region = args[1];

            regionLocale = createLocale(region);
            //Setting default locale to display country name in the native language
            Locale.setDefault(regionLocale);
            faker = createFaker(regionLocale);

            for (int i = 0; i < numberOfRecords; i++) {

                writer.writeNext(createFakeRecord(faker, regionLocale, randomizer));
            }
            writer.close();
        }
    }

}
