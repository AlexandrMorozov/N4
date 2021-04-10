package app;

public class ArgumentsChecker {

    private boolean isArgNumeric(String recordsNumber) {
        try {
            Integer.parseInt(recordsNumber);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public boolean checkNumberOfRecords(String args) {
        if(!isArgNumeric(args)) {
            System.out.println("First argument should be a number!");
        } else if (!(Integer.parseInt(args) > 0)) {
            System.out.println("First argument number should be greater than zero!");
        } else {
            return true;
        }
        return false;
    }

    public boolean checkNumberOfArgs(int numberOfArgs) {
        if (numberOfArgs != 2) {
            System.out.println("There must be only 2 arguments!");
            return false;
        }
        return true;
    }

    public boolean checkRegionName(String region) {
        if(!region.equals("ru-RU") && !region.equals("en-US") && !region.equals("uk-UA")) {
            System.out.println("Application supports only Russian(ru-RU), " +
                    "American(en-US) and Ukrainian(uk-UA) regions!");
            return false;
        }
        return true;
    }
}
