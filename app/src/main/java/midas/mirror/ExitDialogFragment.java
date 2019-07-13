package midas.mirror;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.util.Log;

public class ExitDialogFragment extends DialogFragment {
    // 확인 버튼 인터페이스 구현
    interface ExitDialogListener {
        void OnPositiveListener(DialogFragment dialog);
    }

    ExitDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try{
            listener = (ExitDialogListener) context;
        }catch (ClassCastException e){
            throw new ClassCastException(context.toString()
                    + "must implement ExitDialogListener");
        }
    }

    @Override
    public Dialog onCreateDialog(final Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setTitle("MIDAS 종료");
        builder.setMessage("정말로 종료하시겠습니까?");
        builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();

            }
        });
        builder.setNegativeButton("취소", null);

        return builder.create();
    }
}
