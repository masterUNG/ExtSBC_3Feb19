package ni.mind.th.ac.sutheast.extsbc;


import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.Toolbar;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

/**
 * A simple {@link Fragment} subclass.
 */
public class RegisterFragment extends Fragment {

    private String genderString, dateString, levelString, divisionString, sectionString;
    private boolean genderABoolean = true;


    public RegisterFragment() {
        // Required empty public constructor
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

//        Create Toolber
        createToolber();

//        Level Controller
        levelController();

//        Create Division
        createDivision();

//        Create Section
        createSection();

//        Gender Controller
        genderController();


    }   //Main Method

    private void createSection() {
        final String[] strings = new String[]{"Section1", "Section2", "Section3", "Section่4",};
        Spinner spinner = getView().findViewById(R.id.spnGroup);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                sectionString = strings[1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                sectionString = strings[0];
            }
        });
    }

    private void levelController() {
        final String[] strings = new String[]{"ชั่นปีที่1", "ชั่นปีที่2", "ชั่นปีที่3", "ชั่นปีที่4",};
        Spinner spinner = getView().findViewById(R.id.spnLevel);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                levelString = strings[1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                levelString = strings[0];
            }
        });

    }

    private void genderController() {
        RadioGroup radioGroup = getView().findViewById(R.id.radGender);
        radioGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                genderABoolean = false;
                switch (checkedId) {
                    case R.id.radMail:
                        genderString = "Male";
                        break;
                    case R.id.radFemail:
                        genderString = "Female";
                        break;
                }
            }
        });
    }

    private void createDivision() {

        final String[] strings = new String[]{"Division1", "Division2", "Division3", "Division4",};
        Spinner spinner = getView().findViewById(R.id.spnDivition);
        ArrayAdapter<String> stringArrayAdapter = new ArrayAdapter<>(getActivity(), android.R.layout.simple_list_item_1, strings);
        spinner.setAdapter(stringArrayAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                divisionString = strings[1];
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                divisionString = strings[0];
            }
        });


    }

    private void createToolber() {
        Toolbar toolbar = getView().findViewById(R.id.toolbarRegister);
        ((MainActivity) getActivity()).setSupportActionBar(toolbar);
        ((MainActivity) getActivity()).getSupportActionBar().setTitle(getString(R.string.register));
        ((MainActivity) getActivity()).getSupportActionBar().setSubtitle(getString(R.string.message_have_space));
        ((MainActivity) getActivity()).getSupportActionBar().setHomeButtonEnabled(true);
        ((MainActivity) getActivity()).getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getActivity().getSupportFragmentManager().popBackStack();
            }
        });
        setHasOptionsMenu(true);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.itemCloud) {

            EditText id1EditText = getView().findViewById(R.id.edtID1);
            EditText id2EditText = getView().findViewById(R.id.edtID2);
            EditText passEditText = getView().findViewById(R.id.edtPassword1);
            EditText pass2EditText = getView().findViewById(R.id.edtPassword2);
            EditText nameEditText = getView().findViewById(R.id.edtName);
            EditText surnameEditText = getView().findViewById(R.id.edtSurname);
            EditText ageEditText = getView().findViewById(R.id.edtAge);
            EditText addressEditText = getView().findViewById(R.id.edtAddress);
            EditText emailEditText = getView().findViewById(R.id.edtEmail);
            EditText phoneEditText = getView().findViewById(R.id.edtPhone);

            String id1 = id1EditText.getText().toString().trim();
            String id2 = id2EditText.getText().toString().trim();
            String pass = passEditText.getText().toString().trim();
            String pass2 = pass2EditText.getText().toString().trim();
            String name = nameEditText.getText().toString().trim();
            String surname = surnameEditText.getText().toString().trim();
            String age = ageEditText.getText().toString().trim();
            String address = addressEditText.getText().toString().trim();
            String email = emailEditText.getText().toString().trim();
            String phone = phoneEditText.getText().toString().trim();

            if (id1.isEmpty() ||
                    id2.isEmpty() ||
                    pass.isEmpty() ||
                    pass2.isEmpty() ||
                    name.isEmpty() ||
                    surname.isEmpty() ||
                    age.isEmpty() ||
                    address.isEmpty() ||
                    email.isEmpty() ||
                    phone.isEmpty()) {
//                Have Space
                showAlert("Have Space", "Please Fill All Blank");
            } else if (genderABoolean) {
                showAlert("Choose Gender ?", "Please Choose Gender Male or Female");
            } else if (!pass.equals(pass2)) {
                showAlert("Password not Match", "Please Type Password and Re-Password Match");
            } else {
                FirebaseAuth firebaseAuth = FirebaseAuth.getInstance();
                firebaseAuth.createUserWithEmailAndPassword(email, pass).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
//                            ToDo
                        } else {
                            showAlert("Cannont Resigter", task.getException().toString());
                        }
                    }
                });
            }

        }
        return super.onOptionsItemSelected(item);
    }

    private void showAlert(String title, String message) {
        AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());
        alertDialogBuilder.setTitle(title).setMessage(message).setPositiveButton("OK", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.dismiss();
            }
        }).show();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);


        inflater.inflate(R.menu.menu_register, menu);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_register, container, false);
    }

}
