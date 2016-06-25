package com.example.huyviet1995.gustavusmapproject;

import android.*;
import android.Manifest;
import android.app.Activity;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;


/**
 * Created by huyviet1995 on 6/23/16.
 */
public abstract class PermissionRequests {
    public static void requestPermission (FragmentActivity activity, int requestId, String permission, boolean finishActivity) {
        if (ActivityCompat.shouldShowRequestPermissionRationale(activity,permission)) {
            //Display the dialog right here
            PermissionRequests.RationaleDialog.newInstance(requestId,finishActivity).show(activity.getSupportFragmentManager(),"permissionDialog");
        }
        else {
            ActivityCompat.requestPermissions(activity,new String[]{permission},requestId);
        }
    }

    public static boolean isPermissionGranted(String[] grantPermissions, int[] grantResults,
                                              String permission) {
        for (int i = 0; i < grantPermissions.length; i++) {
            if (permission.equals(grantPermissions[i])) {
                return grantResults[i] == PackageManager.PERMISSION_GRANTED;
            }
        }
        return false;
    }

    public static class PermissionDeniedFragment extends DialogFragment {
        private static final String ACTIVITY_FINISH = "finish";
        private boolean mFinishActivity;
        /*Create a singleton class*/
        public static PermissionDeniedFragment newInstance(boolean finishActivity) {
            Bundle arguments = new Bundle();
            arguments.putBoolean(ACTIVITY_FINISH,finishActivity);
            PermissionDeniedFragment fragment = new PermissionDeniedFragment();
            fragment.setArguments(arguments);
            return fragment;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            mFinishActivity = getArguments().getBoolean(ACTIVITY_FINISH,false);
            super.onCreate(savedInstanceState);
            return new AlertDialog.Builder(getActivity())
                    .setMessage("Permission denied")
                    .setPositiveButton(R.string.ok,null)
                    .create();
        }
        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            if (mFinishActivity) {
                getActivity().finish();
            }
        }
    }

    public static class RationaleDialog extends DialogFragment {
        private static final String REQUESTCODE = "requestCode";
        private static final String ACTIVITY_FINISH = "finishActivity";
        private boolean mFinishActivity;
        private int mRequestCode;
        public static RationaleDialog newInstance(int requestCode, boolean finishActivity) {
            Bundle arguments = new Bundle();
            arguments.putInt(REQUESTCODE,requestCode);
            arguments.putBoolean(ACTIVITY_FINISH,finishActivity);
            RationaleDialog fragment = new RationaleDialog();
            fragment.setArguments(arguments);
            return fragment;
        }
        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {
            mFinishActivity = getArguments().getBoolean(ACTIVITY_FINISH);
            mRequestCode = getArguments().getInt(REQUESTCODE);
            return new AlertDialog.Builder(getActivity())
                    .setMessage(R.string.permission_request)
                    .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            ActivityCompat.requestPermissions(getActivity(),new String[] {Manifest.permission.ACCESS_FINE_LOCATION},mRequestCode);
                            mFinishActivity = false;
                        }
                    })
                    .create();
        }
        @Override
        public void onDismiss(DialogInterface dialog) {
            super.onDismiss(dialog);
            getActivity().finish();
        }
    }

}
