package ni.mind.th.ac.sutheast.extsbc;


import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


/**
 * A simple {@link Fragment} subclass.
 */
public class ResultQRFragment extends Fragment {

    private String qrString;
    private boolean statusABoolean;


    public ResultQRFragment() {
        // Required empty public constructor
    }

    public static ResultQRFragment resultQRInstance(boolean status, String qr) {
        ResultQRFragment resultQRFragment = new ResultQRFragment();
        Bundle bundle = new Bundle();
        bundle.putBoolean("Status", status);
        bundle.putString("QR", qr);
        resultQRFragment.setArguments(bundle);
        return resultQRFragment;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        qrString = getArguments().getString("QR");
        statusABoolean = getArguments().getBoolean("Status");
        Log.d("9febV1", "qr ==> " + qrString);
        Log.d("9febV1", "status ==> " + statusABoolean);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_result_qr, container, false);
    }

}
