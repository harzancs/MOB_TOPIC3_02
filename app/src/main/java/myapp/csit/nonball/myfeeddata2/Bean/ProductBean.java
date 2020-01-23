package myapp.csit.nonball.myfeeddata2.Bean;

public class ProductBean {

    /**
     * id : 1
     * id_product : csit01
     * name : IPhoneXL
     * detail : Black Camera 28MP
     * price : 30000
     * image_url : https://store.ais.co.th/media/catalog/product/i/p/iphone7plus-black-pureangles_2_4.jpg
     */

    private String id;
    private String id_product;
    private String name;
    private String detail;
    private String price;
    private String image_url;

    public static final String BASE_URL="http://10.112.2.110/api/";

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getId_product() {
        return id_product;
    }

    public void setId_product(String id_product) {
        this.id_product = id_product;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }
}
