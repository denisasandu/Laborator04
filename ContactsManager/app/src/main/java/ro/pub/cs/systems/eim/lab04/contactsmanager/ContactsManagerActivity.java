package ro.pub.cs.systems.eim.lab04.contactsmanager;

import android.content.ContentValues;
import android.content.Intent;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;

import java.util.ArrayList;

public class ContactsManagerActivity extends AppCompatActivity {
    Button saveButton, cancelButton, showButton;
    EditText nameField, phoneField, emailField, addressField;
    EditText jobField, companyField, websiteField, imField;
    LinearLayout secondView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contacts_manager);
        saveButton = (Button)findViewById(R.id.save);
        cancelButton = (Button)findViewById(R.id.cancel);
        showButton = (Button)findViewById(R.id.show_hide);
        secondView = (LinearLayout)findViewById(R.id.second_view);

        nameField = (EditText)findViewById(R.id.name_field);
        phoneField = (EditText)findViewById(R.id.phone_field);
        addressField = (EditText)findViewById(R.id.address_field);
        emailField = (EditText)findViewById(R.id.email_field);
        jobField = (EditText)findViewById(R.id.job_field);
        companyField = (EditText)findViewById(R.id.company_field);
        websiteField = (EditText)findViewById(R.id.website_field);
        imField = (EditText)findViewById(R.id.im_field);

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ContactsContract.Intents.Insert.ACTION);
                intent.setType(ContactsContract.RawContacts.CONTENT_TYPE);
                String name, phone, address, email, jobTitle, company, website, im;
                name = nameField.getText().toString();
                phone = phoneField.getText().toString();
                address = addressField.getText().toString();
                email = emailField.getText().toString();
                company = companyField.getText().toString();
                jobTitle = jobField.getText().toString();
                website = websiteField.getText().toString();
                im = imField.getText().toString();
                if (name != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.NAME, name);
                }
                if (phone != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.PHONE, phone);
                }
                if (email != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.EMAIL, email);
                }
                if (address != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.POSTAL, address);
                }
                if (jobTitle != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.JOB_TITLE, jobTitle);
                }
                if (company != null) {
                    intent.putExtra(ContactsContract.Intents.Insert.COMPANY, company);
                }
                ArrayList<ContentValues> contactData = new ArrayList<ContentValues>();
                if (website != null) {
                    ContentValues websiteRow = new ContentValues();
                    websiteRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Website.CONTENT_ITEM_TYPE);
                    websiteRow.put(ContactsContract.CommonDataKinds.Website.URL, website);
                    contactData.add(websiteRow);
                }
                if (im != null) {
                    ContentValues imRow = new ContentValues();
                    imRow.put(ContactsContract.Data.MIMETYPE, ContactsContract.CommonDataKinds.Im.CONTENT_ITEM_TYPE);
                    imRow.put(ContactsContract.CommonDataKinds.Im.DATA, im);
                    contactData.add(imRow);
                }
                intent.putParcelableArrayListExtra(ContactsContract.Intents.Insert.DATA, contactData);
                startActivity(intent);
            }
        });

        cancelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        showButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (secondView.getVisibility() == View.GONE) {
                    secondView.setVisibility(View.VISIBLE);
                    showButton.setText("Hide additional fields");
                } else {
                    secondView.setVisibility(View.GONE);
                    showButton.setText("Show additional fields");
                }
            }
        });
    }
}
