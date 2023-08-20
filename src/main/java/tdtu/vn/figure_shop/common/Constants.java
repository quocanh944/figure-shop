package tdtu.vn.figure_shop.common;

import org.springframework.stereotype.Service;

@Service
public class Constants {
    public static final String FB_SDK_JSON = "example.json"; // Your firebase private json in resource folder

    public static final String STORAGE_ID = "<<bucket>>.appspot.com"; // Storage id <<bucket_name>>

    public static final String FB_PROJECT_ID = ""; // Firebase project id

    public static final String FB_ACCESS_TOKEN = ""; // Token to access file

    public static final String FB_STORAGE_URL = "firebasestorage.googleapis.com/v0/b/" + STORAGE_ID + "/o/";

}
