package com.brigita.dashboard.pika.take_test;

import android.annotation.SuppressLint;
import android.app.ProgressDialog;
import android.content.DialogInterface;
import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.CardView;
import android.text.Html;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.QuestionResponse;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.databinding.FragentQuestionsDialogBinding;
import com.brigita.dashboard.pika.databinding.FragmentTakeTestBinding;
import com.brigita.dashboard.pika.retrofit.RetrofitService;
import com.brigita.dashboard.pika.retrofit.RetrofitServiceGenerator;
import com.brigita.dashboard.pika.take_test.model.Item;
import com.brigita.dashboard.pika.take_test.model.ItemsetSection;
import com.brigita.dashboard.pika.take_test.model.Palette_Model;
import com.brigita.dashboard.pika.take_test.model.SectionAttribute;
import com.brigita.dashboard.pika.take_test.model.SectionItem;
import com.brigita.dashboard.pika.take_test.model.TakeTestResponse;
import com.bumptech.glide.Glide;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import retrofit2.Callback;
import retrofit2.Response;

import static com.brigita.dashboard.pika.R.id;
import static com.brigita.dashboard.pika.R.layout;
import static com.brigita.dashboard.pika.R.string;

/**
 * A simple {@link Fragment} subclass.
 */
public class TakeTestFragment extends Fragment {

   FragmentTakeTestBinding binding;
    ProgressBar progressBar;

    private static final String TAG = TakeTestFragment.class.getSimpleName();

    TextView txtTimer, txtQuestionPage;

    long countTimer, lngsec;

    String timetosec;

    CountDownTimer countDownTimer, dialogTimer;

    boolean isTimerPaused = false, isFinishConfirmed = false, needRemainingTime = true;


    ArrayList<String> sectionItem;
    ArrayList<Item> questioncountarray;
    ArrayList<Palette_Model> strQuestion;
    ArrayList<String> qa = new ArrayList<>();
    ArrayList<String> qnoPalette = new ArrayList<>();

    ProgressDialog dialog ;

    Palette_Model palette_model ;

    LinearLayout questionsview;

    Spinner sectionSpinner;

    List<ItemsetSection> itemsetSectionList;
    List<SectionAttribute> sectionAttributeList;
    List<SectionItem> sectionItems;
    List<Item> itemList;

    TextView submitTv;

    ImageView orgIconTakeTest;

    public TakeTestFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(inflater, layout.fragment_take_test, container, false);

        orgIconTakeTest = binding.orgIconTakeTest.findViewById(id.org_icon_take_test);

        submitTv = binding.submitTvTaketest.findViewById(id.submit_tv_taketest);

        questionsview = binding.directiveSpecId.findViewById(id.directiveSpecId);

        sectionItem = new ArrayList<>();
        strQuestion = new ArrayList<Palette_Model>();
        ArrayList<Palette_Model> strQuestion;

        InitializeData();

        fetchdata();

