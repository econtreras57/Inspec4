package com.example.presentation.utils;

public class Constants {


    public static final int DB = 1;     //2020-01-27 ecv
    public static final int CLOUD = 0;  //2020-01-27 ecv

    public static final String EXTRA_MESSAGE =
            "com.example.presentation.utils";

    public static final int REQUEST_IMAGE_CAPTURE = 128;    // 2020-02-19 ecv: para la foto
    public static final int REQUEST_TAKE_PHOTO = 135;       // 2020-03-30 ecv: para la foto
    public static final int MY_PERMISSIONS_REQUEST_CAMERA = 64;    // 2020-02-19 ecv: permiso...

    public static final Boolean SWITCH_BUTTON_ON = true;
    public static final Boolean SWITCH_BUTTON_OFF = false;



    public class PREFERENCES {
        public static final String PREFERENCE_CURRENT_USER = "PREFERENCE_CURRENT_USER";
    }

    public class PREFERENCES_KEYS {
        public static final String CURRENT_USER_ID = "CURRENT_USER_LOGGED";
        public static final String CURRENT_USER_IDCLOUD = "CURRENT_USER_LOGGED";
        public static final String CURRENT_USER_NAME = "CURRENT_USER_LOGGED";
        public static final String CURRENT_USER_PHONE= "CURRENT_USER_LOGGED";
        public static final String CURRENT_USER_LOGGED = "CURRENT_USER_LOGGED";
    }

    public class FRAGMENTS_TABS {
        public static final int HOME = 0;
        public static final int LOST = 1;
        public static final int ABUSE = 2;
        public static final int ACCOUNT = 3;
    }

    public class FIREBASE_TABLES {
        public static final String USER = "users";
        public static final String LOST = "lost";
        public static final String PET = "pet";
        public static final String ABUSE = "abuse"; //2020-01-23 ecv
    }

    public class FIREBASE_TABLES_FIELDS {
        public static final String USER_id = "id";
        public static final String USER_idCloud = "idCloud";
        public static final String USER_uid = "uid";
        public static final String USER_name = "name";
        public static final String USER_phoneNumber = "phoneNumber";
        public static final String USER_email = "email";
        public static final String USER_location = "location";
        public static final String USER_logged = "logged";
        public static final String USER_active = "active";
        public static final String USER_created_at = "created_at";
        public static final String USER_notifications = "notifications";

        public static final String LOST_id = "id";
        public static final String LOST_idCloud = "idCloud";
        public static final String LOST_petName = "petName";
        public static final String LOST_race = "race";
        public static final String LOST_gender = "gender";
        public static final String LOST_color = "color";
        public static final String LOST_age = "age";
        public static final String LOST_contactPhoneNumber = "contactPhoneNumber";
        public static final String LOST_contactName = "contactName";
        public static final String LOST_description = "description";
        public static final String LOST_reward = "reward";
        public static final String LOST_rewardAmount = "rewardAmount";
        public static final String LOST_country = "country";
        public static final String LOST_state = "state";
        public static final String LOST_city = "city";
        public static final String LOST_urlImage = "urlImage";
        public static final String LOST_lat = "lat";
        public static final String LOST_lng = "lng";
        public static final String LOST_lostAddress = "lostAddress";
        public static final String LOST_found = "found";


    }

}



