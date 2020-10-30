//Zeying Zhou
//20116670

package com.example.guessmaster;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import android.content.DialogInterface;

import java.util.Random;


public class GuessMaster extends AppCompatActivity {
    private TextView entityName;
    private TextView ticketsum;
    private Button guessButton;
    private EditText userIn;
    private Button btnclearContent;
    private ImageView entityImage;
    String answer;
    private int numOfEntities;
    private Entity[] entities;
    private int[] tickets;
    private int totaltik;
    private int numberOfTickets;
    String entName;
    int entityid = 0;

    public GuessMaster() {
        numOfEntities = 0;
        totaltik = 0;
        entities = new Entity[100]; //initialize the entity array
        numberOfTickets = 0;//initialize the number of total tickets to 0
        tickets = new int[100];//initialize the tickets array
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guess_activity);

        //Define veiew components
        guessButton = (Button)findViewById(R.id.btnGuess);
        btnclearContent = (Button)findViewById(R.id.btnClear);
        entityName = (TextView)findViewById(R.id.entityName);
        entityImage = (ImageView)findViewById(R.id.entityImage);
        userIn = (EditText)findViewById(R.id.guessinput);
        ticketsum = (TextView)findViewById(R.id.ticket);

        //Create GuessMaster
        final GuessMaster gm = new GuessMaster();
        Politician trudeau = new Politician("Justin Trudeau", new Date("December", 25, 1971), "Male", "Liberal", 0.25);
        Singer dion = new Singer("Celine Dion", new Date("March", 30, 1961), "Female", "La voix du bon Dieu", new Date("November", 6, 1981), 0.5);
        Person myCreator = new Person("My Creator", new Date("September", 1, 2000), "Female", 1);
        Country usa = new Country("United States", new Date("July", 4, 1776), "Washington D.C.", 0.1);

        //Add entity to GuessMaster
        gm.addEntity(trudeau);
        gm.addEntity(dion);
        gm.addEntity(myCreator);
        gm.addEntity(usa);


        ticketsum.setText("Total Tickets: " + "0");   //Display 0 on initial Total Tickets
        this.numOfEntities = gm.numOfEntities;
        this.entities = gm.entities;
        continueGame(); //Start game on generate random entity
        welcomeToGame(gm.entities[entityid]); //Welcome Message

        //onClickListener on change entity button
        btnclearContent.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                changeEntity();
            }
        });

        //onClickListener on submit guess button
        guessButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                playGame();
            }
        });
    }

    //ImageSetter method using switch case
    public void ImageSetter() {
        switch  (entName) {
            case "Justin Trudeau":
                entityImage.setImageResource(R.drawable.justint);
                break;
            case "Celine Dion":
                entityImage.setImageResource(R.drawable.celidion);
                break;
            case "My Creator":
                entityImage.setImageResource(R.drawable.mycreate);
                break;
            case "United States":
                entityImage.setImageResource(R.drawable.usaflag);
                break;
        }
    }

    //change entity method
    public void changeEntity() {
            userIn.getText().clear(); //clear text
            continueGame(); //generate a new random entity
    }


    //add entity method
    public void addEntity(Entity entity) {
        entities[numOfEntities++] = entity.clone();
    }

    public void playGame(int entityId) {
        Entity entity = entities[entityId];
        playGame(entity);
    }

    //Display welcome dialog
    public void welcomeToGame(Entity entity) {
        AlertDialog.Builder welcomealert = new AlertDialog.Builder(GuessMaster.this);
        welcomealert.setTitle("GuessMaster_Game_v3");
        welcomealert.setMessage(entity.welcomeMessage());
        welcomealert.setCancelable(false);

        welcomealert.setNegativeButton("START_GAME", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                Toast.makeText(getBaseContext(), "Game_is_Starting...Enjoy", Toast.LENGTH_SHORT).show();
            }
        });

        //Show Dialog
        AlertDialog dialog = welcomealert.create();
        dialog.show();
    }


    public void playGame(final Entity entity) {
        //call welcome message follow the format from the exampleOutput

        entityName.setText(entity.getName());
        answer = userIn.getText().toString();
        answer = answer.replace("\n", "").replace("\r", "");
        Date date = new Date(answer);

            if (answer.equals("quit")) {
                System.exit(0);
            }

            //Display later date dialog
            if (date.precedes(entity.getBorn())) {
                AlertDialog.Builder later = new AlertDialog.Builder(GuessMaster.this);
                later.setTitle("Incorrect");
                later.setMessage("Try a later date.");
                later.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = later.create();
                dialog.show();
            }
            //Display earlier dialog
            else if (entity.getBorn().precedes(date)) {
                AlertDialog.Builder earlier = new AlertDialog.Builder(GuessMaster.this);
                earlier.setTitle("Incorrect");
                earlier.setMessage("Incorrect. Try a earlier date.");
                earlier.setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                    }
                });
                AlertDialog dialog = earlier.create();
                dialog.show();
            } else {

                //Display won game alertdialog
                AlertDialog.Builder won = new AlertDialog.Builder(GuessMaster.this);
                won.setTitle("You won");
                won.setMessage("BINGO" + entity.closingMessage());
                won.setCancelable(false);
                //call continuesGame method when click the continue
                won.setNegativeButton("Continue", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Toast.makeText(getBaseContext(),"You won " + entity.getAwardedTicketNumber() + " tickets in this round.", Toast.LENGTH_SHORT).show();
                        continueGame();
                    }
                });
                AlertDialog dialog = won.create();
                dialog.show();
                tickets[numberOfTickets++] = entity.getAwardedTicketNumber();
                for(int i = 0; i < 100; i++) {
                    totaltik = totaltik + tickets[i];
                }
                ticketsum.setText("Total Tickets: " + totaltik);
                totaltik = 0;
            }
    }

    public void playGame() {
            playGame(entityid);
    }

    public void continueGame() {
        entityid = genRandomEntityId();
        Entity entity = entities[entityid];

        entName = entity.getName();

        //Call the ImageSetter method
        ImageSetter();
        //Print the name of the entity to be guessed
        //in the entityName textview
        entityName.setText(entName);
        //Clear Previous Entry;
        userIn.getText().clear();
    }

    public int genRandomEntityId() {
        Random randomNumber = new Random();
        return randomNumber.nextInt(numOfEntities);
    }
}
