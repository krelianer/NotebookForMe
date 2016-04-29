package com.example.notebookforme.src;

import android.app.Dialog;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.example.notebookforme.MainActivity;
import com.example.notebookforme.R;
import com.example.notebookforme.mainfragments.MovieFragment;

/**
 * Created by Kreliou on 23/04/2016.
 */
public class SearchDialog extends DialogFragment {

    private EditText mEditText;
    private Button acceptButton;
    private Button cancelButton;

    public static final int SEARCH_CODE = 0;

    public static final String EDIT_TEXT_BUNDLE_KEY = "searchResult";

    public SearchDialog()
    {
        // Empty constructor required for DialogFragment
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
        View view = inflater.inflate(R.layout.search_layout, container);
        mEditText = (EditText) view.findViewById(R.id.editText);
        acceptButton = (Button) view.findViewById(R.id.dialogfragment_acceptbtn);
        cancelButton = (Button) view.findViewById(R.id.dialogfragment_cancelbtn);
        acceptButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendResult(0);
                dismiss();
            }
        });
 //       cancelButton.setOnClickListener(this);
        getDialog().setTitle(R.string.dialog_title);
        return view;
    }

    private void sendResult(int REQUEST_CODE) {
        Intent intent = new Intent();
        intent.putExtra(EDIT_TEXT_BUNDLE_KEY, mEditText.getText().toString());
        getTargetFragment().onActivityResult(
                getTargetRequestCode(), REQUEST_CODE, intent);
    }


}
