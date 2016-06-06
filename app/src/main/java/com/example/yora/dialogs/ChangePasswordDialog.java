package com.example.yora.dialogs;

import android.app.AlertDialog;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.yora.R;

public class ChangePasswordDialog extends BaseDialogFragment implements View.OnClickListener {
    private EditText _currentPassword;
    private EditText _newPassword;
    private EditText _confirmNewPassword;

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        View dialogView = getActivity().getLayoutInflater().inflate(R.layout.dialog_change_password, null, false);

        _currentPassword = (EditText) dialogView.findViewById(R.id.dialog_change_password_currentPassword);
        _newPassword = (EditText) dialogView.findViewById(R.id.dialog_change_password_newPassword);
        _confirmNewPassword = (EditText) dialogView.findViewById(R.id.dialog_change_password_confirmNewPassword);

        if (!application.getAuth().getUser().isHasPassword())
            _currentPassword.setVisibility(View.GONE);

        AlertDialog dialog = new AlertDialog.Builder(getActivity())
                .setView(dialogView)
                // If an OnClickListener is passed as second argument, no matter what we do
                // when the button is pressed the dialog will close.
                .setPositiveButton("Update", null)
                .setNegativeButton("Cancel", null)
                .setTitle("Change Password")
                .show();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(this);

        return dialog;
    }

    @Override
    public void onClick(View view) {
        // TODO Send new password to server
        Toast.makeText(getActivity(), "Password Updated!", Toast.LENGTH_SHORT).show();
        dismiss();
    }
}
