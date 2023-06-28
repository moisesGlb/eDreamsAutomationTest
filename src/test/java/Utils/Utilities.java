package Utils;

import java.util.Date;

public class Utilities {

    private static Date d=new Date();;

    public static String getMonth(String monthYear) {
        String[] parts = monthYear.split(" '");
        return parts[0];
    }

    public static String getYear(String monthYear) {
        String[] parts = monthYear.split(" '");
        return parts[1];
    }

    public static boolean checkYear(int year){
        if (year < d.getYear() || year >= (d.getTime()+2)){
            return false;
        } else return true;
    }

    public static boolean checkMonthDay(String month, int day){
        if (MonthEnum.valueOf(month).ordinal() < d.getMonth()){
            return false;
        } else{
            if (MonthEnum.valueOf(month).ordinal() == d.getMonth() && d.getDay() < day){
                return false;
            }else return true;
        }
    }

    public static int monthDiference(String month){
        return d.getMonth() - MonthEnum.valueOf(month).ordinal();
    }

    public enum MonthEnum {
        Enero,
        Febrero,
        Marzo,
        Abril,
        Mayo,
        Junio,
        Julio,
        Agosto,
        Septiembre,
        Octubre,
        Noviembre,
        Diciembre
    }
}
