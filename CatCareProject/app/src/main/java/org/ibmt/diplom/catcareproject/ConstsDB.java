package org.ibmt.diplom.catcareproject;

/**
 * Created by Anna on 18.12.2016.
 */

public interface ConstsDB {


    // Database Version
    int DATABASE_VERSION = 29;
    // Database Name
    String DATABASE_NAME = "petcareDB";

    // Table names
    String FOODS_TABLE_NAME = "Foods";
    String CATS_TABLE_NAME = "Cats";
    String MEALS_TABLE_NAME = "Meals";
    String LABTESTS_TABLE_NAME = "LabTests";
    String CONTACTS_TABLE_NAME = "Contacts";
    String INTAKERATES_TABLE_NAME = "IntakeRates";
    String CALENDAR_TABLE_NAME = "Calendar";

    String DEFAULT_SEX = "'неизвестный пол'";
    String DEFAULT_GENDER = "'другой тип'";
    String DEFAULT_CONDITION = "'общее состояние'";

    //------------------------------------ Foods------------------------------------------
    // Foods Table Columns names
    String FOODS_COLUMN_ID = "FoodId";
    String FOODS_COLUMN_NAME = "Name";
    String FOODS_COLUMN_STATE = "State";
    String FOODS_COLUMN_ENERGY = "Energy";
    String FOODS_COLUMN_PROTEINS = "Proteins";
    String FOODS_COLUMN_FATS = "Fats";
    String FOODS_COLUMN_CARBS = "Carbohydrates";
    String FOODS_CONSTRAINT_UNIQUE = "FoodsUnique";
    String FOODS_CONSTRAINT_PRIMARY = "FoodsPrimary";

    String CREATE_FOODS_TABLE = "CREATE TABLE " + FOODS_TABLE_NAME + "(" +
            FOODS_COLUMN_ID + " INTEGER, " +
            FOODS_COLUMN_NAME + " VARCHAR(255) NOT NULL, " +
            FOODS_COLUMN_STATE + " VARCHAR(255) NOT NULL, " +
            FOODS_COLUMN_ENERGY + " REAL, " +
            FOODS_COLUMN_PROTEINS + " REAL, " +
            FOODS_COLUMN_FATS + " REAL, " +
            FOODS_COLUMN_CARBS + " REAL" +
            ", CONSTRAINT " + FOODS_CONSTRAINT_PRIMARY + " PRIMARY KEY (" + FOODS_COLUMN_ID + ") " +
            ", CONSTRAINT " + FOODS_CONSTRAINT_UNIQUE + " UNIQUE (" + FOODS_COLUMN_NAME + ", " + FOODS_COLUMN_STATE + ")" +
            ")";

    String INITIAL_FOODS_ENTRIES = "INSERT INTO " + FOODS_TABLE_NAME + " (" +
            FOODS_COLUMN_NAME + ", " +
            FOODS_COLUMN_STATE + ", " +
            FOODS_COLUMN_ENERGY + ", " +
            FOODS_COLUMN_PROTEINS + ", " +
            FOODS_COLUMN_FATS + ", " +
            FOODS_COLUMN_CARBS +
            ")   Values " +
            " ('Hills k/d', 'сухой', 520, 28, 14, 24)," +
            " ('Hills k/d', 'влажный', 170, 10, 5, 8)," +
            " ('Trainer personal sensirenal', 'сухой', 520, 32, 14, 33)," +
            " ('Trainer light', 'сухой', 450, 35, 15, 23)," +
            " ('Trainer fitness senior 12+', 'сухой', 350, 38, 19, 31)," +
            " ('Trainer fitness', 'сухой', 500, 30, 12, 24)," +
            " ('Trainer natural sterillized', 'сухой', 450, 36, 10, 27)";

    //------------------------------------ IntakeRates------------------------------------------
    // IntakeRates Table Columns names
    String INTAKERATES_COLUMN_ID = "IntakeRateId";
    String INTAKERATES_COLUMN_NAME = "Name";
    String INTAKERATES_COLUMN_GENDER = "Gender";
    String INTAKERATES_COLUMN_AGE = "Age";
    String INTAKERATES_COLUMN_WEIGHT = "Weight";
    String INTAKERATES_COLUMN_SEX = "Sex";
    String INTAKERATES_COLUMN_CONDITION = "Condition";
    String INTAKERATES_COLUMN_ENERGY = "Energy";
    String INTAKERATES_COLUMN_PROTEINS = "Proteins";
    String INTAKERATES_COLUMN_FATS = "Fats";
    String INTAKERATES_COLUMN_CARBS = "Carbohydrates";
    String INTAKERATES_CONSTRAINT_PRIMARY = "RatesPrimary";
    String INTAKERATES_CONSTRAINT_UNIQUE = "RatesUnique";

