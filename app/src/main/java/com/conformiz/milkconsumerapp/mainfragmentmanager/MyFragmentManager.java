package com.conformiz.milkconsumerapp.mainfragmentmanager;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.conformiz.milkconsumerapp.R;
import com.conformiz.milkconsumerapp.callbacks.OnItemClick;
import com.conformiz.milkconsumerapp.fragments.ClientPlansFragment;
import com.conformiz.milkconsumerapp.fragments.DashboardFragment;
import com.conformiz.milkconsumerapp.fragments.ComplaintsFragment;
import com.conformiz.milkconsumerapp.fragments.DeliveriesRecordFragment;
import com.conformiz.milkconsumerapp.fragments.ProductsFragment;
import com.conformiz.milkconsumerapp.fragments.SettingsFragment;
import com.conformiz.milkconsumerapp.fragments.paymentfragments.PaymentFragment;
import com.conformiz.milkconsumerapp.utils.Constants;

import java.util.EmptyStackException;
import java.util.Stack;

/**
 * Created by Fahad.Munir on 11-Apr-17.
 */

public class MyFragmentManager implements Constants, OnItemClick {

    private String TAG = "MyFragmentManager ";
    private static MyFragmentManager mInstance;
    AppCompatActivity mContext;
    Stack<Fragment> mHomeTabStack;
    DashboardFragment homeFragment;

   // DashboardChatFragment mDashboardChatFragment;
    //DashboardMessagesFragment messageFragment;
    //Settings settingsFragment;
    //DashboardMoreFragment moreFragment;
    int selectedTab = 1 ;//TAB_HOME;

    private MyFragmentManager() {
    }

    public static MyFragmentManager getInstance() {
        if (mInstance == null)
            mInstance = new MyFragmentManager();
        return mInstance;
    }

    public DashboardFragment getHomeFragment() {
        return homeFragment;
    }

    public Stack<Fragment> getHomeTabStack() {
        return mHomeTabStack;
    }

    public void initDashboardFragments(AppCompatActivity pContext) {
        mContext = pContext;
        mHomeTabStack = new Stack<Fragment>();

        homeFragment = new DashboardFragment();
        homeFragment.setOnItemClick(this);
        addFragment(homeFragment);


//        mDashboardChatFragment = new DashboardChatFragment();
//        mDashboardChatFragment.setOnItemClick(this);
//
////        messageFragment = new DashboardMessagesFragment();
////        messageFragment.setOnItemClick(this);
//
//        settingsFragment = new Settings();
//        settingsFragment.setOnItemClick(this);
//
//        moreFragment = new DashboardMoreFragment();
//        moreFragment.setOnItemClick(this);
    }

