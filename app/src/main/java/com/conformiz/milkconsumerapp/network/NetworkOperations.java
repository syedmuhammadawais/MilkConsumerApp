package com.conformiz.milkconsumerapp.network;


import android.content.Context;

import com.conformiz.milkconsumerapp.models.response.AllProductsRootResponse;
import com.conformiz.milkconsumerapp.models.response.ClientPlansRootResponse;
import com.conformiz.milkconsumerapp.models.response.ComplaintsReasonsRootResponse;
import com.conformiz.milkconsumerapp.models.response.PreferredTimeListRootResponse;
import com.conformiz.milkconsumerapp.models.response.ZoneListRootResponse;
import com.conformiz.milkconsumerapp.models.response.questions.QuestionsRootResponse;
import com.conformiz.milkconsumerapp.models.response.teachersdata.TeachersDataRootResponse;
import com.conformiz.milkconsumerapp.utils.Constants;
import com.google.gson.Gson;

import org.json.JSONObject;


/**
 * Created by Talha.Hafeez on 5/14/2015.
 */
public class NetworkOperations implements Constants {

    private static NetworkOperations mInstance;

    private NetworkOperations() {
    }

    public static NetworkOperations getInstance() {
        if (mInstance == null)
            mInstance = new NetworkOperations();
        return mInstance;
    }


    public void getProductsList(Context pContext, INetworkListener<AllProductsRootResponse> pNetworkListener) {

        IRequest request = new GetRequest(pContext, ACTION_GET_ALL_PRODUCT, null);
        NetworkThread<AllProductsRootResponse> networkThread = new NetworkThread<AllProductsRootResponse>(pContext, AllProductsRootResponse.class, pNetworkListener);
        networkThread.execute(request);
    }


    public void getZoneList(Context pContext, INetworkListener<ZoneListRootResponse> pNetworkListener){
        IRequest request = new GetRequest(pContext, ACTION_GET_ZONE_LIST, null);
        NetworkThread<ZoneListRootResponse> networkThread = new NetworkThread<ZoneListRootResponse>(pContext, ZoneListRootResponse.class, pNetworkListener);
        networkThread.execute(request);
    }

    public void getPreferredTimeList(Context pContext, INetworkListener<PreferredTimeListRootResponse> pNetworkListener) {

        IRequest request = new GetRequest(pContext, ACTION_GET_PREFERRED_TIME_LIST, null);
        NetworkThread<PreferredTimeListRootResponse> networkThread = new NetworkThread<PreferredTimeListRootResponse>(pContext, PreferredTimeListRootResponse.class, pNetworkListener);
        networkThread.execute(request);
    }




    public void getClientPlansList(Context pContext, INetworkListener<ClientPlansRootResponse> pNetworkListener) {

        IRequest request = new GetRequest(pContext, ACTION_GET_CLIENT_PLANS, null);
        NetworkThread<ClientPlansRootResponse> networkThread = new NetworkThread<ClientPlansRootResponse>(pContext, ClientPlansRootResponse.class, pNetworkListener);
        networkThread.execute(request);

    }

    public void getComplaintsReasons(Context pContext, INetworkListener<ComplaintsReasonsRootResponse> pNetworkListener){

        IRequest request = new GetRequest(pContext, ACTION_GET_COMPLAINT_REASONS,null);
        NetworkThread<ComplaintsReasonsRootResponse> networkThread = new NetworkThread<ComplaintsReasonsRootResponse>(pContext, ComplaintsReasonsRootResponse.class,pNetworkListener);
        networkThread.execute(request);
    }


    public void getQuestionsData(Context pContext, INetworkListener<QuestionsRootResponse> pNetworkListener) {

       // IRequest request = new GetRequest(pContext, ACTION_GET_QUESTIONS_LIST, null);
        //NetworkThread<QuestionsRootResponse> networkThread = new NetworkThread<QuestionsRootResponse>(pContext, QuestionsRootResponse.class, pNetworkListener);
      //  networkThread.execute(request);
    }


