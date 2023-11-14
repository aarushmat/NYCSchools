package com.AarushMathur.NYCSchools.ui;

import android.os.Bundle;
import android.widget.TextView;


import com.AarushMathur.NYCSchools.R;
import com.AarushMathur.NYCSchools.pojo.SATScores;
import com.AarushMathur.NYCSchools.model.SATScoresViewModel;
import com.AarushMathur.NYCSchools.pojo.School;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;

public class SchoolActivity extends AppCompatActivity {

    private School school;
    private SATScoresViewModel satScoresViewModel;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_school);
        Toolbar toolbar = findViewById(R.id.school_toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        //School object to setup the page
        school = getIntent().getParcelableExtra("School");

        // Setup UI with School object from intent
        TextView schoolNameTextView = findViewById(R.id.schoolNameText);
        TextView descTextView = findViewById(R.id.descriptionText);
        schoolNameTextView.setText(school.getSchool_name());
        schoolNameTextView.setSingleLine(false);
        schoolNameTextView.setHorizontallyScrolling(false);
        descTextView.setText(school.getOverview_paragraph());
        descTextView.setSingleLine(false);
        descTextView.setHorizontallyScrolling(false);

        // Loads SAT Scores for the School
        satScoresViewModel = new ViewModelProvider(this).get(SATScoresViewModel.class);
        LiveData<SATScores> score = satScoresViewModel.getScoresForSchool(school.getDbn());
        score.observe(this, new Observer<SATScores>() {
            @Override
            public void onChanged(SATScores satScores) {
                satScoresUpdated(satScores);
            }
        });
    }

    /**
     *  Updates the SAT Scores once available from DB
     * @param score
     */
    private void satScoresUpdated(SATScores score) {
        if(score!=null){
            TextView satScoresTextView = findViewById(R.id.satScoresTextView);
            satScoresTextView.setText(score.toString());
        }
    }


}