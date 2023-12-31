package com.AarushMathur.NYCSchools.ui;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.AarushMathur.NYCSchools.R;
import com.AarushMathur.NYCSchools.pojo.School;

import androidx.recyclerview.widget.RecyclerView;

public class SchoolSimpleViewHolder  extends RecyclerView.ViewHolder    {
    private final TextView schoolItemView;
    private School school;

    private SchoolSimpleViewHolder(View itemView) {
        super(itemView);

        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), SchoolActivity.class);
                intent.putExtra("School", school);
                v.getContext().startActivity(intent);
            }
        });

        schoolItemView = itemView.findViewById(R.id.textView);
    }

    public void bind(School school) {
        this.school = school;
        schoolItemView.setText(school.getSchool_name());
    }

    static SchoolSimpleViewHolder create(ViewGroup parent) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.schools_simple_recyclerview_item, parent, false);
        return new SchoolSimpleViewHolder(view);
    }
}