    public void getTeachersData(Context pContext, INetworkListener<TeachersDataRootResponse> pNetworkListener) {

        IRequest request = new GetRequest(pContext, ACTION_GET_TEACHERS_LIST, null);
        NetworkThread<TeachersDataRootResponse> networkThread = new NetworkThread<TeachersDataRootResponse>(pContext, TeachersDataRootResponse.class, pNetworkListener);
        networkThread.execute(request);
    }


//    public void saveQuestionsData(Context pContext, Object requestDTO, INetworkListener<SaveDataResponse> pNetworkListener,TestNetwork<T> ref) {
//
//        Gson gson = new Gson();
//        String data = gson.toJson(requestDTO);
//
//        IRequest request = new PostRequest(pContext, ACTION_SAVE_QUESTIONS, data);
//        NetworkThread<SaveDataResponse> networkThread = new NetworkThread<SaveDataResponse>(pContext, ref, pNetworkListener);
//
//        //  NetworkThread<QuestionsRootResponse> networkThread = new NetworkThread<QuestionsRootResponse>(pContext, QuestionsRootResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }

//
//
//    public <T> void getData(Context pContext, String action, String params, INetworkListener pNetworkListener, Class<T> ref) {
//
//        IRequest request = new GetRequest(pContext, action, params);
//        NetworkThread<T> networkThread = new NetworkThread<T>(pContext, ref, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public <T> void getData(Context pContext, String action, INetworkListener pNetworkListener, Class<T> ref) {
//        getData(pContext, action, getPatientJSON(pContext), pNetworkListener, ref);
//    }

