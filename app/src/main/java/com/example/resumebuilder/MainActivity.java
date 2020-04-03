package com.example.resumebuilder;

import androidx.annotation.NonNull;
import androidx.annotation.StyleableRes;
import androidx.appcompat.app.AppCompatActivity;

import android.Manifest;
import android.app.Notification;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.util.Property;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.itextpdf.text.BaseColor;
import com.itextpdf.text.Chunk;
import com.itextpdf.text.Document;
import com.itextpdf.text.Element;
import com.itextpdf.text.Font;
import com.itextpdf.text.FontFactory;
import com.itextpdf.text.List;
import com.itextpdf.text.ListItem;
import com.itextpdf.text.PageSize;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.Rectangle;
import com.itextpdf.text.pdf.PdfWriter;
import com.itextpdf.text.pdf.draw.LineSeparator;


import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    EditText name,emailId,address,description,clgname,passyear,cgpa,projects,skills,branch,experience,mobile,links;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        name = findViewById(R.id.name);
        emailId = findViewById(R.id.emailId);
        address = findViewById(R.id.address);
        description = findViewById(R.id.description);
        clgname = findViewById(R.id.clgname);
        passyear = findViewById(R.id.passyear);
        cgpa = findViewById(R.id.cgpa);
        projects = findViewById(R.id.projects);
        skills = findViewById(R.id.skills);
        branch = findViewById(R.id.branch);
        experience = findViewById(R.id.experience);
        links = findViewById(R.id.links);
        mobile = findViewById(R.id.mobile);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Build.VERSION.SDK_INT> Build.VERSION_CODES.M)
                {
                    if(checkSelfPermission(Manifest.permission.WRITE_EXTERNAL_STORAGE)== PackageManager.PERMISSION_DENIED)
                    {
                        String[] parmission={Manifest.permission.WRITE_EXTERNAL_STORAGE};
                        requestPermissions(parmission,1000);
                    }
                    else savepdf();
                }
                else savepdf();
            }
        });
    }
    private  void savepdf()
    {
        Document doc = new Document();// Location to save
        doc.setMargins(20,20,20,0);
        // Open to write
        doc.open();
        doc.setPageSize(PageSize.LEGAL);
        doc.addCreationDate();
        doc.addAuthor("Aman Jhurani");
        String Sname =name.getText().toString();
        String mfile=new SimpleDateFormat("yyyy/MM/dd_HH:mm:ss", Locale.getDefault()).format(System.currentTimeMillis());
        String mfilepath= Environment.getExternalStorageDirectory()+"/"+Sname+".pdf";
        Font smallBold=new Font(Font.FontFamily.TIMES_ROMAN,15, Font.NORMAL,BaseColor.DARK_GRAY);
        Font headBold=new Font(Font.FontFamily.TIMES_ROMAN,30,Font.BOLD);
        Font head1Bold=new Font(Font.FontFamily.TIMES_ROMAN,25,Font.BOLD,BaseColor.DARK_GRAY);

        try{
            PdfWriter.getInstance(doc,new FileOutputStream(mfilepath));
            doc.open();


            String Semail =emailId.getText().toString();
            String Saddress =address.getText().toString();
            String Sdescription  =description.getText().toString();
            String Sclgname  =clgname.getText().toString();
            String Syear  =passyear.getText().toString();
            String Scgpa  =cgpa.getText().toString();
            String Sprojects  =projects.getText().toString();
            String Sskills  =skills.getText().toString();
            String Sbranch = branch.getText().toString();
            String Sexper = experience.getText().toString();
            String Smobile = mobile.getText().toString();
            String Slinks = links.getText().toString();


            // Student Name
            Paragraph nameparagraph = new Paragraph(15,Sname,headBold);
            nameparagraph.setAlignment(Element.ALIGN_CENTER);
            doc.add(nameparagraph);
            doc.add(new Chunk(new LineSeparator()));


            // info
            Paragraph infoparagraph = new Paragraph(12,"\nContact Information : \n",head1Bold);
            Font lifont = FontFactory.getFont(FontFactory.TIMES_ROMAN,15,Font.BOLD,BaseColor.DARK_GRAY);
            List infoordered = new List(List.UNORDERED,false,13);
            infoordered.add(new ListItem("Email ID    : "+Semail,lifont));
            infoordered.add(new ListItem("Mobile Number: "+Smobile,lifont));
            infoordered.add(new ListItem("Address     : "+Saddress,lifont));
            infoparagraph.add(infoordered);

//            Chunk emailC = new Chunk("\nEmail ID    : "+Semail+"    ",smallBold);
//            Chunk mobileC = new Chunk("Mobile Number: "+Semail+"\n",smallBold);
//            Chunk addressC = new Chunk("Address     : "+Saddress,smallBold);
//            infoparagraph.add(emailC);
//            infoparagraph.add(mobileC);
//            infoparagraph.add(addressC);
            doc.add(infoparagraph);
            doc.add(new Chunk(new LineSeparator()));


            // Description
            Paragraph detailparagraph = new Paragraph(17,"\nResume Objective : \n",head1Bold);
            Chunk descripC = new Chunk("\n"+Sdescription,smallBold);
            detailparagraph.add(descripC);
            doc.add(detailparagraph);
            doc.add(new Chunk(new LineSeparator()));


            // Education
            List ordered = new List(List.UNORDERED,false,13);
            Paragraph educationparagraph = new Paragraph(17,"\nEducation : \n",head1Bold);
            ordered.add(new ListItem("College       : "+Sclgname,lifont));
            ordered.add(new ListItem("Branch        : "+Sbranch,lifont));
            ordered.add(new ListItem("Passing Year  : "+Syear,lifont));
            ordered.add(new ListItem("Current CGPA  : "+Scgpa,lifont));
//            Chunk clgC = new Chunk("\nCollege : "+Sclgname+"\n",smallBold);
//            Chunk branchC = new Chunk("Branch : "+"Computer Science"+"\n",smallBold);
//            Chunk yearC = new Chunk("Passing Year : "+Syear+"\n",smallBold);
//            Chunk cgpaC = new Chunk("Current CGPA : "+Scgpa,smallBold);
//            educationparagraph.add(clgC);
//            educationparagraph.add(branchC);
//            educationparagraph.add(yearC);
//            educationparagraph.add(cgpaC);
            educationparagraph.add(ordered);
            doc.add(educationparagraph);
            doc.add(new Chunk(new LineSeparator()));

            //Skills
            Paragraph skillsparagraph = new Paragraph(17,"\nProjects : \n",head1Bold);
            Chunk clgC = new Chunk("\n"+"Programming Skills : " + Sskills,smallBold);
            skillsparagraph.add(clgC);
            doc.add(skillsparagraph);
            doc.add(new Chunk(new LineSeparator()));

            //Skills
            Paragraph projectparagraph = new Paragraph(17,"\nProjects : \n",head1Bold);
            Chunk prjC = new Chunk("\n"+Sprojects,smallBold);
            projectparagraph.add(prjC);
            doc.add(projectparagraph);
            doc.add(new Chunk(new LineSeparator()));

            //Experience
            Paragraph experienceParagraph = new Paragraph(17,"\nExperience : \n",head1Bold);
            Chunk exC = new Chunk("\n"+Sexper,smallBold);
            experienceParagraph.add(exC);
            doc.add(experienceParagraph);
            doc.add(new Chunk(new LineSeparator()));

            //Other Links
            Paragraph linkParagraph = new Paragraph(17,"\nTechnical Profile Links : \n",head1Bold);
            Chunk linkC = new Chunk("\n"+Slinks,smallBold);
            linkParagraph.add(linkC);
            doc.add(linkParagraph);




            doc.close();
            Toast.makeText(this, ""+mfile+".pdf"+" is saved to "+mfilepath, Toast.LENGTH_SHORT).show();
        }
        catch (Exception e)
        {
            Toast.makeText(this,"This is Error msg : " +e.getMessage(), Toast.LENGTH_SHORT).show();
        }

    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode)
        {
            case  1000:
                if(grantResults.length>0 && grantResults[0]== PackageManager.PERMISSION_GRANTED)
                {
                    savepdf();
                }
                else Toast.makeText(this, "parmission denied..", Toast.LENGTH_SHORT).show();
        }
    }
}