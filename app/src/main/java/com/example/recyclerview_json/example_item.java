package com.example.recyclerview_json;

public class example_item {
    private String url;
    private String creator;
    private int likes;

    example_item(String u,String c,int l)
    {
        this.url=u;
        this.creator=c;
        this.likes=l;
    }

    public String getUrl(){
        return  url;}

    public int getLikes() {
        return likes;
    }
    public String getCreator()
    {
        return creator;
    }
}
