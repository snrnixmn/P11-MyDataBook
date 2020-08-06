package com.example.p12_mydatabook;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class AnniversaryDialog extends DialogFragment {

    private static final String TAG = "MyAnniDialog";

    public interface OnInputSelected{
        void sendInput(String input);
    }
    public OnInputSelected mOnInputSelected;

    //widgets
    private EditText etAnni;
    private TextView tvOkAnni, tvCancelAnni;

    @Nullable
    @Override
    public View onCreateView(final LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.activity_anniversary_dialog, container, false);
        tvOkAnni = view.findViewById(R.id.tvOkAnni);
        tvCancelAnni = view.findViewById(R.id.tvCancelAnni);
        etAnni = view.findViewById(R.id.etAnni);

        tvCancelAnni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: closing dialog");
                getDialog().dismiss();
            }
        });

        tvOkAnni.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: capturing input.");

                String input = etAnni.getText().toString();
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