    @Override
    public void onClick(int pPosition, int id) {

        FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
        switch (pPosition) {




            case R.id.rl_manage_order:
                Log.i(TAG, "onClick: on manage order click");
                ProductsFragment productsFragment = new ProductsFragment();
                addFragment(productsFragment);
                transaction.replace(R.id.fragment_container,productsFragment);

//                ClientPlansFragment clientPlansFragment = new ClientPlansFragment();
//                addFragment(clientPlansFragment);
//                transaction.replace(R.id.fragment_container,clientPlansFragment);


               // ChangePasswordFragment changePassword = new ChangePasswordFragment();
               // addFragment(changePassword);
               // transaction.replace(R.id.fragment_container_dashboard, changePassword);

                break;

            case R.id.rl_deliveries_history:
                Log.i(TAG, "onClick: on DELEVIER order click");
                DeliveriesRecordFragment deliveriesRecordFragment = new DeliveriesRecordFragment();
                addFragment(deliveriesRecordFragment);
                transaction.replace(R.id.fragment_container,deliveriesRecordFragment);

                // ChangePasswordFragment changePassword = new ChangePasswordFragment();
               // addFragment(changePassword);
               // transaction.replace(R.id.fragment_container_dashboard, changePassword);

                break;

            case R.id.rl_payments:
                Log.i(TAG, "onClick: on Payments  click");

                PaymentFragment paymentFragment = new PaymentFragment();
                addFragment(paymentFragment);
                transaction.replace(R.id.fragment_container,paymentFragment);

                break;

            case R.id.rl_complaints:
                Log.i(TAG, "onClick: on rl_complaints  click");

                ComplaintsFragment complaintsFragment = new ComplaintsFragment();
                addFragment(complaintsFragment);
                transaction.replace(R.id.fragment_container,complaintsFragment);

                break;

            case R.id.rl_settings:
                Log.i(TAG, "onClick: on rl_settings  click");
                SettingsFragment settingsFragment = new SettingsFragment();
                addFragment(settingsFragment);
                transaction.replace(R.id.fragment_container,settingsFragment);
               // ChangePasswordFragment changePassword = new ChangePasswordFragment();
               // addFragment(changePassword);
               // transaction.replace(R.id.fragment_container_dashboard, changePassword);

                break;


//
//
//            case R.id.rl_more_settings_auto_lock:
//                AutoLogoutTime autoLogoutTime = new AutoLogoutTime(mContext);
//
//                break;
//
//
//            case R.id.rl_more_settings_notification:
//
//                SharedPreferenceUtil.getInstance(mContext).saveEnableBackgroundLogout(false);
//
//                Log.i("Permission", "NOt Granted");
//
//                if (Build.VERSION.SDK_INT >= 16) {
//                    Intent intent = new Intent();
//                    intent.setAction("android.settings.APP_NOTIFICATION_SETTINGS");
//                    intent.putExtra("app_package", mContext.getPackageName());
//                    intent.putExtra("app_uid", mContext.getApplicationInfo().uid);
//                    mContext.startActivity(intent);
//
//                } else {
//
//                }
//
//
//                int permissionCheck = ContextCompat.checkSelfPermission(mContext,
//                        Manifest.permission.ACCESS_NOTIFICATION_POLICY);
//                if (ContextCompat.checkSelfPermission(mContext, Manifest.permission.ACCESS_NOTIFICATION_POLICY)
//                        != PackageManager.PERMISSION_GRANTED
//                        ) {
////                    if(!NotificationManagerCompat.from(getActivity()).areNotificationsEnabled()) {
////
////                    }
////                } else {
////
////                    Log.i("permission","Granted");
//////
//////                    ActivityCompat.requestPermissions(getActivity(),
//////                            new String[]{Manifest.permission.ACCESS_NOTIFICATION_POLICY},
//////                            Constants.GET_NOTIFICATION_PERMISSION_CODE);
////
////                    notificationText.setText("YES");
//                }
//
//                break;
//            case R.id.rl_temp_logout:
//                SharedPreferenceUtil.getInstance(mContext).clearCache();
//                Intent splash = new Intent(mContext, AuthenticateWithMRN.class);
//                splash.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_SINGLE_TOP);
//                mContext.startActivity(splash);
//                mContext.finish();
//                break;
//
//
//            // Not Using Below Code
////            case R.id.rl_more_settings:
////                DashboardMoreSettingsFragment showSettings = new DashboardMoreSettingsFragment();
////                addFragment(showSettings);
////                transaction.replace(R.id.fragment_container_dashboard, showSettings);
////                break;
//
//
//            case R.id.rl_more_privacy:
//                SharedPreferenceUtil.getInstance(mContext).saveEnableBackgroundLogout(false);
//                Uri uriUrl = Uri.parse(Constants.PRIVACY_POLICY_URL);
//                Intent launchBrowser = new Intent(Intent.ACTION_VIEW, uriUrl);
//                mContext.startActivity(launchBrowser);
//                break;
//
//            //Start+
/////////////////////////////////////////////////////////////////
//            case R.id.rl_more_profile_detail:
//                // Demographic fragment goes
//                DemographicFragment demographic = new DemographicFragment();
//                addFragment(demographic);
//                transaction.replace(R.id.fragment_container_dashboard, demographic);
//                break;
//
//            case R.id.rl_dashboard_clinical:
//                DashboardClinicalFragment clinical = new DashboardClinicalFragment();
//                clinical.setOnItemClick(this);
//                addFragment(clinical);
//                transaction.replace(R.id.fragment_container_dashboard, clinical);
//                break;
//
//            case R.id.rl_dashboard_goals_and_tasks:
//                TasksAndGoalsFragment tasksAndGoalsFragment = new TasksAndGoalsFragment();
//                tasksAndGoalsFragment.setOnItemClick(this);
//                addFragment(tasksAndGoalsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, tasksAndGoalsFragment);
//                break;
//
////            case R.id.tv_dashboard_view_all:
////            case R.id.iv_dashboard_arrow_right:
//            case R.id.rl_dashboard_appointments:
//                AppointmentsFragment appointmentsFragment = new AppointmentsFragment();
//                addFragment(appointmentsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, appointmentsFragment);
//                break;
//
//            case R.id.rl_dashboard_messages:
//                DashboardChatFragment dashboardChatFragment = new DashboardChatFragment();
//                dashboardChatFragment.setIsBackButtonVisible(true);
//                addFragment(dashboardChatFragment);
//                transaction.replace(R.id.fragment_container_dashboard, dashboardChatFragment, "DashboardChatFragment");
//                break;
//
//            case R.id.rl_more_supplies_new:
//                SuppliesFragment supplies = new SuppliesFragment();
//                addFragment(supplies);
//                transaction.replace(R.id.fragment_container_dashboard, supplies);
//                break;
//
//            case R.id.rl_more_assessments_new:
//                AssessmentFragment assessmentFragment = new AssessmentFragment();
//                addFragment(assessmentFragment);
//                transaction.replace(R.id.fragment_container_dashboard, assessmentFragment);
////                AssessmentsDashboardFragment assessmentsDashboardFragment = new AssessmentsDashboardFragment();
////                addFragment(assessmentsDashboardFragment);
////                transaction.replace(R.id.fragment_container_dashboard, assessmentsDashboardFragment);
//                break;
//
//            case R.id.rl_more_notes:
//                NotesFragment notesFragment = new NotesFragment();
//                addFragment(notesFragment);
//                transaction.replace(R.id.fragment_container_dashboard, notesFragment);
//                break;
//
//            case R.id.rl_more_ct:
//                CareTeamFragment careTeamFragment = new CareTeamFragment();
//                addFragment(careTeamFragment);
//                transaction.replace(R.id.fragment_container_dashboard, careTeamFragment);
//                break;
//            case R.id.rl_more_cp:
//                CarePlanFragment carePlanFragment = new CarePlanFragment();
//                addFragment(carePlanFragment);
//                transaction.replace(R.id.fragment_container_dashboard, carePlanFragment);
//                break;
//
//            case R.id.rl_more_insurance_new:
//                InsuranceFragment insurance = new InsuranceFragment();
//                addFragment(insurance);
//                transaction.replace(R.id.fragment_container_dashboard, insurance);
//                break;
//
//            // End
//            //////////////////////////////////////////
//            case R.id.rl_other_medical_tasks:
//                InterventionsFragment interventions = new InterventionsFragment();
//                addFragment(interventions);
//                transaction.replace(R.id.fragment_container_dashboard, interventions);
//                break;
//
//            case R.id.rl_other_medical_goals:
//                GoalsFragment goalsFragment = new GoalsFragment();
//                addFragment(goalsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, goalsFragment);
//                break;
//            case R.id.rl_clinical_diagnosis:
//                DiagnosisFragment diagnosisFragment = new DiagnosisFragment();
//                addFragment(diagnosisFragment);
//                transaction.replace(R.id.fragment_container_dashboard, diagnosisFragment);
//                break;
//            case R.id.rl_clinical_medication:
//                MedicationsFragment medicationFragment = new MedicationsFragment();
//                addFragment(medicationFragment);
//                transaction.replace(R.id.fragment_container_dashboard, medicationFragment);
//                break;
//            case R.id.rl_clinical_labresults:
//                // case R.id.rl_dashboard_lab:
//                LabResultsFragment labResultsFragment = new LabResultsFragment();
//                addFragment(labResultsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, labResultsFragment);
//                break;
//            case R.id.rl_clinical_vitals:
////            case R.id.rl_dashboard_vitals:
//                VitalsFragment vitalsFragment = new VitalsFragment();
//                addFragment(vitalsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, vitalsFragment);
//                break;
//
////                DashboardMessagesFragment dashboardMessagesFragment = new DashboardMessagesFragment();
////                addFragment(dashboardMessagesFragment);
////                transaction.replace(R.id.fragment_container_dashboard, dashboardMessagesFragment);
////                break;
//            case R.id.rl_clinical_vaccines:
//                VaccinesFragment vaccinesFragment = new VaccinesFragment();
//                addFragment(vaccinesFragment);
//                transaction.replace(R.id.fragment_container_dashboard, vaccinesFragment);
//                break;
//            case R.id.rl_clinical_visits:
//                VisitsFragment visitsFragment = new VisitsFragment();
//                addFragment(visitsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, visitsFragment);
//                break;
//            case R.id.rl_clinical_procedures:
//                ProcedureFragment procedureFragment = new ProcedureFragment();
//                addFragment(procedureFragment);
//                transaction.replace(R.id.fragment_container_dashboard, procedureFragment);
//                break;
//            case R.id.rl_clinical_allergies:
//                AllergiesFragment allergiesFragment = new AllergiesFragment();
//                addFragment(allergiesFragment);
//                transaction.replace(R.id.fragment_container_dashboard, allergiesFragment);
//                break;
//
////            case R.id.rl_dashboard_demographics:
////                DemographicFragment demographic = new DemographicFragment();
////                addFragment(demographic);
////                transaction.replace(R.id.fragment_container_dashboard, demographic);
////                break;
//            case R.id.rl_clinical_familyhistory:
//                FamilyHistoryFragment familyFragment = new FamilyHistoryFragment();
//                addFragment(familyFragment);
//                transaction.replace(R.id.fragment_container_dashboard, familyFragment);
//                break;
//            case R.id.rl_clinical_socialhistory:
//                SocialHistoryFragment socialHistoryFragment = new SocialHistoryFragment();
//                addFragment(socialHistoryFragment);
//                transaction.replace(R.id.fragment_container_dashboard, socialHistoryFragment);
//                break;
//
//
//            case R.id.rl_messages_inbox:
//                InboxFragment inbox = new InboxFragment();
//                addFragment(inbox);
//                transaction.replace(R.id.fragment_container_dashboard, inbox);
//                break;
//            case R.id.rl_messages_sent:
//                InboxFragment sent = new InboxFragment();
//                addFragment(sent);
//                transaction.replace(R.id.fragment_container_dashboard, sent);
//                sent.setIsInbox(false);
//                break;
//            case R.id.rl_messages_compose:
//                ComposeMessageFragment compose = new ComposeMessageFragment();
//                addFragment(compose);
//                transaction.replace(R.id.fragment_container_dashboard, compose);
//                break;
//            case R.id.rl_messages_outbox:
//                OutboxFragment outbox = new OutboxFragment();
//                addFragment(outbox);
//                transaction.replace(R.id.fragment_container_dashboard, outbox);
//                break;
//            case R.id.rl_messages_drafts:
//                DraftsFragment drafts = new DraftsFragment();
//                addFragment(drafts);
//                transaction.replace(R.id.fragment_container_dashboard, drafts);
//                break;
//
//
//            case 0:
//                AlertsFragment alertFragment = new AlertsFragment();
//                //addFragment(alertFragment);
//                addFragment(alertFragment);
//                transaction.replace(R.id.fragment_container_dashboard, alertFragment);
//                break;
//            case 4:
////                AssessmentFragment assessmentFragment = new AssessmentFragment();
////                addFragment(assessmentFragment);
////                transaction.replace(R.id.fragment_container_dashboard, assessmentFragment);
//                break;
//            case 6:
//                InterventionsFragment interventionsFragment = new InterventionsFragment();
//                addFragment(interventionsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, interventionsFragment);
//                break;
        }
        transaction.commit();
    }

