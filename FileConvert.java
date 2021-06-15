import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.io.RandomAccessFile;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.PrintWriter;

public class FileConvert {

    private String path;
    private String newFilePath;
    private String parsedText = null;
    PDFParser pdfParser;


    public FileConvert(){
        this.path = "C:\\Users\\aydn2\\OneDrive\\Masaüstü\\allcountry.pdf";
        this.newFilePath = "C:/Users/aydn2/OneDrive/Masaüstü/pdf.txt";
    }

    public void convertPDFToText(){
        try {
            // PDF Yükleme
            File f = new File(this.getPath());
            pdfParser = new PDFParser(new RandomAccessFile(f,"r"));
            pdfParser.parse();

            // PDF Dosyasından Veriyi Alma
            COSDocument cosDocument = pdfParser.getDocument();
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            // PDFTextStripper -> Ham metni almak
            PDDocument pdDocument = new PDDocument(cosDocument);
            parsedText = pdfTextStripper.getText(pdDocument);

            // Veriyi aldıktan sonra text'e yazdırma,düzenleme ve bağlantı kapatma
            PrintWriter pw = new PrintWriter(newFilePath);
            parsedText.trim();
            pw.println(parsedText.trim());
            pw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getParsedText() {
        return parsedText;
    }

    public void setParsedText(String parsedText) {
        this.parsedText = parsedText;
    }

    public String getNewFilePath() {
        return newFilePath;
    }

    public void setNewFilePath(String newFilePath) {
        this.newFilePath = newFilePath;
    }
}
