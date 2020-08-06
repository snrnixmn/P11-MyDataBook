package com.example.p12_mydatabook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class VaccinationDialog extends DialogFragment {

    private static final String TAG = "VaccinationDialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets
    private EditText etVac;
    private TextView tvOkVac, tvCancelVac;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_vaccination_dialog, container, false);
        tvOkVac = view.findViewById(R.id.tvOkVac);
        tvCancelVac = view.findViewById(R.id.tvCancelVac);
        etVac = view.findViewById(R.id.etVac);

        tvCancelVac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        tvOkVac.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");

                String input = etVac.getText().toString();
                if(!input.equals("")){

                    mOnInputSelected.sendInput(input);
                }

                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            mOnInputSelected = (OnInputSelected) getTargetFragment();
        }catch (ClassCastException e){
            Log.e(TAG, "onAttach: ClassCastException : " + e.getMessage() );
        }
    }
}

