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

public class BioDialog extends DialogFragment {

    private static final String TAG = "BioDialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets
    private EditText etBio;
    private TextView tvOkBio, tvCancelBio;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_bio_dialog, container, false);
        tvOkBio = view.findViewById(R.id.tvOkBio);
        tvCancelBio = view.findViewById(R.id.tvCancelBio);
        etBio = view.findViewById(R.id.etBio);

        tvCancelBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        tvOkBio.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");

                String input = etBio.getText().toString();
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
