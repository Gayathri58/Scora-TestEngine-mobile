package com.brigita.dashboard.pika.retrofit;

import com.brigita.dashboard.pika.authentication.model.ForgotPasswordRequest;
import com.brigita.dashboard.pika.authentication.model.ForgotPasswordResponse;
import com.brigita.dashboard.pika.authentication.model.SignInRequest;
import com.brigita.dashboard.pika.authentication.model.SignInResponse;
import com.brigita.dashboard.pika.authentication.model.UserDetailsResponse;
import com.brigita.dashboard.pika.dashboard.model.TestDetailsDrilResponse;
import com.brigita.dashboard.pika.dashboard.model.PieChartResponse;
import com.brigita.dashboard.pika.dashboard.model.TestGroupDrillResponse;
import com.brigita.dashboard.pika.dashboard.model.TestPerformanceDrillResponse;
import com.brigita.dashboard.pika.dashboard.model.TestRecentActivityDrillResponse;
import com.brigita.dashboard.pika.performance_insights.model.DifficultyLevelResponse;
import com.brigita.dashboard.pika.performance_insights.model.StrongWeakAreaResponse;
import com.brigita.dashboard.pika.performance_insights.model.TaxonomyLevelResponse;
import com.brigita.dashboard.pika.performance_insights.model.TestAttemptResponse;
import com.brigita.dashboard.pika.performance_insights.model.TestNameResponse;
import com.brigita.dashboard.pika.performance_insights.model.TestScoreBoardResponse;
import com.brigita.dashboard.pika.performance_insights.model.TopicLevelResponse;
import com.brigita.dashboard.pika.scheduled_tests.model.ScheduledTestResponse;
import com.brigita.dashboard.pika.take_test.model.TakeTestRequest;
import com.brigita.dashboard.pika.take_test.model.TakeTestResponse;
import com.brigita.dashboard.pika.test_details.TestDetailsRequest;
import com.brigita.dashboard.pika.test_details.TestDetailsResponse;
import com.brigita.dashboard.pika.test_history.areachart.model.AreaChartResponse;
import com.brigita.dashboard.pika.test_history.model.TestHistoryResponse;
import com.brigita.dashboard.pika.test_instruction.model.InstructionRequest;
import com.brigita.dashboard.pika.test_instruction.model.InstructionResponse;
import com.google.gson.JsonObject;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;


/*
 * This interface for declaring all the
 * requests
 */

public interface RetrofitService {

    @POST("signin")
    Call<SignInResponse> signIn(@Body SignInRequest signInRequest);

    @GET("api/user_details")
    Call<UserDetailsResponse> getUserDetails();

    @POST("forget_password")
    Call<ForgotPasswordResponse> forgetPassword(@Body ForgotPasswordRequest forgotPasswordRequest);

    @GET("http://pikauser.brigita.co/pikauser/public/api/test_details/{org_id}")
    Call<ScheduledTestResponse> getScheduledTests(@Path("org_id") int org_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/user_reports/{org_id}")
    Call<PieChartResponse> gettestsummary(@Path("org_id") int org_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/test_history/{org_id}")
    Call<TestHistoryResponse> getdashBoardTestHistory(@Path("org_id") int org_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/scoreboard/{org_id}/{test_id}/{current_attempt}/{schedule_id}")
    Call<AreaChartResponse> getAreaChart(@Path("org_id") int org_id, @Path("test_id") int test_id, @Path("current_attempt") int current_attempt, @Path("schedule_id") int schedule_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/test_details_drill/{org_id}/{test_status}")
    Call<List<TestDetailsDrilResponse>> getTestDetailsDrill(@Path("org_id") int org_id, @Path("test_status") String test_status);

    @GET("http://pikauser.brigita.co/pikauser/public/api/test_grp_drill/{org_id}/{grp_id}")
    Call<List<TestGroupDrillResponse>> getTestGroupDrill(@Path("org_id") int org_id, @Path("grp_id") Object grp_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/test_instance_details/{org_id}/{test_id}")
    Call<List<TestPerformanceDrillResponse>> getTestPerformanceDrill(@Path("org_id") int org_id, @Path("test_id") Object test_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/activity_log_drill/{org_id}/{test_instance_id}")
    Call<List<TestRecentActivityDrillResponse>> getRecentActivityDrill(@Path("org_id") int org_id, @Path("test_instance_id") Object test_instance_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/test_drop_down/{org_id}")
    Call<List<TestNameResponse>> getTestnameRes(@Path("org_id") int org_id);

    @GET("http://pikauser.brigita.co/pikauser/public/api/attempt_drop_down/{org_id}/{test_id}")
    Call<TestAttemptResponse> getTestAttemptRes(@Path("org_id") int org_id, @Path("test_id") int test_id);

    @GET(" http://pikauser.brigita.co/pikauser/public/api/difficulty_level_details/{org_id}/{test_id}/{current_attempt}")
    Call<DifficultyLevelResponse> getDifficultyLevel(@Path("org_id") int org_id, @Path("test_id") int test_id, @Path("current_attempt") int current_attempt);

    @GET("http://pikauser.brigita.co/pikauser/public/api/taxonomy_details/{org_id}/{test_id}/{current_attempt}")
    Call<TaxonomyLevelResponse> getTaxonomyLevel(@Path("org_id") int org_id, @Path("test_id") int test_id, @Path("current_attempt") int current_attempt);

    @GET("http://pikauser.brigita.co/pikauser/public/api/topic_details/{org_id}/{test_id}/{current_attempt}")
    Call<TopicLevelResponse> getTopicLevel(@Path("org_id") int org_id, @Path("test_id") int test_id, @Path("current_attempt") int current_attempt);

    @GET("http://pikauser.brigita.co/pikauser/public/api/strong_weak_details/{org_id}/{test_id}/{current_attempt}")
    Call<StrongWeakAreaResponse> getStrongWeakArea(@Path("org_id") int org_id, @Path("test_id") int test_id, @Path("current_attempt") int current_attempt);

    @GET("http://pikauser.brigita.co/pikauser/public/api/test_score_board/{org_id}/{test_id}/{current_attempt}")
    Call<List<TestScoreBoardResponse>> getTestScoreBoard(@Path("org_id") int org_id, @Path("test_id") int test_id, @Path("current_attempt") int current_attempt);

    @POST("http://pikatestengine.brigita.co/testengine/public/api/test_instructions")
    Call<InstructionResponse> getInstrutionText (@Body InstructionRequest instructionRequest);

    @POST("http://pikatestengine.brigita.co/testengine/public/api/take_tests")
    Call<TestDetailsResponse> testDetails (@Body TestDetailsRequest testDetailsRequest ) ;

    @POST("http://pikatestengine.brigita.co/testengine/public/api/take_tests")
    Call<TakeTestResponse> takeTest (@Body JsonObject jsonObject) ;

    @POST("http://192.168.1.20/testengine/public/api/take_tests")
    Call<TakeTestResponse> testDetails (@Body TakeTestRequest takeTestRequest ) ;


}