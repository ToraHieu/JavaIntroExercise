package chapter_36;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.Locale;
import java.util.Set;

public class Ex_02 extends Application {
    // Obtain all available locales and time zone ids
    private Locale[] availableLocales = Locale.getAvailableLocales();
    private Set<String> availableTimeZones = ZoneId.getAvailableZoneIds();

    // ComboBoxes to display available locales and time zones
    private ComboBox<String> cboLocales = new ComboBox<>();
    private ComboBox<String> cboTimeZones = new ComboBox<>();
    private ComboBox<String> cboDateStyle = new ComboBox<>();
    private ComboBox<String> cboTimeStyle = new ComboBox<>();

    private TextField tfDate, tfTime;

    private ZonedDateTime dateTime = ZonedDateTime.now();

    @Override
    public void start(Stage primaryStage) {
        tfDate = new TextField();
        tfDate.setEditable(false);

        tfTime = new TextField();
        tfTime.setEditable(false);

        setLocales();
        setTimeZones();
        setStyles();

        HBox v1 = new HBox(new Label("Date:"), tfDate, new Label("Time:"), tfTime);
        HBox v2 = new HBox(new Label("Locale:"), cboLocales, new Label("Time Zone:"), cboTimeZones);
        HBox v3 = new HBox(new Label("Date  Style:"), cboDateStyle, new Label("Time Style:"), cboTimeStyle);
        v3.setAlignment(Pos.CENTER);

        VBox root = new VBox(v1, v2, v3);
        root.setSpacing(5);
        root.setPadding(new Insets(5));
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.setTitle("Ex_02");
        primaryStage.show();

        cboLocales.setOnAction(e -> updateDateTime());
        cboTimeZones.setOnAction(e -> updateDateTime());
        cboDateStyle.setOnAction(e -> updateDateTime());
        cboTimeStyle.setOnAction(e -> updateDateTime());
    }

    private void setTimeZones() {
        for (String timeZone: availableTimeZones) {
            cboTimeZones.getItems().add(timeZone);
        }
        cboTimeZones.getSelectionModel().selectFirst();
    }

    private void setLocales() {
        for (Locale availableLocale : availableLocales) {
            String item = availableLocale
                    .getDisplayName() + " " + availableLocale;
            if (!item.equals(" "))
                cboLocales.getItems().add(item);
        }
        cboLocales.getSelectionModel().selectFirst();
    }

    private void setStyles() {
        cboDateStyle.getItems().addAll("FULL", "LONG", "MEDIUM", "SHORT");
        cboDateStyle.getSelectionModel().selectFirst();
        cboTimeStyle.getItems().addAll("MEDIUM", "SHORT");
        cboTimeStyle.getSelectionModel().selectFirst();
    }

    private void updateDateTime() {
        dateTime = dateTime.withZoneSameInstant(ZoneId.of(cboTimeZones.getValue()));

        String fullLocale = cboLocales.getValue();
        String localeString = fullLocale.substring(fullLocale.lastIndexOf(' ') + 1);
        Locale locale = getLocaleFromString(localeString);

        tfDate.setText(dateTime.toLocalDate().format(DateTimeFormatter.ofLocalizedDate(
                FormatStyle.valueOf(cboDateStyle.getValue())).withLocale(locale)));

        tfTime.setText(dateTime.toLocalTime().format(DateTimeFormatter.ofLocalizedTime(
                FormatStyle.valueOf(cboTimeStyle.getValue())).withLocale(locale)));
    }

    /**
     * Convert a string based locale into a Locale Object.
     * Assumes the string has form "{language}_{country}_{variant}".
     * Examples: "en", "de_DE", "_GB", "en_US_WIN", "de__POSIX", "fr_MAC"
     *
     * @param localeString The String
     * @return the Locale
     */
    public static Locale getLocaleFromString(String localeString)
    {
        if (localeString == null)
        {
            return null;
        }
        localeString = localeString.trim();
        if (localeString.equalsIgnoreCase("default"))
        {
            return Locale.getDefault();
        }

        // Extract language
        int languageIndex = localeString.indexOf('_');
        String language;
        if (languageIndex == -1)
        {
            // No further "_" so is "{language}" only
            return new Locale(localeString, "");
        }
        else
        {
            language = localeString.substring(0, languageIndex);
        }

        // Extract country
        int countryIndex = localeString.indexOf('_', languageIndex + 1);
        String country;
        if (countryIndex == -1)
        {
            // No further "_" so is "{language}_{country}"
            country = localeString.substring(languageIndex+1);
            return new Locale(language, country);
        }
        else
        {
            // Assume all remaining is the variant so is "{language}_{country}_{variant}"
            country = localeString.substring(languageIndex+1, countryIndex);
            String variant = localeString.substring(countryIndex+1);
            return new Locale(language, country, variant);
        }
    }
}
