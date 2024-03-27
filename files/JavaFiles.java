import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.List;
import java.util.Map;

public class Metadata {
    @JsonProperty("contentItemCount")
    private int contentItemCount;
    
    @JsonProperty("pagination")
    private Pagination pagination;
    
    @JsonProperty("_links")
    private Map<String, Object> links;

    // Getters and setters
}

class Pagination {
    private int offset;
    private int limit;
    private int itemCount;
    private int total;
    private Map<String, Link> _links;

    // Getters and setters
}

class Link {
    private String href;
    private String hreflang;
    private String title;
    private String type;
    private String deprecation;
    private String profile;
    private String name;
    private boolean templated;

    // Getters and setters
}

public class ContentItem {
    private String contactId;
    private CompanyContactType companyContactType;
    private String caid;
    private String ueid;
    private String weid;
    private String firstName;
    private String middleName;
    private String lastName;
    private String preferredFirstName;
    private String preferredLastName;
    private String displayFirstName;
    private String displayLastName;
    private boolean authorizedSignatory;
    private boolean purchasingAuthority;
    private String signatoryTitle;
    private String note;
    private String codePhrase;
    private String startDate;
    private String endDate;
    private int versionNumber;
    private List<Responsibility> responsibilities;
    private Communication communication;
    private boolean defaultContact;
    private boolean replaceExistingMain;
    private String correlationId;
    private String inviteSentDate;
    private LinkedUsersForWorkerContact linkedUsersForWorkerContact;
    private String invitedByUser;
    private Map<String, Link> _links;

    // Getters and setters
}

class CompanyContactType {
    private String type;
    private String name;
    private Map<String, Link> _links;

    // Getters and setters
}

class Responsibility {
    private String id;
    private String name;
    private String description;
    private Map<String, Link> _links;

    // Getters and setters
}

class Communication {
    private Telecom telecom;
    private Email email;

    // Getters and setters
}

class Telecom {
    @JsonProperty("additionalProp1")
    private TelecomDetail additionalProp1;

    @JsonProperty("additionalProp2")
    private TelecomDetail additionalProp2;

    @JsonProperty("additionalProp3")
    private TelecomDetail additionalProp3;

    // Getters and setters
}

class Email {
    @JsonProperty("additionalProp1")
    private EmailDetail additionalProp1;

    @JsonProperty("additionalProp2")
    private EmailDetail additionalProp2;

    @JsonProperty("additionalProp3")
    private EmailDetail additionalProp3;

    // Getters and setters
}

class TelecomDetail {
    private String id;
    private String type;
    private String countryCode;
    private String cityCode;
    private String subscriber;
    private String subscriberExtension;
    private String usageType;
    private boolean primary;
    private String correlationId;
    private int versionNumber;
    private Map<String, Link> _links;

    // Getters and setters
}

class EmailDetail {
    private String id;
    private String emailAddress;
    private String providerName;
    private String usageType;
    private boolean primary;
    private String correlationId;
    private int versionNumber;
    private Map<String, Link> _links;

    // Getters and setters
}

class LinkedUsersForWorkerContact {
    private List<String> userIds;
    private String workerId;
    private String caid;

    // Getters and setters
}

class Error {
    private String code;
    private String description;
    private String resolution;
    private String token;
    private String field;
    private String stacktrace;
    private List<Object> additionalInfo;
    private String severity;
    private Map<String, Link> _links;

    // Getters and setters
}
