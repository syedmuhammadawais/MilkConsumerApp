package com.conformiz.milkconsumerapp.utils;


public interface Constants {


   // String URL_BASE_URL = "http://192.168.10.8/milk/api/";
    String URL_BASE_URL = "http://tazafarms.conformiz.com/index.php/api/";
    String KEY_PREF_NAME = "milk_consumer";
    String KEY_SECRET = "encrypted_secret";
    String KEY_ACCESS = "access_id";

    String ACTION_POST_ALL_PRODUCT = "getAllProduct";
    String ACTION_GET_TEACHERS_LIST = "getTeacherList";
    String ACTION_GET_PREFERRED_TIME_LIST = "getPreferredTimeList";

    String ACTION_SAVE_QUESTIONS = "SaveQuestionList";
    String ACTION_GET_CLIENT_PLANS = "getAllPlan";
    String ACTION_GET_COMPLAINT_REASONS = "getComplainType";
    String ACTION_GET_ZONE_LIST = "getZoneList";

    String ACTION_GET_PROFILE_INFO = "getCustomerProfile";
    String ACTION_GET_RECOVER_PASSWORD = "customerForgetPassword";
    String ACTION_GET_CUSTOMER_COMPLAINTS = "getCustomerComplains";

    String ACTION_POST_SAVE_COMPLAINT = "createComplaints";
    String ACTION_POST_SAVE_UPDATE_WEEKLY_PLAN = "updateWeeklySchedual";
    String ACTION_POST_CREATE_SPECIAL_ORDER = "createSpecialOrder";
    String ACTION_POST_CANCEL_SPECIAL_ORDER = "cancelSpecialOrder";
    String ACTION_POST_CUSTOMER_SIGN_UP = "customerSignUP";
    String ACTION_POST_CUSTOMER_AUTHENTICATION = "customerAuthentication";
    String ACTION_POST_RESEND_CODE = "resendCode";
    String ACTION_POST_ACTIVATION_WITH_CODE = "customerActivationByCodeAndPhoneNo";
    String ACTION_POST_DELIVERIES_BETWEEN_DATE_RANGE = "deliverybetweenDateRange";
    String ACTION_POST_CUSTOMER_ACCOUNT_BALANCE = "checkAccountBalnce";
    String ACTION_POST_CHANGE_CUSTOMER_PASSWORD = "changeCustomerPassword";
    String ACTION_POST_UPDATE_CUSTOMER_PROFILE = "UpdateCustomerProfile";

    String ACTION_POST_PAUSE_REGULAR_ORDERS = "haltRegularOrders";
    String ACTION_POST_CANCEL_REGULAR_ORDERS = "cancelRegularOrder";
    String ACTION_POST_RESUME_REGULAR_ORDERS = "resumedRegularOrder";

    String ACTION_POST_CANCEL_SPECIAL_ORDERS = "cancelSpecialOrder";


    // Inter Pay URls
    String INTERPAY_BASE_URL = "https://test.interpayafrica.com/interapi/";
    String ACTION_POST_PROCESS_PAYMENT = "ProcessPayment";
    String ACTION_POST_CONFIRM_PAYMENT = "confirmpayment";
    String ACTION_POST_PAYMENT_COMPLETE = "paymentMethod";

    String ACTION_POST_CHECK_USER_AVAILABLE = "checkClientAvailability";
    String ACTION_POST_LOGIN = "Login";
    String ACTION_POST_WEEKLY_SCHEDULE = "getWeeklySchedule";
    String ACTION_POST_SMS_ALERT_SETTINGS = "MessageAlertPermission";
    String ACTION_POST_SAVE_SMS_ALERT_SETTINGS = "saveMessageAlertPermission";


    String CLIENT_ID = "client_id";
    String KEEP_ME_SIGN = "keep_me_sign";
    String SHOW_CHECK_BOX = "show_check_box";


    int ON_CLICK_PRODUCT = 100;
    int ON_CLICK_PRODUCT_INFO = 200;

    String APPOINTMENT_BUSY_SLOTS_COLOR = "blue";
    String BUSY_SLOTS_COLOR = "gray";

    public  String  UPDATE_WEEKLY_PLAN = "2";
    public  String ADD_SPECIAL_ORDER = "1";
    public  String ADD_NEW_WEEKLY_PLAN = "0";



}
