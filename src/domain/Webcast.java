package domain;

import java.time.LocalDate;

public class Webcast extends ContentItem{

    private String title;
    private String speaker;
    private String organization;
    private int durationInMinutes;
    private String url;
    private String description;


    public Webcast(String title, String speaker, String organization, int durationInMinutes, String url, Status status, LocalDate date, String description) {
        super(date, status);
        this.title = title;
        this.speaker = speaker;
        this.organization = organization;
        this.durationInMinutes = durationInMinutes;
        this.url = url;
        this.description = description;
    }

    public Webcast(String title, String speaker, String organization, int durationInMinutes, String url, Status status, String description) {
        super(LocalDate.now(), status);
        this.title = title;
        this.speaker = speaker;
        this.organization = organization;
        this.durationInMinutes = durationInMinutes;
        this.url = url;
        this.description = description;
    }


    //Getters and setters have been automatically generated
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSpeaker() {
        return speaker;
    }

    public void setSpeaker(String speaker) {
        this.speaker = speaker;
    }

    public String getOrganization() {
        return organization;
    }

    public void setOrganization(String organization) {
        this.organization = organization;
    }

    public int getDurationInMinutes() {
        return durationInMinutes;
    }

    public void setDurationInMinutes(int durationInMinutes) {
        this.durationInMinutes = durationInMinutes;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
}