    public <T> void postData(Context pContext, String action , Object requestDTO, INetworkListener pNetworkListener, Class<T> ref) {


        Gson gson = new Gson();
        String data  = "";
        if(requestDTO instanceof JSONObject){
            data = requestDTO.toString();
        } else {
            data = gson.toJson(requestDTO);
        }

        IRequest request = new PostRequest(pContext, action, data);
        NetworkThread<T> networkThread = new NetworkThread<T>(pContext, ref, pNetworkListener);
        networkThread.execute(request);
    }

//
//    public <T> void putData(Context pContext, String action, Object requestDTO, INetworkListener pNetworkListener, Class<T> ref) {
//
//        Gson gson = new Gson();
//        String data = gson.toJson(requestDTO);
//
//        IRequest request = new PutRequest(pContext, action, data);
//        NetworkThread<T> networkThread = new NetworkThread<T>(pContext, ref, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public <T> void deleteData(Context pContext, String action, INetworkListener pNetworkListener, Class<T> ref) {
//
//        IRequest request = new DeleteRequest(pContext, action);
//        NetworkThread<T> networkThread = new NetworkThread<T>(pContext, ref, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public <T> void deleteDataWithBody(Context pContext, String action, INetworkListener pNetworkListener, Class<T> ref) {
//
//        IRequest request = new DeleteBodyRequest(pContext, action);
//        NetworkThread<T> networkThread = new NetworkThread<T>(pContext, ref, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public <T> void deleteDataWithJsonBody(Context pContext, String action, Object requestDTO, INetworkListener pNetworkListener, Class<T> ref) {
//
//        Gson gson = new Gson();
//        String data = gson.toJson(requestDTO);
//
//        IRequest request = new DeleteBodyRequest(pContext, action, data);
//        NetworkThread<T> networkThread = new NetworkThread<T>(pContext, ref, pNetworkListener);
//        networkThread.execute(request);
//    }


//    public void getClinicalActiveDiagnosisList(Context pContext, INetworkListener<ClinicalActiveDiagnosisRoot> pNetworkListener) {
//
//        IRequest request = new PostRequest(pContext, null, ACTION_CLINICAL_ACTIVE_DIAGNOSIS);
//        NetworkThread<ClinicalActiveDiagnosisRoot> networkThread = new NetworkThread<ClinicalActiveDiagnosisRoot>(pContext, ClinicalActiveDiagnosisRoot.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void authenticateMRN(Context pContext, RequestDTO requestDTO, INetworkListener<EmptyResponse> pNetworkListener) {
//
//        JSONObject data = new JSONObject();
//        try {
//            data.put("mrnNumber", requestDTO.getMRNNumber());
//            data.put("emailId", requestDTO.getEmailId());
//            data.put("deviceId", requestDTO.getDeviceId());
//            data.put("enterpriseId", SharedPreferenceUtil.getInstance(pContext).getEnterpriseId());
//            data.put("practiceId", 1);
//
//        } catch (Exception e) {
//        }
//
//        IRequest request = new PostRequest(pContext, ACTION_AUTHENTICATE_MRN, data.toString());
//        NetworkThread<EmptyResponse> networkThread = new NetworkThread<EmptyResponse>(pContext, EmptyResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void authenticateUser(Context pContext, RequestDTO requestDTO, INetworkListener<UserLoginResponse> pNetworkListener) {
//
//        JSONObject data = new JSONObject();
//        try {
//            data.put("username", requestDTO.getUsername());
//            data.put("password", requestDTO.getPassword());
//            data.put("deviceId", requestDTO.getDeviceId());
//            data.put("enterpriseId", SharedPreferenceUtil.getInstance(pContext).getEnterpriseId());
//            data.put("practiceId", -1);
//
//        } catch (Exception e) {
//        }
//
//        IRequest request = new PostRequest(pContext, ACTION_USER_LOGIN, data.toString());
//        NetworkThread<UserLoginResponse> networkThread = new NetworkThread<UserLoginResponse>(pContext, UserLoginResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void authenticateCode(Context pContext, RequestDTO requestDTO, INetworkListener<VerificationCodeResponse> pNetworkListener) {
//
//        JSONObject data = new JSONObject();
//        try {
//            data.put("mrnNumber", requestDTO.getMRNNumber());
//            data.put("pinCode", requestDTO.getPinCode());
//            data.put("deviceId", requestDTO.getDeviceId());
//            data.put("enterpriseId", SharedPreferenceUtil.getInstance(pContext).getEnterpriseId());
//            data.put("practiceId", 1);
//
//        } catch (Exception e) {
//        }
//
//        IRequest request = new PostRequest(pContext, ACTION_AUTHENTICATE_CODE, data.toString());
//        NetworkThread<VerificationCodeResponse> networkThread = new NetworkThread<VerificationCodeResponse>(pContext, VerificationCodeResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getAlerts(Context pContext, String patientKey, INetworkListener<AlertsResponse> pNetworkListener) {
//
//        IRequest request = new PostRequest(pContext, ACTION_GET_ALERTS, getPatientJSON(pContext, patientKey));
//        NetworkThread<AlertsResponse> networkThread = new NetworkThread<AlertsResponse>(pContext, AlertsResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getVitals(Context pContext, String patientKey, INetworkListener<VitalsResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_VITALS, getPatientJSON(pContext, patientKey));
//        NetworkThread<VitalsResponse> networkThread = new NetworkThread<VitalsResponse>(pContext, VitalsResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getChatHistory(Context pContext, String patientKey, String conditional, INetworkListener<ChatHistoryResponse> pNetworkListener) {
//        IRequest request = new GetRequest(pContext, conditional, null);
//        NetworkThread<ChatHistoryResponse> networkThread = new NetworkThread<ChatHistoryResponse>(pContext, ChatHistoryResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getChatHistoryUnreadMsgs(Context pContext, String patientKey, String conditional, INetworkListener<ChatHistoryUnreadMessageResponse> pNetworkListener) {
//        IRequest request = new GetRequest(pContext, conditional, null);
//        NetworkThread<ChatHistoryUnreadMessageResponse> networkThread = new NetworkThread<ChatHistoryUnreadMessageResponse>(pContext, ChatHistoryUnreadMessageResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getUnreadCountChatMessages(Context pContext, String patientKey, String conditional, INetworkListener<UnreadMessageCountResponse> pNetworkListener) {
//        IRequest request = new GetRequest(pContext, conditional, null);
//        NetworkThread<UnreadMessageCountResponse> networkThread = new NetworkThread<UnreadMessageCountResponse>(pContext, UnreadMessageCountResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getChatMessagesSeenStatus(Context pContext, String patientKey, String conditional, INetworkListener<ChatMessageSeenResponse> pNetworkListener) {
//        IRequest request = new GetRequest(pContext, conditional, null);
//        NetworkThread<ChatMessageSeenResponse> networkThread = new NetworkThread<ChatMessageSeenResponse>(pContext, ChatMessageSeenResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//
//    public void getMedications(Context pContext, String patientKey, INetworkListener<MedicationResponse> pNetworkListener) {
//        IRequest request = new GetRequest(pContext, ACTION_GET_MEDICATIONS, getPatientJSON(pContext, patientKey));
//        NetworkThread<MedicationResponse> networkThread = new NetworkThread<MedicationResponse>(pContext, MedicationResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//
//    public void getMedicationSingleObject(Context pContext, String patientKey, INetworkListener<MedicationResponseSingleObject> pNetworkListener) {
//        IRequest request = new GetRequest(pContext, ACTION_GET_MEDICATIONS, getPatientJSON(pContext, patientKey));
//        NetworkThread<MedicationResponseSingleObject> networkThread = new NetworkThread<MedicationResponseSingleObject>(pContext, MedicationResponseSingleObject.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getVisits(Context pContext, String patientKey, INetworkListener<VisitsResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_VISITS, getPatientJSON(pContext, patientKey));
//        NetworkThread<VisitsResponse> networkThread = new NetworkThread<VisitsResponse>(pContext, VisitsResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getPatientVisits(Context pContext, INetworkListener<PatientVisitsRoot> pNetworkListener) {
//
//        IRequest request = new PostRequest(pContext, null, ACTION_PATIENT_VISITS);
//        NetworkThread<PatientVisitsRoot> networkThread = new NetworkThread<PatientVisitsRoot>(pContext, PatientVisitsRoot.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getInsurance(Context pContext, String patientKey, INetworkListener<InsuranceResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_INSURANCE, patientKey);
//        NetworkThread<InsuranceResponse> networkThread = new NetworkThread<InsuranceResponse>(pContext, InsuranceResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getProcedure(Context pContext, String patientKey, INetworkListener<ProcedureResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_PROCEDURE, getPatientJSON(pContext, patientKey));
//        NetworkThread<ProcedureResponse> networkThread = new NetworkThread<ProcedureResponse>(pContext, ProcedureResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getSocialHistory(Context pContext, String patientKey, INetworkListener<SocialHistoryResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_SOCIAL_HISTORY, getPatientJSON(pContext, patientKey));
//        NetworkThread<SocialHistoryResponse> networkThread = new NetworkThread<SocialHistoryResponse>(pContext, SocialHistoryResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getFamilyHistory(Context pContext, String patientKey, INetworkListener<FamilyHistoryResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_FAMILY_HISTORY, getPatientJSON(pContext, patientKey));
//        NetworkThread<FamilyHistoryResponse> networkThread = new NetworkThread<FamilyHistoryResponse>(pContext, FamilyHistoryResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getLabResults(Context pContext, String patientKey, INetworkListener<LabResultsResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_LAB_RESULTS, getPatientJSON(pContext, patientKey));
//        NetworkThread<LabResultsResponse> networkThread = new NetworkThread<LabResultsResponse>(pContext, LabResultsResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getVaccines(Context pContext, String patientKey, INetworkListener<VaccinesResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_VACCINES, getPatientJSON(pContext, patientKey));
//        NetworkThread<VaccinesResponse> networkThread = new NetworkThread<VaccinesResponse>(pContext, VaccinesResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getAllergies(Context pContext, String patientKey, INetworkListener<AllergiesResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_ALLERGIES, getPatientJSON(pContext, patientKey));
//        NetworkThread<AllergiesResponse> networkThread = new NetworkThread<AllergiesResponse>(pContext, AllergiesResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getProfile(Context pContext, String patientKey, INetworkListener<ProfileResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_PROFILE, getPatientJSON(pContext, patientKey));
//        NetworkThread<ProfileResponse> networkThread = new NetworkThread<ProfileResponse>(pContext, ProfileResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getCareTeam(Context pContext, String patientKey, INetworkListener<CareTeamResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_CARE_TEAM, getPatientJSON(pContext, patientKey));
//        NetworkThread<CareTeamResponse> networkThread = new NetworkThread<CareTeamResponse>(pContext, CareTeamResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getDiagnosis(Context pContext, String patientKey, INetworkListener<DiagnosisResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_DIAGNOSIS, getPatientJSON(pContext, patientKey));
//        NetworkThread<DiagnosisResponse> networkThread = new NetworkThread<DiagnosisResponse>(pContext, DiagnosisResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getCarePlan(Context pContext, String patientKey, INetworkListener<CarePlanResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_CARE_PLAN, patientKey);
//        NetworkThread<CarePlanResponse> networkThread = new NetworkThread<CarePlanResponse>(pContext, CarePlanResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
////    public void getCarePlanDetail(Context pContext, CarePlanRequest carePlan, INetworkListener<CarePlanDetailResponse> pNetworkListener) {
////
////        String params = carePlan.getPatientKey() + "/" + carePlan.getCarePlanId() + "/Existing";
////
////        IRequest request = new GetRequest(pContext, ACTION_GET_CARE_PLAN_DETAIL, params);
////        NetworkThread<CarePlanDetailResponse> networkThread = new NetworkThread<CarePlanDetailResponse>(pContext, CarePlanDetailResponse.class, pNetworkListener);
////        networkThread.execute(request);
////    }
//
//
//    public void getCarePlanDetail(Context pContext, CarePlanRequest carePlan, INetworkListener<CarePlanResponse_> pNetworkListener) {
//
//        String params = carePlan.getPatientKey() + "/" + carePlan.getCarePlanId() + "/Existing";
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_CARE_PLAN_DETAIL, params);
//        NetworkThread<CarePlanResponse_> networkThread = new NetworkThread<CarePlanResponse_>(pContext, CarePlanResponse_.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//
//    public void getAssessment(Context pContext, String patientKey, INetworkListener<AssessmentTemplateMainEntity> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_ASSESSMENT + "/" + patientKey, null);
//        NetworkThread<AssessmentTemplateMainEntity> networkThread = new NetworkThread<AssessmentTemplateMainEntity>(pContext, AssessmentTemplateMainEntity.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getGoals(Context pContext, String patientKey, INetworkListener<GoalsResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_GOALS, SharedPreferenceUtil.getInstance(pContext).getEnterpriseId() + "/" + patientKey);
//        NetworkThread<GoalsResponse> networkThread = new NetworkThread<GoalsResponse>(pContext, GoalsResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getInterventions(Context pContext, String patientKey, INetworkListener<InterventionsResponse> pNetworkListener) {
//        IRequest request = new GetRequest(pContext, ACTION_GET_INTERVENTIONS, SharedPreferenceUtil.getInstance(pContext).getEnterpriseId() + "/" + patientKey);
//        NetworkThread<InterventionsResponse> networkThread = new NetworkThread<InterventionsResponse>(pContext, InterventionsResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getAppointments(Context pContext, String patientKey, INetworkListener<AppointmentsResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_APPOINTMENTS, patientKey);
//        NetworkThread<AppointmentsResponse> networkThread = new NetworkThread<AppointmentsResponse>(pContext, AppointmentsResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getMessages(Context pContext, String patientKey, INetworkListener<MessagesResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_MESSAGES, patientKey);
//        NetworkThread<MessagesResponse> networkThread = new NetworkThread<MessagesResponse>(pContext, MessagesResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getSentMessages(Context pContext, String patientKey, INetworkListener<MessagesResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_MESSAGES_SENT, patientKey);
//        NetworkThread<MessagesResponse> networkThread = new NetworkThread<MessagesResponse>(pContext, MessagesResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void postMessage(Context pContext, OutboxDTO outbox, INetworkListener<ComposeMessageResponse> pNetworkListener) {
//        JSONObject data = new JSONObject();
//        try {
//            data.put("patientKey", outbox.getPatientKey());
//            data.put("subject", outbox.getSubject());
//            data.put("message", outbox.getMessage());
//        } catch (Exception e) {
//        }
//        IRequest request = new PostRequest(pContext, ACTION_POST_MESSAGES, data.toString());
//        NetworkThread<ComposeMessageResponse> networkThread = new NetworkThread<ComposeMessageResponse>(pContext, ComposeMessageResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void replyMessage(Context pContext, OutboxDTO outbox, INetworkListener<ComposeMessageResponse> pNetworkListener) {
//        JSONObject data = new JSONObject();
//        try {
//            data.put("patientKey", outbox.getPatientKey());
//            data.put("patientMessageId", outbox.getMessageId());
//            data.put("subject", outbox.getSubject());
//            data.put("message", outbox.getMessage());
//        } catch (Exception e) {
//        }
//        IRequest request = new PutRequest(pContext, ACTION_REPLY_MESSAGES + "/" + outbox.getMessageId(), data.toString());
//        NetworkThread<ComposeMessageResponse> networkThread = new NetworkThread<ComposeMessageResponse>(pContext, ComposeMessageResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void getGeoFences(Context pContext, INetworkListener<GeoFenceResponse> pNetworkListener) {
//
//        IRequest request = new GetRequest(pContext, ACTION_GET_GEOFENCES, null);
//        NetworkThread<GeoFenceResponse> networkThread = new NetworkThread<GeoFenceResponse>(pContext, GeoFenceResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    public void postGeoFenceLog(Context pContext, GeoFenceLogRequest geoRequest, INetworkListener<EmptyResponse> pNetworkListener) {
//        JSONObject data = new JSONObject();
//        try {
//            data.put("patientKey", geoRequest.getPatientKey());
//            data.put("hospitalId", geoRequest.getHospitalId());
//        } catch (Exception e) {
//        }
//        IRequest request = new PostRequest(pContext, ACTION_REPLY_MESSAGES, data.toString());
//        NetworkThread<EmptyResponse> networkThread = new NetworkThread<EmptyResponse>(pContext, EmptyResponse.class, pNetworkListener);
//        networkThread.execute(request);
//    }
//
//    private String getPatientJSON(Context pContext, String patientKey) {
//        String params = SharedPreferenceUtil.getInstance(pContext).getEnterpriseId() + "/-1/" + patientKey;
//
//        return params;
//    }
//
//    private String getPatientJSON(Context pContext) {
//        String patientKey = SharedPreferenceUtil.getInstance(pContext).getPatientKey();
//        return getPatientJSON(pContext, patientKey + "");
//    }

}