    String CREATE_INTAKERATES_TABLE = "CREATE TABLE " + INTAKERATES_TABLE_NAME + "(" +
            INTAKERATES_COLUMN_ID + " INTEGER, " +
            INTAKERATES_COLUMN_NAME + " VARCHAR(255) NOT NULL, " +
            INTAKERATES_COLUMN_GENDER + " VARCHAR(55) NOT NULL DEFAULT " + DEFAULT_GENDER + ", " +
            INTAKERATES_COLUMN_AGE + "  INTEGER, " +
            INTAKERATES_COLUMN_WEIGHT + "  REAL, " +
            INTAKERATES_COLUMN_SEX + " VARCHAR(15) NOT NULL DEFAULT " + DEFAULT_SEX + ", " +
            INTAKERATES_COLUMN_CONDITION + " VARCHAR(255) NOT NULL DEFAULT " + DEFAULT_CONDITION + ", " +
            INTAKERATES_COLUMN_ENERGY + " REAL, " +
            INTAKERATES_COLUMN_PROTEINS + "  REAL, " +
            INTAKERATES_COLUMN_FATS + "  REAL, " +
            INTAKERATES_COLUMN_CARBS + "  REAL" +
            ", CONSTRAINT " + INTAKERATES_CONSTRAINT_PRIMARY + " PRIMARY KEY (" + INTAKERATES_COLUMN_ID + ") " +
            ", CONSTRAINT " + INTAKERATES_CONSTRAINT_UNIQUE + " UNIQUE (" + INTAKERATES_COLUMN_NAME + ", " + INTAKERATES_COLUMN_GENDER + ", " + INTAKERATES_COLUMN_AGE + ", " + INTAKERATES_COLUMN_WEIGHT + ", " + INTAKERATES_COLUMN_SEX + ", " + INTAKERATES_COLUMN_CONDITION +
            ")" +
            ")";
    String INITIAL_INTAKERATES_ENTRIES = "INSERT INTO " + INTAKERATES_TABLE_NAME + " (" +
            INTAKERATES_COLUMN_NAME + ", " +
            INTAKERATES_COLUMN_GENDER + ", " +
            INTAKERATES_COLUMN_AGE + ", " +
            INTAKERATES_COLUMN_CONDITION + ", " +
            INTAKERATES_COLUMN_ENERGY + ", " +
            INTAKERATES_COLUMN_PROTEINS + ", " +
            INTAKERATES_COLUMN_FATS + ", " +
            INTAKERATES_COLUMN_CARBS +
            ")   Values " +
            " ('forCKD', 'кот', 0, 'хпн', 450.0, 28.0, 18.0, 28.0)," +
            " ('FEDIAF', 'кот', 0, 'кормящая', 600.0, 45.0, 25.0, 35.0)," +
            " ('FEDIAF', 'кот', 0, 'детеныш', 600.0, 45.0, 25.0, 35.0)," +
            " ('FEDIAF', 'собака', 0, 'общее состояние', 450.0, 45.0, 25.0, 35.0)," +
            " ('dietLight', 'кот', 0, 'ожирение', 350.0, 35.0, 12.0, 18.0)";

    //------------------------------------ Cats------------------------------------------
    // Cats Table Columns names
    String CATS_COLUMN_ID = "CatId";
    String CATS_COLUMN_NAME = "Name";
    String CATS_COLUMN_IMAGE = "ImageUriString";
    String CATS_COLUMN_GENDER = "Gender";
    String CATS_COLUMN_SEX = "Sex";
    String CATS_COLUMN_CONDITION = "Condition";
    String CATS_COLUMN_IRATE = "IntakeRateID";
    String CATS_COLUMN_AGE = "Age";
    String CATS_COLUMN_WEIGHT = "Weight";
    String CATS_INTAKERATE_FOREIGN = "CatsIntakeRateForeign";
    String CATS_CONSTRAINT_PRIMARY = "CatsPrimary";

