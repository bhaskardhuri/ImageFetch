package com.example.imageapp.models;

public class ImageModel {
    private int id;
    private String name;
    private String url;

    // Getters and Setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getUrl() {
        // Ensure the URL uses the correct base path
        if (!url.startsWith("http")) {
            return "https://android-backend-hi5z.onrender.com" + url; // Use correct base URL
        }
        return url;
    }
    public void setUrl(String url) { this.url = url; }

    @Override
    public String toString() {
        return "ImageModel{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
