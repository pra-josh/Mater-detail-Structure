package com.pra.interview.UI;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;



/**
 * this class cointains for all fragment in Base you can initialization common instance
 * also some abstract method
 */
public abstract class BaseFragment extends Fragment {

    public ItemDetailHostActivity mActivity;
    public View mView;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mActivity = (ItemDetailHostActivity) this.getActivity();
    }


    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        super.onCreateView(inflater, container, savedInstanceState);
        if (mView == null) {
            mView = getLayoutResourceId(inflater);
            initComponents(mView);
            setActionListeners();
            prepareView();
            // new UiOpearation().execute();
        }
        return mView;
    }


    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }


    private class UiOpearation extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            initComponents(mView);
        }

        @Override
        protected Void doInBackground(Void... voids) {
            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            setActionListeners();
            prepareView();


        }
    }


    protected abstract View getLayoutResourceId(LayoutInflater inflater);

    protected abstract void initComponents(View view);

    protected abstract void prepareView();

    protected abstract void setActionListeners();

    protected abstract void setToolBar();


    @Override
    public void onDestroy() {
        super.onDestroy();
        freeMemory();
    }

    public void freeMemory() {
        System.runFinalization();
        Runtime.getRuntime().gc();
        System.gc();
    }

}