    String CREATE_CATS_TABLE = "CREATE TABLE " + CATS_TABLE_NAME + "(" +
            CATS_COLUMN_ID + " INTEGER, " +
            CATS_COLUMN_NAME + " VARCHAR(255) NOT NULL, " +
            CATS_COLUMN_IMAGE + " VARCHAR(255) DEFAULT '' , " +
            CATS_COLUMN_GENDER + " VARCHAR(55)  DEFAULT " + DEFAULT_GENDER + ", " +
            CATS_COLUMN_AGE + "  INTEGER, " +
            CATS_COLUMN_WEIGHT + "  REAL, " +
            CATS_COLUMN_SEX + " VARCHAR(15) DEFAULT " + DEFAULT_SEX + ", " +
            CATS_COLUMN_CONDITION + " VARCHAR(255) DEFAULT " + DEFAULT_CONDITION + ", " +
            CATS_COLUMN_IRATE + "  INTEGER DEFAULT 2" +
            ", CONSTRAINT " + CATS_CONSTRAINT_PRIMARY + " PRIMARY KEY (" + CATS_COLUMN_ID + ") " +
            ", CONSTRAINT " + CATS_INTAKERATE_FOREIGN + " FOREIGN KEY (" + CATS_COLUMN_IRATE + ") REFERENCES " + INTAKERATES_TABLE_NAME + " (" + INTAKERATES_COLUMN_ID + ")" +
            ")";
    String INITIAL_CATS_ENTRIES = "INSERT INTO " + CATS_TABLE_NAME + " (" +
            CATS_COLUMN_NAME + ", " +
            CATS_COLUMN_GENDER + ", " +
            CATS_COLUMN_SEX + ", " +
            CATS_COLUMN_AGE + ", " +
            CATS_COLUMN_WEIGHT + ", " +
            CATS_COLUMN_CONDITION + ", " +
            CATS_COLUMN_IRATE +
            ")   Values " +
            " ('Фёдор Михайлович', 'кот', 'мужской', 13, 5.5, 'obesity', 5)," +
            " ('Оливка', 'кот', 'женский', 1, 3.5, 'детеныш', 3)," +
            " ('Зоря', 'собака', 'женский', 2, 7.5, 'общее состояние', 4)," +
            " ('Margo', 'кот', 'женский', 12, 3.0, 'хпн', 1)";

    //------------------------------------ Meals------------------------------------------
    // Meals Table Columns names
    String MEALS_COLUMN_ID = "MealId";
    String MEALS_COLUMN_FOOD_ID = "FoodID";
    String MEALS_COLUMN_FOOD_NAME = "FoodName";
    String MEALS_COLUMN_FOOD_STATE = "FoodState";
    String MEALS_COLUMN_QUANTITY = "Quantity";
    String MEALS_FOODS_FOREIGN = "MealsFoodsForeign";
    String MEALS_CONSTRAINT_PRIMARY = "MealsPrimary";

    String CREATE_MEALS_TABLE = "CREATE TABLE " + MEALS_TABLE_NAME + "(" +
            MEALS_COLUMN_ID + " INTEGER, " +
            MEALS_COLUMN_FOOD_ID + " INTEGER, " +
            MEALS_COLUMN_FOOD_NAME + " VARCHAR(255) NOT NULL, " +
            MEALS_COLUMN_FOOD_STATE + " VARCHAR(255) NOT NULL, " +
            MEALS_COLUMN_QUANTITY + " REAL NOT NULL " +
            ", CONSTRAINT " + MEALS_CONSTRAINT_PRIMARY + " PRIMARY KEY (" + MEALS_COLUMN_ID + ") " +
            ", CONSTRAINT " + MEALS_FOODS_FOREIGN + " FOREIGN KEY (" + MEALS_COLUMN_FOOD_ID + ") REFERENCES " + FOODS_TABLE_NAME + " (" + FOODS_COLUMN_ID + ")" +
            ")";

