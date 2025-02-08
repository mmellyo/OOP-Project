import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.print.*;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class MedicalCertificateGenerator implements Printable {

    private final String patientName;
    private final String birthDate;
    private final String diagnosis;
    private final String doctorName;
    private final String clinicName;
    private final int treatmentDuration;
    private BufferedImage signatureImage; // For the signature image

    public MedicalCertificateGenerator(String patientName, String birthDate, String diagnosis, String doctorName, String clinicName, int treatmentDuration) {
        this.patientName = patientName;
        this.birthDate = birthDate;
        this.diagnosis = diagnosis;
        this.doctorName = doctorName;
        this.clinicName = clinicName;
        this.treatmentDuration = treatmentDuration;

        // Load the signature image
        try {
            signatureImage = ImageIO.read(new File("Signature.png"));
        } catch (IOException e) {
            System.err.println("Error loading signature image: " + e.getMessage());
        }
    }

    @Override
    public int print(Graphics g, PageFormat pf, int pageIndex) {
        if (pageIndex > 0) {
            return NO_SUCH_PAGE;
        }

        Graphics2D g2d = (Graphics2D) g;
        g2d.translate(pf.getImageableX(), pf.getImageableY());

        g.setFont(new Font("Arial", Font.BOLD, 18));
        g.drawString("Medical Certificate", 200, 50);

        g.setFont(new Font("Arial", Font.PLAIN, 12));
        g.drawString("Doctor: " + doctorName, 50, 100);
        g.drawString("Clinic: " + clinicName, 50, 120);
        g.drawString("Date: " + new java.text.SimpleDateFormat("yyyy-MM-dd").format(new java.util.Date()), 50, 140);
        g.drawString("Patient Name: " + patientName, 50, 180);
        g.drawString("Date of Birth: " + birthDate, 50, 200);
        g.drawString("Medical Condition: " + diagnosis, 50, 220);
        g.drawString("Treatment Duration: " + treatmentDuration + " days", 50, 240);

        g.drawString("Doctor's Signature:", 50, 300);

        // Draw signature image if loaded successfully
        if (signatureImage != null) {
            g2d.drawImage(signatureImage, 180, 280, 100, 50, null); // Adjust size and position
        } else {
            g.drawString("_____________", 180, 300);
        }

        return PAGE_EXISTS;
    }

    public static void generatePDF(String patientName, String birthDate, String diagnosis, String doctorName, String clinicName, int treatmentDuration) {
        PrinterJob job = PrinterJob.getPrinterJob();
        job.setPrintable(new MedicalCertificateGenerator(patientName, birthDate, diagnosis, doctorName, clinicName, treatmentDuration));

        if (job.printDialog()) {
            try {
                job.print();
            } catch (PrinterException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        generatePDF("John Doe", "1990-05-14", "Flu and Fever", "Dr. Smith", "City Hospital", 7);
    }
}
