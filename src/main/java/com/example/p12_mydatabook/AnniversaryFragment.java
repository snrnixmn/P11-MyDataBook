package com.example.p12_mydatabook;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass.
 */
public class AnniversaryFragment extends Fragment implements AnniversaryDialog.OnInputSelected{
    private static final String TAG = "AnniversaryFragment";
    Button btnEdit;
    TextView tvAnni;

    public AnniversaryFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.anniversaryfragment, container, false);
        btnEdit = view.findViewById(R.id.btnEditAnni);
        tvAnni = view.findViewById(R.id.tvAnni);

        btnEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d(TAG, "onClick: opening dialog");

                AnniversaryDialog dialog = new AnniversaryDialog();
                dialog.setTargetFragment(AnniversaryFragment.this, 1);
                dialog.show(getFragmentManager(), "AnniversaryDialog");
            }
        });


        return view;
    }

    @Override
    public void sendInput(String input) {
        Log.d(TAG, "sendInput: found incoming input: " + input);
        tvAnni.setText(input);
    }
}