    String INITIAL_MEALS_ENTRIES = "INSERT INTO " + MEALS_TABLE_NAME + " (" +
            MEALS_COLUMN_FOOD_ID + ", " +
            MEALS_COLUMN_FOOD_NAME + ", " +
            MEALS_COLUMN_FOOD_STATE + ", " +
            MEALS_COLUMN_QUANTITY +
            ")   Values " +
            " (4, 'Trainer light', 'сухой', 47)," +
            " (4, 'Trainer light', 'сухой', 50)," +
            " (4, 'Trainer light', 'сухой', 48)," +
            " (4, 'Trainer light', 'сухой', 35)," +
            " (5, 'Trainer fitness senior 12+', 'сухой', 25)," +
            " (5, 'Trainer fitness senior 12+', 'сухой', 54)," +
            " (5, 'Trainer fitness senior 12+', 'сухой', 52)," +
            " (4, 'Trainer light', 'сухой', 60)," +
            " (4, 'Trainer light', 'сухой', 48)," +
            " (4, 'Trainer light', 'сухой', 35)," +
            " (5, 'Trainer fitness senior 12+', 'сухой', 25)," +
            " (4, 'Trainer light', 'сухой', 48)," +
            " (4, 'Trainer light', 'сухой', 35)," +
            " (4, 'Trainer light', 'сухой', 30)," +
            " (5, 'Trainer fitness senior 12+', 'сухой', 45)," +

            " (1, 'Hills k/d', 'сухой', 60)," +
            " (1, 'Hills k/d', 'сухой', 55)," +
            " (1, 'Hills k/d', 'сухой', 65)," +
            " (1, 'Hills k/d', 'сухой', 57)";


    //------------------------------------ Calendar------------------------------------------
    // Calendar Table Columns names
    String CALENDAR_COLUMN_ID = "CalendarId";
    String CALENDAR_COLUMN_DATE = "Date";
    String CALENDAR_COLUMN_MEAL_ID = "MealID";
    String CALENDAR_COLUMN_CAT_ID = "CatID";
    String CALENDAR_MEALS_FOREIGN = "CalendarMealsForeign";
    String CALENDAR_CATS_FOREIGN = "CalendarCatsForeign";
    String CALENDAR_CONSTRAINT_PRIMARY = "CalendarPrimary";

    String CREATE_CALENDAR_TABLE = "CREATE TABLE " + CALENDAR_TABLE_NAME + " (" +
            CALENDAR_COLUMN_ID + " INTEGER, " +
            CALENDAR_COLUMN_DATE + " DATE, " +
            CALENDAR_COLUMN_MEAL_ID + " INTEGER, " +
            CALENDAR_COLUMN_CAT_ID + " INTEGER " +
            ", CONSTRAINT " + CALENDAR_CONSTRAINT_PRIMARY + " PRIMARY KEY (" + CALENDAR_COLUMN_ID + ") " +
            ", CONSTRAINT " + CALENDAR_MEALS_FOREIGN + " FOREIGN KEY (" + CALENDAR_COLUMN_MEAL_ID + ") REFERENCES " + MEALS_TABLE_NAME + " (" + MEALS_COLUMN_ID + ")" +
            ", CONSTRAINT " + CALENDAR_CATS_FOREIGN + " FOREIGN KEY (" + CALENDAR_COLUMN_CAT_ID + ") REFERENCES " + CATS_TABLE_NAME + " (" + CATS_COLUMN_ID + ")" +
            ")";

    String INITIAL_CALENDAR_ENTRIES = "INSERT INTO " + CALENDAR_TABLE_NAME + " (" +
            CALENDAR_COLUMN_DATE + ", " +
            CALENDAR_COLUMN_MEAL_ID + ", " +
            CALENDAR_COLUMN_CAT_ID +
            ")   Values " +
            " ('2016-12-31', 1 , 1)," +
            " ('2017-01-01', 2 , 1)," +
            " ('2017-01-02', 3 , 1)," +
            " ('2017-01-03', 4 , 1)," +
            " ('2017-01-03', 5 , 1)," +
            " ('2017-01-04', 6 , 1)," +
            " ('2017-01-05', 7 , 1)," +
            " ('2017-01-06', 8 , 1)," +
            " ('2017-01-07', 9 , 1)," +
            " ('2017-01-08', 10 , 1)," +
            " ('2017-01-08', 11 , 1)," +
            " ('2017-01-09', 12 , 1)," +
            " ('2017-01-10', 13 , 1)," +
            " ('2017-01-10', 14 , 1)," +
            " ('2017-01-11', 15 , 1)," +

            " ('2017-01-31', 16 , 4)," +
            " ('2017-02-01', 17 , 4)," +
            " ('2017-02-02', 18 , 4)," +
            " ('2017-02-03', 19 , 4)";


