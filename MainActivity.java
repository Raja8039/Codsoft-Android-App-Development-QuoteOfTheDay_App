package com.example.quotestoday;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    private TextView quoteTextView;
    private Button nextButton;
    private Button saveButton;

    private String[] quotes = {
            "\"To be yourself in a world that is constantly trying to make you something else is the greatest accomplishment.\" - Ralph Waldo Emerson",

            "\"In three words I can sum up everything I've learned about life: it goes on.\" - Robert Frost",

            "\"The only way to do great work is to love what you do.\" - Steve Jobs",

            "\"In the end, we will remember not the words of our enemies, but the silence of our friends.\" - Martin Luther King Jr.",

            "\"To be or not to be, that is the question.\" - William Shakespeare (from Hamlet)",

            "\"The only thing necessary for the triumph of evil is for good men to do nothing.\" - Edmund Burke",

            "\"Life is what happens when you're busy making other plans.\" - John Lennon",

            "\"Two things are infinite: the universe and human stupidity; and I'm not sure about the universe.\" - Albert Einstein",

            "\"The best time to plant a tree was 20 years ago. The second best time is now.\" - Chinese Proverb",

            "\"The only thing we have to fear is fear itself.\" - Franklin D. Roosevelt"
    };

    private SharedPreferences sharedPreferences;
    private static final String FAVORITE_QUOTE_KEY = "favorite_quote";

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

        if (id == R.id.action_share) {
            shareQuote();
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private void shareQuote() {
        String currentQuote = quoteTextView.getText().toString();
        Intent shareIntent = new Intent(Intent.ACTION_SEND);
        shareIntent.setType("text/plain");
        shareIntent.putExtra(Intent.EXTRA_TEXT, currentQuote);

        // Start the activity for sharing
        startActivity(Intent.createChooser(shareIntent, "Share Quote"));
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        quoteTextView = findViewById(R.id.quoteTextView);
        nextButton = findViewById(R.id.nextButton);
        saveButton = findViewById(R.id.saveButton);

        sharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE);

        nextButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showRandomQuote();
            }
        });

        saveButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                saveFavoriteQuote(quoteTextView.getText().toString());
            }
        });

        showRandomQuote();
    }


    private List<Quote> favoriteQuotes = new ArrayList<>();

    // Add a quote to favorites
    public void addToFavorites(Quote quote) {
        if (!quote.isFavorite()) {
            quote.setFavorite(true);
            favoriteQuotes.add(quote);
        }
    }

    // Remove a quote from favorites
    public void removeFromFavorites(Quote quote) {
        if (quote.isFavorite()) {
            quote.setFavorite(false);
            favoriteQuotes.remove(quote);
        }
    }

    private void showRandomQuote() {
        int randomIndex = new Random().nextInt(quotes.length);
        String randomQuote = quotes[randomIndex];
        quoteTextView.setText(randomQuote);
    }

    private void saveFavoriteQuote(String quote) {
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString(FAVORITE_QUOTE_KEY, quote);
        editor.apply();
    }
}

