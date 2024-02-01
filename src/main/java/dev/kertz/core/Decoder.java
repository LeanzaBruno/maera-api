package dev.kertz.core;

import dev.kertz.model.Airport;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Decoder {
    private final String rawReport;

    public Decoder(String rawReport){
        this.rawReport = rawReport;
    }

    /**
     * Gets the airport code from the raw report
     * @return
     */
    public String getAirportCode(){
        System.out.println(rawReport);
        Pattern airportPattern = Pattern.compile("SA\\w{2}");
        Matcher airportMatcher = airportPattern.matcher( rawReport );
        if( airportMatcher.find() )
            return airportMatcher.group();
        return "";
    }

    public  String getDateAndTime(){
        Pattern dateAndTimePattern = Pattern.compile("\\d{6}Z");
        Matcher dateAndTimeMatcher = dateAndTimePattern.matcher( rawReport );
        if( dateAndTimeMatcher.find() )
            return dateAndTimeMatcher.group();
        return "";
    }

   public  String getWind(){
       Pattern windPattern = Pattern.compile("[\\dG]*KT");
       Matcher windMatcher = windPattern.matcher( rawReport );
       if( windMatcher.find() )
           return windMatcher.group();
       return "";
   }

   public String getPressure(){
       Pattern pressurePattern = Pattern.compile("Q\\d{4}");
       Matcher pressureMatcher = pressurePattern.matcher( rawReport );
       if( pressureMatcher.find() )
           return pressureMatcher.group();
       return "";
   }

   public String getValidPeriod(){
       Pattern validPeriodPattern = Pattern.compile("\\d{4}\\/\\d{4}");
       Matcher validPeriodMatcher = validPeriodPattern.matcher( rawReport );
       if( validPeriodMatcher.find() )
           return validPeriodMatcher.group();
       return "";
   }

   public String getMaxTemperature(){
       Pattern maxTemperaturePattern = Pattern.compile("TX[\\d\\/]*Z");
       Matcher maxTemperatureMatcher = maxTemperaturePattern.matcher( rawReport );
       if( maxTemperatureMatcher.find() )
           return maxTemperatureMatcher.group();
       return "";
   }

    public String getMinTemperature(){
        Pattern maxTemperaturePattern = Pattern.compile("TN[\\d\\/]*Z");
        Matcher maxTemperatureMatcher = maxTemperaturePattern.matcher( rawReport );
        if( maxTemperatureMatcher.find() )
            return maxTemperatureMatcher.group();
        return "";
    }

    public String getTemperatureInfo(){
        Pattern pressurePattern = Pattern.compile("\\d{2}\\/\\d{2}");
        Matcher pressureMatcher = pressurePattern.matcher( rawReport );
        if( pressureMatcher.find() )
            return pressureMatcher.group();
        return "";
    }

    public boolean getCavok(){
        Pattern pressurePattern = Pattern.compile("CAVOK");
        Matcher pressureMatcher = pressurePattern.matcher( rawReport );
        return pressureMatcher.find();
    }

    public String getRemarks(){
        int index = rawReport.indexOf("RMK");
        if(index == -1) return "";
        return rawReport.substring(index + 4, rawReport.length() - 2);
    }

    public String getVisibility(){
        Pattern visibilityPattern = Pattern.compile("\\d000");
        Matcher visibilityMatcher = visibilityPattern.matcher( rawReport );
        if( visibilityMatcher.find() )
            return visibilityMatcher.group();
        return "";
    }

    public String getWindVariation(){
        Pattern windVariationPattern = Pattern.compile("\\d{3}V\\d{3}");
        Matcher windVariationMatcher = windVariationPattern.matcher( rawReport );
        if( windVariationMatcher.find() )
            return windVariationMatcher.group();
        return "";
    }


    public String getPrecipitation(){
        Pattern precipitationPattern = Pattern.compile("PP[0-9\\/]{3}");
        Matcher precipitationMatcher = precipitationPattern.matcher( rawReport );
        if( precipitationMatcher.find() )
            return precipitationMatcher.group();
        return "";
    }


    /*
    private String dateAndTime;
    private String wind;
    private String pressure;
    private String tempetureInfo;
    private boolean isCavok;
    private String windVariation;
    private String visibility;
    private String remarks;
    private String precipitation;
        Decoder decoder = new Decoder(raw);
        dateAndTime = decoder.getDateAndTime();
        wind = decoder.getWind();
        pressure = decoder.getPressure();
        tempetureInfo = decoder.getTemperatureInfo();
        isCavok = decoder.getCavok();
        windVariation = decoder.getWindVariation();
        visibility = decoder.getVisibility();
        precipitation = decoder.getPrecipitation();
        remarks = decoder.getRemarks();

    private String dateAndTime;
    private String validPeriod;
    private String wind;
    private String pressure;
    private String tempetureInfo;
    private boolean isCavok;
    private String windVariation;
    private String visibility;
    private String remarks;
    private String precipitation;
    private String maxTemperature;
    private String minTemperature;
        Decoder decoder = new Decoder(raw);
        dateAndTime = decoder.getDateAndTime();
        validPeriod = decoder.getValidPeriod();
        maxTemperature = decoder.getMaxTemperature();
        minTemperature = decoder.getMinTemperature();
        wind = decoder.getWind();
        pressure = decoder.getPressure();
        tempetureInfo = decoder.getTemperatureInfo();
        isCavok = decoder.getCavok();
        windVariation = decoder.getWindVariation();
        visibility = decoder.getVisibility();
        precipitation = decoder.getPrecipitation();
        //remarks = decoder.getRemarks();
     */
}
