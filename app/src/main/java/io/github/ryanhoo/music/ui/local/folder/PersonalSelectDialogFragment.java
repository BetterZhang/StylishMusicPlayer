package io.github.ryanhoo.music.ui.local.folder;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.text.TextUtils;
import android.widget.EditText;
import android.widget.Toast;

import io.github.ryanhoo.music.R;
import io.github.ryanhoo.music.data.model.PersonalSelect;
import io.github.ryanhoo.music.ui.base.BaseDialogFragment;

/**
 * Created by Android Studio.
 * Author : zhangzhongqiang
 * Email  : zhangzhongqiang@jsdttec.com
 * Time   : 2017/07/19 下午 2:25
 * Desc   : description
 */

public class PersonalSelectDialogFragment extends BaseDialogFragment implements Dialog.OnShowListener {

    private static final String ARGUMENT_PERSONAL_SELECT = "personalSelect";

    private EditText startNumStr;
    private EditText endNumStr;

    private PersonalSelect mPersonalSelect;

    private Callback mCallback;

    public static PersonalSelectDialogFragment newInstance(PersonalSelect personalSelect) {
        PersonalSelectDialogFragment fragment = new PersonalSelectDialogFragment();
        if (personalSelect != null) {
            Bundle arguments = new Bundle();
            arguments.putParcelable(ARGUMENT_PERSONAL_SELECT, personalSelect);
            fragment.setArguments(arguments);
        }
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle arguments = getArguments();
        if (arguments != null) {
            mPersonalSelect = arguments.getParcelable(ARGUMENT_PERSONAL_SELECT);
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        final Dialog dialog = new AlertDialog.Builder(getContext())
                .setTitle(getString(R.string.mp_menu_personal_select))
                .setView(R.layout.dialog_personal_select)
                .setNegativeButton(R.string.mp_cancel, null)
                .setPositiveButton(R.string.mp_Confirm, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (TextUtils.isEmpty(startNumStr.getText()) || TextUtils.isEmpty(endNumStr.getText())) {
                            Toast.makeText(getContext(), "输入信息不完整", Toast.LENGTH_SHORT).show();
                            return;
                        }
                        onConfirm();
                    }
                }).create();
        dialog.setOnShowListener(this);
        return dialog;
    }

    @Override
    public void onShow(DialogInterface dialog) {
        resizeDialogSize();
        if (startNumStr == null) {
            startNumStr = (EditText) getDialog().findViewById(R.id.edit_start_num);
        }
        if (endNumStr == null) {
            endNumStr = (EditText) getDialog().findViewById(R.id.edit_end_num);
        }
        if (mPersonalSelect != null) {
            if (!TextUtils.isEmpty(mPersonalSelect.getStartNumStr()))
                startNumStr.setText(mPersonalSelect.getStartNumStr());
            if (!TextUtils.isEmpty(mPersonalSelect.getEndNumStr()))
                endNumStr.setText(mPersonalSelect.getEndNumStr());

            startNumStr.requestFocus();
            startNumStr.setSelection(startNumStr.length());
        }
    }

    public PersonalSelectDialogFragment setCallback(Callback callback) {
        mCallback = callback;
        return this;
    }

    private void onConfirm() {
        if (mCallback == null) return;

        PersonalSelect personalSelect = mPersonalSelect;
        if (personalSelect == null) {
            personalSelect = new PersonalSelect();
        }
        personalSelect.setStartNumStr(startNumStr.getText().toString());
        personalSelect.setEndNumStr(endNumStr.getText().toString());

        mCallback.onSelect(personalSelect);
    }

    public interface Callback {
        void onSelect(PersonalSelect personalSelect);
    }

}
