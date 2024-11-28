package helper;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class AddressAPI {
    private HashMap<String, String> mapCity, mapDistrict, mapWard;
    private String codeCity, codeDistrict, codeWard;
    private String nameCity, nameDistrict, nameWard;

    public AddressAPI() {
        super();
        loadDataCity();
    }

    public AddressAPI(String nameCity, String nameDistrict, String nameWard) {
        this.nameCity = nameCity;
        this.nameDistrict = nameDistrict;
        this.nameWard = nameWard;

        loadDataCity();
        setCodeCity(nameCity);
        loadDataDistrict();
        setCodeDistrict(nameDistrict);
        loadDataWard();
        setCodeWard(nameWard);
    }

    public String getCodeCity() {
        return codeCity;
    }

    public void setCodeCity(String keyCity) {
        this.codeCity = mapCity.get(keyCity);
    }


    public String getCodeDistrict() {
        return codeDistrict;
    }

    public void setCodeDistrict(String keyDistrict) {
        this.codeDistrict = mapDistrict.get(keyDistrict);
    }

    public String getCodeWard() {
        return codeWard;
    }

    public void setCodeWard(String keyWard) {
        this.codeWard = mapWard.get(keyWard);
    }

    public void loadDataCity() {
        OkHttpClient client = new OkHttpClient();

        // Địa chỉ API
        String url = "https://esgoo.net/api-tinhthanh/1/0.htm";

        // Gửi yêu cầu HTTP GET
        Request request = new Request.Builder().url(url).build();

        try {
            // Gửi yêu cầu và nhận phản hồi
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Lấy dữ liệu JSON từ phản hồi
                String jsonResponse = response.body().string();
                // Chuyển đổi dữ liệu JSON thành JSONObject
                JSONObject jsonObject = new JSONObject(jsonResponse);

                // Tạo HashMap để lưu trữ dữ liệu
                mapCity = new HashMap<>();

                // Lấy mảng dữ liệu từ JSON
                JSONArray provinces = jsonObject.getJSONArray("data");

                // Duyệt qua mảng và thêm dữ liệu vào HashMap
                for (int i = 0; i < provinces.length(); i++) {
                    JSONObject province = provinces.getJSONObject(i);
                    String id = province.getString("id");
                    String name = province.getString("full_name");
                    mapCity.put(name, id); // Thêm id và name vào HashMap
                }

            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataDistrict() {
        OkHttpClient client = new OkHttpClient();

        // Địa chỉ API
        String url = "https://esgoo.net/api-tinhthanh/2/" + codeCity + ".htm";

        // Gửi yêu cầu HTTP GET
        Request request = new Request.Builder().url(url).build();

        try {
            // Gửi yêu cầu và nhận phản hồi
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Lấy dữ liệu JSON từ phản hồi
                String jsonResponse = response.body().string();
                // Chuyển đổi dữ liệu JSON thành JSONObject
                JSONObject jsonObject = new JSONObject(jsonResponse);

                // Tạo HashMap để lưu trữ dữ liệu
                mapDistrict = new HashMap<>();

                // Lấy mảng dữ liệu từ JSON
                JSONArray districts = jsonObject.getJSONArray("data");

                // Duyệt qua mảng và thêm dữ liệu vào HashMap
                for (int i = 0; i < districts.length(); i++) {
                    JSONObject district = districts.getJSONObject(i);
                    String id = district.getString("id");
                    String name = district.getString("full_name");
                    mapDistrict.put(name, id); // Thêm id và name vào HashMap
                }

            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void loadDataWard() {
        OkHttpClient client = new OkHttpClient();

        // Địa chỉ API
        String url = "https://esgoo.net/api-tinhthanh/3/" + codeDistrict + ".htm";

        // Gửi yêu cầu HTTP GET
        Request request = new Request.Builder().url(url).build();

        try {
            // Gửi yêu cầu và nhận phản hồi
            Response response = client.newCall(request).execute();
            if (response.isSuccessful()) {
                // Lấy dữ liệu JSON từ phản hồi
                String jsonResponse = response.body().string();
                // Chuyển đổi dữ liệu JSON thành JSONObject
                JSONObject jsonObject = new JSONObject(jsonResponse);

                // Tạo HashMap để lưu trữ dữ liệu
                mapWard = new HashMap<>();

                // Lấy mảng dữ liệu từ JSON
                JSONArray wards = jsonObject.getJSONArray("data");

                // Duyệt qua mảng và thêm dữ liệu vào HashMap
                for (int i = 0; i < wards.length(); i++) {
                    JSONObject ward = wards.getJSONObject(i);
                    String id = ward.getString("id");
                    String name = ward.getString("full_name");
                    mapWard.put(name, id); // Thêm id và name vào HashMap
                }

            } else {
                System.out.println("Request failed: " + response.code());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String[] getMapString(HashMap<String, String> map) {
        return map.keySet().toArray(new String[0]);
    }

    public static String[] sortData(String[] data, String defaultData) {
        Arrays.sort(data);

        ArrayList<String> sortedDataValue = new ArrayList<>(Arrays.asList(data));
        sortedDataValue.add(0, defaultData);

        String[] resultValue = new String[sortedDataValue.size()];
        sortedDataValue.toArray(resultValue);

        return resultValue;
    }

    public String[] getAllCity() {
        return sortData(getMapString(mapCity), "Chọn Tỉnh/Thành phố");
    }

    public String[] getAllDistrict() {
        loadDataDistrict();
        return sortData(getMapString(mapDistrict), "Chọn Quận/Huyện");
    }

    public String[] getAllWard() {
        loadDataWard();
        return sortData(getMapString(mapWard), "Chọn Phường/Xã");
    }

    public static void main(String[] args) {
        AddressAPI service = new AddressAPI();
    }
}
