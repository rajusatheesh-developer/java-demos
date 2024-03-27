import java.util.List;

public class Contact {
    private Name name;
    private String purchasingAuthority;
    private String contactType;
    private List<Responsibility> responsibilities;
    private List<Communication> communications;

    // Getters and setters
}

class Name {
    private String familyName;
    private String givenName;

    // Getters and setters
}

class Responsibility {
    private String name;

    // Getters and setters
}

class Communication {
    private String type;
    private String usageType;
    private String dialCountry;
    private String dialArea;
    private String dialNumber;

    // Getters and setters
}
