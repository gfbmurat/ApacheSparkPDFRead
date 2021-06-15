import org.apache.log4j.Logger;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;
import org.apache.log4j.Level;


public class Main {

    // Spark ile Text Dosyası Okunarak Analiz İşlemleri Yapıldı.
    
    public static void main(String[] args) {

        /* author : Murat Aydın Date : 14.06.2021 */
        Logger.getLogger("org").setLevel(Level.OFF);

        SparkSession sparkSession = SparkSession.builder().master("local").appName("SparkSessionApp").getOrCreate();

        // Transform
        FileConvert fc = new FileConvert();
        fc.convertPDFToText(); // PDF -> Text Dönüştürme

        // Actions
        Dataset<String> data = sparkSession.read().textFile(fc.getNewFilePath());
        long count = data.count();
        System.out.println("Ülke Adedi: " +  count);
        data.show(10,false);

        Dataset<Row> countries = data.withColumn("countries", data.col("value"));
        Dataset<Row> countriesData = countries.select("countries");
        countriesData.show(10, false);


    }
}
