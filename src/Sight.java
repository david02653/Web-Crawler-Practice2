public class Sight implements java.io.Serializable {
    private String sightName = null;
    private String zone = null;
    private String category = null;
    private String photoURL = null;
    private String description = null;
    private String address = null;
    private String url = null;
    private String googleMapUrl = null;

    public Sight(){}

    public void setSightName(final String sightName) {
        this.sightName = sightName;
    }

    public void setZone(final String zone) {
        this.zone = zone;
    }

    public void setCategory(final String category) {
        this.category = category;
    }

    public void setPhotoURL(final String photoURL) {
        this.photoURL = photoURL;
    }

    public void setDescription(final String description) {
        this.description = description;
    }

    public void setAddress(final String address) {
        this.address = address;
    }

    public void setUrl(final String url){
        this.url = url;
    }

    public void setGoogleMapUrl(final String googleMapUrl){
        this.googleMapUrl = googleMapUrl;
    }

    public String getSightName(){
        return sightName;
    }

    public String getZone() {
        return zone;
    }

    public String getCategory() {
        return category;
    }

    public String getPhotoURL() {
        return photoURL;
    }

    public String getDescription() {
        return description;
    }

    public String getAddress() {
        return address;
    }

    public String getUrl() {
        return url;
    }

    public String getGoogleMapUrl(){
        return googleMapUrl;
    }

    public String toString(){
        String info = "SightName: ";
        info = info.concat(sightName + "\r\nZone: ");
        info = info.concat(zone + "\r\nCategory: ");
        info = info.concat(category + "\r\nPhotoURL: ");
        info = info.concat(photoURL + "\r\nDescription: ");
        info = info.concat(description + "\r\nAddress: ");
        info = info.concat(address + "\r\nUrl: ");
        info = info.concat(url + "\r\nGoogleMapUrl: ");
        info = info.concat(googleMapUrl + "\r\n");

        return info;
    }

    public void show(){
        System.out.print("SightName = " + sightName + "; Zone = " + zone + "; Category = " + category + "; PhotoURL = " + photoURL + "; Description = " + description + "; Address =  " + address + "; Url = " + url);
    }
}