    public Fragment switchTab(Fragment currentFragment, int pSwitchedTab) {
//        if (selectedTab == pSwitchedTab)
//            return currentFragment;

        FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
        selectedTab = pSwitchedTab;
        mHomeTabStack.clear();
        switch (pSwitchedTab) {
//            case TAB_HOME:
//                addFragment(homeFragment);
//                transaction.replace(R.id.fragment_container_dashboard, homeFragment);
//                transaction.commit();
//                return homeFragment;
//
//            case TAB_MESSAGE:
//
//                mDashboardChatFragment.setRetainInstance(true);
//                mDashboardChatFragment.setIsBackButtonVisible(false);
//                addFragment(mDashboardChatFragment);
//                transaction.replace(R.id.fragment_container_dashboard, mDashboardChatFragment);
//                transaction.commit();
//                return mDashboardChatFragment;
////                addFragment(messageFragment);
////                transaction.replace(R.id.fragment_container_dashboard, messageFragment);
////                transaction.commit();
////                return messageFragment;
//            case TAB_SETTINGS:
//                addFragment(settingsFragment);
//                transaction.replace(R.id.fragment_container_dashboard, settingsFragment);
//                transaction.commit();
//                return settingsFragment;
//            case TAB_MORE:
//                addFragment(moreFragment);
//                transaction.replace(R.id.fragment_container_dashboard, moreFragment);
//                transaction.commit();
//                return moreFragment;
        }
        return currentFragment;
    }

    public void addFragment(Fragment pFragment) {
        mHomeTabStack.push(pFragment);
    }

    public Fragment popFragment() throws EmptyStackException {
        FragmentTransaction transaction = mContext.getSupportFragmentManager().beginTransaction();
        mHomeTabStack.pop();
        Fragment fragment = mHomeTabStack.peek();
        if (fragment != null) {
            transaction.replace(R.id.fragment_container, fragment);
            transaction.commit();
        }
        return fragment;
    }

    public boolean isStackEmpty() {
        return mHomeTabStack.isEmpty();
    }
}
