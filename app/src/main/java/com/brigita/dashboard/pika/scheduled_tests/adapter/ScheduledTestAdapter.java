package com.brigita.dashboard.pika.scheduled_tests.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.RecyclerView;
import android.text.Html;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.brigita.dashboard.pika.Const.Const;
import com.brigita.dashboard.pika.R;
import com.brigita.dashboard.pika.databinding.RecViewDashboardScheduledTestItemBinding;
import com.brigita.dashboard.pika.scheduled_tests.model.ScheduledTest;
import com.brigita.dashboard.pika.test_details.TestDetailsActivity;

import java.util.List;

/*
 * Recycler view Adapter class for scheduled test
 * shown in schedule
 */

public class ScheduledTestAdapter extends RecyclerView.Adapter<ScheduledTestAdapter.ViewHolder> {

    private List<ScheduledTest> scheduledTestList;
    private Context context;

    public ScheduledTestAdapter(List<ScheduledTest> scheduledTestList, Context context) {
        this.scheduledTestList = scheduledTestList;
        this.context = context;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        RecViewDashboardScheduledTestItemBinding binding;

        public ViewHolder(RecViewDashboardScheduledTestItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        RecViewDashboardScheduledTestItemBinding binding = RecViewDashboardScheduledTestItemBinding.
                inflate(LayoutInflater.from(parent.getContext()), parent, false);

        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder holder, int position) {

        final ScheduledTest scheduledTest = scheduledTestList.get(position);

      /*  if (position % 2 == 0) {

            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.table_even));

        } else {

            holder.itemView.setBackgroundColor(ContextCompat.getColor(context, R.color.table_odd));
        }*/


        String testName = "Test Name : " + "<b>" + scheduledTest.testName + "</b>";
        holder.binding.rvDashScheduledTestSubjectTextView.setText(Html.fromHtml(testName));

        String fromDate = "From : " + "<b>" + scheduledTest.scheduledDate.toUpperCase() + "," + Const.time_zone + "</b>";
        holder.binding.rvDashScheduledTestFromDateTextView.setText(Html.fromHtml(fromDate));

        String toDate = "To : " + "<b>" + scheduledTest.scheduledEndDate.toUpperCase() + "," + Const.time_zone + "</b>";
        holder.binding.rvDashScheduledTestToDateTextView.setText(Html.fromHtml(toDate));

        holder.binding.rvDashScheduledTestAttempts.setText(scheduledTest.attemptCount + "/" + scheduledTest.totalCount + "  ");

        //convert date format

       /* SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        try {
            Date scheduledDate = sdf.parse(scheduledTest.scheduledDate);
            Date scheduledEndDate = sdf.parse(scheduledTest.scheduledEndDate);

            sdf = new SimpleDateFormat("dd-MMM-yyyy");

            holder.binding.rvDashScheduledTestFromDateTextView.setText("From : " + sdf.format(scheduledDate));
            Log.e("todate " , scheduledTest.scheduledDate);
            holder.binding.rvDashScheduledTestToDateTextView.setText("To : " + sdf.format(scheduledEndDate));
            Log.e("fromdate " , scheduledTest.scheduledEndDate);
        } catch (ParseException e) {
            e.printStackTrace();
        }
*/
        //set test status

        if (scheduledTest.testStatus == 1) {
            holder.binding.rvDashScheduledTestBtn.setVisibility(View.VISIBLE);
            holder.binding.rvDashScheduledResumeTestBtn.setVisibility(View.GONE);
            holder.binding.rvDashScheduledExpriedTestBtn.setVisibility(View.GONE);
            holder.binding.rvDashScheduledTestBtn.setText("Take Test");
            holder.binding.rvDashScheduledTestBtn.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.binding.rvDashScheduledTestBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Const.org_id_instruction = Integer.parseInt(scheduledTest.orgId);
                    Const.test_id_instruction = (scheduledTest.testID);
                    Const.test_id_instruction_str = String.valueOf(scheduledTest.testID);
                    Const.user_id_instruction = (scheduledTest.userId);
                    Const.schedule_id_instruction = String.valueOf((scheduledTest.scheduleId));
                    Const.org_id_instruction_str = String.valueOf((scheduledTest.orgId));
                    Intent intent = new Intent(context, TestDetailsActivity.class);
                    context.startActivity(intent);

                }
            });
        } else if (scheduledTest.testStatus == 2) {
            holder.binding.rvDashScheduledTestBtn.setVisibility(View.GONE);
            holder.binding.rvDashScheduledResumeTestBtn.setVisibility(View.VISIBLE);
            holder.binding.rvDashScheduledExpriedTestBtn.setVisibility(View.GONE);
            holder.binding.rvDashScheduledResumeTestBtn.setText("Resume Test");
            holder.binding.rvDashScheduledResumeTestBtn.setTextColor(ContextCompat.getColor(context, R.color.colorPrimary));
            holder.binding.rvDashScheduledResumeTestBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    Const.org_id_instruction = Integer.parseInt(scheduledTest.orgId);
                    Const.test_id_instruction = (scheduledTest.testID);
                    Const.test_id_instruction_str = String.valueOf(scheduledTest.testID);
                    Const.user_id_instruction = (scheduledTest.userId);
                    Const.schedule_id_instruction = String.valueOf((scheduledTest.scheduleId));
                    Const.org_id_instruction_str = String.valueOf((scheduledTest.orgId));

                    Intent intent = new Intent(context, TestDetailsActivity.class);
                    context.startActivity(intent);
                }
            });
        } else if (scheduledTest.testStatus == 3) {
            holder.binding.rvDashScheduledTestBtn.setVisibility(View.GONE);
            holder.binding.rvDashScheduledResumeTestBtn.setVisibility(View.GONE);
            holder.binding.rvDashScheduledExpriedTestBtn.setVisibility(View.VISIBLE);
            holder.binding.rvDashScheduledExpriedTestBtn.setText("Expired");
            holder.binding.rvDashScheduledExpriedTestBtn.setTextColor(ContextCompat.getColor(context, R.color.color_nav_drawer_line_color));
            holder.binding.rvDashScheduledExpriedTestBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    new AlertDialog.Builder(context).
                            setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {

                        }
                    })
                            .setMessage("Test has been expired  ").create().show();
                }
            });
        }

    }


    @Override
    public int getItemCount() {
        if (scheduledTestList == null) {
            return 0;
        } else {
            return scheduledTestList.size();
        }
    }

}