    //------------------------------------ Contacts------------------------------------------
    // Meals Table Columns names
    String CONTACTS_COLUMN_ID = "Id";
    String CONTACTS_COLUMN_NAME = "Name";
    String CONTACTS_COLUMN_DESCRIPTION = "Description";
    String CONTACTS_COLUMN_NUMBER = "TelNumber";
    String CONTACTS_CONSTRAINT_UNIQUE = "ContactsUnique";
    String CONTACTS_CONSTRAINT_PRIMARY = "ContactsPrimary";

    String CREATE_CONTACTS_TABLE = "CREATE TABLE " + CONTACTS_TABLE_NAME + "(" +
            CONTACTS_COLUMN_ID + " INTEGER, " +
            CONTACTS_COLUMN_NAME + " VARCHAR(45) NOT NULL, " +
            CONTACTS_COLUMN_DESCRIPTION + " VARCHAR(255), " +
            CONTACTS_COLUMN_NUMBER + " VARCHAR(20) NOT NULL " +
            ", CONSTRAINT " + CONTACTS_CONSTRAINT_PRIMARY + " PRIMARY KEY (" + CONTACTS_COLUMN_ID + ") " +
            ", CONSTRAINT " + CONTACTS_CONSTRAINT_UNIQUE + " UNIQUE (" + CONTACTS_COLUMN_NAME + ", " + CONTACTS_COLUMN_NUMBER + ")" +
            ")";

    String INITIAL_CONTACTS_ENTRIES = "INSERT INTO " + CONTACTS_TABLE_NAME + " (" +
            CONTACTS_COLUMN_NAME + ", " +
            CONTACTS_COLUMN_NUMBER +
            ")   Values " +
            " ('Vet', '+375296850338')," +
            " ('AlfaVet', '+375257521521')," +
            " ('zooexpress.by', '+375259489212')," +
            " ('visheron.by', '+37533370000')," +
            " ('morda.by', '+375257521521')";


//---------------------------------------------REPORTS QUERIES--------------------------------------------------------------------------------

    String ENERGY_VALUE = "EnergyValue";
    String PROTEIN_VALUE = "ProteinValue";
    String FATS_VALUE = "FatsValue";
    String CARBS_VALUE = "CarbsValue";
    String DAY_QUANTITY = "DayQuantity";
    String AVG_DAY_QUANTITY = "AvgDayQuantity";

    // 0) get cat's  selected intake rates
    String GET_RATES_FOR_CAT = "SELECT * FROM " + CATS_TABLE_NAME + " C JOIN " + INTAKERATES_TABLE_NAME + " R ON C." + CATS_COLUMN_IRATE + "=R." + INTAKERATES_COLUMN_ID + " WHERE " + CATS_COLUMN_ID + "=?";

//    1) get meals and its values for certain cat
    String GET_ALL_MEALS_VALUES_FOR_CAT =
            "SELECT " + CALENDAR_COLUMN_DATE + ", M." + MEALS_COLUMN_FOOD_NAME + ", M." + MEALS_COLUMN_FOOD_STATE + ", " + MEALS_COLUMN_QUANTITY + ", " +
                    FOODS_COLUMN_ENERGY + "*" + MEALS_COLUMN_QUANTITY + "/100 [" + ENERGY_VALUE + "] , " +
                    FOODS_COLUMN_PROTEINS + "*" + MEALS_COLUMN_QUANTITY + "/100 [" + PROTEIN_VALUE + "] , " +
                    FOODS_COLUMN_FATS + "*" + MEALS_COLUMN_QUANTITY + "/100 [" + FATS_VALUE + "] , " +
                    FOODS_COLUMN_CARBS + "*" + MEALS_COLUMN_QUANTITY + "/100 [" + CARBS_VALUE + "] FROM " +
                    CATS_TABLE_NAME + " C JOIN " + CALENDAR_TABLE_NAME + " L ON C." + CATS_COLUMN_ID + "=L." + CALENDAR_COLUMN_CAT_ID +
                    " JOIN " + MEALS_TABLE_NAME + " M ON L." + CALENDAR_COLUMN_MEAL_ID + "=M." + MEALS_COLUMN_ID +
                    " JOIN " + FOODS_TABLE_NAME + " F ON F." + FOODS_COLUMN_ID + "=M." + MEALS_COLUMN_FOOD_ID +
                    " WHERE L." + CALENDAR_COLUMN_CAT_ID + "=? ORDER BY " + CALENDAR_COLUMN_DATE;


