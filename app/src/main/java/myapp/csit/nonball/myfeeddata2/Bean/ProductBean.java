package myapp.csit.nonball.myfeeddata2.Bean;

import android.os.Parcel;
import android.os.Parcelable;

public class ProductBean implements Parcelable {

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


    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(this.id);
        dest.writeString(this.id_product);
        dest.writeString(this.name);
        dest.writeString(this.detail);
        dest.writeString(this.price);
        dest.writeString(this.image_url);
    }

    public ProductBean() {
    }

    protected ProductBean(Parcel in) {
        this.id = in.readString();
        this.id_product = in.readString();
        this.name = in.readString();
        this.detail = in.readString();
        this.price = in.readString();
        this.image_url = in.readString();
    }

    public static final Parcelable.Creator<ProductBean> CREATOR = new Parcelable.Creator<ProductBean>() {
        @Override
        public ProductBean createFromParcel(Parcel source) {
            return new ProductBean(source);
        }

        @Override
        public ProductBean[] newArray(int size) {
            return new ProductBean[size];
        }
    };
}
