import org.apache.pdfbox.cos.COSDocument;
import org.apache.pdfbox.pdfparser.PDFParser;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.PrintWriter;

public class FileConvert {
    
    // FileConvert Sınıfı PDF Dosyayı Text'e Çevirmek İçin Yazılmıştır.
    
    private String path;
    private String newFilePath;
    private String parsedText = null;
    PDFParser pdfParser;


    public FileConvert(){
        this.path = "C:\\Users\\aydn2\\OneDrive\\Masaüstü\\allcountry.pdf";
        this.newFilePath = "C:/Users/aydn2/OneDrive/Masaüstü/pdf.txt";
    }

    public String convertPDFToText(){
        try {
            File f = new File(this.getPath());
            pdfParser = new PDFParser(new org.apache.pdfbox.io.RandomAccessFile(f,"r"));
            pdfParser.parse();
            COSDocument cosDoc = pdfParser.getDocument();
            PDFTextStripper pdfTextStripper = new PDFTextStripper();
            PDDocument pdDoc = new PDDocument(cosDoc);
            parsedText = pdfTextStripper.getText(pdDoc);
            PrintWriter pw = new PrintWriter(newFilePath);
            parsedText.trim();
            pw.println(parsedText.trim());
            pw.close();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        return this.getParsedText();
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