        return binding.getRoot();

    }

    private void InitializeData() {

        txtTimer = binding.timerColId.findViewById(id.timerColId);

    }

    public void fetchdata() {

        RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

        JsonObject gsonObject = new JsonObject();
        JsonObject paramObject = new JsonObject();
        JsonObject current_section = new JsonObject();

        paramObject.addProperty("test_id", Const.test_id_instruction_str);
        paramObject.addProperty("user_id", 12);
        paramObject.addProperty("org_id", Const.org_id_instruction);
        paramObject.addProperty("schedule_id", Const.schedule_id_instruction);
        paramObject.addProperty("next_section_id", "");
        paramObject.addProperty("device_type", 2);
        paramObject.addProperty("group_id", "null");
        paramObject.addProperty("current_section", String.valueOf(current_section));

        JsonParser jsonParser = new JsonParser();
        gsonObject = (JsonObject) jsonParser.parse(paramObject.toString());

        retrofit2.Call<TakeTestResponse> userCall = retrofitService.takeTest(gsonObject);
        userCall.enqueue(new Callback<TakeTestResponse>() {
            @Override
            public void onResponse(retrofit2.Call<TakeTestResponse> call, Response<TakeTestResponse> response) {

                if (response.isSuccessful()) {

                    TakeTestResponse takeTestResponse = response.body();

                    Log.e(TAG, takeTestResponse.orgName);

                    itemsetSectionList = takeTestResponse.getItemsetSections();
                    sectionAttributeList = takeTestResponse.getSectionAttributes();

                    String url = takeTestResponse.getOrgLogo();

                   // getquestionsView();

                    Glide.with(getContext()).load(url).into(orgIconTakeTest);

                    submitTv.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            confirmAndFinish();
                        }
                    });

                    binding.timerColId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            pauseAndResume();
                        }
                    });

                    binding.selectQuestionId.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {

                            questionPalette();

                        }
                    });

                    sectionSpinner = binding.sectionsId.findViewById(id.sectionsId);
                    sectionSpinner.getBackground().setColorFilter(getResources().getColor(R.color.whit), PorterDuff.Mode.SRC_ATOP);

                    for (int i = 0; i < sectionAttributeList.size(); i++) {

                        //  txtQuestionPage.setText(Const.currentpage + "/" + sectionAttributeList.get(i).getItemCount());
                        sectionItem.add(sectionAttributeList.get(i).getSectionName());
                        Valuesfromserver();
                    }

                    Log.e(TAG, response.message());

                    binding.progressBarTest.setVisibility(View.GONE);

                } else {

                    Log.e("fail", response.message());

                }
            }

            @Override
            public void onFailure(retrofit2.Call<TakeTestResponse> call, Throwable t) {

            }
        });
    }

    private void Valuesfromserver() {

        ArrayAdapter aa = new ArrayAdapter(getContext(), R.layout.layout_coustom_spinner, sectionItem);
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        sectionSpinner.setAdapter(aa);


    }

    private void startCountDownTimer() {
        Log.d("Timer_value", String.valueOf(countTimer));
        countDownTimer = new CountDownTimer(countTimer * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                countTimer = millisUntilFinished;
                String time = String.format("%02d:%02d:%02d", TimeUnit.MILLISECONDS.toHours(countTimer), TimeUnit.MILLISECONDS.toMinutes(countTimer) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(countTimer)), TimeUnit.MILLISECONDS.toSeconds(countTimer) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(countTimer)));
                Log.e("time:", time);
                txtTimer.setText(time);
            }

            @Override
            public void onFinish() {
                txtTimer.setText("TIME UP");
                //     needRemainingTime = false;
                getActivity().finish();
            }
        }.start();
        //  testEventCapturing("CountDown Timer Started");
    }

    private void pauseAndResume() {
        if (!isTimerPaused) {
            timetosec = txtTimer.getText().toString();
            String hour = timetosec.substring(0, 2);
            String mintus = timetosec.substring(3, 5);
            String sconds = timetosec.substring(6, 8);

            lngsec = (Long.parseLong(hour) * 3600) + (Long.parseLong(mintus) * 60) + Long.parseLong(sconds);
            pausingTestDialog();

        } else if (isTimerPaused) {
            startCountDownTimer();
            isTimerPaused = false;
        }
    }

    private void pausingTestDialog() {
//     testEventCapturing("pause");
//     dataEventCapture("pause", questFragment.getCurrentPageNum());

        countDownTimer.cancel();
        isTimerPaused = true;
        txtTimer.setText("Pause");
        txtTimer.setGravity(Gravity.CENTER_HORIZONTAL);
        AlertDialog.Builder pauseConfirmationBuilder = new AlertDialog.Builder(getContext());
        pauseConfirmationBuilder.setTitle("PAUSE CONFIRMATION");
        pauseConfirmationBuilder.setMessage(string.pause_confirmation);
        pauseConfirmationBuilder.setPositiveButton("PAUSE", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //  testEventCapturing("pause confirmed");
                isFinishConfirmed = true;
                getActivity().finish();
            }
        });

        pauseConfirmationBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                countTimer = lngsec;
                startCountDownTimer();
                isTimerPaused = false;
                dialogTimer.cancel();
            }
        });
        pauseConfirmationBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                countTimer = lngsec;
                startCountDownTimer();
                isTimerPaused = false;
            }
        });
        final AlertDialog alertDialog = pauseConfirmationBuilder.create();
        alertDialog.show();
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        dialogTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long dialogSeconds) {
                alertDialog.setTitle("PAUSE CONFIRMATION in 00:" + "0" + (dialogSeconds / 1000));

            }

            @Override
            public void onFinish() {
                alertDialog.cancel();
            }
        }.start();
    }


    public void confirmAndFinish() {
        // dataEventCapture("submit", questFragment.getCurrentPageNum());
       // countDownTimer.cancel();
        isTimerPaused = true;
        txtTimer.setText("Pause");
        txtTimer.setGravity(Gravity.CENTER_HORIZONTAL);
        AlertDialog.Builder confirmationBuilder = new AlertDialog.Builder(getContext());
        confirmationBuilder.setTitle("SUBMIT CONFIRMATION");
        confirmationBuilder.setMessage(string.submit_confirmation);
        confirmationBuilder.setPositiveButton("SUBMIT", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //    testEventCapturing("submit confirmed");
                // dataEventCapture("submit_confirm", questFragment.getCurrentPageNum());
                isFinishConfirmed = true;
                needRemainingTime = false;
                getActivity().finish();
            }
        });
        confirmationBuilder.setNegativeButton("CANCEL", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                countTimer = lngsec;
                startCountDownTimer();
                isTimerPaused = false;
                dialogTimer.cancel();
            }
        });
        confirmationBuilder.setOnCancelListener(new DialogInterface.OnCancelListener() {
            @Override
            public void onCancel(DialogInterface dialogInterface) {
                countTimer = lngsec;
                startCountDownTimer();
                isTimerPaused = false;
            }
        });
        final AlertDialog alertDialog = confirmationBuilder.create();
        alertDialog.show();
        WindowManager.LayoutParams lp = alertDialog.getWindow().getAttributes();
        lp.dimAmount = 0.8f;
        alertDialog.getWindow().setAttributes(lp);
        alertDialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_DIM_BEHIND);
        /**
         *  timer dialog
         */
        dialogTimer = new CountDownTimer(10000, 1000) {

            @Override
            public void onTick(long dialogSeconds) {
                alertDialog.setTitle("SUBMIT CONFIRMATION in 00:" + (dialogSeconds / 1000));
            }

            @Override
            public void onFinish() {
                alertDialog.cancel();
            }
        }.start();
    }

    public static class QuestionsDialogFragment extends DialogFragment {

        List<SectionAttribute> getSectionAttribute;
        List<Item> getItem;
        List<ItemsetSection> getItemsetSection;
        List<SectionItem> getSection;


        LinearLayout allQuestions;

        Palette_Model palette_model;

        ArrayList<Palette_Model> strQuestion;
        ArrayList<String> sectionItem;
        QuestionResponse questionResponse;

        QuestionsDialogFragment newInstance() {

            return new QuestionsDialogFragment();
        }

        FragentQuestionsDialogBinding fragentQuestionsDialogBinding;

        @Override
        public View onCreateView(final LayoutInflater inflater, final ViewGroup container, Bundle savedInstanceState) {

            getDialog().getWindow().requestFeature(Window.FEATURE_NO_TITLE);

            fragentQuestionsDialogBinding = DataBindingUtil.inflate(inflater, layout.fragent_questions_dialog, container, false);


            getQuestionPalette();

            fragentQuestionsDialogBinding.closeOnlyQuestion.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    dismiss();

                }
            });

            fragentQuestionsDialogBinding.onlyQuestionPalette.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    getQuestionPalette();

                    allQuestions = fragentQuestionsDialogBinding.linerQuestion.findViewById(R.id.linerQuestion);
                    fragentQuestionsDialogBinding.rules.setVisibility(View.GONE);
                    fragentQuestionsDialogBinding.questionPaletteFilter.setVisibility(View.VISIBLE);
                    fragentQuestionsDialogBinding.onlyQuestionPalette.setVisibility(View.GONE);
                    fragentQuestionsDialogBinding.linerQuestion.setVisibility(View.VISIBLE);

                    getquestions();

                    fragentQuestionsDialogBinding.filterLayoutId.setVisibility(View.GONE);
                    // getQuestionPalette();

                    strQuestion.clear();

                    if (Const.QuestionVal == 0) {

                        Const.QuestionVal++;
                    }

                }
            });

            fragentQuestionsDialogBinding.instructionTvPalette.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragentQuestionsDialogBinding.rules.setVisibility(View.VISIBLE);
                    fragentQuestionsDialogBinding.linerQuestion.setVisibility(View.GONE);
                    getInstruction();
                    fragentQuestionsDialogBinding.filterLayoutId.setVisibility(View.GONE);

                }
            });

            fragentQuestionsDialogBinding.questionPaletteFilter.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    fragentQuestionsDialogBinding.onlyQuestionPalette.setVisibility(View.VISIBLE);
                    fragentQuestionsDialogBinding.questionPaletteFilter.setVisibility(View.GONE);
                    fragentQuestionsDialogBinding.linerQuestion.setVisibility(View.GONE);
                    fragentQuestionsDialogBinding.filterLayoutId.setVisibility(View.VISIBLE);

                }
            });

            strQuestion = new ArrayList<Palette_Model>();
            sectionItem = new ArrayList<>();

            return fragentQuestionsDialogBinding.getRoot();

        }


        @SuppressLint("ResourceType")
        private void getQuestionPalette() {

            RetrofitService retrofitService = RetrofitServiceGenerator.getRetrofitOauthClient(getContext());

            JsonObject gsonObject = new JsonObject();
            JsonObject paramObject = new JsonObject();
            JsonObject current_section = new JsonObject();

            paramObject.addProperty("test_id", Const.test_id_instruction_str);
            paramObject.addProperty("user_id", 12);
            paramObject.addProperty("org_id", Const.org_id_instruction);
            paramObject.addProperty("schedule_id", Const.schedule_id_instruction);
            paramObject.addProperty("next_section_id", "");
            paramObject.addProperty("device_type", 2);
            paramObject.addProperty("group_id", "null");
            paramObject.addProperty("current_section", String.valueOf(current_section));

            JsonParser jsonParser = new JsonParser();
            gsonObject = (JsonObject) jsonParser.parse(paramObject.toString());

            retrofit2.Call<TakeTestResponse> userCall = retrofitService.takeTest(gsonObject);
            userCall.enqueue(new Callback<TakeTestResponse>() {
                @Override
                public void onResponse(retrofit2.Call<TakeTestResponse> call, Response<TakeTestResponse> response) {

                    if (response.isSuccessful()) {

                        TakeTestResponse takeTestResponse = response.body();

                        Log.e(TAG, takeTestResponse.orgName);

                        Log.e(TAG, response.message());

                        getItemsetSection = takeTestResponse.getItemsetSections();
                        getSectionAttribute = takeTestResponse.getSectionAttributes();



                    } else {

                        Log.e("fail", response.message());


                    }
                }

                @Override
                public void onFailure(retrofit2.Call<TakeTestResponse> call, Throwable t) {

                }
            });


        }

        @SuppressLint("ResourceType")

        private void getquestions() {

            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.WRAP_CONTENT);

            allQuestions.setOrientation(LinearLayout.VERTICAL);

            for (int i = 0; i < getItemsetSection.size(); i++) {
                getSection = getItemsetSection.get(i).sectionItems;
            }

            for (int i = 0; i < getSection.size(); i++) {
                getItem = getSection.get(i).item;

                for (int j = 0; j < getItem.size(); j++) {

                    palette_model = new Palette_Model(getItem.get(j).getDataFormatValue(), getItem.get(j).getDataFormatId());
                    strQuestion.add(palette_model);

                }
            }

             /* for (int i = 0; i < getSectionAttribute.size(); i++) {

                            txtQuestionPage.setText(Const.currentpage + "/" + getSectionAttribute.get(i).getItemCount());
                            sectionItem.add(getSectionAttribute.get(i).getSectionName());
                            Valuesfromserver();
                        }
*/
            Log.e(TAG, String.valueOf(strQuestion));

            LinearLayout.LayoutParams param = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);

            CardView cardView = new CardView(getContext());
            cardView.setLayoutParams(param);
            cardView.setRadius(9);
            cardView.setContentPadding(5, 5, 5, 5);
            cardView.setCardBackgroundColor(Color.parseColor("#FFC6D6C3"));
            cardView.setMaxCardElevation(15);
            cardView.setCardElevation(9);
            param.setMargins(10, 10, 10, 10);
            LinearLayout linearLayout = new LinearLayout(getContext());
            linearLayout.setLayoutParams(param);
            linearLayout.setOrientation(LinearLayout.VERTICAL);
            for (int m = 0; m < strQuestion.size(); m++) {
                int val_id = strQuestion.get(m).getData_format_id();
                String value = strQuestion.get(m).getData_format_value();

                if (val_id == 1) {
                    Log.e(TAG, String.valueOf(val_id));
                    TextView textView = new TextView(getContext());
                    textView.setText(Html.fromHtml(strQuestion.get(m).getData_format_value()));
                    textView.setId(1);
                    textView.setLayoutParams(params);
                    textView.setPadding(5, 5, 5, 5);
                    linearLayout.setGravity(Gravity.CENTER);
                    linearLayout.addView(textView);

                } else if (val_id == 6) {

                    Log.e(TAG, String.valueOf(val_id));
                    Const.Image_Path = value;
                    Const.Audio_Path = null;
                    Const.Video_path = null;
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(new LinearLayout.LayoutParams(300, 250));

                    String url = value;

                    Glide
                            .with(getContext())
                            .load(url)
                            .into(imageView);
                    linearLayout.setGravity(Gravity.CENTER);
                    linearLayout.addView(imageView);

                } else if (val_id == 11) {
                    Log.e(TAG, String.valueOf(val_id));
                    String url = value;
                    Const.Image_Path = null;
                    Const.Audio_Path = null;
                    Const.Video_path = value;
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(params);
                    imageView.setImageResource(R.drawable.video_player);
                    linearLayout.setGravity(Gravity.CENTER);
                    linearLayout.addView(imageView);
                    /*videoView = new VideoView(this);
                    videoView.setLayoutParams(new FrameLayout.LayoutParams(650, 550));
                    MediaController mediaController= new MediaController(this);
                    mediaController.setAnchorView(videoView);
                    videoView.setVideoURI(Uri.parse(url));
                    videoView.setMediaController(mediaController);
                    videoView.start();
                    videoView.requestFocus();
                    linearLayout.addView(videoView);*/

                } else if (val_id == 10) {
                    Log.e(TAG, String.valueOf(val_id));
                    Const.Image_Path = null;
                    Const.Audio_Path = value;
                    Const.Video_path = null;
                    ImageView imageView = new ImageView(getContext());
                    imageView.setLayoutParams(params);
                    imageView.setImageResource(R.drawable.speaker);
                    linearLayout.setGravity(Gravity.CENTER);
                    linearLayout.addView(imageView);
                } else if (val_id == 9) {
                    Log.e(TAG, String.valueOf(val_id));
                            Const.Image_Path = null;
                            Const.Audio_Path = value;
                            Const.Video_path = null;
                            TableLayout layout = fragentQuestionsDialogBinding.tableLayout.findViewById(R.id.table_layout);
                            TableRow tbrow0 = new TableRow(getContext());
                            TextView tv0 = new TextView(getContext());
                          //  tv0.setText(strQuestion.get(m).dataFormatMatchValue.get(i).matchData.get(j).matchValue );
                            tv0.setTextColor(Color.BLUE);
                            tbrow0.addView(tv0);
                            TextView tv1 = new TextView(getContext());
                         //   tv1.setText(strQuestion.get(m).dataFormatMatchValue.get(i).matchData.get(j).matchValue );
                            tv1.setTextColor(Color.BLUE);
                            tbrow0.addView(tv1);
                            linearLayout.addView(tbrow0);

                    }
            }
            cardView.addView(linearLayout);
            allQuestions.addView(cardView);
            strQuestion.clear();

        }

        private void getInstruction() {

            fragentQuestionsDialogBinding.rules.setText(Html.fromHtml(Const.InstructionStr));

        }

    }

    private void questionPalette() {

        FragmentTransaction ft = getFragmentManager().beginTransaction();
        Fragment prev = getFragmentManager().findFragmentByTag("dialog");
        if (prev != null) {
            ft.remove(prev);
        }
        ft.addToBackStack(null);
        DialogFragment dialogFragment = new QuestionsDialogFragment();
        dialogFragment.show(ft, "dialog");

    }


}
