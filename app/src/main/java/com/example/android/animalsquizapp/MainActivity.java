package com.example.android.animalsquizapp;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RadioGroup;
import android.widget.ScrollView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void scoreQuiz(View view) {

        //QUESTIONS

        ArrayList<Boolean> results = new ArrayList<Boolean>();

        //Flamingo

        int flamingoGroupId = R.id.flamingo_group;
        int flamingoSolutionId = R.id.flamingo_solution;

        boolean isCorrectFlamingo = scoreRadio(flamingoGroupId, flamingoSolutionId);
        results.add(isCorrectFlamingo);

        //Lion

        List<Integer> lionSolutionIds = new ArrayList<Integer>();
        lionSolutionIds.add(R.id.lion_solution_lion);
        lionSolutionIds.add(R.id.lion_solution_tiger);

        List<Integer> lionOptionIds = new ArrayList<Integer>();
        lionOptionIds.add(R.id.lion_solution_leopard);
        lionOptionIds.add(R.id.lion_solution_lion);
        lionOptionIds.add(R.id.lion_solution_jaguar);
        lionOptionIds.add(R.id.lion_solution_tiger);

        boolean isCorrectLion = scoreCheckbox(lionSolutionIds, lionOptionIds);
        results.add(isCorrectLion);

        //Shark

        int sharkInputFieldId = R.id.shark_answer_input_field;
        String sharkSolution = "hammerhead";

        boolean isCorrectShark = scoreInput(sharkInputFieldId, sharkSolution);
        results.add(isCorrectShark);

        //Elephant

        int elephantGroupId = R.id.elephant_group;
        int elephantSolutionId = R.id.elephant_solution;

        boolean isCorrectElephant = scoreRadio(elephantGroupId, elephantSolutionId);
        results.add(isCorrectElephant);

        //Polar Bear

        List<Integer> polarbearSolutionIds = new ArrayList<Integer>();
        polarbearSolutionIds.add(R.id.polarbear_solution_arctic);

        List<Integer> polarbearOptionIds = new ArrayList<Integer>();
        polarbearOptionIds.add(R.id.polarbear_solution_arctic);
        polarbearOptionIds.add(R.id.polarbear_solution_antarctica);

        boolean isCorrectPolarBear = scoreCheckbox(polarbearSolutionIds, polarbearOptionIds);
        results.add(isCorrectPolarBear);

        //RESULTS

        //Score
        int score = 0;
        for (Boolean value : results) {
            if (value) {
                score ++;
            }
        }

        TextView scoreTextView = (TextView) findViewById(R.id.score);
        scoreTextView.setText(score + " / " + results.size());

        //Feedback

        //Flamingo Feedback
        int flamingoId = R.id.flamingo_feedback;
        int flamingoQuestionNumber = 1;

        setFeedback(flamingoId, flamingoQuestionNumber, results);

        //Lion Feedback
        int lionId = R.id.lion_feedback;
        int lionQuestionNumber = 2;

        setFeedback(lionId, lionQuestionNumber, results);

        //Shark Feedback
        int sharkId = R.id.shark_feedback;
        int sharkQuestionNumber = 3;

        setFeedback(sharkId, sharkQuestionNumber, results);

        //Elephant Feedback
        int elephantId = R.id.elephant_feedback;
        int elephantQuestionNumber = 4;

        setFeedback(elephantId, elephantQuestionNumber, results);

        //Polar Bear Feedback
        int polarBearId = R.id.polarbear_feedback;
        int polarBearQuestionNumber = 5;

        setFeedback(polarBearId, polarBearQuestionNumber, results);

        //Visibility and scroll
        LinearLayout resultsLinearLayout = (LinearLayout) findViewById(R.id.results_root_linear_layout);
        resultsLinearLayout.setVisibility(LinearLayout.VISIBLE);

        final ScrollView scrollview = ((ScrollView) findViewById(R.id.scrollView));
        scrollview.post(new Runnable() {
            @Override
            public void run() {
                scrollview.fullScroll(ScrollView.FOCUS_DOWN);
            }
        });
    }

    private void setFeedback(int feedbackId, int questionNumber, ArrayList<Boolean> results) {
        TextView feedbackTextView = (TextView) findViewById(feedbackId);
        if (results.get(questionNumber - 1) == true) {
            feedbackTextView.setText(questionNumber + ". Correct");
        } else {
            feedbackTextView.setText(questionNumber + ". Incorrect");
        }
    }

    private boolean scoreRadio(int groupId, int solutionId) {

        RadioGroup radioGroup = (RadioGroup) findViewById(groupId);

        if (radioGroup.getCheckedRadioButtonId() == solutionId) {
            return true;
        } else {
            return false;
        }
    }

    private boolean scoreCheckbox(List<Integer> solutionIds, List<Integer> optionIds) {

        List<Integer> selectedIds = new ArrayList<Integer>();

        for (Integer option : optionIds) {
            CheckBox checkBox = (CheckBox) findViewById(option);
            if (checkBox.isChecked()) {
                selectedIds.add(option);
            }
        }

        Collections.sort(selectedIds);
        Collections.sort(solutionIds);

        return selectedIds.toString().equals(solutionIds.toString());
    }

    private boolean scoreInput(int inputFieldId, String solution) {

        EditText inputField = (EditText) findViewById(inputFieldId);

        String userAnswer = inputField.getText().toString();

        userAnswer = userAnswer.toLowerCase();
        userAnswer = userAnswer.replaceAll(" ", "");
        userAnswer = userAnswer.replaceAll("-", "");

        return userAnswer.contains(solution);
    }
}