    //2) get meals' nutrition values for certain cat summed by date
//    SELECT  Date, SUM(F.Protein*Quantity/100) [Total Proteins a Day], R.Protein [Intake_Rate Reference]
//    FROM Users U JOIN Intake_Rate R ON U.Id_R=R.Id_R
//    JOIN Calendar C ON U.Id_U=C.Id_U
//    JOIN Meals M ON C.Id_M=M.Id_M
//    JOIN Foods F ON F.Id_F=M.Id_F
//    WHERE User_Name='Jane Doe'
//    GROUP BY  Date , R.Protein
    String GET_ALL_MEALS_SUM_VALUES_BYDATE_RATES_FOR_CAT =
            "SELECT " + CALENDAR_COLUMN_DATE +
                    ", SUM(" + MEALS_COLUMN_QUANTITY + ") AS " + DAY_QUANTITY +
                    ", SUM(F." + FOODS_COLUMN_ENERGY + "*" + MEALS_COLUMN_QUANTITY + "/100) [" + ENERGY_VALUE + "] " +
                    ", SUM(F." + FOODS_COLUMN_PROTEINS + "*" + MEALS_COLUMN_QUANTITY + "/100) [" + PROTEIN_VALUE + "] " +
                    ", SUM(F." + FOODS_COLUMN_FATS + "*" + MEALS_COLUMN_QUANTITY + "/100) [" + FATS_VALUE + "] " +
                    ", SUM(F." + FOODS_COLUMN_CARBS + "*" + MEALS_COLUMN_QUANTITY + "/100) [" + CARBS_VALUE + "] " +
                    " FROM " + CATS_TABLE_NAME + " C JOIN " + CALENDAR_TABLE_NAME + " L ON C." + CATS_COLUMN_ID + "=L." + CALENDAR_COLUMN_CAT_ID +
                    " JOIN " + MEALS_TABLE_NAME + " M ON L." + CALENDAR_COLUMN_MEAL_ID + "=M." + MEALS_COLUMN_ID +
                    " JOIN " + FOODS_TABLE_NAME + " F ON F." + FOODS_COLUMN_ID + "=M." + MEALS_COLUMN_FOOD_ID +
                    " WHERE L." + CALENDAR_COLUMN_CAT_ID + "=?" +
                    " GROUP BY " + CALENDAR_COLUMN_DATE;


    //  4) get daily average food quantity consumed by cat
    String GET_DAILY_AVG_FOOD_QUANTITY_FOR_CAT =
            "SELECT AVG(S." + DAY_QUANTITY + ") AS " + AVG_DAY_QUANTITY +
                    " FROM (SELECT " + CALENDAR_COLUMN_DATE + ", SUM(" + MEALS_COLUMN_QUANTITY + ") AS " + DAY_QUANTITY +
                    " FROM " + MEALS_TABLE_NAME + " M, " + CALENDAR_TABLE_NAME + " L, " + CATS_TABLE_NAME + " C " +
                    " WHERE L." + CALENDAR_COLUMN_CAT_ID + "=? AND C." + CATS_COLUMN_ID + "=L." + CALENDAR_COLUMN_CAT_ID + " AND L." + CALENDAR_COLUMN_MEAL_ID + "=M." + MEALS_COLUMN_ID +
                    " GROUP BY " + CALENDAR_COLUMN_DATE + ") S";


    //3) get food quantity consumed by cat summed by date
    String GET_FOOD_QUANTITY_BYDATE_FOR_CAT =
            "SELECT " + CALENDAR_COLUMN_DATE +
                    ", SUM(" + MEALS_COLUMN_QUANTITY + ") AS " + DAY_QUANTITY +
                    " FROM " + MEALS_TABLE_NAME + " M, " + CALENDAR_TABLE_NAME + " L, " + CATS_TABLE_NAME + " C " +
                    " WHERE L." + CALENDAR_COLUMN_CAT_ID + "=?  AND C." + CATS_COLUMN_ID + "=L." + CALENDAR_COLUMN_CAT_ID + " AND L." + CALENDAR_COLUMN_MEAL_ID + "=M." + MEALS_COLUMN_ID +
                    " GROUP BY " + CALENDAR_COLUMN_DATE;


}


