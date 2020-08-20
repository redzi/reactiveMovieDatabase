package com.redzi.movie.database.service.movie.search.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.ToString;

@ToString
public class SearchDetails
{
    private String title;
    private String year;
    @JsonProperty("imdbID")
    private String id;
    private Type type;
    @JsonProperty("poster")
    private String posterURL;

    public String getTitle()
    {
        return title;
    }

    public void setTitle(String title)
    {
        this.title = title;
    }

    public String getYear()
    {
        return year;
    }

    public void setYear(String year)
    {
        this.year = year;
    }

    public String getId()
    {
        return id;
    }

    public void setId(String id)
    {
        this.id = id;
    }

    public Type getType()
    {
        return type;
    }

    public void setType(Type type)
    {
        this.type = type;
    }

    public String getPosterURL()
    {
        return posterURL;
    }

    public void setPosterURL(String posterURL)
    {
        this.posterURL = posterURL;
    }
}
